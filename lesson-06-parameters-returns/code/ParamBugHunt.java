public class ParamBugHunt {

    public static void main(String[] args) {
        printDivider();
        int total = addUp(5, 7);
        System.out.println("Total: " + total);
        celebrate(2026);
    }

    public static void printDivider(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static int addUp(int a, int b) {
        System.out.println("Adding...");
    }

    public static void celebrate(String team) {
        System.out.println("Great job, " + team + "!");
    }
}
