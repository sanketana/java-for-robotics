public class PredictComposition {

    public static void main(String[] args) {
        Robot alpha = new Robot();
        Robot beta = new Robot();

        alpha.driveForward(0.5);
        alpha.raiseLift(200);
        beta.raiseLift(50);

        System.out.println("Alpha:");
        alpha.printStatus();

        System.out.println("Beta:");
        beta.printStatus();
    }
}
