public class NumberBugHunt {

    public static void main(String[] args) {
        int power = 1.0 / 2;
        System.out.println("Power: " + power);

        double quarter = 1 / 4;
        System.out.println("Quarter: " + quarter);

        int avg = averageOf(80, 81);
        System.out.println("Average: " + avg);
    }

    public static double averageOf(int a, int b) {
        return (a + b) / 2.0;
    }
}
