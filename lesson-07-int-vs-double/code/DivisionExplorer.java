public class DivisionExplorer {

    public static void main(String[] args) {
        // int maths: the remainder is thrown away.
        System.out.println("int maths (whole numbers only):");
        System.out.println("5 / 2 = " + (5 / 2));
        System.out.println("7 / 2 = " + (7 / 2));
        System.out.println("9 / 2 = " + (9 / 2));

        System.out.println();

        // double maths: the decimals are kept.
        System.out.println("double maths (decimals kept):");
        System.out.println("5.0 / 2 = " + (5.0 / 2));
        System.out.println("7.0 / 2 = " + (7.0 / 2));
        System.out.println("9.0 / 2 = " + (9.0 / 2));
    }
}
