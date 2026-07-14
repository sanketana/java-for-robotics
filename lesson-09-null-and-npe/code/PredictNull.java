public class PredictNull {

    public static void main(String[] args) {
        String phase = "Autonomous";
        System.out.println("Phase: " + phase);
        System.out.println("Length: " + phase.length());

        String next = null;
        System.out.println("Next phase set? " + (next != null));
        System.out.println("Next length: " + next.length());
        System.out.println("Done");
    }
}
