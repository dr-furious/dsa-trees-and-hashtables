package dataStructures;

import dataStructures.Test.Operations;
import dataStructures.Test.Tester;
import dataStructures.hashTable.ChainedHashTable;
import dataStructures.hashTable.HashTable;
import dataStructures.hashTable.LinearProbingHashTable;
import dataStructures.hashTable.Node;
import dataStructures.tree.AVLTree;
import dataStructures.tree.BinarySearchTree;
import dataStructures.tree.SplayTree;

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

        /*
        HashTable hashTable = new LinearProbingHashTable();
        // HashTable hashTable = new ChainedHashTable();
        String[] stringTest = new String[1_000_000];
        for (int i = 0; i < stringTest.length; i++) {
            stringTest[i] = Tester.getRandomString(10, 20, 97, 123);
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

         */
        Tester tester = new Tester();

        // 5, 7
        int testNumber = 3;

        // 6, 8
        int endTestNum = 4;

        //
        int startSize = 0;

        for (int i = testNumber; i<=endTestNum;i++) {
            System.out.println("Testing AVL started...");
            tester.testAllOperationsInBinarySearchTree(new AVLTree(), 10_000,100, 100, true);
            System.out.println("Writing AVL data...");
            tester.dataToCSV("AVL_tree_" + testNumber, true);
            System.out.println("AVL done");

            System.out.println("Testing Splay started...");
            tester.testAllOperationsInBinarySearchTree(new SplayTree(), 10_000,100, 100, true);
            System.out.println("Writing Splay data...");
            tester.dataToCSV("Splay _tree_" + testNumber, true);
            System.out.println("Splay done");

            System.out.println("Testing LP started...");
            tester.testAllOperationsInHashTable(new LinearProbingHashTable(), 10_000,100, 100, true);
            System.out.println("Writing LP data...");
            tester.dataToCSV("Linear_Probing_" + testNumber, true);
            System.out.println("LP done");

            System.out.println("Testing CH started...");
            tester.testAllOperationsInHashTable(new ChainedHashTable(),10_000,100, 100, true);
            System.out.println("Writing CH data...");
            tester.dataToCSV("Chaining_" + testNumber, true);
            System.out.println("CH done");
            System.out.println("Testing done");
            testNumber++;
        }
/*
        tester.testOperationInHashTable(new LinearProbingHashTable(), Operations.CREATE, 10_000, 10, 10, true);
        tester.testOperationInHashTable(new LinearProbingHashTable(), Operations.DELETE, 10_000, 10, 10, true);
        tester.testOperationInHashTable(new LinearProbingHashTable(), Operations.ADD, 10_000, 10, 10, true);
        tester.testOperationInHashTable(new LinearProbingHashTable(), Operations.REMOVE, 10_000, 10, 10, true);
        tester.testOperationInHashTable(new LinearProbingHashTable(), Operations.FIND, 10_000, 10, 10, true);
*/

        /*HashTable hashTable = new ChainedHashTable();
        String[] stringArr = new String[10_000];
        SplayTree binarySearchTree = new SplayTree();
        int[] intArr = new int[10_000];

        for (int i = 0; i<stringArr.length; i++) {
            stringArr[i] = Tester.getRandomString(10, 20, 97, 123);
            hashTable.add(stringArr[i]);

            intArr[i] = Tester.getRandomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            binarySearchTree.appendNode(intArr[i]);
        }

        long start;
        long stop;

        start = System.nanoTime();
        Node node = hashTable.find(stringArr[Tester.getRandomInt(0, stringArr.length-1)]);
        stop = System.nanoTime();
        System.out.println("Hashtable find: " + (stop-start));
        node.toString();

        System.out.println();

        start = System.nanoTime();
        dataStructures.tree.Node treeNode = binarySearchTree.findNode(intArr[Tester.getRandomInt(0, intArr.length-1)]);
        stop = System.nanoTime();
        System.out.println("Binary Search Tree find: " + (stop-start));
        treeNode.toString();*/
    }

    public static double toSeconds(long milliseconds) {
        return milliseconds/1000.0;
    }



}