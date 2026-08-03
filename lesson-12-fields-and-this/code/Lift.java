public class Lift {

    // Fields: this lift remembers these between method calls, for as long as it exists.
    String name;
    int position;

    Lift(String name) {
        this.name = name;
        this.position = 0;
    }

    void raise(int amount) {
        this.position = this.position + amount;
    }

    // The parameter is also called 'name', so it shadows the field here.
    // this.name reaches past it to the field the lift actually remembers.
    void rename(String name) {
        this.name = name;
    }

    void printStatus() {
        System.out.println(name + " at height " + position);
    }
}
