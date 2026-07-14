public class NullGuard {

    public static void main(String[] args) {
        String robotName = null;

        // Guard: only call a method on robotName if it points at something.
        if (robotName != null) {
            System.out.println("Robot name has " + robotName.length() + " letters.");
        } else {
            System.out.println("No robot name set yet.");
        }

        // A method that hands back null when it has no answer.
        String nickname = nicknameFor(1);
        if (nickname != null) {
            System.out.println("Robot 1 nickname: " + nickname);
        } else {
            System.out.println("Robot 1 has no nickname.");
        }

        // Printing a null value is safe — it just shows the word "null".
        // (Calling a method on it would crash; printing it does not.)
        String missing = nicknameFor(99);
        System.out.println("Robot 99 nickname: " + missing);
    }

    // Returns a nickname, or null when we don't have one on file.
    public static String nicknameFor(int robotId) {
        if (robotId == 1) {
            return "T-Rex";
        }
        return null;
    }
}
