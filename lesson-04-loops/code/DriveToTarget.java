import java.util.Scanner;

public class DriveToTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter target distance in cm: ");
        double target = scanner.nextDouble();

        double position = 0.0;
        double step = 10.0;   // cm moved each tick

        // We don't know how many steps ahead of time, so we loop until we arrive.
        while (position < target) {
            position = position + step;
            System.out.println("Position: " + position + " cm");
        }

        System.out.println("Target reached!");
    }
}
