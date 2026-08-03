public class ThisBugHunt {

    String name;
    int position;

    ThisBugHunt(String name) {
        this.name = name;
        this.position = 0;
    }

    void raise(int amount) {
        int position = this.position + amount;
    }

    void rename(String name) {
        name = name;
    }

    void printStatus() {
        System.out.println(name + " at height " + position);
    }

    public static void main(String[] args) {
        ThisBugHunt lift = new ThisBugHunt("arm");

        lift.raise(100);
        lift.raise(50);
        lift.rename("main_lift");

        lift.printStatus();
    }
}
