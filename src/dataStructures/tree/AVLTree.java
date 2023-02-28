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
        super.appendNode(data);
        balance(getRoot());
    }
    @Override
    public void removeNode(int data) {
        super.removeNode(data);
        balance(getRoot());
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
    private void balance(Node root) {
        // If node is leaf, exit function
        if (root == null || (!root.hasLeftChild() && !root.hasRightChild())) {
            return;
        }
        int balance = getNodeBalance(root);
        Node leftChild = root.getLeft();
        Node rightChild = root.getRight();

        if (balance >= 2) {
            rotateRight(root);
            balance(getRoot());
        } else if (balance <= -2) {
            rotateLeft(root);
            balance(getRoot());
        }

        balance(leftChild);
        balance(rightChild);
    }
    public void rotateLeft(Node root) {
        if (root == null) {
            System.out.println("Root was null in rotateLeft");
            return;
        }
        // ?
        if (getNodeBalance(root.getRight()) > 0) {
            rotateRight(root.getRight());
        }
        Node rootParent = root.getParent();
        Node rightChild = root.getRight();
        if (rightChild == null) {
            System.out.println("RightChild was null in rotateLeft");
            return;
        }

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

        // TODO: Dont forget to change the root if the root is rotated!
        setRoot((rootParent == null) ? root.getParent() : getRoot());
    }
    public void rotateRight(Node root) {
        if (root == null) {
            System.out.println("Root was null in rotateRight");
            return;
        }
        // ?
        if (getNodeBalance(root.getLeft()) < 0) {
            rotateLeft(root.getLeft());
        }
        Node rootParent = root.getParent();
        Node leftChild = root.getLeft();
        if (leftChild == null) {
            System.out.println("LeftChild was null in rotateRight");
            return;
        }

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
