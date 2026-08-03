public class PredictThis {

    public static void main(String[] args) {
        Lift left = new Lift("left_lift");
        Lift right = new Lift("right_lift");

        left.raise(200);
        left.raise(100);
        right.raise(50);

        left.printStatus();
        right.printStatus();
    }
}
