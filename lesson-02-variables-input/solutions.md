# Lesson 02 — Homework Solutions

Try every task on your own first. Reading an error and fixing it yourself is the skill we're building — use this only to check your work afterward.

## 1. Build your own calculator

This one is yours to design, so the questions and messages can be anything. A correct version reads two numbers and prints the four results:

```java
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        double first = scanner.nextDouble();

        System.out.print("Enter the second number: ");
        double second = scanner.nextDouble();

        System.out.println("Sum: " + (first + second));
        System.out.println("Difference: " + (first - second));
        System.out.println("Product: " + (first * second));
        System.out.println("Remainder: " + (first % second));
    }
}
```

Two things worth checking:
- The brackets around `(first + second)` matter. Without them, `"Sum: " + first + second` would glue the numbers on as text instead of adding them — try it and see.
- Using `double` (not `int`) means decimals work, so `5` divided in a later step won't surprise you.

## 2. Prediction exercise

`PredictMath.java` prints:

```
9
3
1
3.5
```

Why each line:
- `a + b` → `9`
- `a / b` → `3`, **not** `3.5` — both `a` and `b` are `int` (whole numbers), so Java does whole-number division and throws away the remainder
- `a % b` → `1`, the leftover when 7 is divided by 2
- `x / y` → `3.5`, because `x` and `y` are `double` (decimals), so Java keeps the decimal part

This is the "division surprise" from class: **whole ÷ whole = whole.** If you want a decimal answer, use decimal numbers.

## 3. Error detective

`TypeBugHunt.java` had three errors. The corrected program:

```java
public class TypeBugHunt {
    public static void main(String[] args) {
        int age = 12;
        double height = 1.5;
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
    }
}
```

What each error message said, and the fix:

1. **`error: incompatible types: String cannot be converted to int`** — `int age = "twelve";` tried to put text into a whole-number variable. An `int` holds a number, so it becomes `int age = 12;`. (Using `String age = "twelve";` would also compile, but then it's text, not a number.)
2. **`error: ';' expected`** — the `double height = 1.5` line was missing its semicolon. Added `;`.
3. **`error: cannot find symbol` (pointing at `heigth`)** — the last line used `heigth`, but the variable is spelled `height`. Java doesn't guess at typos; the name must match exactly.
