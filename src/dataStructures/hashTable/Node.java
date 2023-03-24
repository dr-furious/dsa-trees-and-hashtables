package dataStructures.hashTable;

import dataStructures.Test.Tester;

public class Node {

    private String key;
    private int data;
    private Node next;
    private boolean deleted;

    public Node(String data) {
        this.key = data;
        this.data = Tester.getRandomInt(0, Integer.MAX_VALUE);
        this.next = null;
        this.deleted = false;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public boolean hasNext() {
        return (next != null);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        String s = "[<" + key +
                "> N:" + ((next == null) ? "null" : next.getKey()) +
                " c:" + data +
                " d:" + deleted +
                "] ";
        System.out.printf(s);
        return s;
    }
}
