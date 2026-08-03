# Lesson 15 — Homework

About 35–45 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **composition** — building classes whose fields are other objects, and letting them delegate work to their parts. Each build is a fresh program you write and run.

Compile reminder: name every `.java` file the program needs (the mechanism classes *and* the class that composes them), then run the one with `main`.

## 1. Build a DriveTrain out of two motors

You'll need a `Motor` class — reuse the one from this lesson's `code/` folder (copy it in).

Write a brand-new class `DriveTrain.java` that **HAS-A** two motors:
- `private Motor left;` and `private Motor right;`
- a constructor `DriveTrain()` that builds both: `this.left = new Motor("left_motor");` and the same for `right`
- a method `void tankDrive(double leftPower, double rightPower)` that sets each motor's power **independently** (left gets `leftPower`, right gets `rightPower`)
- a `void printStatus()` that prints both motors' statuses.

Then write `DriveTrainDemo.java` that creates one `DriveTrain`, calls `tankDrive(0.5, 0.8)`, and prints the status. Confirm the two motors ended at *different* powers — the drive train handled both from one call.

## 2. Build an Arm out of two servos

You'll need a `Servo` class. Write a simple one (`Servo.java`): a `private String name`, a `private double angle` starting at `0.0`, a `public void setAngle(double a)`, and a `public void printStatus()` that prints, e.g., `shoulder_servo at angle 45.0`.

Now write `Arm.java` that **HAS-A** two servos:
- `private Servo shoulder;` and `private Servo elbow;`
- a constructor that builds both
- a method `void setPose(double shoulderAngle, double elbowAngle)` that delegates to each servo
- a `void printStatus()`.

Then `ArmDemo.java` creates one `Arm`, calls `setPose(45.0, 90.0)`, and prints. One command, two servos posed.

## 3. Prediction exercise

Before running `PredictComposition.java` (in the `code/` subfolder), write down every line it prints — **all eight**.

```java
public class PredictComposition {

    public static void main(String[] args) {
        Robot alpha = new Robot();
        Robot beta = new Robot();

        alpha.driveForward(0.5);
        alpha.raiseLift(200);
        beta.raiseLift(50);

        System.out.println("Alpha:");
        alpha.printStatus();

        System.out.println("Beta:");
        beta.printStatus();
    }
}
```

(Hint: `alpha` and `beta` are two separate robots, and *each has its own motors and its own lift*. Driving `alpha` forward — does it change `beta`'s motors at all? And what power are `beta`'s motors at, given `beta` was only ever told to raise its lift?)

Compile and run with `Motor.java`, `Lift.java`, and `Robot.java` to check:
```
javac Motor.java Lift.java Robot.java PredictComposition.java
java PredictComposition
```

## 4. Error detective

`CompositionBugHunt.java` (in the `code/` subfolder) compiles fine, prints nothing useful, and **crashes**. This is a composition classic — and you're ready for it.

```java
public CompositionBugHunt() {
    this.driveMotor = new Motor("drive_motor");
}

public void raiseLift(int amount) {
    lift.raise(amount);
}
```

Compile and run it (with `Motor.java` and `Lift.java`), then work through the crash:
```
javac Motor.java Lift.java CompositionBugHunt.java
java CompositionBugHunt
```

1. Read the stack trace. What exception, on what line, and what does the message say is `null`?
2. Look at the constructor. It builds `driveMotor` — what part did it **forget** to build? So what is the `lift` field still pointing at?
3. Fix the constructor so it builds the lift too. Run again and confirm it prints both the motor and the lift.
4. In one sentence, connect this to what you learned about `null`: what must a composed object always do with each of its parts before anything uses them?

The sentence in step 4 is the rule that prevents this bug for good — a composed object must `new` *every* part in its constructor, or that part stays `null`.
