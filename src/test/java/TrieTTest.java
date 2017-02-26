import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTTest {
    @Test
    public void howManyStartsWithPrefix() throws Exception {
        TrieT tr = new TrieT();
        tr.add("Hell");
        tr.add("Hello");
        tr.add("Hel");
        assertEquals(3, tr.howManyStartsWithPrefix("He"));
        tr.remove("Hell");
        assertEquals(2, tr.howManyStartsWithPrefix("He"));
    }

    @Test
    public void size() throws Exception {
        TrieT tr = new TrieT();
        tr.add("a");
        tr.add("Hello");
        tr.add("b");
        assertEquals(3, tr.size());
        tr.remove("a");
        assertEquals(2, tr.size());
    }

    @Test
    public void remove() throws Exception {
        TrieT tr = new TrieT();
        tr.add("a");
        tr.add("Hello");
        tr.add("b");
        assertTrue(tr.contains("a"));
        tr.remove("a");
        assertFalse(tr.contains("a"));
        assertTrue(tr.remove("Hello"));
        assertFalse(tr.contains("Hello"));
        assertFalse(tr.remove("He"));
    }

    @Test
    public void contains() throws Exception {
        TrieT triet = new TrieT();
        triet.add("a");
        triet.add("Hello");
        assertTrue(triet.contains("a"));
        assertFalse(triet.contains("b"));
        assertTrue(triet.contains("Hello"));
        assertFalse(triet.contains("Hall"));
    }

    @Test
    public void add() throws Exception {
        TrieT triet = new TrieT();
        triet.add("");
        assertTrue(triet.contains(""));
        assertEquals(1, triet.size());
        assertFalse(triet.add(""));
        assertTrue(triet.remove(""));
        assertFalse(triet.contains(""));
        assertFalse(triet.remove(""));
        assertEquals(0, triet.size());

        triet.remove("");
        assertEquals(0, triet.size());
        assertFalse(triet.contains(""));
        for(char c = 'a'; c <= 'z'; c++) {
            triet.add(Character.toString(c));
        }

        for(char c = 'A'; c <= 'Z'; c++) {
            triet.add(Character.toString(c));
        }
        assertEquals(52, triet.size());
        assertFalse(triet.add("a"));

    }

    @Test
    public void testFromTask1() throws Exception {
        TrieT trie = new TrieT();
        trie.add("Hello");
        assertTrue(trie.contains("Hello"));
        assertEquals(trie.size(), 1);
        trie.remove("Hello");
        assertFalse(trie.contains("Hello"));
    }

    @Test
    public void testFromTask2() {
        TrieT trie = new TrieT();
        trie.add("Hello");
        assertTrue(trie.contains("Hello"));
        assertEquals(1, trie.howManyStartsWithPrefix("He"));
        trie.remove("Hello");
        assertEquals(0, trie.size());
        assertEquals(0, trie.howManyStartsWithPrefix("He"));
    }

    public void testFromTask3() {
        TrieT trie = new TrieT();
        trie.add("Hello");
        assertEquals( 1, trie.size());
        assertEquals(1, trie.howManyStartsWithPrefix("He"));
        trie.add("Hello");
        assertEquals(1, trie.size());
        assertEquals(1, trie.howManyStartsWithPrefix("He"));
    }

}
