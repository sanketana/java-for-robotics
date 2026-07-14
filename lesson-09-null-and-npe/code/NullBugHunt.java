public class NullBugHunt {

    public static void main(String[] args) {
        String driver = driverForRobot(2);
        System.out.println("Driver name has " + driver.length() + " letters.");

        String backup = null;
        System.out.println("Backup driver: " + backup);
        System.out.println("Backup in caps: " + backup.toUpperCase());
    }

    // Returns a driver's name, or null if no driver is assigned.
    public static String driverForRobot(int robotId) {
        if (robotId == 1) {
            return "Asha";
        }
        return null;
    }
}
