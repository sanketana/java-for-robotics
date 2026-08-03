import robotparts.Motor;

public class RobotMain {

    public static void main(String[] args) {
        Motor left = new Motor("left_motor");
        left.setPower(0.5);
        left.printStatus();
    }
}
