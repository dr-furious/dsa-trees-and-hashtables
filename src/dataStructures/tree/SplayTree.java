package dataStructures.tree;

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

    public void appendNode(int data) {
        Node child = super.appendNode(getRoot(), data);
        splay(child);
    }
    public Node findNode(int data) {
        Node wanted = super.findNode(data);
        splay(wanted);
        return getRoot();
    }
    public void removeNode(int data) {
        findNode(data);
        Node root = getRoot();
        if (root == null || root.getData() != data) {
            return;
        }
        if (!root.hasLeftChild() && !root.hasRightChild()) {
            setRoot(null);
            return;
        }
        if (!root.hasLeftChild()) {
            Node child = root.getRight();
            child.setParent(null);
            setRoot(child);
            return;
        }
        if (!root.hasRightChild()) {
            Node child = root.getLeft();
            child.setParent(null);
            setRoot(child);
            return;
        }

        Node left = getRoot().getLeft();
        Node right = getRoot().getRight();

        Node leftRoot = findPreviousInOrder(left);

        splay(leftRoot);
        leftRoot.setRight(right);
    }
    private Node findPreviousInOrder(Node node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }

        return node;
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
                if (parent.getLeft() == node) { // Zig-Zig rotation gp -> p
                    zigRotate(grandParent);
                    zigRotate(parent);
                } else { // Zag-Zig rotation p -> gp
                    zagRotate(parent);
                    zigRotate(grandParent);
                }
            } else {
                if (parent.getLeft() == node) { // Zig-Zag rotation p -> gp
                    zigRotate(parent);
                    zagRotate(grandParent);
                } else { // Zag-Zag rotation gp -> p
                    zagRotate(grandParent);
                    zagRotate(parent);
                }
            }
            splay(node);
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
