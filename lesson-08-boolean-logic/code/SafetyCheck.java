public class SafetyCheck {

    public static void main(String[] args) {
        boolean buttonHeld = true;
        boolean armAtTop = false;
        int battery = 35;

        // Raise the arm only if the driver is holding the button
        // AND the arm is not already at the top.
        boolean canRaiseArm = buttonHeld && !armAtTop;
        System.out.println("Can raise arm: " + canRaiseArm);

        // Warn if the battery is low OR the arm is stuck at the top.
        boolean shouldWarn = battery < 20 || armAtTop;
        System.out.println("Should warn: " + shouldWarn);

        // Drive fast only if the battery is healthy AND the arm is down.
        boolean canDriveFast = battery > 30 && !armAtTop;
        System.out.println("Can drive fast: " + canDriveFast);
    }
}
