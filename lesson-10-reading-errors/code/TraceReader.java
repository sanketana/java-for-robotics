public class TraceReader {

    public static void main(String[] args) {
        System.out.println("Starting up...");
        int avg = averageScore(50, 0);
        System.out.println("Average: " + avg);
    }

    public static int averageScore(int total, int matches) {
        return dividePoints(total, matches);
    }

    public static int dividePoints(int total, int matches) {
        return total / matches;
    }
}
