public class BooleanBugHunt {

    public static void main(String[] args) {
        int temperature = 60;
        boolean motorRunning = true;

        if (temperature = 50) {
            System.out.println("Temperature is exactly 50");
        }

        if (temperature > 10 || temperature < 50) {
            System.out.println("Temperature is in the safe range (10 to 50)");
        }

        System.out.println("Safe to run: " + isSafe(motorRunning));
    }

    public static boolean isSafe(boolean running) {
        if (running) {
            return true;
        }
    }
}
