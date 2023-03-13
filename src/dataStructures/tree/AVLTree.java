package dataStructures.tree;

import dataStructures.*;

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
    public Node appendNode(Node node, int data) {
        super.appendNode(node, data);

        updateHeight(node);
        balance(node);
        return node;
    }
    public void removeNode(int data) {
        removeNode(getRoot(), data);
    }
    private void removeNode(Node node, int data) {
        if (node == null) {
            return;
        }
        if (data < node.getData()) {
            removeNode(node.getLeft(), data);
        } else if (data > node.getData()) {
            removeNode(node.getRight(), data);
        } else { // dataStructures.Node to be removed is found
            // If the node is leaf
            if (!node.hasLeftChild() && !node.hasRightChild()) {
                removeLeaf(node);
            } else if (node.hasLeftChild() && node.hasRightChild()) {
                Node nextInOrder = findNextInOrder(node.getRight());
                Node.transmit(nextInOrder, node);
                removeNode(node.getRight(), nextInOrder.getData());
            } else {
                Node child;
                if (node.hasLeftChild()) {
                    child = node.getLeft();
                } else {
                    child = node.getRight();
                }
                Node.transmit(child, node);
                removeNode(child, child.getData());
            }
        }

        updateHeight(node);
        balance(node);
    }
    private void removeLeaf(Node leaf) {
        if (leaf == null) {
            return;
        }
        Node nodeParent = leaf.getParent();
        // If node is the single node in the tree
        if (nodeParent == null) {
            setRoot(null);
            return;
        }
        if (nodeParent.getLeft() == leaf) {
            nodeParent.setLeft(null);
        }
        if (nodeParent.getRight() == leaf) {
            nodeParent.setRight(null);
        }
    }
    private Node findNextInOrder(Node root) {
        if (root == null) {
            return null;
        }
        while (root.hasLeftChild()) {
            root = root.getLeft();
        }
        return root;
    }
    private void updateHeight(Node node) {
        if (node == null){
            return;
        }
        node.setHeight(1 + Math.max(getNodeHeight(node.getLeft()), getNodeHeight(node.getRight())));
    }
    private int getNodeHeight(Node node) {
        if (node == null) {
            return 0;
        }

        return node.getHeight();
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

        root.setHeight(1+Math.max(getNodeHeight(root.getLeft()), getNodeHeight(root.getRight())));
        rightChild.setHeight(1+Math.max(getNodeHeight(rightChild.getLeft()), getNodeHeight(rightChild.getRight())));
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

        root.setHeight(1+Math.max(getNodeHeight(root.getLeft()), getNodeHeight(root.getRight())));
        leftChild.setHeight(1+Math.max(getNodeHeight(leftChild.getLeft()), getNodeHeight(leftChild.getRight())));
    }
}
