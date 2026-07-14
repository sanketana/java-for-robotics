public class PredictObjects {

    public static void main(String[] args) {
        Robot a = new Robot("Alpha", 1.2);
        Robot b = new Robot("Beta", 1.2);

        a.addScore(10);
        a.addScore(5);
        b.addScore(100);

        a.printStatus();
        b.printStatus();
    }
}
