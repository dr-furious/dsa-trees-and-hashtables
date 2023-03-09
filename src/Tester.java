public class Tester {

    public static int getRandomInt(int min, int max) {
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        return ( (int)(Math.random() * (max-min)) + min);
    }


}
