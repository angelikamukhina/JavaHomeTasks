class List {
    private Node head = null;

    void addFront(String key, String value)
    {
        Node newNode = new Node(key, value);
        if(head == null)
        {
            head = newNode;
        }
        else
        {
            newNode.next = head;
            head = newNode;
        }
    }

    String deleteNode(String delKey) {
        if(head == null)
        {
            return null;
        }
        else {
            String tmp;
            Node tmpNode = head;
            Node prev = head;
            while(tmpNode != null)
            {
                tmp = tmpNode.key;
                if(tmp.equals(delKey)) {
                    prev.next = tmpNode.next;
                    if(tmpNode == head) {
                        head = tmpNode.next;
                    }
                    return tmpNode.value;
                }
                prev = tmpNode;
                tmpNode = tmpNode.next;
            }
        }
        return null;
    }

    Node find(String key) {
        if(head == null)
        {
            return null;
        }
        else {
            String tmp;
            Node currNode = head;
            while(currNode!= null)
            {
                tmp = currNode.key;
                if(tmp.equals(key)) {
                    return currNode;
                }
                currNode = currNode.next;
            }

            return null;
        }
    }

    String valueByKey(String key) {
        Node foundNode = find(key);
        if(foundNode == null) {
            return null;
        }
        return foundNode.value;
    }

    String findAndReplace(String key, String value) {
        Node foundNode = find(key);
        if(foundNode == null) {
            return null;
        }
        String oldValue = foundNode.value;
        foundNode.value = value;
        return oldValue;

    }


    static class Node
    {
        String key;
        String value;
        Node next;

        Node(String key, String value)
        {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    static class Iterator {
        Node currNode;

        Iterator(List lst) {
            currNode = lst.head;
        }


        Node next() {
            Node oldCurrNode = currNode;
            if(currNode != null) {
                currNode = currNode.next;
            }
            return oldCurrNode;
        }
    }
}
