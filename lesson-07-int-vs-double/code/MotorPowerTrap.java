public class MotorPowerTrap {

    public static void main(String[] args) {
        // The classic bug: we wanted half power, but int maths gives 0.
        int wrongPower = 1 / 2;
        System.out.println("Wrong power (int): " + wrongPower);

        // The fix: use a decimal so the maths keeps the fraction.
        double rightPower = 1.0 / 2;
        System.out.println("Right power (double): " + rightPower);

        // A returning method can hide the very same trap inside it.
        double avg = averageOf(80, 81);
        System.out.println("Average reading: " + avg);
    }

    // Returns a double, AND divides by 2.0 (not 2) so the half is kept.
    public static double averageOf(int a, int b) {
        return (a + b) / 2.0;
    }
}
