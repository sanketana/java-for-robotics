# Lesson 09 — Homework

About 30–40 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **writing fresh programs** that stay safe around `null`, and about reading the crash when they don't. Build each one, run it, and check it behaves the way you expect.

## 1. Safe robot greeting

Write a brand-new program `RobotGreeting.java`.

- Make a `String robotName` and set it to `null`.
- Print a greeting that uses `robotName.length()` — but **only** inside an `if (robotName != null)` guard. In the `else`, print `No robot name set yet.`
- Run it (you should see the `else` message, no crash).
- Now change `robotName` to a real name like `"Titan"` and run again. This time the greeting should print, e.g. `Titan has 5 letters.`

The point: the same program handles both a real name and a missing one without ever crashing.

## 2. Locker lookup

Write a brand-new program `LockerLookup.java`.

- Write a method `String lockerOwner(int lockerNumber)` that returns a name for one or two known lockers (say `12` → `"Meera"`), and `null` for any other locker.
- In `main`, look up **two** lockers: one that exists and one that doesn't.
- For each, print the owner **only if** the result isn't null; otherwise print `Locker N is empty.`

Remember: a method returning `null` is normal — it's how the method says "I have no answer." Your job as the caller is to check before you use it.

## 3. Prediction exercise

Before running `PredictNull.java`, write down exactly what it prints — **and** predict the line it crashes on and what the error will be. (A program stops the instant it hits the bad line, so some lines print and some never do.)

```java
public class PredictNull {

    public static void main(String[] args) {
        String phase = "Autonomous";
        System.out.println("Phase: " + phase);
        System.out.println("Length: " + phase.length());

        String next = null;
        System.out.println("Next phase set? " + (next != null));
        System.out.println("Next length: " + next.length());
        System.out.println("Done");
    }
}
```

(Hint: `"Autonomous"` has 10 letters. Work out which lines run *before* the crash, and remember `Done` is below the crashing line.)

## 4. Error detective

`NullBugHunt.java` crashes. This time your job is to **read the stack trace** and fix it — then find that it hides a *second* null bug behind the first.

```java
public class NullBugHunt {

    public static void main(String[] args) {
        String driver = driverForRobot(2);
        System.out.println("Driver name has " + driver.length() + " letters.");

        String backup = null;
        System.out.println("Backup driver: " + backup);
        System.out.println("Backup in caps: " + backup.toUpperCase());
    }

    public static String driverForRobot(int robotId) {
        if (robotId == 1) {
            return "Asha";
        }
        return null;
    }
}
```

Do this in order and write down what you find at each step:
1. Run it. Read the stack trace: what line does it crash on, and why? (What did `driverForRobot(2)` return?)
2. Fix that first crash with a null guard, then run again.
3. A **second** `NullPointerException` now appears — read its line and message, and fix that one too.
4. Notice which line printed `null` happily without crashing. Why did that one *not* crash, when the line just after it did?
