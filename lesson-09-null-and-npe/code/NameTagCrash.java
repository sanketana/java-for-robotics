public class NameTagCrash {

    public static void main(String[] args) {
        // We never gave robotName a real value, so it points at nothing.
        String robotName = null;

        System.out.println("Starting up...");

        // This line crashes: you can't ask a "nothing" for its length.
        System.out.println("Robot name has " + robotName.length() + " letters.");

        System.out.println("This line never runs.");
    }
}
