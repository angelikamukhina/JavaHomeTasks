import org.junit.Test;

import static org.junit.Assert.*;
public class Function1Test {
    @Test
    public void apply() throws Exception {
        class Square extends Function1<Integer, Integer> {
            @Override
            public Integer apply(Integer n1)
            {
                return n1*n1;
            }
        }

        Square sq = new Square();
        assertEquals(64, sq.apply(8).intValue());
        }


    @Test
    public void compose() throws Exception {
        class MultiplyByTwo extends Function1<Integer, Integer>{
            @Override
            public Integer apply(Integer n) {
                return 2*n;
            }
        }

        class Sum extends Function2<Integer, Integer, Integer>{
            @Override
            public Integer apply(Integer n1, Integer n2)
            {
                return n1+n2;
            }
        }
        Sum sum = new Sum();
        MultiplyByTwo mbt = new MultiplyByTwo();
        assertEquals(14, sum.compose(mbt).apply(3, 4).intValue());

    }
    }