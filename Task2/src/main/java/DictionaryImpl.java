public class DictionaryImpl implements Dictionary {
    private static final int initialSize = 500;
    private List[] table = new List[initialSize];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public String get(String key) {
        int hash = hash(key, table.length);

        List lst = table[hash];
        if (lst == null) {
            return null;
        }
        return lst.valueByKey(key);
    }

    public String put(String key, String value) {
        int hash = hash(key, table.length);
        if (table[hash] == null) {
            table[hash] = new List();
        }
        String foundValue = table[hash].findAndReplace(key, value);
        if (foundValue == null) {
            size++;
        }
        if (Math.abs(size - 4 * table.length / 3) < 0.001) {
            reHash();
        }
        return foundValue;
    }

    public String remove(String key) {
        int hash = hash(key, table.length);
        String deletedValue = table[hash].deleteNode(key);
        if (deletedValue != null) {
            size--;
        }
        return deletedValue;
    }

    public void clear() {
        table = new List[initialSize];
        size = 0;
    }

    private static int hash(String key, int tableSize) {
        return key.hashCode() % tableSize;
    }

    private void reHash() {
        int newTableSize = 2 * table.length;
        List[] newTable = new List[newTableSize];
        List tmpList;
        List.Node tmpNode;
        List.Iterator iterator;
        int newHash;
        for (List aTable : table) {
            tmpList = aTable;
            if (tmpList == null) {
                continue;
            }
            iterator = new List.Iterator(tmpList);
            tmpNode = iterator.next();
            while (tmpNode != null) {
                newHash = hash(tmpNode.key, newTableSize);
                if (newTable[newHash] == null) {
                    newTable[newHash] = new List();
                }
                newTable[newHash].addFront(tmpNode.key, tmpNode.value);
                tmpNode = iterator.next();
            }
        }
        table = newTable;
    }
}
