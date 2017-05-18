package ru.spbau.mit;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleImplementor implements Implementor {
    private final String outputDirectory;

    SimpleImplementor(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    public String implementFromDirectory(String directoryPath, String className) throws ImplementorException {
        Class<?> clazz = loadClassFromDirectory(directoryPath, className);
        StringBuilder impl = new StringBuilder();
        addPackage(impl, clazz.getPackage());
        addClassImplementation(impl, clazz);
        writeToFile(impl, clazz.getCanonicalName());
        return clazz.getCanonicalName() + "Impl";
    }

    @Override
    public String implementFromStandardLibrary(String className) throws ImplementorException {
        Class<?> clazz = loadClassFromStandardLibrary(className);
        StringBuilder impl = new StringBuilder();
        addClassImplementation(impl, clazz);
        writeToFile(impl, clazz.getSimpleName());
        return clazz.getSimpleName() + "Impl";
    }


    private void addClassImplementation(StringBuilder impl, Class<?> clazz) throws ImplementorException {
        addModifiers(impl, clazz.getModifiers());
        impl.append(" class ");
        addNameOfClass(impl, clazz.getSimpleName());
        impl.append(" ");
        addExtendedClassOrImplementedInterface(impl, clazz);
        impl.append(" {\n");
        addAbstractMethods(impl, clazz);
        impl.append("\n}");
    }

    private Class<?> loadClassFromDirectory(String directoryPath, String className) throws ImplementorException {
        try {
            File directoryFile = new File(directoryPath);
            URL[] classLoaderUrls = new URL[]{directoryFile.toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
            return urlClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new ImplementorException("There is no such interface or abstract class in " + directoryPath);
        } catch (MalformedURLException e) {
            throw new ImplementorException("Directory path has malformed URL");
        }
    }

    private Class<?> loadClassFromStandardLibrary(String className) throws ImplementorException {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ImplementorException("There is no such class in standard library");
        }
    }

    private void addPackage(StringBuilder impl, Package classPackage) {
        impl.append(classPackage.toString()).append(";\n");
    }

    private void addModifiers(StringBuilder impl, int modifiers) {
        impl.append(Modifier.toString(modifiers & ~Modifier.ABSTRACT & ~Modifier.INTERFACE));
    }

    private void addNameOfClass(StringBuilder impl, String simpleName) {
        impl.append(simpleName).append("Impl");
    }

    private void addExtendedClassOrImplementedInterface(StringBuilder impl, final Class<?> clazz) {
        if (clazz.isInterface()) {
            impl.append("implements ");
        } else {
            impl.append("extends ");
        }
        impl.append(clazz.getCanonicalName());
    }

    private void addAbstractMethods(StringBuilder impl, Class<?> clazz) {
        Set<String> methodsImpls = new HashSet<>();
        Class<?>[] interfaces = clazz.getInterfaces();
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            Method[] methods = superclass.getDeclaredMethods();
            addMethodImplToSet(methodsImpls, methods);
        }
        for (Class<?> inter : interfaces) {
            Method[] methods = inter.getDeclaredMethods();
            addMethodImplToSet(methodsImpls, methods);
        }
        addMethodImplToSet(methodsImpls, clazz.getDeclaredMethods());
        for (String methodImpl : methodsImpls) {
            impl.append(methodImpl);
        }
    }

    private void addMethodImplToSet(Set<String> methodsImpls, Method[] methods) {
        for (Method method : methods) {
            String methodImpl = getMethodImplementation(method);
            if (Modifier.isAbstract(method.getModifiers())) {
                if (!methodsImpls.contains(methodImpl)) {
                    methodsImpls.add(methodImpl);
                }
            }
        }
    }

    private String getMethodImplementation(Method method) {
        StringBuilder impl = new StringBuilder();
        impl.append(Modifier.toString(method.getModifiers() & ~Modifier.ABSTRACT)).append(" ");
        Class<?> retType = method.getReturnType();
        impl.append(retType.getCanonicalName()).append(" ");
        impl.append(method.getName()).append(" (");
        Parameter[] params = method.getParameters();
        final String paramString = Arrays.stream(params)
                .map(p -> p.getType().getCanonicalName() + " " + p.getName())
                .collect(Collectors.joining(", "));
        impl.append(paramString).append(") {");
        if (!retType.getSimpleName().equals("void")) {
            impl.append("\n\treturn ");
            if (retType.getSimpleName().equals("boolean")) {
                impl.append(false);
            } else if (retType.isPrimitive()) {
                impl.append(0);
            } else {
                impl.append("null");
            }
            impl.append(";\n");
        }
        impl.append("}\n");
        return impl.toString();
    }

    private void writeToFile(StringBuilder impl, String className) throws ImplementorException {
        final String packageDirs = className.replace('.', '/');
        File outputFile = new File(outputDirectory + "/" + packageDirs + "Impl.java");
        final boolean success = outputFile.getParentFile().mkdirs();
        if (!success && packageDirs.contains("/")) {
            throw new ImplementorException("Can't create package directories");
        }
        try {
            DataOutputStream foutStream = new DataOutputStream(new FileOutputStream(outputFile));
            foutStream.writeBytes(impl.toString());
        } catch (IOException e) {
            throw new ImplementorException("Can't write implemented class");
        }
    }
}
