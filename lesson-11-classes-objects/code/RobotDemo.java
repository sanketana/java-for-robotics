public class RobotDemo {

    public static void main(String[] args) {
        Robot titan = new Robot("Titan", 1.5);
        Robot rex = new Robot("Rex", 2.0);

        titan.addScore(30);
        rex.addScore(45);
        titan.addScore(10);

        titan.printStatus();
        rex.printStatus();
    }
}
