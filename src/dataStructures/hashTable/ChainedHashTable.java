package dataStructures.hashTable;

public class ChainedHashTable extends HashTable {

    public ChainedHashTable() {
        super();
    }

    private void move(Node node) {
        while (node != null) {
            String key = node.getKey();
            int index = hash(key);
            Node ghost = getData(index);
            Node next = node.getNext();
            node.setNext(null);

            if (ghost == null) {
                setData(node, index);
            } else {
                chain(ghost, node);
            }

            node = next;
        }
    }

    private void chain(Node head, Node tail) {
        while (head.getNext() != null) {
            if (head.getKey().equals(tail.getKey())) {
                return;
            }
            head = head.getNext();
        }

        head.setNext(tail);
    }

    @Override
    public void add(String key) {
        if (find(key) != null) {
            return;
        }
        int index = hash(key);
        Node ghost = getData(index);

        if (ghost == null) {
            setData(new Node(key), index);
        } else {
            chain(ghost, new Node(key));
        }

        incrementNumberOfElements();

        // If table is filled on more than 75%
        if (getFillRatio() >= 0.75) {
            Node[] denseData = getData();
            Node[] newData = new Node[getSize()*2]; // Double the size of the table
            setData(newData);
            setSize(newData.length);
            for (Node d:denseData) {
                move(d);
            }
        }
    }

    @Override
    public void remove(String key) {
        if (key == null) {
            return;
        }
        int index = hash(key);
        Node ghost = getData(index);

        if (ghost == null) {
            return;
        } else if (!ghost.hasNext()) {
            if (key.equals(ghost.getKey())) {
                setData(null, index);
            } else {
                return;
            }
        } else {
            if (key.equals(ghost.getKey())) {
                setData(ghost.getNext(), index);
            } else {
                Node previous = ghost;
                Node next = ghost.getNext();
                boolean found = false;
                while (next != null) {
                    if (key.equals(next.getKey())) {
                        previous.setNext(next.getNext());
                        found = true;
                        break;
                    }
                    previous = next;
                    next = next.getNext();
                }
                if (!found) {
                    return;
                }
            }
        }

        decrementNumberOfElements();

        // If table is filled on less than 25%
        if (getFillRatio() < 0.25 && getSize() != getINITIAL_SIZE()) {
            Node[] sparseData = getData();
            Node[] newData = new Node[getSize()/2]; // Split the size of the table
            setData(newData);
            setSize(newData.length);
            for (Node d:sparseData) {
                move(d);
            }
        }
    }

    @Override
    public Node find(String key) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        Node ghost = getData(index);

        if (ghost == null) {
            return null;
        }

        do {
            if (ghost.getKey().equals(key)) {
                return ghost;
            }
            ghost = ghost.getNext();
        } while (ghost != null);

        return null;
    }


}
