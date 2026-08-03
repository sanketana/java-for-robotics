# Lesson 15 — A Robot Is Made of Parts

## Lesson Theme

Last lesson's stretch left `main` juggling a loose motor, a loose servo, and a loose lift — driving each one by hand. Add a claw and a second drive motor and that `main` becomes an unmanageable pile of parts: the *exact* mess that made you want classes in the first place, now one level up. Today you fix it with the biggest payoff idea in Tier 3: build **one `Robot` object that HAS-A drive train and HAS-A lift**, and tell *it* "drive forward" — it passes the order down to its own parts. A class whose fields are *other objects* is called **composition**, and it's the shape of every real robot's code.

## What You'll Build

- `Motor.java` and `Lift.java` — small mechanism classes, the kind you've built before.
- `Robot.java` — a class whose **fields are objects**: two `Motor`s and a `Lift`, built in its constructor. Its methods (`driveForward`, `raiseLift`) **delegate** — they ask the parts to do the work.
- `RobotDemo.java` — talks to just *one* `Robot` and watches both motors and the lift respond to a single command each.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- Pure Java, single folder (packages were last lesson; today is about structure, not layout)

## What You'll Learn

**Java skills:**
- **Composition** — a field whose *type is another class* (`private Motor leftMotor;`)
- Building sub-objects in the constructor (`this.leftMotor = new Motor("left_motor");`)
- **Delegation** — a method that does its job by calling methods on its parts

**Thinking skills:**
- The **has-a** relationship: a robot *has a* motor, an arm *has a* servo
- Modelling a system as a hierarchy of parts, each responsible for its own job
- How composition tames complexity by hiding a mechanism's guts behind one simple command

## In Class

A 60-minute session.

### The pile of loose parts (7 min)

Recap last lesson's stretch: `main` created a `Motor`, a `Servo`, and a `Lift`, and drove each separately. Ask the class: *what happens when the robot grows to eight mechanisms?* `main` fills with loose objects, and every program that uses the robot has to know how to wire up all eight. You solved this exact problem before — scattered variables became a class. Now scattered *objects* become a class too. The robot should own its own parts.

### The has-a idea (8 min)

You already know one relationship between classes: nothing formal yet, just "a program *uses* a Motor." Composition names a stronger one — **has-a**:

- A robot **has a** drive train.
- A drive train **has a** left motor and a right motor.
- An arm **has a** shoulder servo and an elbow servo.

Whenever you can say "X *has a* Y," Y can be a **field** inside X — a field whose type is another class. That's composition. The container is responsible for its parts, creates them, and speaks for them.

### Fields that are objects (13 min)

Open `Robot.java`:

```java
public class Robot {

    private Motor leftMotor;
    private Motor rightMotor;
    private Lift lift;

    public Robot() {
        this.leftMotor = new Motor("left_motor");
        this.rightMotor = new Motor("right_motor");
        this.lift = new Lift("arm_lift");
    }
    ...
}
```

Look at the fields. So far every field has been an `int`, `double`, `String`, or `boolean`. Now the fields are `Motor` and `Lift` — **objects**. A `Robot` *has a* left motor, a right motor, and a lift, and it holds each in a field.

And crucially, the constructor **builds its own parts**: `this.leftMotor = new Motor(...)`. When you create a robot, it creates the objects it's made of. This is a callback worth naming: an object field you *don't* `new` stays `null` — and you know what happens next (that's today's bug hunt).

### Delegation — the robot speaks for its parts (14 min)

Now the methods:

```java
public void driveForward(double power) {
    leftMotor.setPower(power);
    rightMotor.setPower(power);
}

public void raiseLift(int amount) {
    lift.raise(amount);
}
```

The `Robot` doesn't spin a motor itself — it has no `power` field of its own. It **delegates**: `driveForward` asks *both its motors* to set their power; `raiseLift` asks *its lift* to raise. The robot is a manager; the parts do the work.

Run `RobotDemo.java`:

```java
Robot robot = new Robot();
robot.driveForward(0.6);
robot.raiseLift(300);
robot.printStatus();
```

Output:

```
left_motor at power 0.6
right_motor at power 0.6
arm_lift at height 300
```

Notice how small `main` is now. One `robot`. One command drives *both* motors, because the robot knows it has two and handles them for you. That's the whole win: the messy details live *inside* `Robot`, and everyone else just says "drive forward."

### Your turn (10 min)

Give the robot a new ability without touching `main`'s simplicity:

1. Add a `private Motor` for an intake (call it `intakeMotor`) as a fourth part; build it in the constructor.
2. Add a method `runIntake(double power)` that delegates to it.
3. In `RobotDemo`, call `robot.runIntake(0.4)` and confirm the intake shows up in the status. `main` still just talks to the one robot — all the new wiring lived inside `Robot`.

### Why this matters (the bridge) (5 min)

This is *exactly* how a real FTC robot is coded. A `Robot` class (teams often call it a "hardware" or "robot" class) has fields for each `DcMotor` and `Servo`, builds them in an init step, and offers clean commands like `driveForward()` and `armUp()`. The OpMode that runs the match talks to that one robot object, not to twelve loose motors. You've just built the backbone of a competition codebase — and you did it with composition, no inheritance in sight. (Inheritance comes next tier, and you'll see composition was the more important idea all along.)

### Reflection (3 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. What does it mean for a field's *type* to be another class? Give one "has-a" example from the lesson.
2. What does "delegation" mean — what does `driveForward` actually do, given the `Robot` has no `power` field of its own?
3. Why is `main` so much shorter once the `Robot` owns its own parts?

## Stretch Project (Optional)

Finished early? Build composition *out of composed things* — the way a real robot is layered.

**Layered Robot.** Build two mid-level classes first:
- `DriveTrain` — HAS-A left `Motor` and a right `Motor`, with a `tankDrive(double leftPower, double rightPower)` that sets each, and a `printStatus()`.
- `Arm` — HAS-A shoulder `Servo` and an elbow `Servo`, with a `setPose(double shoulderAngle, double elbowAngle)` and a `printStatus()`.

Then build a `LayeredRobot` that HAS-A `DriveTrain` **and** an `Arm` (both built in its constructor), with `driveForward(power)` that delegates to the drive train and `setArmPose(...)` that delegates to the arm. A `main` should create one `LayeredRobot`, drive it, pose the arm, and print everything. Notice you now have three levels — robot → sub-system → individual motor/servo — each only responsible for the level below it. A worked version is in `solutions.md`; try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `Motor.java`, `Lift.java` — the mechanism classes
- `Robot.java` — composed of two motors and a lift; delegates via `driveForward` / `raiseLift`
- `RobotDemo.java` — drives one robot

Your homework files (`PredictComposition.java`, `CompositionBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

Compile everything together and run the demo:
```
javac Motor.java Lift.java Robot.java RobotDemo.java
java RobotDemo
```

## Notes for the Teacher

**Setup check before the session:**
- Back to single-folder — no packages today. Compile all the named `.java` files together, then run the one with `main`. A `Robot` won't compile without `Motor.java` and `Lift.java` present, since it's built from them.

**On the felt-need (don't skip the pile-of-parts opener):**
- The motivation is a callback: "scattered variables → a class" (Lesson 11) now becomes "scattered objects → a class." If students feel that echo, composition lands as *obvious* rather than new. Show the messy loose-objects `main` first, then the one-robot version, and let them feel the shrink.

**On "has-a" vs "is-a":**
- Keep it to **has-a** today. If a student asks "isn't a Motor a kind of Robot?" — no: a robot *has a* motor, it isn't one. That has-a/is-a distinction is the doorway to inheritance next tier; plant the phrase now, don't open the door yet.
- CLAUDE.md is deliberate that **composition lands before inheritance**. Resist any urge to say "you could also do this with `extends`." You can't, cleanly — a robot genuinely *has* parts, it isn't a subtype of them. Composition is the right tool and the more common one in real FTC code.

**On the constructor building the parts:**
- The line `this.leftMotor = new Motor(...)` is where composition and Lesson 11's `new` meet. Emphasise: creating the container creates its parts. And the flip side — a part you forget to `new` is `null` — is the single most common composition bug, which the homework hunts. This ties L9 (null), L11 (un-newed = null), and L12 (fields) together in one place.

**On scope (deliberately held back):**
- **No inheritance / `extends`** — that's Tier 4.
- **No `static`.**
- **No passing parts in from outside** (dependency injection) — the robot builds its own parts in its constructor. Passing a pre-made `Motor` *into* the constructor is a real and useful pattern, but it's a complication for later; keep it self-contained now.
- Getters like `getPosition` exist on the parts but the `Robot` mostly delegates via action methods — that's the cleaner teaching shape.

**Anticipated questions:**
- *"Why does `driveForward` take one power but set two motors?"* — "Because the robot knows it has two motors and handles both for you. That's the point — the caller doesn't have to know how many motors there are."
- *"Can a part be shared between two robots?"* — "Not the way we built it — each robot `new`s its own parts in its constructor, so `alpha`'s motors are entirely separate from `beta`'s. (Sharing is possible but it's a later, trickier topic.)"
- *"Does `Robot` need `this.` in `driveForward`?"* — "No — there's no local called `leftMotor` shadowing the field, so plain `leftMotor` already means the field. `this.` was needed in the constructor style but isn't here."

**Common mistakes to watch for:**
- Forgetting to `new` a part in the constructor → that field is `null` → `NullPointerException` the moment a method delegates to it. This is the whole bug hunt; expect it live too.
- Declaring the part's field but trying to use it before the constructor runs.
- Giving `Robot` its own `power`/`position` fields instead of delegating — re-anchor: the robot *has* parts that hold that state; it shouldn't duplicate it.
