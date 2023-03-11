import dataStructures.tree.AVLTree;
import dataStructures.tree.BinarySearchTree;
import dataStructures.tree.Node;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();
        int[] test1 = new int[10_000_000];
        for (int i = 0; i < test1.length; i++) {
            test1[i] = Tester.getRandomInt(0, Integer.MAX_VALUE); // Integer.MAX_VALUE = 2_147_483_647
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < test1.length; i++) {
            avlTree.appendNode(test1[i]);
        }
        long stop = System.currentTimeMillis();

        avlTree.print();
        System.out.println(Arrays.toString(test1));

        System.out.println(stop-start + " milliseconds -> " + toSeconds(stop-start) + " seconds");

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < test1.length; i++) {
            avlTree.removeNode(test1[i]);
        }
        long stop1 = System.currentTimeMillis();

        avlTree.print();
        System.out.println("Deletion: " + (stop1-start1) + " milliseconds -> " + toSeconds(stop1-start1) + " seconds");
    }

    public static double toSeconds(long milliseconds) {
        return milliseconds/1000.0;
    }
}