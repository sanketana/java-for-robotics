# Lesson 10 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. The whole skill this lesson builds is reading a message and predicting the fix yourself — do that before you look here.

## 1. Write a clean program, then break it on purpose

One correct version of `RobotStats.java`:

```java
public class RobotStats {

    public static void main(String[] args) {
        int robots = 4;
        int wins = 18;
        int matches = 6;

        System.out.println("Robots on team: " + robots);
        System.out.println("Wins: " + wins);
        System.out.println("Average wins per match: " + (wins / matches));
    }
}
```

Clean output:

```
Robots on team: 4
Wins: 18
Average wins per match: 3
```

The three breaks:

1. **Delete the semicolon after `int wins = 18`** — **compile-time**, no output at all:
   ```
   RobotStats.java:5: error: ';' expected
   ```
2. **Change `matches` to `Matches`** — **compile-time**, no output:
   ```
   RobotStats.java: error: cannot find symbol ... variable Matches
   ```
   Java is case-sensitive: `Matches` is a name it was never introduced to.
3. **Set `matches = 0`** — **run-time**. It compiles, prints the first two lines, *then* crashes:
   ```
   Robots on team: 4
   Wins: 18
   Exception in thread "main" java.lang.ArithmeticException: / by zero
   ```
   The tell: breaks 1 and 2 printed nothing (never ran); break 3 printed output first, then crashed.

## 2. Prediction table

| Snippet | Family | Message |
|---|---|---|
| **A** `int power = 100` (no `;`) | Compile-time | `';' expected` |
| **B** `println(sped)` (typo of `speed`) | Compile-time | `cannot find symbol ... variable sped` |
| **C** `String name = 42;` | Compile-time | `incompatible types: int cannot be converted to String` |
| **D** `300 / robots` with `robots = 0` | **Run-time** | `ArithmeticException: / by zero` |

Three of the four (A, B, C) are compiler errors — the program never runs, so nothing prints. Only **D** compiles and runs, printing nothing before it happens to hit the division, then crashing. That's the one-line summary of the whole lesson: the compiler catches typos and type mistakes *before* running; only the running program can discover a value it didn't like.

## 3. Error detective — three faults, one at a time

**Stage 1 — compile as given.** Only the first error shows, because the parser stops at the broken line:

```
TripleFault.java:4: error: ';' expected
        int robots = 0
                      ^
```
Line 4, **compile-time**. Add the missing semicolon: `int robots = 0;`

**Stage 2 — compile again.** A new error, which was hiding behind the first:

```
TripleFault.java:11: error: cannot find symbol
        return totalPoints / robotCont;
                             ^
  symbol:   variable robotCont
```
Line 11, **compile-time**. `cannot find symbol` means you used a name Java never met — `robotCont` is a typo of the parameter `robotCount`. Fix it to `robotCount`.

**Stage 3 — compile again: it builds.** Now *run* it, and it crashes:

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at TripleFault.totalScore(TripleFault.java:11)
	at TripleFault.main(TripleFault.java:5)
```
**Run-time**, line 11. Why no compiler warning? Because `robots` is `0`, so `totalScore` divides `120 / 0` — but the compiler can't know a variable's *value* ahead of time; only the running program discovers it's zero. Reading the trace top-down: it blew up in `totalScore` (line 11), which was called from `main` (line 5). To fix it for real you'd give `robots` a non-zero value (or guard against zero before dividing).

The full journey in one file: `';' expected` → `cannot find symbol` → builds → `/ by zero`. Two compile-time errors surfacing one at a time, then one runtime crash — both families from class.

## Stretch Project — Sample Solution

Try it yourself first. `ScorePipeline.java`:

```java
public class ScorePipeline {

    public static void main(String[] args) {
        System.out.println("Loading scores...");
        int total = totalFromRaw("10", "20", "3x");
        System.out.println("Total: " + total);
    }

    public static int totalFromRaw(String a, String b, String c) {
        return toNumber(a) + toNumber(b) + toNumber(c);
    }

    public static int toNumber(String raw) {
        return Integer.parseInt(raw);
    }
}
```

Run it and it prints `Loading scores...`, then crashes:

```
Exception in thread "main" java.lang.NumberFormatException: For input string: "3x"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
	at java.base/java.lang.Integer.parseInt(Integer.java:668)
	at java.base/java.lang.Integer.parseInt(Integer.java:786)
	at ScorePipeline.toNumber(ScorePipeline.java:14)
	at ScorePipeline.totalFromRaw(ScorePipeline.java:10)
	at ScorePipeline.main(ScorePipeline.java:5)
```

How to read it: the top three lines start with `java.base/...` — that's **Java's own code** doing the number-parsing, not yours. Skip past them to the **first line that names your file**: `ScorePipeline.toNumber(ScorePipeline.java:14)`. That's where your bug lives — `toNumber` tried to `parseInt("3x")`, and `"3x"` is not a whole number, so it threw a `NumberFormatException`. The frames below (`totalFromRaw`, then `main`) show how the program got there: `main` called `totalFromRaw`, which called `toNumber` with the bad value `"3x"`.

The lesson of the stretch: real stack traces often start deep inside Java's libraries. Don't panic at the `java.base` lines — **scan down to your own file name**, and that's where you start fixing.
