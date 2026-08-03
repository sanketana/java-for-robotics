public class LiftDemo {

    public static void main(String[] args) {
        Lift arm = new Lift("arm_lift");

        arm.raise(100);
        arm.raise(50);
        arm.printStatus();

        arm.rename("main_lift");
        arm.printStatus();
    }
}
