public class LoopBugHunt {
    public static void main(String[] args) {
        int count = 5;

        while (count > 0) {
            System.out.println("Counting: " + count)
        }

        for (int i = 0, i < 3, i++) {
            System.out.println("Step " + i);
        }
    }
}
