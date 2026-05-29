public class MethodBugHunt {
    public static void main(String[] args) {
        greets();
        countdown();
    }

    public static void greet() {
        System.out.println("Hello, driver!");
    }

    public void countdown() {
        for (int i = 3; i >= 1; i--) {
            System.out.println(i)
        }
    }
}
