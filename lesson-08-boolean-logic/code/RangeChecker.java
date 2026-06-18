public class RangeChecker {

    public static void main(String[] args) {
        int armPosition = 450;

        // The method hands back true or false; we use it straight in the if.
        if (isInRange(armPosition, 0, 1000)) {
            System.out.println("Arm position " + armPosition + " is safe.");
        } else {
            System.out.println("Arm position " + armPosition + " is OUT OF RANGE!");
        }

        System.out.println("Is 1500 in range? " + isInRange(1500, 0, 1000));
    }

    // Returns true when value sits between low and high (inclusive).
    public static boolean isInRange(int value, int low, int high) {
        return value >= low && value <= high;
    }
}
