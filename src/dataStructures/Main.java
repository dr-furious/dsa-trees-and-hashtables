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
        Tester tester = new Tester();

        /*
        * Here is an example of testing
        * We test each data structure with all operations: CREATE, DELETE, FIND_ALL, ADD, REMOVE, and FIND
        * Testing function parameters:
        *   data structure - AVLTree, SplayTree, ChainedHashTable or LinearProbingHashTable
        *   startSize (optional) - size (number of Nodes) from which you want DS to be tested
        *   span - how many Nodes will be added after each test iteration
        *   numberOfTests - the total number of tests performed
        *   printFlag - if you want to see some additional print statements
        *
        * Another function pushes the data from tester fields to the .csv file. Flush flag set to true clears these fields
        * after the data is transferred
        * */

        System.out.println("Testing AVL started...");
        tester.testAllOperationsInBinarySearchTree(new AVLTree(), 100_000,10, 10, false);
        System.out.println("Writing AVL data...");
        tester.dataToCSV("AVL_tree_final", true);
        System.out.println("AVL done");

        System.out.println("Testing Splay started...");
        tester.testAllOperationsInBinarySearchTree(new SplayTree(), 100_000,10, 10, false);
        System.out.println("Writing Splay data...");
        tester.dataToCSV("Splay_tree_final", true);
        System.out.println("Splay done");

        System.out.println("Testing LP started...");
        tester.testAllOperationsInHashTable(new LinearProbingHashTable(), 100_000,10, 10, false);
        System.out.println("Writing LP data...");
        tester.dataToCSV("Linear_Probing_final", true);
        System.out.println("LP done");

        System.out.println("Testing CH started...");
        tester.testAllOperationsInHashTable(new ChainedHashTable(), 100_000,10, 10, false);
        System.out.println("Writing CH data...");
        tester.dataToCSV("Chaining_final", true);
        System.out.println("CH done");
        System.out.println("Testing done");

    }

    public static double toSeconds(long milliseconds) {
        return milliseconds/1000.0;
    }



}