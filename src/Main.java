import dataStructures.tree.BinarySearchTree;
import dataStructures.tree.Node;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {


        Node root = new Node(10);
        BinarySearchTree bst = new BinarySearchTree(root);

        root.setLeft(new Node(7));
        root.setRight(new Node(15));

        root.getLeft().setLeft(new Node(3));
        root.getLeft().setRight(new Node(8));

        root.getRight().setLeft(new Node(13));
        root.getRight().setRight(new Node(19));

        root.getRight().getLeft().setLeft(new Node(11));
        root.getRight().getLeft().setRight(new Node(14));

        root.getRight().getRight().setLeft(new Node(17));
        root.getRight().getRight().setRight(new Node(20));

        bst.print();
        System.out.println();

        for (int i = 1; i < 10; i++) {
            bst.appendNode(i*5);
        }

        bst.print();

        bst.appendNode( 99);

        for (int i = 0; i<100; i++) {
            Node wanted = bst.findNode(i);
            if (wanted != null) {
                wanted.toString();
            } else {
                System.out.println("null");
            }
        }

        System.out.println("\n============");
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.setLeft(node2);
        node1.setRight(node3);
        node1.toString();
        node2.toString();
        node3.toString();

        System.out.println();
        rotateLeft(node1);

        node1.toString();
        node2.toString();
        node3.toString();

        System.out.println();
        rotateRight(node3);

        node1.toString();
        node2.toString();
        node3.toString();

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
    }

    // ?
    public static void rotateRight(Node root) {
        Node rootParent = Node.clone(root.getParent());
        Node temp = Node.clone(root.getLeft().getRight());
        root.getLeft().setRight(root);
        root.setLeft(temp);
        root.getParent().setParent(rootParent);
    }
}