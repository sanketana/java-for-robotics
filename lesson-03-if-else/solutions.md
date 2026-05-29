# Lesson 03 — Homework Solutions

Try every task on your own first. Reading an error and fixing it yourself is the skill we're building — use this only to check your work afterward.

## 1. Add a decision to your calculator

This task is open-ended, so there's no single right answer — yours just needs at least one new working `if`. A divide-by-zero guard might look like this:

```java
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        double first = scanner.nextDouble();

        System.out.print("Enter the second number: ");
        double second = scanner.nextDouble();

        if (second == 0) {
            System.out.println("Can't divide by zero!");
        } else {
            System.out.println("Quotient: " + (first / second));
        }
    }
}
```

Check: the condition uses `==` (not `=`), and exactly one branch runs.

## 2. Prediction exercise

`PredictDecisions.java` prints:

```
true
false
false
You qualified!
```

Why each line:
- `score > 5` → `true` (7 is greater than 5)
- `score == 10` → `false` (7 is not 10)
- `score != 7` → `false` ("not equal to 7" is false, because it *is* 7)
- `score >= 7` is true, so the `if` block runs → `You qualified!` (the `else` is skipped)

## 3. Error detective

`DecisionBugHunt.java` had three errors. The corrected program:

```java
public class DecisionBugHunt {
    public static void main(String[] args) {
        int temperature = 40;

        if (temperature == 40) {
            System.out.println("Motor is overheating!");
        } else {
            System.out.println("Temp is fine: " + temperature);
        }
    }
}
```

What each error message said, and the fix:

1. **`error: incompatible types: int cannot be converted to boolean`** — the condition used a single `=` (`temperature = 40`), which tries to *assign* instead of *compare*. A condition needs a true/false answer, so it must be `==`. This is the trap from class.
2. **`error: ';' expected`** — the `println("Motor is overheating!")` line was missing its semicolon. Added `;`.
3. **`error: cannot find symbol` (pointing at `temperatur`)** — the last line used `temperatur`, but the variable is spelled `temperature`. Names must match exactly.
