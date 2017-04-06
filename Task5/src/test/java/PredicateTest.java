import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    @Test
    public void apply() throws Exception {
        class IsLessThanTwo extends Predicate<Integer>{
            @Override
            public Boolean apply(Integer n){
                if(n >= 2) return false;
                else return true;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        assertTrue(lessTwo.apply(1));
        assertFalse(lessTwo.apply(2));
    }

    @Test
    public void or() throws Exception {
        class IsLessThanTwo extends Predicate<Integer>{
            @Override
            public Boolean apply(Integer n){
                if(n >= 2) return false;
                else return true;
            }
        }

        class IsLessThanFive extends Predicate<Integer>{
            @Override
            public Boolean apply(Integer n){
                if(n >= 5) return false;
                else return true;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        IsLessThanFive lessFive = new IsLessThanFive();
        assertTrue(lessTwo.or(lessFive).apply(1));
        assertTrue(lessTwo.or(lessFive).apply(3));
        assertFalse(lessTwo.or(lessFive).apply(5));
    }

    @Test
    public void and() throws Exception {
        class IsLessThanTwo extends Predicate<Integer>{
            @Override
            public Boolean apply(Integer n){
                if(n >= 2) return false;
                else return true;
            }
        }

        class IsLessThanFive extends Predicate<Integer>{
            @Override
            public Boolean apply(Integer n){
                if(n >= 5) return false;
                else return true;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        IsLessThanFive lessFive = new IsLessThanFive();
        assertTrue(lessTwo.and(lessFive).apply(1));
        assertFalse(lessTwo.and(lessFive).apply(3));
        assertFalse(lessTwo.or(lessFive).apply(5));
    }

    @Test
    public void not() throws Exception {
        class IsLessThanTwo extends Predicate<Integer>{
            @Override
            public Boolean apply(Integer n){
                if(n >= 2) return false;
                else return true;
            }
        }

        IsLessThanTwo lessTwo = new IsLessThanTwo();
        assertTrue(lessTwo.not().apply(4));
        assertFalse(lessTwo.not().apply(1));
    }

}