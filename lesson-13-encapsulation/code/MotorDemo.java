public class MotorDemo {

    public static void main(String[] args) {
        Motor left = new Motor("left_motor");

        left.setPower(0.5);
        left.printStatus();

        // Try to set an illegal power. The guard refuses it and clamps to 1.0.
        left.setPower(3.0);
        left.printStatus();

        System.out.println("Reading power through the doorway: " + left.getPower());
    }
}
