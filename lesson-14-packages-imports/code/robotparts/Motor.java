package robotparts;

public class Motor {

    private String name;
    private double power;

    public Motor(String name) {
        this.name = name;
        this.power = 0.0;
    }

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

    public void printStatus() {
        System.out.println(name + " at power " + power);
    }
}
