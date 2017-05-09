import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Collections {

    public static <T, R> List<R> map(final Function1<? super T, R> func,
                                     final Iterable<? extends T> items) {
        List<R> res = new ArrayList<>();
        for (T currentElement : items) {
            res.add(func.apply(currentElement));
        }
        return res;
    }

    public static <T> List<T> filter(final Predicate<? super T> predicate,
                                     final Iterable<? extends T> items) {
        List<T> res = new ArrayList<>();
        for (T currElement : items) {
            if (predicate.apply(currElement)) {
                res.add(currElement);
            }
        }
        return res;
    }

    public static <T> List<T> takeWhile(final Predicate<? super T> predicate,
                                        final Iterable<? extends T> items) {
        List<T> res = new ArrayList<>();
        for (T currElement : items) {
            if (predicate.apply(currElement)) {
                res.add(currElement);
            } else {
                break;
            }
        }
        return res;
    }

    public static <T> List<T> takeUnless(final Predicate<? super T> predicate,
                                         final Iterable<? extends T> items) {
        return takeWhile(predicate.not(), items);
    }

    public static <T, R> R foldr(final Function2<? super T, ? super R, ? extends R> func,
                                 final R ini,
                                 final Iterable<T> items) {
        Iterator<T> iterator = items.iterator();
        if (!iterator.hasNext()) {
            return ini;
        }
        T nextElement = iterator.next();
        Iterable<T> aNext = () -> iterator;
        return func.apply(nextElement, foldr(func, ini, aNext));
    }

    public static <T, R> R foldl(final Function2<? super R, ? super T, ? extends R> func,
                                 final R ini,
                                 final Iterable<T> items) {
        Iterator<T> iterator = items.iterator();
        if (!iterator.hasNext()) return ini;
        T nextElement = iterator.next();

        Iterable<T> aNext = () -> iterator;
        return foldl(func, func.apply(ini, nextElement), aNext);
    }
}
