# Lesson 11 — Homework

About 35–45 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **writing your own blueprints** — designing a class, then stamping out objects from it. Every task is a fresh program you build and run.

Remember the two-file compile: put your blueprint class and the program that uses it in the **same folder**, compile both, then run the one with `main`. For example:
```
javac Motor.java MotorDemo.java
java MotorDemo
```

## 1. Build a Motor blueprint

Write a brand-new blueprint class `Motor.java`, then a program `MotorDemo.java` that uses it.

`Motor` should have:
- two fields: a `String name` and a `double power`,
- a constructor `Motor(String name)` that sets the name and starts `power` at `0.0`,
- a method `void setPower(double newPower)` that sets the motor's power,
- a method `void printStatus()` that prints, e.g., `left_motor running at power 0.5`.

In `MotorDemo`, create **two** motors (`"left_motor"` and `"right_motor"`), set them to *different* powers, and print both statuses. Confirm that setting one motor's power leaves the other's unchanged — that independence is the thing you're proving.

## 2. Build a Player scoreboard

Write a brand-new blueprint class `Player.java`, then a program `ScoreBoard.java`.

`Player` should have:
- fields `String name` and `int score`,
- a constructor `Player(String name)` that starts `score` at `0`,
- a method `void addPoints(int points)` that adds to the score,
- a method `void printScore()` that prints, e.g., `Asha: 30`.

In `ScoreBoard`, create **three** players, add different points to each (some more than once), and print all three scores. This is the same shape as the `Robot` class from class — build it from memory rather than copying, to make the pattern stick.

## 3. Prediction exercise

Before running `PredictObjects.java` (in the `code/` subfolder), write down exactly what it prints — **both** lines.

```java
public class PredictObjects {

    public static void main(String[] args) {
        Robot a = new Robot("Alpha", 1.2);
        Robot b = new Robot("Beta", 1.2);

        a.addScore(10);
        a.addScore(5);
        b.addScore(100);

        a.printStatus();
        b.printStatus();
    }
}
```

(Hint: `a` and `b` were built with the *same* top speed, but they are two different objects. Adding points to `a` — does it change `b` at all? Work out each object's score separately.)

Compile and run it with `Robot.java` to check:
```
javac Robot.java PredictObjects.java
java PredictObjects
```

## 4. Error detective

`ObjectBugHunt.java` (in the `code/` subfolder) hides **two** bugs — and the second only appears after you fix the first. This is exactly the two-family journey from Lesson 10: a compile-time error first, then a runtime crash.

```java
public class ObjectBugHunt {

    public static void main(String[] args) {
        Robot atlas = new Robot("Atlas");
        atlas.addScore(50);

        Robot nova = null;
        nova.addScore(20);

        atlas.printStatus();
        nova.printStatus();
    }
}
```

Work through it in order, writing down what you find at each step:
1. Compile it (with `Robot.java`). Read the first error — which line, and what is it telling you about the constructor? (How many things does `Robot`'s constructor need, and how many did this line give it?) Fix that line.
2. Compile again — it now **builds**. Run it. A `NullPointerException` appears. Which line, and *why* is `nova` null? (What step did we skip for `nova` that we did for `atlas`?)
3. Fix `nova` so the program runs cleanly and prints both robots' statuses.

The lesson hiding in bug 2: an object variable you never `new`-ed holds *no object* — it's `null` — and calling a method on it is the very crash you learned to read two lessons ago.
