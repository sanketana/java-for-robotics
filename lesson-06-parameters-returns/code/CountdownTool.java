public class CountdownTool {

    public static void main(String[] args) {
        // One method, used with different inputs — no copy-pasting.
        countdownFrom(5);
        countdownFrom(3);

        printDivider(10);
        printDivider(20);
    }

    // The (int start) is the input slot: whatever number you pass in
    // becomes "start" inside the method.
    public static void countdownFrom(int start) {
        for (int i = start; i >= 1; i--) {
            System.out.println(i);
        }
        System.out.println("GO!");
    }

    // print (without ln) stays on the same line, so the dashes line up.
    public static void printDivider(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
