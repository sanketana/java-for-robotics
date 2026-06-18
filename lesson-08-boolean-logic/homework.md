# Lesson 08 — Homework

About 30–40 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **writing fresh programs** that combine conditions and hand back true/false answers. Build each one, run it, and check the results match what you predicted.

## 1. Drive guard

Write a brand-new program `DriveGuard.java`.

- Write a method `boolean canDrive(boolean buttonHeld, boolean pathClear)` that returns true **only when both** are true (use `&&`).
- From `main`, call it three times and print the result each time: button held with a clear path; button held with a blocked path; button up with a clear path.
- Each line should read like `Can drive (button held, path clear): true`.

## 2. Temperature watch

Write a brand-new program `TempCheck.java`.

- Write a method `boolean needsAttention(int temp)` that returns true when the temperature is **too high or too low** — say above 80 or below 10 (use `||`).
- In `main`, call it with three temperatures: one that's too hot, one that's fine, and one that's too cold. Print each result.
- Then use the method inside an `if` so it prints a warning line only when attention is needed.

## 3. Prediction exercise

Before running `PredictBooleans.java`, write down exactly what it prints, line by line. Then run it and check.

```java
public class PredictBooleans {

    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;

        System.out.println(a && b);
        System.out.println(a || b);
        System.out.println(!b);
        System.out.println(a && !b);

        int x = 7;
        System.out.println(x > 5 && x < 10);
        System.out.println(x > 5 || x > 100);
        System.out.println(bothPositive(4, -2));
    }

    public static boolean bothPositive(int p, int q) {
        return p > 0 && q > 0;
    }
}
```

(Hint: for each line, work out the true/false value of each side first, then apply the `&&`, `||`, or `!`.)

## 4. Error detective

`BooleanBugHunt.java` has three bugs. Same twist as last lesson, and it's worth saying again:

> Two of these bugs the compiler will catch (and it reveals them **one at a time** — fix the first, recompile, and the second appears). **The third it won't catch at all** — the program runs and prints a line that is simply wrong. Spotting that one is the real test.

```java
public class BooleanBugHunt {

    public static void main(String[] args) {
        int temperature = 60;
        boolean motorRunning = true;

        if (temperature = 50) {
            System.out.println("Temperature is exactly 50");
        }

        if (temperature > 10 || temperature < 50) {
            System.out.println("Temperature is in the safe range (10 to 50)");
        }

        System.out.println("Safe to run: " + isSafe(motorRunning));
    }

    public static boolean isSafe(boolean running) {
        if (running) {
            return true;
        }
    }
}
```

For each bug, write down: the symptom (an error message, or a wrong printed line), and what you changed. (Hint for the silent one: the temperature is 60. Is 60 really "in the safe range (10 to 50)"? Read the condition carefully — would *any* number make it false?)
