import dataStructures.tree.BinarySearchTree;
import dataStructures.tree.Node;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        
        BinarySearchTree bst = new BinarySearchTree();
        bst.appendNode(10);
        bst.appendNode(7);
        bst.appendNode(15);
        bst.appendNode(3);
        bst.appendNode(8);
        bst.appendNode(13);
        bst.appendNode(19);
        bst.appendNode(11);
        bst.appendNode(14);
        bst.appendNode(17);
        bst.appendNode(20);
        bst.appendNode(30);

        bst.print();
        System.out.println();

        bst.removeNode(30);
        bst.removeNode(20);
        bst.removeNode(19);
        bst.removeNode(15);
        bst.removeNode(17);
        bst.removeNode(13);
        bst.removeNode(14);
        bst.removeNode(11);
        bst.removeNode(10);
        bst.removeNode(7);
        bst.removeNode(8);
        bst.removeNode(3);

        bst.print();

        Node root = new Node(3);
        root.setLeft(new Node(2));
        root.getLeft().setLeft(new Node(1));
        //root.getLeft().getLeft().setLeft(new Node(0));

        BinarySearchTree bst2 = new BinarySearchTree(root);
        bst2.print();
        rotateRight(root.getLeft());
        BinarySearchTree bst3 = new BinarySearchTree(root);
        bst3.print();
        //bst2.print();

        /*          [4]
        *          /   \
        *         /     \
        *        /       \
        *       /         \
        *     [2]         [8]
        *    /   \       /   \
        * [1]    [3]  [6]    [9]
        *
        * */
    }

    public static void rotateLeft(Node root) {
        if (root == null) {
            System.out.println("Root was null in rotateRight");
            return;
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

        BinarySearchTree bst2 = new BinarySearchTree(root.getParent());
        bst2.print();

        // TODO: Dont forget to change the root if the root is rotated!
        // this.root = (rootParent == null) ? null : root.getParent();
    }

    // ?
    public static void rotateRight(Node root) {
        if (root == null) {
            System.out.println("Root was null in rotateRight");
            return;
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

        BinarySearchTree bst2 = new BinarySearchTree(root.getParent());
        bst2.print();

        // TODO: Dont forget to change the root if the root is rotated!
        // this.root = (rootParent == null) ? null : root.getParent();
    }
}