import java.util.Scanner;

public class DistanceZone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter distance to the wall in cm: ");
        double distance = scanner.nextDouble();

        // Java checks each test top to bottom and runs the first one that is true.
        if (distance < 10.0) {
            System.out.println("Too close - back up!");
        } else if (distance < 30.0) {
            System.out.println("Good distance - hold position.");
        } else {
            System.out.println("Too far - move closer.");
        }
    }
}
