package dataStructures.hashTable;

import dataStructures.Tester;

public class Node {
    private String key;
    private int data;
    private Node next;

    public Node(String data) {
        this.key = data;
        this.data = Tester.getRandomInt(0, Integer.MAX_VALUE);
        this.next = null;
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

    @Override
    public String toString() {
        String s = "[<" + key +
                "> N:" + ((next == null) ? "null" : next.getKey()) +
                " c:" + data +
                "] ";
        System.out.printf(s);
        return s;
    }
}
