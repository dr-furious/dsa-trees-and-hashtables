package dataStructures.Test;

import dataStructures.hashTable.HashTable;

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
    public void testOperationInHashTable(HashTable table, Operations operation, int span, int numberOfTests, int averageFrom) {
        int size = span;
        boolean writeSize = true;
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
                        for (String s : test) {
                            table.add(s);
                        }
                        String subject = getRandomString(10, 20, 97, 123);

                        // Measuring addition operation
                        start = System.nanoTime();
                        table.add(subject);
                        stop = System.nanoTime();

                        table.remove(subject);
                        for (String s : test) {
                            table.remove(s);
                        }
                    }
                    case REMOVE -> {
                        for (String s : test) {
                            table.add(s);
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring deletion operation
                        start = System.nanoTime();
                        table.remove(test[index]);
                        stop = System.nanoTime();

                        for (String s : test) {
                            table.remove(s);
                        }
                    }
                    case FIND -> {
                        for (String s : test) {
                            table.add(s);
                        }
                        int index = getRandomInt(0, test.length);

                        // Measuring search operation
                        start = System.nanoTime();
                        table.find(test[index]);
                        stop = System.nanoTime();
                        for (String s : test) {
                            table.remove(s);
                        }
                    }
                    default -> {return;}
                }
                sum += (stop-start);
                count++;
            }
            this.sizeList.add(size);
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
            System.out.println("Tested " +
                    operation + " operation for " +
                    size + " items, with average time of " +
                    sum/count + " nanoseconds");
            size += span;
        }

        System.out.println(this.sizeList.toString());
        System.out.println(this.creationList.toString());
        System.out.println(this.deletionList.toString());
        System.out.println(this.additionList.toString());
        System.out.println(this.removalList.toString());
        System.out.println(this.findList.toString());
    }

    /*public void dataToCSV(String fileName) {
        File file = new File(fileName + ".csv");
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write("Data Structure Size;Time to perform an operation\n");
            for (TestCase testCase : this.testCases) {
                String dataChunk = testCase.getItemsSize() + ";" + testCase.getTime() + "\n";
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
        }

    }*/

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
