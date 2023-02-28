import dataStructures.tree.AVLTree;
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
        root.getLeft().getLeft().setLeft(new Node(0));

        AVLTree bst2 = new AVLTree(root);
        bst2.print();
        bst2.rotateRight(root.getLeft());
        BinarySearchTree bst3 = new BinarySearchTree(root);
        bst3.print();
        bst2.rotateRight(root.getLeft());
        bst3.print();
        bst2.rotateLeft(root.getLeft().getRight());
        bst3.print();

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


}