public class PredictDecisions {
    public static void main(String[] args) {
        int score = 7;

        System.out.println(score > 5);
        System.out.println(score == 10);
        System.out.println(score != 7);

        if (score >= 7) {
            System.out.println("You qualified!");
        } else {
            System.out.println("Try again.");
        }
    }
}
