# Lesson 15 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. Composition only clicks once you've wired the parts together yourself and watched one command flow down to them. Build it before reading on.

## 1. DriveTrain out of two motors

Reusing `Motor.java` from this lesson. `DriveTrain.java`:

```java
public class DriveTrain {

    private Motor left;
    private Motor right;

    public DriveTrain() {
        this.left = new Motor("left_motor");
        this.right = new Motor("right_motor");
    }

    public void tankDrive(double leftPower, double rightPower) {
        left.setPower(leftPower);
        right.setPower(rightPower);
    }

    public void printStatus() {
        left.printStatus();
        right.printStatus();
    }
}
```

`DriveTrainDemo.java`:

```java
public class DriveTrainDemo {

    public static void main(String[] args) {
        DriveTrain drive = new DriveTrain();
        drive.tankDrive(0.5, 0.8);
        drive.printStatus();
    }
}
```

Compile and run:
```
javac Motor.java DriveTrain.java DriveTrainDemo.java
java DriveTrainDemo
```

Output:

```
left_motor at power 0.5
right_motor at power 0.8
```

The drive train *has* two motors and speaks for both: one `tankDrive` call set each to its own power. `main` never touched a motor directly.

## 2. Arm out of two servos

`Servo.java`:

```java
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

`Arm.java`:

```java
public class Arm {

    private Servo shoulder;
    private Servo elbow;

    public Arm() {
        this.shoulder = new Servo("shoulder_servo");
        this.elbow = new Servo("elbow_servo");
    }

    public void setPose(double shoulderAngle, double elbowAngle) {
        shoulder.setAngle(shoulderAngle);
        elbow.setAngle(elbowAngle);
    }

    public void printStatus() {
        shoulder.printStatus();
        elbow.printStatus();
    }
}
```

`ArmDemo.java`:

```java
public class ArmDemo {

    public static void main(String[] args) {
        Arm arm = new Arm();
        arm.setPose(45.0, 90.0);
        arm.printStatus();
    }
}
```

Output:

```
shoulder_servo at angle 45.0
elbow_servo at angle 90.0
```

Same shape as the drive train, different parts. That sameness is the pattern: a container class holds its parts as fields, builds them in the constructor, and delegates through its methods.

## 3. Prediction exercise

Expected output of `PredictComposition.java` (all eight lines):

```
Alpha:
left_motor at power 0.5
right_motor at power 0.5
arm_lift at height 200
Beta:
left_motor at power 0.0
right_motor at power 0.0
arm_lift at height 50
```

Why:
- `alpha` and `beta` are **two separate robots**, each with *its own* two motors and *its own* lift.
- `alpha.driveForward(0.5)` set both of alpha's motors to `0.5`; `alpha.raiseLift(200)` raised alpha's lift to `200`.
- `beta` was only ever told to raise its lift (`beta.raiseLift(50)` → `50`). It was never driven, so **beta's motors are still at their starting power, `0.0`.**
- Driving alpha never touched beta — the independence you saw with simple fields in Lessons 11–12 holds just as firmly when the fields are whole objects.

## 4. Error detective

`CompositionBugHunt.java` crashes:

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Lift.raise(int)" because "this.lift" is null
	at CompositionBugHunt.raiseLift(CompositionBugHunt.java:15)
	at CompositionBugHunt.main(CompositionBugHunt.java:27)
```

**Step 1 — the trace:** a `NullPointerException` on line 15 (inside `raiseLift`), which says `this.lift` is `null`. (Because `lift` is a *field*, the message names it directly even with a plain `javac` — no `<local>` placeholder.) The trace shows `main` (line 27) called `raiseLift`, which then blew up.

**Step 2 — why:** look at the constructor:
```java
public CompositionBugHunt() {
    this.driveMotor = new Motor("drive_motor");
}
```
It builds `driveMotor` but **never builds `lift`**. An object field you don't `new` stays `null` — so `lift` is `null`, and `lift.raise(amount)` calls a method on nothing.

**Step 3 — the fix:** build the lift in the constructor too:
```java
public CompositionBugHunt() {
    this.driveMotor = new Motor("drive_motor");
    this.lift = new Lift("arm_lift");
}
```

Run again:
```
drive_motor at power 0.7
arm_lift at height 300
```

**Step 4 — the rule:** *a composed object must `new` **every** part in its constructor, or that part stays `null` and the first method that uses it throws a `NullPointerException`.* This is the same null lesson from Lessons 9 and 11, now wearing a composition hat: creating the container must also create everything the container is made of.

## Stretch Project — Sample Solution

Try it yourself first. Uses `Motor.java` and `Servo.java`.

`DriveTrain.java` and `Arm.java` — as in homework tasks 1 and 2 above.

`LayeredRobot.java` (composed of composed things):

```java
public class LayeredRobot {

    private DriveTrain drive;
    private Arm arm;

    public LayeredRobot() {
        this.drive = new DriveTrain();
        this.arm = new Arm();
    }

    public void driveForward(double power) {
        drive.tankDrive(power, power);
    }

    public void setArmPose(double shoulderAngle, double elbowAngle) {
        arm.setPose(shoulderAngle, elbowAngle);
    }

    public void printStatus() {
        drive.printStatus();
        arm.printStatus();
    }
}
```

`LayeredRobotDemo.java`:

```java
public class LayeredRobotDemo {

    public static void main(String[] args) {
        LayeredRobot robot = new LayeredRobot();
        robot.driveForward(0.6);
        robot.setArmPose(30.0, 120.0);
        robot.printStatus();
    }
}
```

Compile (all six files) and run:
```
javac Motor.java Servo.java DriveTrain.java Arm.java LayeredRobot.java LayeredRobotDemo.java
java LayeredRobotDemo
```

Output:

```
left_motor at power 0.6
right_motor at power 0.6
shoulder_servo at angle 30.0
elbow_servo at angle 120.0
```

Three levels of composition, each responsible only for the level directly below it:
- `LayeredRobot` HAS-A `DriveTrain` and an `Arm` — it delegates to them and knows nothing about individual motors.
- `DriveTrain` HAS-A two `Motor`s; `Arm` HAS-A two `Servo`s — they delegate to those.
- `Motor` and `Servo` do the actual work.

`robot.driveForward(0.6)` flows down two levels — robot → drive train → each motor — and `main` never sees any of it. This is precisely how a real FTC robot's code is layered, and you built it with nothing but composition. No inheritance required — and that's the point CLAUDE.md's curriculum is making by teaching composition first: most of the "a robot is made of parts" structure you'll ever need is has-a, not is-a.
