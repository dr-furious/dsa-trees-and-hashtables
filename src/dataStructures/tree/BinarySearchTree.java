package dataStructures.tree;

import dataStructures.*;

public class BinarySearchTree {
    private Node root;

    // Constructors
    public BinarySearchTree() {
        this.root = null;
    }
    public BinarySearchTree(Node root) {
        this.root = root;
    }
    public BinarySearchTree(int rootValue) {
        this.root = new Node(rootValue);
    }
    public Node getRoot() {
        return this.root;
    }
    public void setRoot(Node root) {
         this.root = root;
    }

    // Appends dataStructures.Node to the leaf of the tree according to the BST property PRIVATE!
    public void appendNode(Node root, int data) {
        if (this.root == null) {
            this.root = new Node(data);
            return;
        }
        if (data < root.getData()) {
            if (root.hasLeftChild()) {
                appendNode(root.getLeft(), data);
            } else {
                root.setLeft(new Node(data, root));
            }
        } else if (data > root.getData()) {
            if (root.hasRightChild()) {
                appendNode(root.getRight(), data);
            } else {
                root.setRight(new Node(data, root));
            }
        }
    }

    private void appendNode(Node root, Node node) {
        if (this.root == null) {
            this.root = node;
            return;
        }
        if (node.getData() < root.getData()) {
            if (root.hasLeftChild()) {
                appendNode(root.getLeft(), node);
            } else {
                root.setLeft(node);
            }
        } else if (node.getData() > root.getData()) {
            if (root.hasRightChild()) {
                appendNode(root.getRight(), node);
            } else {
                root.setRight(node);
            }
        }
    }

    public void appendNode(int data) {
        appendNode(this.root, data);
    }
    public Node appendNode(Node node) {
        appendNode(this.root, node);
        return node;
    }

    public void removeNode(int data) {
        Node wanted = findNode(data);
        if (wanted == null) {
            return;
        }
        Node wantedParent = wanted.getParent();

        // If removed node is leaf
        if (!wanted.hasLeftChild() && !wanted.hasRightChild()) {
            // If it is the only node
            if (wantedParent == null) {
                this.root = null;
                return;
            }
            if (wantedParent.getLeft() == wanted) {
                wantedParent.setLeft(null);
            } else {
                wantedParent.setRight(null);
            }
            return;
        }

        if (wanted.hasRightChild()) {
            if (!wanted.hasLeftChild()) {
                if (wantedParent == null) {
                    wanted.getRight().setParent(null);
                    this.root = wanted.getRight();
                } else {
                    wantedParent.setRight(null);
                    appendNode(wantedParent, wanted.getRight());
                }

                return;
            }

            appendNode(wanted.getRight(), wanted.getLeft());
            if (wantedParent == null) {
                wanted.getRight().setParent(null);
                this.root = wanted.getRight();
            } else if (wantedParent.getRight() == wanted) {
                wantedParent.setRight(wanted.getRight());
            } else if (wantedParent.getLeft() == wanted) {
                wantedParent.setLeft(wanted.getRight());
            }
        } else {
            if (wantedParent == null) {
                wanted.getLeft().setParent(null);
                this.root = wanted.getLeft();
            } else {
                wantedParent.setRight(null);
                appendNode(wantedParent, wanted.getLeft());
            }
        }
    }

    public Node findNode(int data) {
        return findNode(this.root, data);
    }

    private Node findNode(Node root, int data) {
        if (root == null) {
            return null;
        }

        if (data == root.getData()) {
            return root;
        }

        if (data < root.getData()) {
            return findNode(root.getLeft(), data);
        } else {
            return findNode(root.getRight(), data);
        }
    }

    // Prints given level of the tree from specified node
    private void printLevel(Node root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            root.toString();
        } else if (level > 1) {
            printLevel(root.getLeft(), level-1);
            printLevel(root.getRight(), level-1);
        }
    }

    // Prints given level of the tree from the root
    public void printLevel(int level) {
        printLevel(this.root, level);
    }

    // Prints the tree
    public void print() {
        if (this.root == null) {
            System.out.println("<null>");
            return;
        }
        int treeHeight = this.root.computeHeight();

        for (int i = 1; i < treeHeight+1; i++) {
            System.out.print("Level " + (i-1) + ": ");
            printLevel(root, i);
            System.out.println();
        }
    }

    // Counts the leaves of the tree from the root
    public int countLeaves() {
        return countLeaves(this.root);
    }

    // Counts the leaves of the tree from the specified node
    private int countLeaves(Node root) {
        if (root == null) {
            return 0;
        } else if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }

        return countLeaves(root.getLeft()) + countLeaves(root.getRight());
    }
}
