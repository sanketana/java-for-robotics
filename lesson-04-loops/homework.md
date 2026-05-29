# Lesson 04 — Homework

About 30 minutes. The files you need are in this lesson's `code/` subfolder.

## 1. Build a loop of your own

Write a new program that uses a loop. Pick whichever fits your idea:
- a `for` loop that prints the times-table for a number the user enters (e.g. 7 × 1, 7 × 2, … 7 × 10), or
- a `while` loop that keeps adding 0.1 to a motor power starting at 0.0 and prints each value until it reaches 1.0.

Make it yours — the goal is one working loop that you wrote from scratch.

## 2. Prediction exercise

Before running `PredictLoops.java`, write down everything it prints, in order. Then run it and check.

```java
public class PredictLoops {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Lap " + i);
        }

        int count = 0;
        while (count < 2) {
            System.out.println("Charging...");
            count++;
        }
        System.out.println("Done");
    }
}
```

## 3. Error detective

`LoopBugHunt.java` has three problems. **Two** stop it from compiling (they give error messages). **One** has no error message at all — it compiles fine but runs forever. Find all three, fix them, and write down what each compiler error said and what the third bug was.

```java
public class LoopBugHunt {
    public static void main(String[] args) {
        int count = 5;

        while (count > 0) {
            System.out.println("Counting: " + count)
        }

        for (int i = 0, i < 3, i++) {
            System.out.println("Step " + i);
        }
    }
}
```

Warning: if you run it before fixing the third bug, it will print forever — press **Ctrl+C** in the terminal to stop it.

(One line is missing a semicolon; the `for` header uses the wrong separators; and the `while` loop never changes `count` — but try to find them yourself before checking the solutions.)
