public class PredictReturns {

    public static void main(String[] args) {
        int a = doubleIt(4);
        int b = doubleIt(a);
        System.out.println(a);
        System.out.println(b);
        System.out.println(doubleIt(10));
        greet("Asha");
    }

    public static int doubleIt(int n) {
        return n * 2;
    }

    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
