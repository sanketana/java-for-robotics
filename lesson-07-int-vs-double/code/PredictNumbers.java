public class PredictNumbers {

    public static void main(String[] args) {
        int a = 7;
        int b = 2;
        System.out.println(a / b);
        System.out.println(a / 2.0);

        double c = 10 / 4;
        System.out.println(c);

        double d = 10.0 / 4;
        System.out.println(d);

        System.out.println(half(3));
    }

    public static double half(int n) {
        return n / 2.0;
    }
}
