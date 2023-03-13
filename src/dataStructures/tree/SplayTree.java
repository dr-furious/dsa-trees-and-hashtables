package dataStructures.tree;

import dataStructures.*;

public class SplayTree extends BinarySearchTree {

    public SplayTree() {
        super();
    }
    public SplayTree(Node root) {
        super(root);
    }

    public SplayTree(int rootValue) {
        super(rootValue);
    }

    @Override
    public Node appendNode(Node node, int data) {
        Node child =  super.appendNode(node, data);

        splay(child);
        return child;
    }
    private void findNode(Node root, int data) {
        if (root == null) {
            return ;
        }

        if (data < root.getData()) {
            findNode(root.getLeft(), data);
        } else if (data > root.getData()) {
            findNode(root.getRight(), data);
        }

        splay(root);
    }

    public Node findNode(int data) {
        findNode(getRoot(), data);
        return getRoot();
    }
    private void splay(Node node) {
        if (node == null || node.getParent() == null) {
            return;
        }

        Node parent = node.getParent();
        Node grandParent = parent.getParent();
        if (grandParent == null) {
            if (parent.getLeft() == node) {
                // Zig rotation p
                zigRotate(parent);
            } else {
                // Zag rotation p
                zagRotate(parent);
            }
        } else {
            if (grandParent.getLeft() == parent) {
                if (parent.getLeft() == node) {
                    // Zig-Zig rotation gp -> p
                    zigRotate(grandParent);
                    zigRotate(parent);
                } else {
                    // Zag-Zig rotation p -> gp
                    zagRotate(parent);
                    zigRotate(grandParent);
                }
            } else {
                if (parent.getLeft() == node) {
                    // Zig-Zag rotation p -> gp
                    zigRotate(parent);
                    zagRotate(grandParent);
                } else {
                    // Zag-Zag rotation gp -> p
                    zagRotate(grandParent);
                    zagRotate(parent);
                }
            }
        }
    }
    private void zagRotate(Node root) {
        Node rootParent = root.getParent();
        Node rightChild = root.getRight();
        Node rightChildLeftChild = root.getRight().getLeft();

        // Changing root's rightChild to point to root's parent and vice versa
        if (rootParent == null) {
            root.getRight().setParent(null);
        } else if (rootParent.getLeft() == root) {
            rootParent.setLeft(rightChild);
        } else if (rootParent.getRight() == root) {
            rootParent.setRight(rightChild);
        }

        // Setting the root's rightChild's leftChild to be root
        // so now root becomes leftChild of root's rightChild
        rightChild.setLeft(root);

        // Root's rightChild previously had leftChild. Now we
        // set root's rightChild to be this lost child
        root.setRight(rightChildLeftChild);
        setRoot((rootParent == null) ? root.getParent() : getRoot());
    }
    private void zigRotate(Node root) {
        Node rootParent = root.getParent();
        Node leftChild = root.getLeft();
        Node leftChildRightChild = root.getLeft().getRight();

        // Changing root's leftChild to point to root's parent and vice versa
        if (rootParent == null) {
            root.getLeft().setParent(null);
        } else if (rootParent.getLeft() == root) {
            rootParent.setLeft(leftChild);
        } else if (rootParent.getRight() == root) {
            rootParent.setRight(leftChild);
        }

        // Setting the root's leftChild's rightChild to be root
        // so now root becomes rightChild of root's leftChild
        leftChild.setRight(root);

        // Root's leftChild previously had rightChild. Now we
        // set root's leftChild to be this lost child
        root.setLeft(leftChildRightChild);
        setRoot((rootParent == null) ? root.getParent() : getRoot());
    }
}
