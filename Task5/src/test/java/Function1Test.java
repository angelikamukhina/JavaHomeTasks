import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Function1Test {

    @Test
    public void apply() throws Exception {
        class Square extends Function1<Integer, Integer> {
            @Override
            public Integer apply(Integer n1) {
                return n1 * n1;
            }
        }

        Square sq = new Square();
        assertEquals(64, sq.apply(8).intValue());
    }

    @Test
    public void compose() throws Exception {
        class Identity extends Function1<Number, Number> {
            @Override
            public Number apply(Number n) {
                return n;
            }
        }

        class Square extends Function1<Integer, Number> {
            @Override
            public Number apply(Integer n) {
                return n * n;
            }
        }
        Square sq = new Square();
        Identity id = new Identity();
        assertEquals(9, sq.compose(id).apply(3));
    }
}