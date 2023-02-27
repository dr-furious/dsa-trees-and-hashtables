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
        Node rootParent = Node.clone(root.getParent());
        Node temp = Node.clone(root.getRight().getLeft());
        root.getRight().setLeft(root);
        root.setRight(temp);
        root.getParent().setParent(rootParent);

        // TODO: Dont forget to change the root if the root is rotated!
    }

    // ?
    public static void rotateRight(Node root) {
        Node rootParent = Node.clone(root.getParent());
        Node temp = Node.clone(root.getLeft().getRight());
        root.getLeft().setRight(root);
        root.setLeft(temp);
        root.getParent().setParent(rootParent);

        // TODO: Dont forget to change the root if the root is rotated!
    }
}