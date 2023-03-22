package dataStructures;

import dataStructures.hashTable.ChainedHashTable;
import dataStructures.hashTable.HashTable;
import dataStructures.hashTable.LinearProbingHashTable;
import dataStructures.tree.AVLTree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*

        // SplayTree tree = new SplayTree();
        AVLTree tree = new AVLTree();

        int[] test = new int[1_000_000];
        int[] testSorted = new int[10_000_000];

        for (int i = 0; i < test.length; i++) {
            test[i] = Tester.getRandomInt(0, 1_000_000_000); // Integer.MAX_VALUE = 2_147_483_647
            testSorted[i] = Tester.getRandomInt(0, Integer.MAX_VALUE); // Integer.MAX_VALUE = 2_147_483_647
        }
        //Arrays.sort(testSorted);


        long start = System.currentTimeMillis();
        for (int i = 0; i < test.length; i++) {
            tree.appendNode(test[i]);
        }
        long stop = System.currentTimeMillis();

        tree.print();
        //System.out.println(Arrays.toString(test));

        System.out.println("Construction: " + (stop-start) + " milliseconds -> " + toSeconds(stop-start) + " seconds");
        System.out.println();

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < test.length; i++) {
            tree.removeNode(test[i]);
        }
        long stop1 = System.currentTimeMillis();

        tree.print();
        System.out.println("Deletion: " + (stop1-start1) + " milliseconds -> " + toSeconds(stop1-start1) + " seconds");

        */

        HashTable hashTable = new LinearProbingHashTable();
        // HashTable hashTable = new ChainedHashTable();
        String[] stringTest = new String[1_000_000];
        for (int i = 0; i < stringTest.length; i++) {
            stringTest[i] = Tester.getRandomString(2, 8, 97, 123);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < stringTest.length; i++) {
            hashTable.add(stringTest[i]);
        }
        long stop = System.currentTimeMillis();
        hashTable.print();
        System.out.println("Hashtable size: " + hashTable.getSize() + ", Hashtable noe: " + hashTable.getNumberOfElements());
        System.out.println("Construction: " + (stop-start) + " milliseconds -> " + toSeconds(stop-start) + " seconds");



        long startDelete = System.currentTimeMillis();
        System.out.println();
        for (int i = 0; i < stringTest.length; i++) {
            hashTable.remove(stringTest[i]);
        }
        long stopDelete = System.currentTimeMillis();
        hashTable.print();

        System.out.println("Hashtable size: " + hashTable.getSize() + ", Hashtable noe: " + hashTable.getNumberOfElements());
        System.out.println("Deletion: " + (stopDelete-startDelete) + " milliseconds -> " + toSeconds(stopDelete-startDelete) + " seconds");
        System.out.println();
    }

    public static double toSeconds(long milliseconds) {
        return milliseconds/1000.0;
    }



}