import java.util.ArrayList;
import java.util.Iterator;

public abstract class Collections {

    public static <T, R> ArrayList<R> map(Function1<? super T, R> f, Iterable<T> a) {
        ArrayList<R> res = new ArrayList<>();
        for (T currentElement : a) {
            res.add(f.apply(currentElement));
        }
        return res;
    }

    public static <T> ArrayList<T> filter(Predicate<T> p, Iterable<T> a) {
        ArrayList<T> res = new ArrayList<T>();
        for (T currElement : a) {
            if (p.apply(currElement)) {
                res.add(currElement);
            }
        }
        return res;
    }

    public static <T> ArrayList<T> takeWhile(Predicate<T> p, Iterable<T> a) {
        ArrayList<T> res = new ArrayList<T>();
        for (T currElement : a) {
            if (p.apply(currElement)) {
                res.add(currElement);
            } else {
                break;
            }
        }
        return res;
    }

    public static <T> ArrayList<T> takeUnless(Predicate<T> p, Iterable<T> a) {
        ArrayList<T> res = new ArrayList<>();
        for (T currElement : a) {
            if (!p.apply(currElement)) {
                res.add(currElement);
            } else {
                break;
            }
        }
        return res;
    }

    public static <T, R> R foldr(Function2<T, R, R> f, R ini, Iterable<T> a) {
        Iterator<T> iterator = a.iterator();
        if (!iterator.hasNext()) {
            return ini;
        }
        T nextElement = iterator.next();
        Iterable<T> aNext = () -> iterator;
        return f.apply(nextElement, foldr(f, ini, aNext));
    }

    public static <T, R> R foldl(Function2<R, T, R> f, R ini, Iterable<T> a) {
        Iterator<T> iterator = a.iterator();
        if (!iterator.hasNext()) return ini;
        T nextElement = iterator.next();

        Iterable<T> aNext = () -> iterator;
        return foldl(f, f.apply(ini, nextElement), aNext);
    }

}
