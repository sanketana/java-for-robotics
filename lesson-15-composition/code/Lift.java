public class Lift {

    private String name;
    private int position;

    public Lift(String name) {
        this.name = name;
        this.position = 0;
    }

    public void raise(int amount) {
        this.position = this.position + amount;
    }

    public int getPosition() {
        return this.position;
    }

    public void printStatus() {
        System.out.println(name + " at height " + position);
    }
}
