package dataStructures.hashTable;

public class LinearProbingHashTable extends HashTable {

    public LinearProbingHashTable() {
        super();
    }

    @Override
    public void add(String key) {
        int index = hash(key);
        Node ghost = getData(index);

        if (ghost == null || ghost.isDeleted()) {
            setData(new Node(key), index);
        } else if (!key.equals(ghost.getKey())){
            int nextIndex = findNextSlot(index, key);
            if (nextIndex == -1) {
                return; // If the value already is in table
            }
            setData(new Node(key), nextIndex);
        } else {
            return;
        }

        incrementNumberOfElements();

        // If table is filled on more than 75%
        if (getFillRatio() >= 0.75) {
            Node[] denseData = getData();
            Node[] newData = new Node[getSize()*2]; // double the size of the table
            setData(newData);
            setSize(newData.length);
            for (Node d:denseData) {
                move(d);
            }
        }
    }

    private void move(Node node) {
        if (node == null || node.isDeleted()) {
            return;
        }
        String key = node.getKey();
        int index = hash(key);
        Node ghost = getData(index);

        if (ghost == null || ghost.isDeleted()) {
            setData(node, index);
        } else {
            int nextIndex = findNextSlot(index, key);
            setData(node, nextIndex);
        }
    }

    private int findNextSlot(int startIndex, String key) {
        Node check;
        int size = getSize();
        while ((check = getData(startIndex)) != null) {
            if (check.isDeleted()) {
                return startIndex;
            }
            if (key.equals(check.getKey())) {
                return -1;
            }
            startIndex = (startIndex + 1) % size;
        }
        return startIndex;
    }

    @Override
    public void remove(String key) {
        int index = hash(key);
        Node ghost = getData(index);
        if (ghost == null) {
            return;
        }

        do {
            if (key.equals(ghost.getKey())) {
                if (ghost.isDeleted()) {
                    return;
                }
                ghost.setDeleted(true);
                decrementNumberOfElements();
                break;
            }
            index++;
            ghost = getData(index % getSize());
        } while (ghost != null);

        // If table is filled on less than 25%
        if (getFillRatio() < 0.25 && getSize() != getINITIAL_SIZE()) {
            Node[] sparseData = getData();
            Node[] newData = new Node[getSize()/2]; // split the size of the table
            setData(newData);
            setSize(newData.length);
            for (Node d:sparseData) {
                move(d);
            }
        }
    }

    @Override
    public Node find(String key) {
        int index = hash(key);
        Node ghost;
        while ((ghost = getData(index)) != null) {
            if (!ghost.isDeleted() && key.equals(ghost.getKey())) {
                return ghost;
            }

            index = (index+1) % getSize();
        }
        return null;
    }


}
