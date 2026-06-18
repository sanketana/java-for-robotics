public class PredictBooleans {

    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;

        System.out.println(a && b);
        System.out.println(a || b);
        System.out.println(!b);
        System.out.println(a && !b);

        int x = 7;
        System.out.println(x > 5 && x < 10);
        System.out.println(x > 5 || x > 100);
        System.out.println(bothPositive(4, -2));
    }

    public static boolean bothPositive(int p, int q) {
        return p > 0 && q > 0;
    }
}
