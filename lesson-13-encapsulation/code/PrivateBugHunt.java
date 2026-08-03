public class PrivateBugHunt {

    public static void main(String[] args) {
        Motor arm = new Motor("arm_motor");

        arm.power = 0.7;
        System.out.println("Arm power: " + arm.power);

        arm.printStatus();
    }
}
