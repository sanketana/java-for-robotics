public class PredictLoops {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Lap " + i);
        }

        int count = 0;
        while (count < 2) {
            System.out.println("Charging...");
            count++;
        }
        System.out.println("Done");
    }
}
