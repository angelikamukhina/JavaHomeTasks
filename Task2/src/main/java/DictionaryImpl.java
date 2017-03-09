public class DictionaryImpl implements Dictionary {
    private int tableSize = 500; //initial value
    private List [] table = new List[tableSize];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public String get(String key) {
        int hash = hash(key, tableSize);

        List lst = table[hash];
        if(lst == null) {
            return null;
        }
        return  lst.valueByKey(key);
    }

    public String put(String key, String value) {

        int hash = hash(key, tableSize);

        if(table[hash] == null) {
            table[hash] = new List();
        }
        String foundValue = table[hash].findAndReplace(key, value);
        if(foundValue == null) {
            table[hash].addFront(key, value);
            size++;
        }
        if(Math.abs(size - 4*tableSize/3) < 0.001) {
            reHash();
        }
        return foundValue;
    }

    public String remove(String key) {
        int hash = hash(key, tableSize);
        String deletedValue = null;
        if(contains(key)) {
            deletedValue = table[hash].deleteNode(key);
            size--;
        }
        return deletedValue;
    }

    public void clear() {
        for(int i = 0; i < tableSize; i++) {
            table[i] = null;
        }
        size = 0;
    }


    private void reHash() {
        int newTableSize = 2*tableSize;
        List [] newTable = new List[newTableSize];
        List tmpList;
        List.Node tmpNode;
        List.Iterator iterator;
        int newHash;
        for(int i = 0; i < tableSize; i++) {
            tmpList = table[i];
            if(tmpList == null) {
                continue;
            }
            iterator = new List.Iterator(tmpList);
            tmpNode = iterator.next();
            while(tmpNode != null) {
                newHash = hash(tmpNode.key, newTableSize);
                if(newTable[newHash] == null) {
                    newTable[newHash] = new List();
                }
                newTable[newHash].addFront(tmpNode.key, tmpNode.value);
                tmpNode = iterator.next();
            }
        }
        tableSize = newTableSize;
        table = newTable;
    }

    private static int hash(String key, int tableSize) {
        return key.hashCode() % tableSize;
    }
}
