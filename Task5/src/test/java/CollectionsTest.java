import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class CollectionsTest {
    @Test
    public void map() throws Exception {
        ArrayList<Integer> intslist = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++){
            intslist.add(i);
        }

        Function1<Integer, Integer> f = new Function1<Integer, Integer>(){
            @Override
            public Integer apply(Integer n){
                return n*n;
            }
        };

        ArrayList<Integer> res = Collections.map(f, intslist);

        for(int i = 0; i < 10; i++){
            assertEquals(i*i, res.get(i).intValue());
        }

    }

    @Test
    public void filter() throws Exception {
        Predicate<Integer> p = new Predicate<Integer>(){
            @Override
            public Boolean apply(Integer n){
                if(n.compareTo(3) < 0) return true;
                else return false;
            }
        };

        ArrayList<Integer> intsList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            intsList.add(i);
        }

        ArrayList<Integer> res = Collections.filter(p, intsList);
        for(Integer elem : res){
            assertTrue(elem.intValue() < 3);
        }
    }

    @Test
    public void takeWhile() throws Exception {
        Predicate<Integer> p = new Predicate<Integer>(){
            @Override
            public Boolean apply(Integer n){
                if(n.compareTo(3) < 0) return true;
                else return false;
            }
        };

        ArrayList<Integer> intsList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            intsList.add(i);
        }

        ArrayList<Integer> res = Collections.takeWhile(p, intsList);
        for(Integer elem : res){
            assertTrue(elem.intValue() < 3);
        }

        assertEquals(3, res.size());
    }

    @Test
    public void takeUnless() throws Exception {
        Predicate<Integer> p = new Predicate<Integer>(){
            @Override
            public Boolean apply(Integer n){
                if(n.compareTo(3) >= 0) return true;
                else return false;
            }
        };

        ArrayList<Integer> intsList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            intsList.add(i);
        }

        ArrayList<Integer> res = Collections.takeUnless(p, intsList);
        for(Integer elem : res){
            assertTrue(elem.intValue() < 3);
        }

        assertEquals(3, res.size());
    }

    @Test
    public void foldr() throws Exception {
        ArrayList<Integer> intsList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            intsList.add(i);
        }

        Function2<Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer arg1, Integer arg2) {
                return arg1+arg2;
            }
        };
        assertEquals(10, Collections.foldr(f, new Integer(0), intsList).intValue());
    }

    @Test
    public void foldl() throws Exception {
        ArrayList<Integer> intsList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            intsList.add(i);
        }

        Function2<Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer arg1, Integer arg2) {
                return arg1+arg2;
            }
        };
        assertEquals(10, Collections.foldl(f, new Integer(0), intsList).intValue());
    }

}