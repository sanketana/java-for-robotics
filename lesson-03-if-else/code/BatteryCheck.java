import java.util.Scanner;

public class BatteryCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter battery voltage: ");
        double voltage = scanner.nextDouble();

        // The condition in the brackets is either true or false.
        if (voltage < 11.0) {
            System.out.println("Battery low - charge before the match.");
        } else {
            System.out.println("Battery good - ready to go.");
        }
    }
}
