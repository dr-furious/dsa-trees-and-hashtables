package dataStructures.tree;

import dataStructures.Tester;

public class Node {

    private int data;
    private String content;
    private int height;

    private Node parent;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
        this.content = generateRandomContent();
        this.height = 1;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public Node (int data, Node parent) {
        this(data);
        this.parent = parent;
    }

    public void setLeft(Node node) {
        this.left = node;
        if (node != null) {
            node.setParent(this);
        }
    }

    public Node getLeft() {
        return this.left;
    }

    public void setRight(Node node) {
        this.right = node;
        if (node != null) {
            node.setParent(this);
        }
    }

    public Node getRight() {
        return this.right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int computeHeight() {
        return computeHeight(this);
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public boolean hasLeftChild() {
        return this.left != null;
    }

    public boolean hasRightChild() {
        return this.right != null;
    }

    @Override
    public String toString() {
        String s = "[<" + data +
                "> P:" + ((parent == null) ? "null" : parent.getData()) +
                " L:" + ((left == null) ? "null" : left.getData()) +
                " R:" + ((right == null) ? "null" : right.getData()) +
                " h:" + height +
                " c:" + content +
                "] ";
        System.out.printf(s);
        return s;
    }

    public static Node clone(Node node) {
        if (node == null) {
            return null;
        }
        Node clone = new Node(node.getData(), node.getParent());
        clone.setRight(node.getRight());
        clone.setLeft(node.getLeft());

        return clone;
    }

    public static int computeHeight(Node root) {

        if (root == null) {
            return 0;
        }
        int leftHeight = computeHeight(root.getLeft());
        int rightHeight = computeHeight(root.getRight());

        return ((leftHeight > rightHeight) ? leftHeight : rightHeight) + 1;
    }

    public static void transmit(Node location, Node target) {
        if (location == null || target == null) {
            return;
        }
        target.setData(location.getData());
        target.setContent(location.getContent());
    }

    private String generateRandomContent() {
        int length = Tester.getRandomInt(2,11);
        String random = "";
        for (int i = 0; i < length; i++) {
            int intChar = Tester.getRandomInt(97, 123); // generate ascii value of one character
            random += Character.toString((char) intChar); // convert character from ascii code to string
        }

        return random;
    }
}
