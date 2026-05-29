import java.util.Scanner;

public class TripCalculator {
    public static void main(String[] args) {
        // These two lines "switch on the question machine". We unpack import and new in Tier 3.
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter distance in cm: ");
        double distance = scanner.nextDouble();

        System.out.print("Enter speed in cm per second: ");
        double speed = scanner.nextDouble();

        // time is computed, not typed in. That is the whole point of variables.
        double time = distance / speed;

        System.out.println("Travel time: " + time + " seconds");
    }
}
