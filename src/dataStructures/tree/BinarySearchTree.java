package dataStructures.tree;

public class BinarySearchTree {
    private final Node root;

    // Constructors
    public BinarySearchTree(Node root) {
        this.root = root;
    }
    public BinarySearchTree(int rootValue) {
        this.root = new Node(rootValue);
    }

    // Appends Node to the leaf of the tree according to the BST property PRIVATE!
    private void appendNode(Node root, int data) {
        if (root == null) {
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

    public void appendNode(int data) {
        appendNode(this.root, data);
    }

    public void removeNode(int data) {

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
        int treeHeight = root.getHeight();

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
