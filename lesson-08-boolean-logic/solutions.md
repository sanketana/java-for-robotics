# Lesson 08 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. Especially for the bug hunt: one bug produces no error at all, so run your code and read every printed line critically before looking here.

## 1. Drive guard

One correct version of `DriveGuard.java`:

```java
public class DriveGuard {

    public static void main(String[] args) {
        System.out.println("Can drive (button held, path clear): " + canDrive(true, true));
        System.out.println("Can drive (button held, path blocked): " + canDrive(true, false));
        System.out.println("Can drive (button up, path clear): " + canDrive(false, true));
    }

    public static boolean canDrive(boolean buttonHeld, boolean pathClear) {
        return buttonHeld && pathClear;
    }
}
```

Expected output:

```
Can drive (button held, path clear): true
Can drive (button held, path blocked): false
Can drive (button up, path clear): false
```

`&&` needs *both* to be true, so only the first line is `true`.

## 2. Temperature watch

One correct version of `TempCheck.java`:

```java
public class TempCheck {

    public static void main(String[] args) {
        System.out.println("Needs attention at 95: " + needsAttention(95));
        System.out.println("Needs attention at 40: " + needsAttention(40));
        System.out.println("Needs attention at 5: " + needsAttention(5));

        int temp = 95;
        if (needsAttention(temp)) {
            System.out.println("WARNING: temperature " + temp + " needs attention!");
        }
    }

    public static boolean needsAttention(int temp) {
        return temp > 80 || temp < 10;
    }
}
```

Expected output:

```
Needs attention at 95: true
Needs attention at 40: false
Needs attention at 5: true
WARNING: temperature 95 needs attention!
```

`95` is too hot and `5` is too cold, so both make the `||` true. `40` is comfortable, so both sides are false and the result is false.

## 3. Prediction exercise

Expected output of `PredictBooleans.java`:

```
false
true
true
true
true
true
false
```

Why:
- `a && b` → `true && false` → `false`
- `a || b` → `true || false` → `true`
- `!b` → `!false` → `true`
- `a && !b` → `true && true` → `true`
- `x > 5 && x < 10` → `true && true` → `true`
- `x > 5 || x > 100` → `true || false` → `true`
- `bothPositive(4, -2)` → `4 > 0 && -2 > 0` → `true && false` → `false`

## 4. Error detective

`BooleanBugHunt.java` has three bugs — two the compiler catches (one at a time), one it doesn't.

**Bug 1 — `=` instead of `==` (compiler error, shows first).**
`if (temperature = 50)` uses a single `=`, which *assigns* 50 rather than *comparing*. Since `temperature` is an `int`, the `if` can't use it.
Message:
```
incompatible types: int cannot be converted to boolean
```
Fix: use `==`.
```java
if (temperature == 50) {
```

**Bug 2 — missing return (compiler error, shows after Bug 1 is fixed).**
`isSafe` promises to return a `boolean`, but if `running` is false it returns nothing.
Message:
```
missing return statement
```
Fix: handle the other case.
```java
public static boolean isSafe(boolean running) {
    if (running) {
        return true;
    }
    return false;
}
```
(You could also write the whole method as just `return running;` — even cleaner.)

**Bug 3 — wrong operator (no error; silent logic bug).**
`temperature > 10 || temperature < 50` is meant to test "between 10 and 50", but with `||` it is true for **every possible number**: any number is either greater than 10 or less than 50 (most are both). So it always prints "in the safe range", even for 60 — which is clearly *not* between 10 and 50. There is no error message; you catch it only by reading the output.
Fix: the rule needs **both** to be true, so use `&&`.
```java
if (temperature > 10 && temperature < 50) {
```

After all three fixes, with `temperature = 60`:

```
Safe to run: true
```

Why so short? `60 == 50` is false (no "exactly 50" line), and `60 > 10 && 60 < 50` is false (60 isn't under 50, so no "safe range" line). Only the last line prints. Change `temperature` to `30` and run again — now the "safe range" line correctly appears. That's the proof your `&&` fix works.

The lesson from Bug 3: the compiler checked your *types* and your *returns*, but it cannot know you meant `&&` when you typed `||`. Choosing the right operator is on you — and reading your output is how you catch it.
