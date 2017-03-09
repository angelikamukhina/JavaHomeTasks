import org.junit.Test;

import static org.junit.Assert.*;

public class DictionaryImplTest {
    @Test
    public void size() throws Exception {

    }

    @Test
    public void contains() throws Exception {
        DictionaryImpl dict = new DictionaryImpl();
        dict.put("1", "Vasya");
        dict.put("2", "Petya");
        assertTrue(dict.contains("1"));
        assertTrue(dict.contains("2"));
        assertFalse(dict.contains("0"));
    }

    @Test
    public void get() throws Exception {
        DictionaryImpl dict = new DictionaryImpl();
        dict.put("1", "Vasya");
        dict.put("2", "Petya");
        assertEquals("Vasya", dict.get("1"));
        assertNull(dict.get("0"));

    }

    @Test
    public void put() throws Exception {
        DictionaryImpl dict = new DictionaryImpl();
        for(int i = 0; i < 664; i++) {
            dict.put("" + i, "Vasya" + i);
        }
        assertEquals("Vasya2", dict.get("2"));
        assertEquals("Vasya1", dict.put("1", "Vasya"));
        assertEquals("Vasya2", dict.put("2", "Petya"));
        assertEquals("Petya", dict.get("2"));
        assertEquals(664, dict.size());
        for(int i = 664; i < 700; i++) {
            dict.put("" + i, "Petya" + i);
        }

        assertEquals("Petya666", dict.get("666"));


    }

    @Test
    public void remove() throws Exception {
        DictionaryImpl dict = new DictionaryImpl();
        dict.put("1", "Vasya");
        dict.put("2", "Petya");
        assertEquals("Vasya", dict.remove("1"));
        assertEquals(1, dict.size());
    }

    @Test
    public void clear() throws Exception {
        DictionaryImpl dict = new DictionaryImpl();
        dict.put("1", "Vasya");
        dict.put("2", "Petya");
        dict.clear();
        assertEquals(0, dict.size());
    }

}