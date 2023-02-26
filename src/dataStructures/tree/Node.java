package dataStructures.tree;

public class Node {

    private int data;
    private Node parent;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return getHeight(this);
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
        String s = "[<" + this.getData() +
                "> P:" + ((parent == null) ? "null" : parent.getData()) +
                ",L:" + ((left == null) ? "null" : left.getData()) +
                ",R:" + ((right == null) ? "null" : right.getData()) +
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

    public static int getHeight(Node root) {

        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.getLeft());
        int rightHeight = getHeight(root.getRight());

        return ((leftHeight > rightHeight) ? leftHeight : rightHeight) + 1;
    }
}