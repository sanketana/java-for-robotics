public class CompositionBugHunt {

    private Motor driveMotor;
    private Lift lift;

    public CompositionBugHunt() {
        this.driveMotor = new Motor("drive_motor");
    }

    public void drive(double power) {
        driveMotor.setPower(power);
    }

    public void raiseLift(int amount) {
        lift.raise(amount);
    }

    public void printStatus() {
        driveMotor.printStatus();
        lift.printStatus();
    }

    public static void main(String[] args) {
        CompositionBugHunt robot = new CompositionBugHunt();

        robot.drive(0.7);
        robot.raiseLift(300);

        robot.printStatus();
    }
}
