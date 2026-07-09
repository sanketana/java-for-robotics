# Lesson 04 — Homework Solutions

Try every task on your own first. Reading an error (and stopping a runaway loop) yourself is the skill we're building — use this only to check your work afterward.

## 1. Build a loop of your own

Open-ended, so there's no single right answer. A times-table version with a `for` loop:

```java
import java.util.Scanner;

public class TimesTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = scanner.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }
    }
}
```

A motor-ramp version with a `while` loop:

```java
public class MotorRamp {
    public static void main(String[] args) {
        double power = 0.0;
        while (power < 1.0) {
            power = power + 0.1;
            System.out.println("Power: " + power);
        }
    }
}
```

Check: the loop counter or value changes each pass, so the loop is guaranteed to end.

## 2. Prediction exercise

`PredictLoops.java` prints:

```
Lap 1
Lap 2
Lap 3
Charging...
Charging...
Done
```

Why:
- The `for` loop runs with `i` = 1, 2, 3 → three "Lap" lines. When `i` becomes 4, `4 <= 3` is false, so it stops.
- The `while` loop runs while `count < 2`: count is 0 (print, count→1), count is 1 (print, count→2). Now `2 < 2` is false, so it stops — two "Charging..." lines.
- Then `Done` prints once, after the loop.

## 3. Error detective

`LoopBugHunt.java` had three problems. The corrected program:

```java
public class LoopBugHunt {
    public static void main(String[] args) {
        int count = 5;

        while (count > 0) {
            System.out.println("Counting: " + count);
            count--;
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("Step " + i);
        }
    }
}
```

The three problems:

1. **`error: ';' expected`** — the `println("Counting: " + count)` line was missing its semicolon. Added `;`.
2. **`error: ';' expected` (in the for header)** — the `for` loop used commas: `for (int i = 0, i < 3, i++)`. The three parts of a `for` header are separated by **semicolons**: `for (int i = 0; i < 3; i++)`.
3. **The infinite loop (no error message)** — the `while` loop printed `count` but never changed it, so `count > 0` stayed true forever. Adding `count--;` inside the loop lets the condition eventually become false. This is the bug with no compiler message: the program *runs*, it just never stops. Press Ctrl+C to stop a runaway loop.

## Stretch Project — Sample Solution

Try it yourself first. `DriveSteps.java`:

```java
import java.util.Scanner;

public class DriveSteps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Target distance (cm): ");
        int target = scanner.nextInt();
        System.out.print("Step size (cm): ");
        int step = scanner.nextInt();

        for (int i = 3; i >= 1; i--) {
            System.out.println(i);
        }
        System.out.println("GO!");

        int position = 0;
        int steps = 0;
        while (position < target) {
            position = position + step;
            steps = steps + 1;
            System.out.println("Step " + steps + ": " + position + " cm");
        }
        System.out.println("Reached " + position + " cm in " + steps + " steps.");
    }
}
```

With target `35` and step `10`:

```
3
2
1
GO!
Step 1: 10 cm
Step 2: 20 cm
Step 3: 30 cm
Step 4: 40 cm
Reached 40 cm in 4 steps.
```

The `for` loop handles the fixed countdown; the `while` loop handles the "keep going until we pass the target" part; and `steps` is a counter that adds one each pass. Notice the overshoot to 40 — same reason as `DriveToTarget` in class.
