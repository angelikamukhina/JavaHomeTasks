import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PredicateTest {

    @Test
    public void apply() throws Exception {
        class IsLessThanTwo extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return n < 2;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        assertTrue(lessTwo.apply(1));
        assertFalse(lessTwo.apply(2));
        assertTrue(Predicate.ALWAYS_TRUE.apply(42));
        assertFalse(Predicate.ALWAYS_FALSE.apply(42));
    }

    @Test
    public void or() throws Exception {
        class IsLessThanTwo extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return n < 2;
            }
        }

        class IsLessThanFive extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return n < 5;
            }
        }

        class IsDivisorOf42 extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return 42 % n == 0;
            }
        }

        class NumTrue extends Predicate<Number> {
            @Override
            public Boolean apply(Number n) {
                return true;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        IsLessThanFive lessFive = new IsLessThanFive();
        IsDivisorOf42 divOf42 = new IsDivisorOf42();
        NumTrue numTrue = new NumTrue();
        assertTrue(lessTwo.or(lessFive).apply(1));
        assertTrue(lessTwo.or(lessFive).apply(3));
        assertFalse(lessTwo.or(lessFive).apply(5));
        assertTrue(lessTwo.or(divOf42).apply(0)); //laziness of or operation
        assertTrue(lessTwo.or(numTrue).apply(1));
    }

    @Test
    public void and() throws Exception {
        class IsLessThanTwo extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return n < 2;
            }
        }

        class IsLessThanFive extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return n < 5;
            }
        }

        class IsDivisorOf42 extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return 42 % n == 0;
            }
        }

        class NumTrue extends Predicate<Number> {
            @Override
            public Boolean apply(Number n) {
                return true;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        IsLessThanFive lessFive = new IsLessThanFive();
        IsDivisorOf42 divOf42 = new IsDivisorOf42();
        NumTrue numTrue = new NumTrue();
        assertTrue(lessTwo.and(lessFive).apply(1));
        assertFalse(lessTwo.and(lessFive).apply(3));
        assertFalse(lessTwo.and(lessFive).apply(5));
        assertFalse(lessTwo.and(lessFive).apply(3));
        assertFalse(lessTwo.not().and(divOf42).apply(0)); //laziness of and operation
        assertTrue(lessTwo.and(numTrue).apply(1));
    }

    @Test
    public void not() throws Exception {
        class IsLessThanTwo extends Predicate<Integer> {
            @Override
            public Boolean apply(Integer n) {
                return n < 2;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        assertTrue(lessTwo.not().apply(4));
        assertFalse(lessTwo.not().apply(1));
    }
}