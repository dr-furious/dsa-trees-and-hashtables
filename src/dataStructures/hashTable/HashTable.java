package dataStructures.hashTable;

public abstract class HashTable {
    private Node[] data;
    private final int INITIAL_SIZE = 4;
    private int size;
    private int numberOfElements;

    public HashTable() {
        this.size = INITIAL_SIZE;
        this.data = new Node[size];
        this.numberOfElements = 0;
    }

    public Node getData(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return data[index];
    }
    public Node[] getData() {
        return data;
    }
    public void setData(Node data, int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index in method setData(), adding " + data.getKey() + " failed");
            return;
        }
        this.data[index] = data;
    }

    public void setData(Node[] data) {
        this.data = data;
    }

    public int getINITIAL_SIZE() {
        return INITIAL_SIZE;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void incrementNumberOfElements() {
        this.numberOfElements++;
    }

    public void decrementNumberOfElements() {
        this.numberOfElements--;
    }

    // hash
    public int hash(String s) {
        int hashCode = s.hashCode();
        if (hashCode < 0) {
            hashCode *= -1;
        }
        return hashCode % this.size;
    }

    public double getFillRatio() {
        return (numberOfElements / (double) size);
    }

    public int findNextPrime(int number) {
        do {
            number++;
        } while (!isPrime(number));

        return number;
    }
    public int findPreviousPrime(int number) {
        do {
            number--;
        } while (!isPrime(number));

        return number;
    }
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.print("index: (" + i + ") => ");
            printNodeChain(data[i]);
        }
    }

    private void printNodeChain(Node node) {
        while (node != null) {
            node.toString();
            System.out.print("(hash:" + hash(node.getKey()) + ")");
            System.out.print("--> ");
            node = node.getNext();
        }
        System.out.println("[null]");
    }

    // expand -abstract
    // shrink -abstract

    public abstract void add(String key);
    public abstract void remove(String key);
    public abstract Node find(String key);

}
