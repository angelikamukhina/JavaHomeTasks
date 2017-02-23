/**
 * Created by angelika on 22.02.17.
 */
public class Node {
    private Node [] next;
    private Node parent;
    private boolean isLeaf;
    private boolean isWord;
    private int index;


    Node(Node parent, int index) {
        next = new Node[52];
        this.parent = parent;
        isLeaf = true;
        isWord = false;
        this.index = index;
    }


    public boolean addWord(String word) {
        if(word.length() == 0) {
            if(isWord == true) {
                return false;
            }
            else {
                isWord = true;
                return true;
            }
        }
        else {
            int index = charToIndex(word.charAt(0));

            if(next[index] == null) {
                next[index] = new Node(this, index);
                isLeaf = false;
            }
            return next[index].addWord(word.substring(1));
        }
    }

    private static int charToIndex(char c) {
        int index;
        if(Character.isUpperCase(c)) {
            index = c - 'A';
        }
        else {
            index = c - 'a' + 26;
        }
        return index;
    }

    private Node getNode(char c) {
        return next[charToIndex(c)];
    }

    public boolean containsWord(String element) {
        if(isWord && element.length() == 0) {
            return true;
        }
        if(isLeaf) {
            return false;
        }
        Node nextNode = getNode(element.charAt(0));
        if (nextNode == null) {
            return false;
        }
        return nextNode.containsWord(element.substring(1));
    }

    private boolean isAnyChild() {
        for(int i = 0; i < 52; i++) {
            if(next[i] != null) {
                return true;
            }
        }
        return false;
    }

    private void deleteNode() {
        if(isLeaf && parent != null) {
            parent.next[index] = null;
            if(parent.isAnyChild()) {
                return;
            }
            parent.isLeaf = true;
            parent.deleteNode();

        }
    }

    public boolean removeWord(String element) {
        if(element.isEmpty()) {
            if(isWord) {
                isWord = false;
                deleteNode();
                return true;
            }
            else {
                return false;
            }
        }
        else {
            Node next = getNode(element.charAt(0));
            if(next == null) {
                return false;
            }
            else {
               return next.removeWord(element.substring(1));
            }
        }
    }

    private int amountOfWordsInSubtree() {
        int counter = 0;
        if(isWord) {
            counter++;
        }
        if(isLeaf) {
            return counter;
        }

        for(int i = 0; i < 52; i++) {
            if(next[i] != null) {
                counter += next[i].amountOfWordsInSubtree();
            }
        }
        return counter;
    }

    public int amountOfWordsWithPrefix(String prefix) {
        if(prefix.isEmpty()) {
            return amountOfWordsInSubtree();
        }
        Node next = getNode(prefix.charAt(0));
        if(next == null) {
            return 0;
        }
        return next.amountOfWordsWithPrefix(prefix.substring(1));
    }
}
