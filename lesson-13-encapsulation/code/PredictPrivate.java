public class PredictPrivate {

    public static void main(String[] args) {
        Motor m = new Motor("test_motor");

        m.setPower(0.4);
        System.out.println(m.getPower());

        m.setPower(2.5);
        System.out.println(m.getPower());

        m.setPower(-9.0);
        System.out.println(m.getPower());
    }
}
