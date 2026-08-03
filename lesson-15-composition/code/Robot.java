public class Robot {

    // A Robot HAS-A left motor, a right motor, and a lift. Its fields are objects.
    private Motor leftMotor;
    private Motor rightMotor;
    private Lift lift;

    public Robot() {
        this.leftMotor = new Motor("left_motor");
        this.rightMotor = new Motor("right_motor");
        this.lift = new Lift("arm_lift");
    }

    // The Robot doesn't spin motors itself — it asks its motors to.
    public void driveForward(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public void raiseLift(int amount) {
        lift.raise(amount);
    }

    public void printStatus() {
        leftMotor.printStatus();
        rightMotor.printStatus();
        lift.printStatus();
    }
}
