package ru.spbau.mit;

/** Implementor – объект, позволяющий сгенерировать файл с java классом,
 *  который реализует данный интерфейс или абстрактный класс.
 *  Под реализацией подразумевается следующее:
 *   1) Это файл с исходным кодом сгенерированного класса.
 *   2) Исходный код успешно компилируется.
 *   3) Класс в файле реализует данный интерфейс/является наследником данного класса.
 *   4) Класс в файле не является абстрактным.
 *
 * Ограничения на входной интерфейс/абстрактный класс.
 *   1) Интерфейс/Абстрактный класс всегда публичен.
 *   2) Интерфейс/Абстрактный класс может находится в произвольном пакете,
 *      например ru.spbau.mit.java2017.implementor.InputInterface.
 *   3) Интерфейс/Абстрактный класс может находится в стандартной библиотеке.
 *   4) Интерфейс/Абстрактный класс может быть наследником класса/быть реализацией
 *      интерфейсов из стандартной библиотеки.
 *   5) Внутри интерфейса/абстрактного класса нет полей/методов с default видимостью (без модификатора видимости).
 *   6) Не требуется поддерживать нововведения Java 8.
 *   7) Не требуется поддерживать модификаторы, отличные от модификаторов
 *      доступа, static, final (например, synchronized)
 *   8) Не требуется поддерживать generics. Достаточно работать с ними так, как будто вы не указывали тип,
 *      например, нужно реализовать не Comparable<T>, а просто Comparable.
 *   9) Не требуется поддерживать аннотации.
 *
 *   Реализация Implementor'а должна содержать конструктор от строки `outputDirectory`
 *   `outputDirectory` – путь к папке, в которую будут записываться сгенерированные исходники.
 */
public interface Implementor {

    /**
     * Имплементор по данной папке с class файлами java ищет в ней java класс, которые требуется реализовать.
     * Класс записывает реализацию в папку `outputDirectory` (учитывая пакеты)
     * и возвращает полное название нового класса.
     * Реализация должна находится в том же пакете, что и исходный класс/интерфейс.
     *
     * Например, требуется реализовать интерфейс `ru.spbau.mit.AnInterface`.
     * Тогда в папке ожидается файл ru/spbau/mit/AnInterface.class.
     * Implementor генерирует реализацию этого интерфейса, кладет её в
     * `workingDirectory`/ru/spbau/mit/AnInterfaceImpl.java
     * и возвращает полное имя сгенерированного класса ru.spbau.mit.AnInterfaceImpl.
     *
     *   @param directoryPath путь до директории, которая содержит данный класс/интерфейс
     *   @param className полное название класса/интерфейса, которое требуется реализовать
     *   @throws ImplementorException в тех ситуациях когда
     *   1) Невозможно создать наследника класса.
     *   2) Входной класс не найден.
     *   3) Невозможно записать сгенерированный класс.
     *
     */
    String implementFromDirectory(final String directoryPath, final String className) throws ImplementorException;


    /**
     * Имплементор ищет java класс/интерфейс из стандартной библеотеки, которые требуется реализовать.
     * Класс записывает реализацию в папку `outputDirectory`.
     * Реализация должна находится в default пакете.
     *
     * Например, требуется реализовать интерфейс `java.lang.Comparable`
     * Имплементор генерирует реализацию этого интерфейса, кладет её в `workingDirectory`/ComparableImpl.java и
     * возвращает полное имя сгенерированного класса ComparableImpl.
     *
     * @param className полное название класса/интерфейса, которое требуется реализовать
     * @return полное название нового класса, например ComparableImpl
     * @throws ImplementorException в тех ситуациях когда
     *   1) Невозможно создать наследника класса.
     *   2) Входной класс не найден.
     *   3) Невозможно записать сгенерированный класс.
     */
    String implementFromStandardLibrary(final String className) throws ImplementorException;
}
