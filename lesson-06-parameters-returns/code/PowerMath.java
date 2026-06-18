public class PowerMath {

    public static void main(String[] args) {
        // A reporter block hands a value back. We catch it in a variable.
        int ticks = ticksForRotations(3);
        System.out.println("3 rotations = " + ticks + " ticks");

        // You can use a returned value straight away, too.
        System.out.println("5 rotations = " + ticksForRotations(5) + " ticks");

        int faster = higher(40, 75);
        System.out.println("Higher power: " + faster);
    }

    // Takes a number of wheel rotations, hands back the encoder ticks.
    // (A common FTC motor reports 1120 ticks per full rotation.)
    public static int ticksForRotations(int rotations) {
        return rotations * 1120;
    }

    // Hands back whichever of the two values is larger.
    public static int higher(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
}
