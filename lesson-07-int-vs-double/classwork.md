# Lesson 07 — When 1/2 = 0

## Lesson Theme

In Scratch, a number was just a number — `1 / 2` was always `0.5`. Java is stricter: it makes you *declare* whether a number is a whole number (`int`) or a decimal (`double`), and that choice quietly changes the maths. Today you meet the most famous bug in beginner robotics: telling a motor to run at half power and watching it sit still — because `1 / 2` in `int` maths is `0`.

## What You'll Build

Short experiments that expose the trap, then fix it:

- A program that prints `5 / 2`, `7 / 2`, `9 / 2` and shows `int` maths dropping the fraction.
- The motor-power bug: set a power of `1 / 2`, watch it become `0`, then fix it to a real `0.5`.
- `averageOf(...)` — a method that **returns a `double`** but is *still wrong* until you fix the hidden integer division inside it.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — still pure Java

## What You'll Learn

**Java skills:**
- Why `int / int` always gives an `int` — the remainder is thrown away (`9 / 2` is `4`, not `4.5`)
- How to keep the decimals by making at least one number a `double` (`9.0 / 2`)
- The silent trap `double half = 1 / 2;` — why it stores `0.0`, not `0.5`
- That a method's **return type** (`double`) doesn't protect the maths *inside* it
- (Briefly) one other way you'll see the fix written: casting with `(double)`

**Thinking skills:**
- Spotting the most dangerous kind of bug — the one that produces **no error message** and just gives a wrong answer
- Reasoning about *where* in an expression the maths happens, before the value is stored

## In Class

A 60-minute session.

### Recap and the silent bug (7 min)

Recap Lesson 6: a method can take inputs and hand a value back. Now a twist. Your teacher writes what looks like obviously correct code:

```java
int power = 1 / 2;
System.out.println("Power: " + power);
```

Predict the output together — most people say `0.5`. Run it. It prints `0`. No error, no warning, no red squiggle. On a real robot, this is `setPower(1 / 2)` — you asked for half power and the robot doesn't move. Sit in the surprise for a moment: **this is a bug Java will not warn you about.**

In Scratch this never happened — `1 / 2` was always `0.5`, because Scratch has only one kind of number. Java has two, and you have to pick.

| Scratch | Java |
|---|---|
| every number is the same kind; `1 / 2` → `0.5` | you choose: `int` (whole) or `double` (decimal) |
| `(1 / 2)` reporter → `0.5` | `1 / 2` → `0`  but  `1.0 / 2` → `0.5` |

### Integer division explored (12 min)

Open `DivisionExplorer.java` and run it. Look at the `int` block:

```
5 / 2 = 2
7 / 2 = 3
9 / 2 = 4
```

Work out the rule together: when both numbers are `int`, Java does the division and then **throws away the remainder** (it does not round — `9 / 2` is `4`, not `5`). The leftover is simply gone. This is called *integer division*, and it's not a Java mistake — it's a deliberate rule. Sometimes you even want it (how many full groups of 2 fit in 9? Four). The bug is when you *forget* it's happening.

### The double fix (13 min)

Now the bottom half of `DivisionExplorer.java`:

```
5.0 / 2 = 2.5
7.0 / 2 = 3.5
9.0 / 2 = 4.5
```

The only change is the `.0`. The rule: **if even one number in the division is a `double`, Java keeps the decimals.** Mixing a decimal and a whole number "promotes" the whole calculation to decimal.

So the motor-power fix is just:

```java
double rightPower = 1.0 / 2;   // 0.5
```

Now show the trap that catches everyone. This still prints `0.0`:

```java
double half = 1 / 2;   // still 0.0!
```

Why? Java does the `1 / 2` *first*, in `int` maths, gets `0`, and only *then* stores it in the `double`. Declaring the variable a `double` is too late — the decimals were already lost. The fix has to be on the maths itself: `1.0 / 2`.

(Mention once, then move on: you'll sometimes see this written with a *cast* — `(double) 1 / 2` — which tells Java "treat this as a decimal." Same result; the `1.0` style is cleaner for now.)

### A returning method that's secretly wrong (13 min)

Open `MotorPowerTrap.java`. Look at `averageOf`. Temporarily break it to use `2` instead of `2.0`:

```java
public static double averageOf(int a, int b) {
    return (a + b) / 2;     // broken
}
```

Call `averageOf(80, 81)`. You'd expect `80.5`. It prints `80.0`. The method's return type is `double` — and it *still* gives the wrong answer, because the division `(80 + 81) / 2` happened in `int` maths *before* it became a `double`. The return type does not protect the maths inside.

Fix it to `/ 2.0` and run again: `80.5`. This is the lesson in one example — **the type of the answer doesn't fix the type of the calculation.**

### Your turn (12 min)

Write a short program of your own with a `double`-returning method that *keeps* its decimals:

1. `threeQuarterPower()` (or similar) that returns `3.0 / 4` and prove it prints `0.75`, not `0`.
2. A method `splitEvenly(int total, int people)` that returns `total / people` as a `double` — make sure you use the `.0` trick so `splitEvenly(7, 2)` gives `3.5`, not `3.0`.

Run each one and check the decimals survived.

### Why this matters (the bridge) (3 min)

Real robots live in decimals: motor power runs `0.0` to `1.0`, sensor readings and averages are rarely whole numbers. Choosing `double` for these — and watching where your division happens — is something you'll do in every OpMode you write. Today's silent `0` is a bug you'll now catch on sight.

### Reflection (3 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. In Scratch, `1 / 2` was always `0.5`. In Java, when does `1 / 2` give `0`, and when does it give `0.5`?
2. Why was today's bug more dangerous than the bugs in the last two lessons?
3. `double half = 1 / 2;` stores `0.0`. Explain, in your own words, why declaring the variable a `double` wasn't enough.

## Stretch Project (Optional)

Done early? Build a calculator where **every** answer is a decimal — so every method is a trap waiting to happen if you forget today's lesson.

**Robot Speed Calculator.** Write a new program, `SpeedCalculator.java`, with three `double`-returning methods:
- `double speed(int distance, int seconds)` — distance divided by time (cm per second).
- `double gearRatio(int drivenTeeth, int driverTeeth)` — one gear's teeth divided by another's.
- `double averageLap(int lap1, int lap2, int lap3)` — the average of three lap times.

Each one divides `int` inputs, so each one must protect its decimals (the `.0` or `(double)` trick). Call them from `main` with values like `speed(100, 8)` and confirm you get `12.5`, not `12`. If any answer comes out whole when it shouldn't, the integer-division trap has bitten you.

A worked version is in `solutions.md` — try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `DivisionExplorer.java` — `int` division vs `double` division, side by side
- `MotorPowerTrap.java` — the `1 / 2` power bug and its fix, plus an `averageOf` method that returns a `double`

Your homework files (`PredictNumbers.java`, `NumberBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Setup check before the session:**
- No new tools. Same compile-and-run workflow as Lessons 1–6.

**On scope (deliberately held back):**
- **Only `int` and `double` today.** `boolean` logic (`&&`, `||`, `!`) is the next session; `null` is the session after. Don't tour all the primitive types — `char`, `long`, `float`, `short` are "encounter, don't teach."
- **The modulo operator `%`** (the remainder itself) is *not* taught here. If a sharp student asks "where does the thrown-away remainder go?", you can name `%` in one sentence, but don't build on it.
- **Casting `(double)`** gets exactly one mention as "another way you'll see it." The teaching fix is the decimal literal (`1.0`, `2.0`, `3.0`). Don't drill casting.
- **No floating-point precision rabbit hole.** All the in-class examples divide cleanly (`/2`) on purpose, so nothing prints an ugly `3.3333333335`. If a student stumbles onto one, the honest one-liner is "doubles are very precise but not perfect — that's a story for later," then move on.

**The big idea to land:**
- The maths happens *before* the value is stored. `double x = 1 / 2;` is `0.0` because Java evaluates the right side (`int` division → `0`) first. The fix belongs on the calculation, not the variable. This is the single most important sentence in the lesson.

**Anticipated questions:**
- *"Does Java round?"* — "No. Integer division *truncates* — it drops the remainder. `9 / 2` is `4`, not `5`. It always rounds *down* toward zero, no matter how big the remainder."
- *"Why would anyone want integer division?"* — "Sometimes you do — 'how many full teams of 4 from 9 students?' is `9 / 4 = 2`. It's only a bug when you forgot it was happening."
- *"Is it enough to make the variable a `double`?"* — "No — that's the trap. The division runs first. Put the `.0` on a number in the calculation itself."

**Common mistakes to watch for:**
- `double x = 5 / 2;` and expecting `2.5` (gets `2.0`). The number-one trap. Fix the maths: `5.0 / 2`.
- A method declared `public static double` but dividing two `int`s inside — the `averageOf` case. The return type misleads them into thinking it's safe.
- Assigning a `double` result to an `int` variable, e.g. `int p = 1.0 / 2;` → `incompatible types: possible lossy conversion from double to int`. This one the compiler *does* catch (it's in the bug hunt) — contrast it with the silent bug so students see Java catches *some* type slips but not the division one.
