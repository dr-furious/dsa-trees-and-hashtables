package dataStructures;

public class Tester {

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
