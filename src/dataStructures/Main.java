package dataStructures;

import dataStructures.tree.AVLTree;
import dataStructures.tree.SplayTree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        SplayTree tree = new SplayTree();
        // AVLTree tree = new AVLTree();

        int[] test = new int[10];
        int[] testSorted = new int[10_000_000];

        for (int i = 0; i < test.length; i++) {
            test[i] = Tester.getRandomInt(0, 100); // Integer.MAX_VALUE = 2_147_483_647
            testSorted[i] = Tester.getRandomInt(0, Integer.MAX_VALUE); // Integer.MAX_VALUE = 2_147_483_647
        }
        Arrays.sort(testSorted);

        long start = System.currentTimeMillis();
        for (int i = 0; i < test.length; i++) {
            tree.appendNode(test[i]);
            //tree.print();
            //System.out.println("==============");
        }
        long stop = System.currentTimeMillis();

        tree.print();
        tree.findNode(test[0]);
        System.out.println("==========");
        tree.print();
        tree.findNode(test[5]);
        System.out.println("==========");
        tree.print();
        tree.findNode(test[test.length-1]);
        System.out.println("==========");
        tree.print();
        System.out.println(Arrays.toString(test));

        System.out.println(stop-start + " milliseconds -> " + toSeconds(stop-start) + " seconds");

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < test.length; i++) {
            //tree.removeNode(test[i]);
        }
        long stop1 = System.currentTimeMillis();

        //tree.print();
        //System.out.println("Deletion: " + (stop1-start1) + " milliseconds -> " + toSeconds(stop1-start1) + " seconds");
    }

    public static double toSeconds(long milliseconds) {
        return milliseconds/1000.0;
    }
}