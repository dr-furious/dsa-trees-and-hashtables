package dataStructures.tree;

import com.sun.source.tree.Tree;

public class AVLTree extends BinarySearchTree {

    public AVLTree() {
        super();
    }
    public AVLTree(Node root) {
        super(root);
    }
    public AVLTree(int rootValue) {
        super(rootValue);
    }

    @Override
    public void appendNode(int data) {
        Node node = appendNode(new Node(data));
        balance(node.getParent());
    }
    @Override
    public void removeNode(int data) {
        super.removeNode(data);
        balance(findNode(data));
    }
    private int getNodeBalance(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        if (root.hasLeftChild()) {
            leftHeight = root.getLeft().getHeight();
        }
        if (root.hasRightChild()) {
            rightHeight = root.getRight().getHeight();
        }

        return leftHeight-rightHeight;
    }
    private void balance(Node node) {
        // If node is root, exit function
        if (node == null) {
            return;
        }
        Node nodeParent = node.getParent();
        int balance = getNodeBalance(node);

        if (balance > 1) {
            Node leftChild = node.getLeft();
            if (getNodeBalance(leftChild) < 0) {
                rotateLeft(leftChild);
            }
            rotateRight(node);
        } else if (balance < -1) {
            Node rightChild = node.getRight();
            if (getNodeBalance(rightChild) > 0) {
                rotateRight(rightChild);
            }
            rotateLeft(node);
        }

        balance(nodeParent);
    }
    private void rotateLeft(Node root) {
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
    private void rotateRight(Node root) {
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
