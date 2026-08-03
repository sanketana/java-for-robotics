# Lesson 13 — Homework

About 35–45 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **objects that protect their own data**: `private` fields, and `public` methods that guard every change. Each build is a fresh program you write and run.

Two-file reminder: blueprint and demo in the **same folder**, compile both, run the one with `main`.

## 1. Build a Lift that can't leave its legal range

Write a brand-new blueprint `Lift.java`, then a program `LiftDemo.java`.

`Lift` should have:
- `private String name` and `private int position`,
- a constructor `Lift(String name)` that starts `position` at `0`,
- a `public void setPosition(int newPosition)` that **guards** the value into the range `0` to `2000` (anything above `2000` becomes `2000`; anything below `0` becomes `0`),
- a `public int getPosition()` and a `public void printStatus()`.

In `LiftDemo`, set the position to a legal value (say `500`) and print it; then set it to `9999` and confirm it clamps to `2000`; then set it to `-50` and confirm it clamps to `0`. The object should be physically incapable of holding an out-of-range height.

## 2. Build a Score that outsiders can read but never set

Write a brand-new blueprint `Score.java`, then a program `ScoreDemo.java`.

`Score` should have:
- `private String team` and `private int points`,
- a constructor `Score(String team)` that starts `points` at `0`,
- a `public void addPoints(int amount)` that adds to the score,
- a `public int getPoints()` to read it,
- **no setter** — there is deliberately no `setPoints`. The only way points change is by *adding* through `addPoints`.

In `ScoreDemo`, create a team, add points a couple of times, and print the total with `getPoints()`. Then try to write `red.points = 999;` and see that it **won't compile** (`points has private access`). That failure is the feature: because there's no setter and the field is private, nobody can ever slam the score to an arbitrary number — it can only go up, point by point. Encapsulation here enforces a *rule*, not just a range.

## 3. Prediction exercise

Before running `PredictPrivate.java` (in the `code/` subfolder), write down all three lines it prints.

```java
public class PredictPrivate {

    public static void main(String[] args) {
        Motor m = new Motor("test_motor");

        m.setPower(0.4);
        System.out.println(m.getPower());

        m.setPower(2.5);
        System.out.println(m.getPower());

        m.setPower(-9.0);
        System.out.println(m.getPower());
    }
}
```

(Hint: `setPower` clamps to the range −1.0…1.0. Which of these three values is already legal, and which two get pulled to a boundary?)

Compile and run with `Motor.java` to check:
```
javac Motor.java PredictPrivate.java
java PredictPrivate
```

## 4. Error detective

`PrivateBugHunt.java` (in the `code/` subfolder) **won't even compile**. This is the good kind of error — the object refusing to be misused.

```java
public class PrivateBugHunt {

    public static void main(String[] args) {
        Motor arm = new Motor("arm_motor");

        arm.power = 0.7;
        System.out.println("Arm power: " + arm.power);

        arm.printStatus();
    }
}
```

Work through it and write down what you find:
1. Compile it (with `Motor.java`). You'll get **two** errors, both the same kind. What do they say, and what are the two lines trying to do that they're not allowed to?
2. Fix the *write* — instead of setting `arm.power` directly, go through the doorway. Which method should you call?
3. Fix the *read* — instead of reading `arm.power` directly, go through the doorway. Which method should you call?
4. Compile and run. In one sentence, write *why* the compiler stopped you here, and why that's a good thing rather than an annoyance.

The sentence you write in step 4 is the heart of encapsulation — keep it.
