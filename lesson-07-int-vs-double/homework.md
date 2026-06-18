# Lesson 07 — Homework

About 30–40 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **writing fresh programs** that work with decimals correctly. Build each one, run it, and check the decimals didn't quietly disappear.

## 1. Motor power levels

Write a brand-new program `PowerLevels.java`.

- Write a method `double powerLevel(int numerator, int denominator)` that returns the fraction as a real decimal. (Hint: dividing two `int`s won't keep the decimals — use the `.0` trick on one of them, e.g. `numerator / (double) denominator`, **or** divide and multiply so a decimal is involved.)
- From `main`, print half power, quarter power, and three-quarter power by calling it with `(1, 2)`, `(1, 4)`, and `(3, 4)`.
- Each line should read like `Quarter power: 0.25`. If you see `0.0`, the integer-division trap has bitten you — fix it.

## 2. Sensor average

Write a brand-new program `SensorAverage.java`.

- Write a method `double averageOf(int a, int b, int c)` that returns the average of three sensor readings.
- Make sure the division keeps its decimals (divide by `3.0`, not `3`).
- In `main`, call it with three readings whose average is **not** a whole number — for example `70, 71, 72` — and print `Average reading: 71.0`, then try `70, 71, 73` and confirm you get a decimal.

## 3. Prediction exercise

Before running `PredictNumbers.java`, write down exactly what it prints, line by line. Then run it and check.

```java
public class PredictNumbers {

    public static void main(String[] args) {
        int a = 7;
        int b = 2;
        System.out.println(a / b);
        System.out.println(a / 2.0);

        double c = 10 / 4;
        System.out.println(c);

        double d = 10.0 / 4;
        System.out.println(d);

        System.out.println(half(3));
    }

    public static double half(int n) {
        return n / 2.0;
    }
}
```

(Hint: the tricky one is `double c = 10 / 4;`. Work out the right side *first*, in `int` maths, before it gets stored in the `double`.)

## 4. Error detective

`NumberBugHunt.java` has three bugs. This time there's a twist worth knowing **before** you start:

> Two of these bugs the compiler will catch and refuse to run. **One it won't** — the program runs perfectly and just prints the wrong answer. That silent one is exactly what today's lesson was about. So you'll fix the two compiler errors first, run the program, and then have to *spot* that one of the printed answers is wrong.

```java
public class NumberBugHunt {

    public static void main(String[] args) {
        int power = 1.0 / 2;
        System.out.println("Power: " + power);

        double quarter = 1 / 4;
        System.out.println("Quarter: " + quarter);

        int avg = averageOf(80, 81);
        System.out.println("Average: " + avg);
    }

    public static double averageOf(int a, int b) {
        return (a + b) / 2.0;
    }
}
```

For each bug, write down: what the symptom was (an error message, or a wrong printed value), and what you changed to fix it. The expected *correct* output is:

```
Power: 0.5
Quarter: 0.25
Average: 80.5
```
