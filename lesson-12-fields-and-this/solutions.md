# Lesson 12 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. The skill this lesson builds — reasoning about where a value lives and how long it survives — only sticks if you build and break the code yourself first.

## 1. Battery that remembers its charge

One correct `Battery.java`:

```java
public class Battery {

    String name;
    int charge;

    Battery(String name) {
        this.name = name;
        this.charge = 100;
    }

    void drain(int amount) {
        this.charge = this.charge - amount;
    }

    void recharge(int amount) {
        this.charge = this.charge + amount;
    }

    void printStatus() {
        System.out.println(name + " charge: " + charge);
    }
}
```

One correct `BatteryDemo.java`:

```java
public class BatteryDemo {

    public static void main(String[] args) {
        Battery pack = new Battery("main_pack");

        pack.drain(30);
        pack.drain(25);
        pack.recharge(15);

        pack.printStatus();
    }
}
```

Output (starts at `100`, then `- 30 - 25 + 15`):

```
main_pack charge: 60
```

Because `charge` is a **field**, every call picks up the running total the last call left behind: `100 → 70 → 45 → 60`. If `charge` had been a local inside `drain`, it would reset every call and never accumulate.

## 2. Servo whose setter uses `this`

One correct `Servo.java`:

```java
public class Servo {

    String name;
    double angle;

    Servo(String name) {
        this.name = name;
        this.angle = 0.0;
    }

    void setAngle(double angle) {
        this.angle = angle;
    }

    void printStatus() {
        System.out.println(name + " at angle " + angle);
    }
}
```

One correct `ServoDemo.java`:

```java
public class ServoDemo {

    public static void main(String[] args) {
        Servo claw = new Servo("claw_servo");
        claw.setAngle(90.0);
        claw.printStatus();
    }
}
```

Output:

```
claw_servo at angle 90.0
```

The experiment: if you change the setter to `angle = angle;` (no `this.`), the output becomes `claw_servo at angle 0.0` — the angle never changes. That line assigns the **parameter** to itself; the **field** `this.angle` is never touched. Restoring `this.angle = angle;` fixes it. That contrast is exactly why `this` exists: to reach the field when a parameter shares its name.

## 3. Prediction exercise

Expected output of `PredictThis.java`:

```
left_lift at height 300
right_lift at height 50
```

Why:
- `left`'s `position` is a field, so its two raises add up: `200 + 100 = 300`.
- `right` is a **separate object** with its own `position` field. Raising `left` never touches it, so `right` is just `50`.

Same lesson as the score independence from Lesson 11, now with a field that accumulates across calls.

## 4. Error detective

`ThisBugHunt.java` runs with no error and prints the wrong thing:

```
arm at height 0
```

Two bugs, one root cause.

**Bug 1 — `raise`:**
```java
void raise(int amount) {
    int position = this.position + amount;   // declares a NEW local called position
}
```
`int position = ...` creates a brand-new **local** variable. It computes `this.position + amount` correctly — and then the method ends and that local is thrown away. The **field** `this.position` was never assigned, so the height stays `0` no matter how many times you raise. Fix:
```java
void raise(int amount) {
    this.position = this.position + amount;
}
```

**Bug 2 — `rename`:**
```java
void rename(String name) {
    name = name;   // both sides are the parameter
}
```
Both `name`s here refer to the **parameter** (it shadows the field). So this assigns the parameter to itself and the field `this.name` is never changed — the lift keeps its original name. Fix:
```java
void rename(String name) {
    this.name = name;
}
```

**After both fixes** it prints:

```
main_lift at height 150
```

**The one rule that fixes both (step 3):** *to change the object's own value, assign to `this.field` — writing to a plain local or parameter of the same name changes nothing the object keeps.*

## Stretch Project — Sample Solution

Try it yourself first. `Rover.java`:

```java
public class Rover {

    String name;
    int distance;
    int batteryUsed;

    Rover(String name) {
        this.name = name;
        this.distance = 0;
        this.batteryUsed = 0;
    }

    void move(int cm) {
        this.distance = this.distance + cm;
        this.batteryUsed = this.batteryUsed + cm / 10;
    }

    void printStatus() {
        System.out.println(name + " | distance " + distance + " cm | battery used " + batteryUsed);
    }
}
```

`RoverRun.java`:

```java
public class RoverRun {

    public static void main(String[] args) {
        Rover scout = new Rover("scout");
        Rover hauler = new Rover("hauler");

        scout.move(100);
        scout.move(50);
        hauler.move(200);

        scout.printStatus();
        hauler.printStatus();
    }
}
```

Output:

```
scout | distance 150 cm | battery used 15
hauler | distance 200 cm | battery used 20
```

Each rover keeps **two** running fields, and both climb independently of the other rover. `scout` moved `100 + 50 = 150` cm and used `10 + 5 = 15` battery; `hauler` moved `200` cm and used `20`. Note the `cm / 10` uses integer division from Lesson 7 — `50 / 10` is `5`, and had you moved by, say, `55`, the battery cost would round down to `5`, not `5.5`. Two objects, two fields each, four independent running totals — all remembered because they're fields, all kept straight because `move` writes to `this.`.
