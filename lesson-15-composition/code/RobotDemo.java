public class RobotDemo {

    public static void main(String[] args) {
        Robot robot = new Robot();

        robot.driveForward(0.6);
        robot.raiseLift(300);

        robot.printStatus();
    }
}
