import dataStructures.tree.AVLTree;
import dataStructures.tree.Node;

public class Main {
    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();

        avlTree.appendNode(3);
        avlTree.appendNode(1);
        avlTree.appendNode(2);
        avlTree.appendNode(6);
        avlTree.appendNode(5);
        avlTree.appendNode(7);
        avlTree.appendNode(20);
        avlTree.appendNode(25);
        avlTree.removeNode(20);
        avlTree.removeNode(5);
        avlTree.appendNode(0);
        avlTree.appendNode(-5);
        avlTree.appendNode(10);
        avlTree.removeNode(6);

        avlTree.print();


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