# Lesson 07 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. The whole point of today's lesson is the bug Java *won't* warn you about — so run your programs and read the output critically before looking here.

## 1. Motor power levels

One correct version of `PowerLevels.java`:

```java
public class PowerLevels {

    public static void main(String[] args) {
        System.out.println("Half power: " + powerLevel(1, 2));
        System.out.println("Quarter power: " + powerLevel(1, 4));
        System.out.println("Three-quarter power: " + powerLevel(3, 4));
    }

    public static double powerLevel(int numerator, int denominator) {
        return numerator / (double) denominator;
    }
}
```

Expected output:

```
Half power: 0.5
Quarter power: 0.25
Three-quarter power: 0.75
```

The fix is the `(double)` in front of `denominator` — it forces decimal maths. You could equally write a version that keeps decimals another way; what matters is that at least one number in the division is a `double`. If any line printed `0.0`, the division ran in `int` maths.

## 2. Sensor average

One correct version of `SensorAverage.java`:

```java
public class SensorAverage {

    public static void main(String[] args) {
        System.out.println("Average reading: " + averageOf(70, 71, 72));
        System.out.println("Average reading: " + averageOf(70, 71, 73));
    }

    public static double averageOf(int a, int b, int c) {
        return (a + b + c) / 3.0;
    }
}
```

Expected output:

```
Average reading: 71.0
Average reading: 71.33333333333333
```

The first average is a whole number, so it prints `71.0`. The second isn't, so you see the decimals — proof the division kept them. (The long tail of digits is normal: doubles are very precise but not perfectly exact. That's fine for now.) Note the divisor is `3.0`, not `3` — with `3`, both lines would have truncated to `71.0`.

## 3. Prediction exercise

Expected output of `PredictNumbers.java`:

```
3
3.5
2.0
2.5
1.5
```

Why:
- `a / b` is `7 / 2`, both `int` → `3` (remainder dropped).
- `a / 2.0` is `7 / 2.0`, a `double` is involved → `3.5`.
- `double c = 10 / 4;` — the right side runs first in `int` maths: `10 / 4 = 2`. *Then* it's stored as `2.0`. **The trap.**
- `double d = 10.0 / 4;` — a `double` is involved, so `2.5`.
- `half(3)` returns `3 / 2.0` → `1.5`.

## 4. Error detective

`NumberBugHunt.java` has three bugs — two the compiler catches, one it doesn't.

**Bug 1 — lossy assignment (compiler error).**
`int power = 1.0 / 2;` — the right side is a `double` (`0.5`), but `power` is an `int`, which can't hold the `.5`.
Message:
```
incompatible types: possible lossy conversion from double to int
```
Fix: make the variable a `double`.
```java
double power = 1.0 / 2;
```

**Bug 2 — lossy assignment (compiler error).**
`int avg = averageOf(80, 81);` — `averageOf` returns a `double` (`80.5`), but `avg` is an `int`.
Message:
```
incompatible types: possible lossy conversion from double to int
```
Fix: make the variable a `double`.
```java
double avg = averageOf(80, 81);
```

**Bug 3 — the silent one (no error message).**
`double quarter = 1 / 4;` — this compiles and runs happily. But `1 / 4` is `int` division, which is `0`, so it stores `0.0`. You wanted `0.25`. There is **no error** — you only catch it by noticing the printed value is wrong.
Fix: put the maths in decimals.
```java
double quarter = 1.0 / 4;
```

After all three fixes, the program prints:

```
Power: 0.5
Quarter: 0.25
Average: 80.5
```

The lesson in Bug 3: the most dangerous bugs don't announce themselves. The compiler caught the two type-mismatch slips, but it had no way to know you *meant* `0.25` when you wrote `1 / 4`. Reading your own output critically is part of the job.

## Stretch Project — Sample Solution

Try it yourself first. `SpeedCalculator.java`:

```java
public class SpeedCalculator {
    public static void main(String[] args) {
        System.out.println("Speed (100 cm in 8 s): " + speed(100, 8) + " cm/s");
        System.out.println("Gear ratio (60 : 20): " + gearRatio(60, 20));
        System.out.println("Average lap (31, 34, 30): " + averageLap(31, 34, 30) + " s");
    }

    public static double speed(int distance, int seconds) {
        return distance / (double) seconds;
    }

    public static double gearRatio(int drivenTeeth, int driverTeeth) {
        return drivenTeeth / (double) driverTeeth;
    }

    public static double averageLap(int lap1, int lap2, int lap3) {
        return (lap1 + lap2 + lap3) / 3.0;
    }
}
```

Output:

```
Speed (100 cm in 8 s): 12.5 cm/s
Gear ratio (60 : 20): 3.0
Average lap (31, 34, 30): 31.666666666666668 s
```

Every method divides `int` inputs, so every one needed protecting: `speed` and `gearRatio` cast one side with `(double)`, and `averageLap` divides by `3.0`. Drop the `(double)` or the `.0` from any of them and that line collapses to whole-number maths — `12.5` would become `12`. (The long tail on the lap average is normal: doubles are very precise, but not perfectly exact.)
