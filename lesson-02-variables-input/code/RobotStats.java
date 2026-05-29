public class RobotStats {
    public static void main(String[] args) {
        // Each variable says its type first, then its name, then its value.
        String robotName = "Sparky";
        int motorCount = 4;
        double wheelDiameter = 9.0;
        boolean isCompetitionReady = false;

        // Variables are for computing, not just storing.
        double wheelCircumference = 3.14159 * wheelDiameter;

        System.out.println("Robot name: " + robotName);
        System.out.println("Number of motors: " + motorCount);
        System.out.println("Wheel diameter: " + wheelDiameter + " cm");
        System.out.println("Wheel circumference: " + wheelCircumference + " cm");
        System.out.println("Competition ready: " + isCompetitionReady);
    }
}
