# Lesson 14 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. The part that only sticks by doing it — getting the folder to match the package name and the import to line up — has to be your own hands. Build it before you read this.

## 1. Build a sensors package

`sensors/DistanceSensor.java`:

```java
package sensors;

public class DistanceSensor {

    private String name;
    private double distance;

    public DistanceSensor(String name) {
        this.name = name;
        this.distance = 0.0;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public void printReading() {
        System.out.println(name + " reads " + distance + " cm");
    }
}
```

`SensorMain.java` (outside the `sensors` folder):

```java
import sensors.DistanceSensor;

public class SensorMain {

    public static void main(String[] args) {
        DistanceSensor front = new DistanceSensor("front_distance");
        front.setDistance(42.5);
        front.printReading();
    }
}
```

Compile and run:
```
javac sensors/DistanceSensor.java SensorMain.java
java SensorMain
```

Output:

```
front_distance reads 42.5 cm
```

The file said `package sensors;`, it lived in a folder called `sensors`, and `SensorMain` imported it by that package name. All three have to agree.

## 2. Add a second class to the same package

`sensors/TouchSensor.java`:

```java
package sensors;

public class TouchSensor {

    private String name;
    private boolean pressed;

    public TouchSensor(String name) {
        this.name = name;
        this.pressed = false;
    }

    public void press() {
        this.pressed = true;
    }

    public void printReading() {
        System.out.println(name + " pressed? " + pressed);
    }
}
```

Extended `SensorMain.java`:

```java
import sensors.DistanceSensor;
import sensors.TouchSensor;

public class SensorMain {

    public static void main(String[] args) {
        DistanceSensor front = new DistanceSensor("front_distance");
        front.setDistance(42.5);
        front.printReading();

        TouchSensor bumper = new TouchSensor("bumper");
        bumper.press();
        bumper.printReading();
    }
}
```

Compile (name all three files) and run:
```
javac sensors/DistanceSensor.java sensors/TouchSensor.java SensorMain.java
java SensorMain
```

Output:

```
front_distance reads 42.5 cm
bumper pressed? true
```

One package (`sensors`), two classes inside it, and **one `import` per class** you use. That's how a real project groups related classes and pulls in exactly the ones a program needs.

## 3. Compile prediction

| Snippet | Compiles? | Why |
|---|---|---|
| **A** — `import robotparts.Motor;` then uses `Motor` | **Yes** | The import lets it use the short name `Motor`. |
| **B** — uses `Motor` with **no import** | **No** | `error: cannot find symbol — class Motor`. `Motor` is in the `robotparts` package; without an import (or the full name) this file has no idea what `Motor` means. |
| **C** — uses `robotparts.Motor` fully-qualified, no import | **Yes** | Writing the full name is the alternative to importing — same result, no import line needed. |
| **D** — uses only `String` and `System` | **Yes** | Both live in `java.lang`, which every file imports automatically. No import ever needed for those. |

The single question that decides each one: *is this file using a class from another package by its short name, and if so, did it import that class?* Only **B** uses a short name (`Motor`) without importing it, so only **B** fails. **C** dodges the import by writing the full name; **D** dodges it because `java.lang` is automatic.

## 4. Error detective

`ImportBugHunt.java` won't compile:

```
ImportBugHunt.java:4: error: cannot find symbol
        Motor arm = new Motor("arm_motor");
        ^
  symbol:   class Motor
  location: class ImportBugHunt
```

**Step 1 — what's wrong:** Java can't find the symbol `Motor`. `Motor` lives in the `robotparts` package (its file starts with `package robotparts;`), but `ImportBugHunt` never said to look there — so the short name `Motor` means nothing to it.

**Step 2 — the fix:** add the import as the first line:

```java
import robotparts.Motor;

public class ImportBugHunt {
    ...
```

**Step 3 — compile and run:**
```
javac robotparts/Motor.java ImportBugHunt.java
java ImportBugHunt
```

Output:

```
arm_motor at power 0.8
```

**What `import` did (one sentence):** *it let this file refer to `robotparts`'s `Motor` class by its short name `Motor`, instead of Java having no idea which class was meant.* That's all an import ever does — it points a file at a class in another package so the short name works.

## Stretch Project — Sample Solution

Try it yourself first. Three mechanism classes in the `robotparts` package:

`robotparts/Motor.java` — as provided in this lesson (has `setPower`).

`robotparts/Servo.java`:

```java
package robotparts;

public class Servo {

    private String name;
    private double angle;

    public Servo(String name) {
        this.name = name;
        this.angle = 0.0;
    }

    public void setAngle(double newAngle) {
        this.angle = newAngle;
    }

    public void printStatus() {
        System.out.println(name + " at angle " + angle);
    }
}
```

`robotparts/Lift.java`:

```java
package robotparts;

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

    public void printStatus() {
        System.out.println(name + " at height " + position);
    }
}
```

`RobotBuild.java` (outside the package, importing all three):

```java
import robotparts.Motor;
import robotparts.Servo;
import robotparts.Lift;

public class RobotBuild {

    public static void main(String[] args) {
        Motor drive = new Motor("drive_motor");
        Servo claw = new Servo("claw_servo");
        Lift arm = new Lift("arm_lift");

        drive.setPower(0.7);
        claw.setAngle(90.0);
        arm.raise(500);

        drive.printStatus();
        claw.printStatus();
        arm.printStatus();
    }
}
```

Compile and run:
```
javac robotparts/Motor.java robotparts/Servo.java robotparts/Lift.java RobotBuild.java
java RobotBuild
```

Output:

```
drive_motor at power 0.7
claw_servo at angle 90.0
arm_lift at height 500
```

This is the shape of a real FTC project in miniature: the mechanism classes (`Motor`, `Servo`, `Lift`) grouped in one package, and a single main program importing exactly the ones it needs. In Tier 4 the package will be `org.firstinspires.ftc.teamcode` and the imported classes will come from the SDK — but the layout you just built is identical.
