package dataStructures.Test;

import dataStructures.hashTable.HashTable;
import dataStructures.tree.BinarySearchTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tester {

    private List<Integer> sizeList;
    private List<Double> creationList;
    private List<Double> deletionList;
    private List<Double> additionList;
    private List<Double> removalList;
    private List<Double> findList;

    public Tester() {
        this.sizeList = new ArrayList<>();
        this.creationList = new ArrayList<>();
        this.deletionList = new ArrayList<>();
        this.additionList = new ArrayList<>();
        this.removalList = new ArrayList<>();
        this.findList = new ArrayList<>();
    }
    public void testOperationInBinarySearchTree(BinarySearchTree tree, Operations operation, int size, int span, int numberOfTests, int averageFrom, boolean printFlag) {
        for (int i = 0; i < numberOfTests; i++) {
            int[] test = new int[size];
            double sum = 0;
            double count = 0;
            for (int j = 0; j < averageFrom; j++) {
                // Filling the test array
                for (int k = 0; k < test.length; k++) {
                    test[k] = getRandomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                }
                long start;
                long stop;
                switch (operation) {
                    case CREATE -> {
                        // Measuring creation
                        start = System.nanoTime();
                        for (int n : test) {
                            tree.appendNode(n);
                        }
                        stop = System.nanoTime();

                        for (int n : test) {
                            tree.removeNode(n);
                        }
                    }
                    case DELETE -> {
                        for (int n : test) {
                            tree.appendNode(n);
                        }

                        // Measuring deletion
                        start = System.nanoTime();
                        for (int n : test) {
                            tree.removeNode(n);
                        }
                        stop = System.nanoTime();
                    }
                    case ADD -> {
                        if (j==0) {
                            for (int s : test) {
                                tree.appendNode(s);
                            }
                        }
                        int subject = getRandomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

                        // Measuring addition operation
                        start = System.nanoTime();
                        tree.appendNode(subject);
                        stop = System.nanoTime();

                        tree.removeNode(subject);
                        if (j == averageFrom-1) {
                            for (int n : test) {
                                tree.removeNode(n);
                            }
                        }
                    }
                    case REMOVE -> {
                        if (j==0) {
                            for (int s : test) {
                                tree.appendNode(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring deletion operation
                        start = System.nanoTime();
                        tree.removeNode(test[index]);
                        stop = System.nanoTime();

                        tree.appendNode(test[index]);
                        if (j== averageFrom-1){
                            for (int n : test) {
                                tree.removeNode(n);
                            }
                        }
                    }
                    case FIND -> {
                        if (j==0) {
                            for (int s : test) {
                                tree.appendNode(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring search operation
                        start = System.nanoTime();
                        tree.findNode(test[index]);
                        stop = System.nanoTime();

                        if (j== averageFrom-1){
                            for (int n : test) {
                                tree.removeNode(n);
                            }
                        }
                    }
                    default -> {return;}
                }
                sum += (stop-start);
                count++;
            }
            writeToFields(operation, size, sum, count);
            if (printFlag) {
                System.out.println("Tested " +
                        operation + " operation for " +
                        size + " items, with average time of " +
                        sum/count + " nanoseconds");
            }
            size += span;
        }
    }
    public void testAllOperationsInBinarySearchTree(BinarySearchTree tree, int size,int span, int numberOfTests, int averageFrom, boolean printFlag) {
        testOperationInBinarySearchTree(tree, Operations.CREATE,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.DELETE,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.ADD,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.REMOVE,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.FIND,size, span, numberOfTests, averageFrom, printFlag);

        printFields(printFlag);
    }
    public void testOperationInHashTable(HashTable table, Operations operation,int size, int span, int numberOfTests, int averageFrom, boolean printFlag) {
        for (int i = 0; i < numberOfTests; i++) {
            String[] test = new String[size];
            double sum = 0;
            double count = 0;
            for (int j = 0; j < averageFrom; j++) {
                // Filling the test array
                for (int k = 0; k < test.length; k++) {
                    test[k] = getRandomString(10, 20, 97, 123);
                }
                long start;
                long stop;
                switch (operation) {
                    case CREATE -> {
                        // Measuring creation
                        start = System.nanoTime();
                        for (String s : test) {
                            table.add(s);
                        }
                        stop = System.nanoTime();

                        for (String s : test) {
                            table.remove(s);
                        }
                    }
                    case DELETE -> {
                        for (String value : test) {
                            table.add(value);
                        }

                        // Measuring deletion
                        start = System.nanoTime();
                        for (String s : test) {
                            table.remove(s);
                        }
                        stop = System.nanoTime();
                    }
                    case ADD -> {
                        if (j==0) {
                            for (String s : test) {
                                table.add(s);
                            }
                        }
                        String subject = getRandomString(10, 20, 97, 123);

                        // Measuring addition operation
                        start = System.nanoTime();
                        table.add(subject);
                        stop = System.nanoTime();

                        table.remove(subject);
                        if (j== averageFrom-1){
                            for (String s : test) {
                                table.remove(s);
                            }
                        }
                    }
                    case REMOVE -> {
                        if (j==0) {
                            for (String s : test) {
                                table.add(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring deletion operation
                        start = System.nanoTime();
                        table.remove(test[index]);
                        stop = System.nanoTime();

                        table.add(test[index]);
                        if (j== averageFrom-1){
                            for (String s : test) {
                                table.remove(s);
                            }
                        }
                    }
                    case FIND -> {
                        if (j==0) {
                            for (String s : test) {
                                table.add(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring search operation
                        start = System.nanoTime();
                        table.find(test[index]);
                        stop = System.nanoTime();

                        if (j== averageFrom-1){
                            for (String s : test) {
                                table.remove(s);
                            }
                        }
                    }
                    default -> {return;}
                }
                sum += (stop-start);
                count++;
            }
            writeToFields(operation, size, sum, count);
            if (printFlag) {
                System.out.println("Tested " +
                        operation + " operation for " +
                        size + " items, with average time of " +
                        sum/count + " nanoseconds");
            }
            size += span;
        }
    }
    public void testAllOperationsInHashTable(HashTable table,int size, int span, int numberOfTests, int averageFrom, boolean printFlag) {
        testOperationInHashTable(table, Operations.CREATE,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.DELETE,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.ADD,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.REMOVE,size, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.FIND,size, span, numberOfTests, averageFrom, printFlag);

        printFields(printFlag);
    }
    // ===========
    public void testOperationInBinarySearchTree(BinarySearchTree tree, Operations operation, int span, int numberOfTests, int averageFrom, boolean printFlag) {
        int size = span;
        for (int i = 0; i < numberOfTests; i++) {
            int[] test = new int[size];
            double sum = 0;
            double count = 0;
            // Filling the test array
            for (int k = 0; k < test.length; k++) {
                test[k] = getRandomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            for (int j = 0; j < averageFrom; j++) {
                long start;
                long stop;
                switch (operation) {
                    case CREATE -> {
                        // Measuring creation
                        start = System.nanoTime();
                        for (int n : test) {
                            tree.appendNode(n);
                        }
                        stop = System.nanoTime();

                        for (int n : test) {
                            tree.removeNode(n);
                        }
                    }
                    case DELETE -> {
                        for (int n : test) {
                            tree.appendNode(n);
                        }

                        // Measuring deletion
                        start = System.nanoTime();
                        for (int n : test) {
                            tree.removeNode(n);
                        }
                        stop = System.nanoTime();
                    }
                    case ADD -> {
                        if (j==0) {
                            for (int s : test) {
                                tree.appendNode(s);
                            }
                        }
                        int subject = getRandomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

                        // Measuring addition operation
                        start = System.nanoTime();
                        tree.appendNode(subject);
                        stop = System.nanoTime();

                        tree.removeNode(subject);
                        if (j == averageFrom-1) {
                            for (int n : test) {
                                tree.removeNode(n);
                            }
                        }
                    }
                    case REMOVE -> {
                        if (j==0) {
                            for (int s : test) {
                                tree.appendNode(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring deletion operation
                        start = System.nanoTime();
                        tree.removeNode(test[index]);
                        stop = System.nanoTime();

                        tree.appendNode(test[index]);
                        if (j== averageFrom-1){
                            for (int n : test) {
                                tree.removeNode(n);
                            }
                        }
                    }
                    case FIND -> {
                        if (j==0) {
                            for (int s : test) {
                                tree.appendNode(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring search operation
                        start = System.nanoTime();
                        tree.findNode(test[index]);
                        stop = System.nanoTime();

                        if (j== averageFrom-1){
                            for (int n : test) {
                                tree.removeNode(n);
                            }
                        }
                    }
                    default -> {return;}
                }
                sum += (stop-start);
                count++;
            }
            writeToFields(operation, size, sum, count);
            if (printFlag) {
                System.out.println("Tested " +
                        operation + " operation for " +
                        size + " items, with average time of " +
                        sum/count + " nanoseconds");
            }
            size += span;
        }
    }
    public void testAllOperationsInBinarySearchTree(BinarySearchTree tree, int span, int numberOfTests, int averageFrom, boolean printFlag) {
        testOperationInBinarySearchTree(tree, Operations.CREATE, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.DELETE, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.ADD, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.REMOVE, span, numberOfTests, averageFrom, printFlag);
        testOperationInBinarySearchTree(tree, Operations.FIND, span, numberOfTests, averageFrom, printFlag);

        printFields(printFlag);
    }
    public void testOperationInHashTable(HashTable table, Operations operation, int span, int numberOfTests, int averageFrom, boolean printFlag) {
        int size = span;
        for (int i = 0; i < numberOfTests; i++) {
            String[] test = new String[size];
            double sum = 0;
            double count = 0;
            // Filling the test array
            for (int k = 0; k < test.length; k++) {
                test[k] = getRandomString(10, 20, 97, 123);
            }
            for (int j = 0; j < averageFrom; j++) {
                long start;
                long stop;
                switch (operation) {
                    case CREATE -> {
                        // Measuring creation
                        start = System.nanoTime();
                        for (int k = 0; k < test.length; k++) {
                            table.add(test[k]);
                        }
                        stop = System.nanoTime();

                        for (String s : test) {
                            table.remove(s);
                        }
                    }
                    case DELETE -> {
                        for (String value : test) {
                            table.add(value);
                        }

                        // Measuring deletion
                        start = System.nanoTime();
                        for (int k = 0; k < test.length; k++) {
                            table.remove(test[k]);
                        }
                        stop = System.nanoTime();
                    }
                    case ADD -> {
                        if (j==0) {
                            for (String s : test) {
                                table.add(s);
                            }
                        }
                        String subject = getRandomString(10, 20, 97, 123);

                        // Measuring addition operation
                        start = System.nanoTime();
                        table.add(subject);
                        stop = System.nanoTime();

                        table.remove(subject);
                        if (j== averageFrom-1){
                            for (String s : test) {
                                table.remove(s);
                            }
                        }
                    }
                    case REMOVE -> {
                        if (j==0) {
                            for (String s : test) {
                                table.add(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring deletion operation
                        start = System.nanoTime();
                        table.remove(test[index]);
                        stop = System.nanoTime();

                        table.add(test[index]);
                        if (j== averageFrom-1){
                            for (String s : test) {
                                table.remove(s);
                            }
                        }
                    }
                    case FIND -> {
                        if (j==0) {
                            for (String s : test) {
                                table.add(s);
                            }
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring search operation
                        start = System.nanoTime();
                        table.find(test[index]);
                        stop = System.nanoTime();

                        if (j== averageFrom-1){
                            for (String s : test) {
                                table.remove(s);
                            }
                        }
                    }
                    default -> {return;}
                }
                sum += (stop-start);
                count++;
            }
           writeToFields(operation, size, sum, count);
            if (printFlag) {
                System.out.println("Tested " +
                        operation + " operation for " +
                        size + " items, with average time of " +
                        sum/count + " nanoseconds");
            }
            size += span;
        }
    }
    public void testAllOperationsInHashTable(HashTable table, int span, int numberOfTests, int averageFrom, boolean printFlag) {
        testOperationInHashTable(table, Operations.CREATE, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.DELETE, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.ADD, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.REMOVE, span, numberOfTests, averageFrom, printFlag);
        testOperationInHashTable(table, Operations.FIND, span, numberOfTests, averageFrom, printFlag);

        printFields(printFlag);
    }
    public void dataToCSV(String name, boolean flush) {
        File file = new File("./tests/" + name + ".csv");
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write("Sample Size;CREATE;DELETE;ADD;REMOVE;FIND\n");
            for (int i = 0; i < sizeList.size(); i++) {
                String dataChunk = sizeList.get(i) +
                        ";" + creationList.get(i) +
                        ";" + deletionList.get(i) +
                        ";" + additionList.get(i) +
                        ";" + removalList.get(i) +
                        ";" + findList.get(i) +
                        "\n";
                bw.write(dataChunk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flush) {
                flushData();
            }
        }

    }
    public void flushData() {
        this.sizeList.clear();
        this.creationList.clear();
        this.deletionList.clear();
        this.additionList.clear();
        this.removalList.clear();
        this.findList.clear();
    }
    private void printFields(boolean printFlag) {
        if (printFlag) {
            System.out.println(this.sizeList.toString());
            System.out.println(this.creationList.toString());
            System.out.println(this.deletionList.toString());
            System.out.println(this.additionList.toString());
            System.out.println(this.removalList.toString());
            System.out.println(this.findList.toString());
        }
    }
    private void writeToFields(Operations operation, int size,double sum, double count) {
        if (!this.sizeList.contains(size)) {
            this.sizeList.add(size);
        }
        switch (operation) {
            case CREATE -> {
                this.creationList.add(sum/count);
            }
            case DELETE -> {
                this.deletionList.add(sum/count);
            }
            case ADD -> {
                this.additionList.add(sum/count);
            }
            case REMOVE -> {
                this.removalList.add(sum/count);
            }
            case FIND -> {
                this.findList.add(sum/count);
            }
            default -> {return;}
        }
    }

    // ========== STATIC METHODS ==========
    public static int getRandomInt(int min, int max) {
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        return ( (int)(Math.random() * (max-min)) + min);
    }
    public static String getRandomString(int minLength, int maxLength, int asciiStart, int asciiEnd) {
        int length = getRandomInt(minLength, maxLength+1);
        String random = "";
        for (int i = 0; i < length; i++) {
            int intChar = Tester.getRandomInt(asciiStart, asciiEnd); // generate ascii value of one character
            random += Character.toString((char) intChar); // convert character from ascii code to string
        }

        return random;
    }


}
