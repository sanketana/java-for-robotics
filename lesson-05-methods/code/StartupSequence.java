public class StartupSequence {

    // main reads like a summary: do this, then this, then this.
    public static void main(String[] args) {
        printBanner();
        countdown();
        System.out.println("Robot ready!");
    }

    // Each method is a "custom block" - a named chunk of work.
    public static void printBanner() {
        System.out.println("=================");
        System.out.println("  SANKETANA BOT  ");
        System.out.println("=================");
    }

    public static void countdown() {
        for (int i = 5; i >= 1; i--) {
            System.out.println(i);
        }
        System.out.println("GO!");
    }
}
