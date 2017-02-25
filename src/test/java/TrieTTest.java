import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by angelika on 21.02.17.
 */
public class TrieTTest {
    @Test
    public void howManyStartsWithPrefix() throws Exception {
        TrieT tr = new TrieT();
        tr.add("Hell");
        tr.add("Hello");
        tr.add("Hel");
        assertEquals(tr.howManyStartsWithPrefix("He"), 3);
        tr.remove("Hell");
        assertEquals(tr.howManyStartsWithPrefix("He"), 2);
    }

    @Test
    public void size() throws Exception {
        TrieT tr = new TrieT();
        tr.add("a");
        tr.add("Hello");
        tr.add("b");
        assertEquals(tr.size(), 3);
        tr.remove("a");
        assertEquals(tr.size(), 2);
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
        assertEquals(triet.size(), 0);

        triet.remove("");
        assertEquals(triet.size(), 0);
        assertFalse(triet.contains(""));
        for(char c = 'a'; c <= 'z'; c++) {
            triet.add(Character.toString(c));
        }

        for(char c = 'A'; c <= 'Z'; c++) {
            triet.add(Character.toString(c));
        }
        assertEquals(triet.size(), 52);
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
        assertEquals(trie.howManyStartsWithPrefix("He"), 1);
        trie.remove("Hello");
        assertEquals(trie.size(), 0);
        assertEquals(trie.howManyStartsWithPrefix("He"), 0);
    }

    public void testFromTask3() {
        TrieT trie = new TrieT();
        trie.add("Hello");
        assertEquals(trie.size(), 1);
        assertEquals(trie.howManyStartsWithPrefix("He"), 1);
        trie.add("Hello");
        assertEquals(trie.size(), 1);
        assertEquals(trie.howManyStartsWithPrefix("He"), 1);
    }

}
