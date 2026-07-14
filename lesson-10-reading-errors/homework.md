# Lesson 10 — Homework

About 30–40 minutes. The file you need is in this lesson's `code/` subfolder.

Today's homework is about **writing a fresh program**, then reading errors like a mechanic reads a diagnostic: name the family (compile-time or run-time), find the line, fix the cause.

## 1. Write a clean program, then break it on purpose

Write a brand-new program `RobotStats.java` that works first:

- Make three `int` variables: `robots = 4`, `wins = 18`, `matches = 6`.
- Print `Robots on team: 4`, `Wins: 18`, and `Average wins per match: 3` (that's `wins / matches`).
- Run it and confirm the clean output.

Now, on a **copy**, break it three ways — one at a time, undoing each before the next — and for **each** break write down two things *before you run it*: (a) is this a compile-time error or a runtime crash? (b) what line, and roughly what message?

1. Delete the semicolon after `int wins = 18`.
2. Change `matches` to `Matches` in the `wins / matches` line.
3. Set `matches = 0` and run.

Then run each and check your two predictions. The goal is getting the *prediction* right, not the fix.

## 2. Prediction table

For each snippet below, **predict two things without running it**: the family (compile-time / run-time) and the message you'd expect. Then, if you like, type each into a tiny program to check.

**Snippet A**
```java
int power = 100
System.out.println(power);
```

**Snippet B**
```java
int speed = 60;
System.out.println(sped);
```

**Snippet C**
```java
String name = 42;
System.out.println(name);
```

**Snippet D**
```java
int robots = 0;
int perRobot = 300 / robots;
System.out.println(perRobot);
```

(Hint: three of these never run at all. One runs, then crashes. Which is which?)

## 3. Error detective — three faults, one at a time

`TripleFault.java` (in the `code/` subfolder) is broken in more than one way. You will fix it in **stages**, because Java only shows you the errors it has reached so far — fix the top one, recompile, and the next reveals itself.

```java
public class TripleFault {

    public static void main(String[] args) {
        int robots = 0
        int scored = totalScore(robots);
        System.out.println("Total score: " + scored);
    }

    public static int totalScore(int robotCount) {
        int totalPoints = 120;
        return totalPoints / robotCont;
    }
}
```

Work through it in order, writing down what you find at each stage:

1. Compile it. Read the first error — which line, which family, what message? Fix just that one.
2. Compile again. A **different** error now appears (it was hiding behind the first). Which line, and what does `cannot find symbol` mean here? Fix it.
3. Compile again. This time it **builds** — but now *run* it. A **runtime crash** appears. Which family is it, which line, and *why* did the compiler never warn you about it?

By the end you'll have walked the full journey: two compile-time errors, then one runtime crash — the two families from class, in a single file.
