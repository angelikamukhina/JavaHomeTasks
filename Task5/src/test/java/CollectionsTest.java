import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectionsTest {

    @Test
    public void map() throws Exception {
        ArrayList<Integer> intlist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intlist.add(i);
        }

        Function1<Number, Integer> intSquare = new Function1<Number, Integer>() {
            @Override
            public Integer apply(Number n) {
                return n.intValue() * n.intValue();
            }
        };

        List<Integer> res = Collections.map(intSquare, intlist);

        for (int i = 0; i < 10; i++) {
            assertEquals(i * i, res.get(i).intValue());
        }
    }

    @Test
    public void filter() throws Exception {
        Predicate<Integer> p = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer n) {
                return n.compareTo(3) < 0;
            }
        };
        ArrayList<Integer> intsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intsList.add(i);
        }

        List<Integer> res = Collections.filter(p, intsList);
        for (Integer elem : res) {
            assertTrue(elem < 3);
        }
    }

    @Test
    public void takeWhile() throws Exception {
        Predicate<Number> p = new Predicate<Number>() {
            @Override
            public Boolean apply(Number n) {
                return n.intValue() < 3;
            }
        };

        ArrayList<Integer> intsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intsList.add(i);
        }

        List<Integer> res = Collections.takeWhile(p, intsList);
        for (Integer elem : res) {
            assertTrue(elem < 3);
        }
        assertEquals(3, res.size());
    }

    @Test
    public void takeUnless() throws Exception {
        Predicate<Number> p = new Predicate<Number>() {
            @Override
            public Boolean apply(Number n) {
                return n.intValue() >= 3;
            }
        };
        ArrayList<Integer> intsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intsList.add(i);
        }

        List<Integer> res = Collections.takeUnless(p, intsList);
        for (Integer elem : res) {
            assertTrue(elem < 3);
        }

        assertEquals(3, res.size());
    }

    @Test
    public void foldr() throws Exception {
        ArrayList<Integer> intsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            intsList.add(i);
        }

        Function2<Number, Object, Integer> f = new Function2<Number, Object, Integer>() {
            @Override
            public Integer apply(Number arg1, Object arg2) {
                return arg1.intValue() + (Integer) arg2;
            }
        };
        assertEquals(10, Collections.foldr(f, 0, intsList).intValue());
    }

    @Test
    public void foldl() throws Exception {
        ArrayList<Integer> intsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            intsList.add(i);
        }

        Function2<Number, Object, Integer> f = new Function2<Number, Object, Integer>() {
            @Override
            public Integer apply(Number arg1, Object arg2) {
                return arg1.intValue() + (Integer) arg2;
            }
        };
        assertEquals(10, Collections.foldl(f, 0, intsList).intValue());
    }
}