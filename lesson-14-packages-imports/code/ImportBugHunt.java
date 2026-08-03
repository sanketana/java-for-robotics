public class ImportBugHunt {

    public static void main(String[] args) {
        Motor arm = new Motor("arm_motor");
        arm.setPower(0.8);
        arm.printStatus();
    }
}
