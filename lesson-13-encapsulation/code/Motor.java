public class Motor {

    // private: only Motor's own methods can touch these. No outsider can reach in.
    private String name;
    private double power;

    public Motor(String name) {
        this.name = name;
        this.power = 0.0;
    }

    // The guarded doorway: any value handed in is clamped to the legal motor range.
    public void setPower(double newPower) {
        if (newPower > 1.0) {
            newPower = 1.0;
        }
        if (newPower < -1.0) {
            newPower = -1.0;
        }
        this.power = newPower;
    }

    public double getPower() {
        return this.power;
    }

    public String getName() {
        return this.name;
    }

    public void printStatus() {
        System.out.println(name + " at power " + power);
    }
}
