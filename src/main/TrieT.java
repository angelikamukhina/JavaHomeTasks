import sun.font.TrueTypeFont;

/**
 * Created by angelika on 18.02.17.
 */
public class TrieT implements Trie {
    private Node root;
    private int size;

    TrieT() {
        root = new Node(null, -1);
        size = 0;
    }

    @Override
    public boolean add(String element) {
        boolean success = root.addWord(element);
        if(success) {
            size++;
        }
        return success;
    }

    @Override
    public boolean contains(String element) {

        return root.containsWord(element);
    }

    @Override
    public boolean remove(String element) {
        boolean success = root.removeWord(element);
        if(success) {
            size--;
        }
        return success;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int howManyStartsWithPrefix(String prefix) {
        return root.amountOfWordsWithPrefix(prefix);
    }

}
