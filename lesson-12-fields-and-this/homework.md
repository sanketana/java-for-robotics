# Lesson 12 — Homework

About 35–45 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **fields that remember** and `this` that keeps them straight. Every build is a fresh program you write and run.

Two-file reminder: put your blueprint and the program that uses it in the **same folder**, compile both, run the one with `main`.

## 1. Build a Battery that remembers its charge

Write a brand-new blueprint `Battery.java`, then a program `BatteryDemo.java`.

`Battery` should have:
- fields `String name` and `int charge`,
- a constructor `Battery(String name)` that starts `charge` at `100`,
- a method `void drain(int amount)` that *subtracts* `amount` from the charge,
- a method `void recharge(int amount)` that *adds* `amount` to the charge,
- a `void printStatus()` that prints, e.g., `main_pack charge: 60`.

In `BatteryDemo`, create one battery, then `drain` it twice and `recharge` it once (your choice of amounts). Print the status. The whole point: because `charge` is a **field**, it holds a running total across all three calls — each call picks up where the last left off. Work out on paper what the final number should be, then check your program agrees.

## 2. Build a Servo whose setter uses `this`

Write a brand-new blueprint `Servo.java`, then a program `ServoDemo.java`.

`Servo` should have:
- fields `String name` and `double angle`,
- a constructor `Servo(String name)` that starts `angle` at `0.0`,
- a method `void setAngle(double angle)` — **name the parameter `angle`, the same as the field, on purpose** — that sets the field using `this.angle = angle;`,
- a `void printStatus()` that prints, e.g., `claw_servo at angle 90.0`.

In `ServoDemo`, create a servo, set its angle, and print. Then, as an experiment, temporarily change the setter's body to just `angle = angle;` (no `this.`), run it again, and see the angle stay at `0.0`. Change it back. That experiment *is* the lesson: without `this`, you were assigning the parameter to itself and never touching the field.

## 3. Prediction exercise

Before running `PredictThis.java` (in the `code/` subfolder), write down both lines it prints.

```java
public class PredictThis {

    public static void main(String[] args) {
        Lift left = new Lift("left_lift");
        Lift right = new Lift("right_lift");

        left.raise(200);
        left.raise(100);
        right.raise(50);

        left.printStatus();
        right.printStatus();
    }
}
```

(Hint: `left`'s height is a field, so its two `raise` calls add up. And `right` is a *separate* object — does raising `left` change `right` at all?)

Compile and run with `Lift.java` to check:
```
javac Lift.java PredictThis.java
java PredictThis
```

## 4. Error detective

`ThisBugHunt.java` (in the `code/` subfolder) **compiles and runs with no error at all** — and yet it's broken. Raise the lift by 100, then 50, rename it to `"main_lift"`, and it prints:

```
arm at height 0
```

The height never moved and the name never changed. There's no crash to read this time — you have to reason about *where each value went*.

```java
void raise(int amount) {
    int position = this.position + amount;
}

void rename(String name) {
    name = name;
}
```

Work through it and write down what you find:
1. In `raise`, what does `int position = ...` create — a new local, or the field? So what happens to the lift's real height field? Fix it so `raise` updates the field.
2. In `rename`, both sides of `name = name` refer to the same thing — what? So which variable never gets changed? Fix it so the field is updated.
3. Run it again. It should now print `main_lift at height 150`. In one sentence, write the single rule that fixes *both* bugs.

The rule you write in step 3 is the whole lesson in one line — keep it somewhere you'll see it again.
