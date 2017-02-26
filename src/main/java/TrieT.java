public class TrieT implements Trie {

    static private class Node {
        private Node[] next;
        private boolean isWord;
        private int amountOfWordsInSubtree;


        Node() {
            next = new Node[52];
            isWord = false;
            amountOfWordsInSubtree = 0;
        }

        private Node getNodeWithAdd(char c) {
            int index = charToIndex(c);
            if(getNode(c) == null) {
                next[index] = new Node();
            }
            return next[index];

        }
        private Node getNode(char c) {
            return next[charToIndex(c)];
        }
    }

    private Node root;

    TrieT() {

        root = new Node();
    }

    public boolean add(String element) {
        if(element.isEmpty()) {
            if(root.isWord) {
                return false;
            }
            root.isWord = true;
            root.amountOfWordsInSubtree++;
            return true;
        }

        Node tmp = root;

        for (int i = 0; i < element.length(); i++) {
            tmp.amountOfWordsInSubtree++;
            tmp = tmp.getNodeWithAdd(element.charAt(i));
            if (i == element.length() - 1 && tmp.isWord) {
                deleteNodes(element);
                return false;
            }
        }
        tmp.isWord = true;
        tmp.amountOfWordsInSubtree++;
        return true;
    }

    private static int charToIndex(char c) {
        if(Character.isUpperCase(c)) {
            return c - 'A';
        }
        else {
            return c - 'a' + 26;
        }
    }

    private void deleteNodes(String word) {
        if(word.isEmpty()) {
            root.isWord = false;
            return;
        }
        Node parent = root;
        Node child;
        for(int i = 0; i < word.length(); i++) {
            parent.amountOfWordsInSubtree--;
            child = parent.getNode(word.charAt(i));

            if(child.amountOfWordsInSubtree <= 1) {
                parent.next[charToIndex(word.charAt(i))] = null;
                return;
            }
            parent = child;
        }
    }



    public boolean contains(String element) {
        Node lastNode = lastNodeOfPrefix(element);

        return lastNode != null && lastNode.isWord;
    }

    private Node lastNodeOfPrefix(String word) {
        Node tmp = root;
        for(int i = 0; i < word.length(); i++) {
            tmp = tmp.getNode(word.charAt(i));
            if(tmp == null) {
                break;
            }
        }
        return tmp;
    }

    public boolean remove(String element) {
        if(element.isEmpty()) {
            if(root.isWord) {
                root.isWord = false;
                root.amountOfWordsInSubtree--;
                return true;
            }
            return false;
        }
        Node lastNode = lastNodeOfPrefix(element);
        if (lastNode != null && lastNode.isWord) {
            deleteNodes(element);
            lastNode.isWord = false;
            return true;
        }
        return false;
    }

    public int size() {
        return root.amountOfWordsInSubtree;
    }

    public int howManyStartsWithPrefix(String prefix) {
        Node lastNode = lastNodeOfPrefix(prefix);
        if(lastNode == null) {
            return 0;
        }
        return lastNode.amountOfWordsInSubtree;
    }

}
