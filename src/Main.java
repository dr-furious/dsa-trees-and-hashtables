import dataStructures.tree.AVLTree;
import dataStructures.tree.BinarySearchTree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();
        int[] test1 = new int[1_00];
        for (int i = 0; i < test1.length; i++) {
            test1[i] = Tester.getRandomInt(0, 2_000_000_000);
        }

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        long start = System.currentTimeMillis();
        for (int i = 0; i < test1.length; i++) {
            avlTree.appendNode(test1[i]);
            //binarySearchTree.appendNode(test1[i]);
        }
        long stop = System.currentTimeMillis();

        binarySearchTree.print();
        avlTree.print();
        System.out.println(Arrays.toString(test1));

        System.out.println(stop-start + " milliseconds -> " + toSeconds(stop-start) + " seconds");
    }

    public static double toSeconds(long milliseconds) {
        return milliseconds/1000.0;
    }
}