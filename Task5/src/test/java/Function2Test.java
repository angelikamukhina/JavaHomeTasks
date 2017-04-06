import org.junit.Test;

import static org.junit.Assert.*;

public class Function2Test {

    @Test
    public void bind1() throws Exception {
        class Sum extends Function2<Integer, Integer, Integer> {
            @Override
            public Integer apply(Integer n1, Integer n2) {
                return n1 + n2;
            }
        }
        Sum sum = new Sum();
        assertEquals(5, sum.bind1(2).apply(3).intValue());
    }

    @Test
    public void bind2() throws Exception {
        class Div extends Function2<Integer, Integer, Integer> {
            @Override
            public Integer apply(Integer n1, Integer n2) {
                return n1 / n2;
            }
        }

        Div div = new Div();
        assertEquals(7, div.bind2(8).apply(56).intValue());
    }

    @Test
    public void apply() throws Exception {
        class Sum extends Function2<Integer, Integer, Integer> {
            @Override
            public Integer apply(Integer n1, Integer n2) {
                return n1 + n2;
            }
        }

        Sum sum = new Sum();
        assertEquals(7, sum.apply(3, 4).intValue());
    }

    @Test
    public void compose() throws Exception {
        class Sum extends Function2<Integer, Integer, Integer> {
            @Override
            public Integer apply(Integer n1, Integer n2) {
                return n1 + n2;
            }
        }

        class Square extends Function1<Integer, Integer> {
            @Override
            public Integer apply(Integer n) {
                return n * n;
            }
        }

        Sum sum1 = new Sum();
        Square sq = new Square();

        assertEquals(49, sum1.compose(sq).apply(3, 4).intValue());
    }

    @Test
    public void curry() throws Exception {
        class Sum extends Function2<Integer, Integer, Integer> {
            @Override
            public Integer apply(Integer n1, Integer n2) {
                return n1 + n2;
            }
        }

        Sum sum = new Sum();
        assertEquals(10, sum.curry().apply(4).apply(6).intValue());
    }
}