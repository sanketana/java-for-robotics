# Lesson 06 — Homework

About 30–40 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is all about **writing fresh programs** that use parameters and return values. Build each one, run it, and check the output is what you expected.

## 1. The greeter

Write a brand-new program `Greeter.java`.

- Write a `void` method `greet(String name)` that prints `Hello, <name>! Ready to drive?` using the name passed in.
- From `main`, call `greet` three times with three different names.

The whole point: one method, three different inputs, three different greetings — no copy-pasting.

## 2. The calculator method

Write a brand-new program `AreaCalculator.java`.

- Write a method `rectangleArea(int width, int height)` that **returns** `width * height`. (Return type `int`, not `void`.)
- In `main`, call it for at least three different rectangles. For each one, store the result in a variable and print a line like `A 4 by 6 rectangle has area 24`.

Make sure you *catch* the returned value in a variable — don't just call the method and throw the answer away.

## 3. The decision reporter

Write a brand-new program `BatteryCheck.java`.

- Write a method `int higherCharge(int robotA, int robotB)` that **returns** the larger of the two battery percentages passed in. (Hint: use an `if/else` with a `return` in each branch, like `higher` from class.)
- In `main`, call it with two numbers, store the answer, and print `The fuller battery is at <answer>%`.

## 4. Prediction exercise

Before running `PredictReturns.java`, write down exactly what it prints, in order. Then run it and check.

```java
public class PredictReturns {

    public static void main(String[] args) {
        int a = doubleIt(4);
        int b = doubleIt(a);
        System.out.println(a);
        System.out.println(b);
        System.out.println(doubleIt(10));
        greet("Asha");
    }

    public static int doubleIt(int n) {
        return n * 2;
    }

    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
```

(Hint: work out what each `doubleIt(...)` hands back *before* the line prints. Notice that `b` is built from `a`.)

## 5. Error detective

`ParamBugHunt.java` has three errors, one for each idea from today. Find them, fix them, and write down what each error message said.

```java
public class ParamBugHunt {

    public static void main(String[] args) {
        printDivider();
        int total = addUp(5, 7);
        System.out.println("Total: " + total);
        celebrate(2026);
    }

    public static void printDivider(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static int addUp(int a, int b) {
        System.out.println("Adding...");
    }

    public static void celebrate(String team) {
        System.out.println("Great job, " + team + "!");
    }
}
```

(One method is called with no argument when it needs one; one method promises to return an `int` but never does; and one method is given the wrong *type* of argument. Try to find each one from the error message before checking the solutions.)

A heads-up that's worth noticing: Java won't show you all three at once. The first compile reports two of them. Fix those, compile *again*, and the third error appears. That's normal — real debugging is often "fix what you can see, recompile, repeat." Write down all three messages as you go.
