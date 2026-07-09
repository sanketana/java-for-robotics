# Lesson 08 — Combining Conditions

## Lesson Theme

In Lesson 3 your `if` statements asked **one** question: `if (power > 0)`. But real robot decisions are rarely that simple. "Raise the arm only if the driver is holding the button **and** the arm isn't already at the top." "Warn the driver if the battery is low **or** time is almost up." Today you learn to combine conditions with `&&`, `||`, and `!` — the exact same green `and` / `or` / `not` blocks you snapped together in Scratch.

## What You'll Build

- `SafetyCheck.java` — a robot guard that decides what's allowed by combining several true/false facts.
- `RangeChecker.java` — a method `isInRange(...)` that **returns a `boolean`** and is then used directly inside an `if`. This pulls together return values (Lesson 6), typed returns (Lesson 7), and today's logic into one tidy idea.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — still pure Java

## What You'll Learn

**Java skills:**
- `&&` (and) — true only when **both** sides are true
- `||` (or) — true when **at least one** side is true
- `!` (not) — flips a `boolean` (`!true` is `false`)
- Combining comparisons into one test: `value >= low && value <= high`
- Storing a true/false result in a `boolean` variable, and **returning** one from a method

**Thinking skills:**
- Translating an everyday rule ("only if X and not Y") into a precise boolean expression
- Choosing `&&` vs `||` deliberately — and seeing how the wrong one becomes a silent bug

## In Class

A 60-minute session.

### Recap and the felt need (7 min)

Recap Lesson 3: `if` runs a block when a condition is true. But take a real rule: *"Raise the arm only if the driver is holding the button and the arm is not already at the top."* That's **two** conditions joined by "and", plus a "not". With only single tests, you'd have to nest `if` inside `if` — clumsy and hard to read.

You already solved this in Scratch with the green hexagonal blocks. Java has them too:

| Scratch | Java |
|---|---|
| `< > and < >` | `&&` |
| `< > or < >` | `\|\|` |
| `not < >` | `!` |
| a hexagon block reporting true/false | a method that returns a `boolean` |

### `&&` and `||` (13 min)

Open `SafetyCheck.java`. Walk through the first two:

```java
boolean canRaiseArm = buttonHeld && !armAtTop;
boolean shouldWarn  = battery < 20 || armAtTop;
```

The rules to land:
- `&&` is strict: **both** sides must be true, or the whole thing is false.
- `||` is generous: it's true if **either** side (or both) is true; only false when *both* are false.

Try changing the starting values at the top (`buttonHeld`, `armAtTop`, `battery`) and predicting each result before you run. Note that `&&` and `||` always produce a `boolean` — the same kind of value as a single comparison, just built from more than one.

### `!` — flipping a boolean (8 min)

`!` reads as "not" and flips true/false:

```java
boolean armAtTop = false;
boolean armIsDown = !armAtTop;   // true
```

It shines when a variable is named for the *opposite* of what you want to test. We have `armAtTop`, but the rule cares about "not at the top" — so we write `!armAtTop` rather than inventing a second variable. Point out the readable phrasing: `buttonHeld && !armAtTop` reads almost like the English rule.

### Boolean variables and a boolean-returning method (15 min)

Here's the session's centrepiece. A method can do the true/false reasoning and **hand back the answer** — exactly like a Scratch hexagon reporter. Open `RangeChecker.java`:

```java
public static boolean isInRange(int value, int low, int high) {
    return value >= low && value <= high;
}
```

The return type is `boolean`. Inside, the `&&` expression produces true or false, and `return` hands it back. Now `main` can drop the method straight into an `if` — no extra variable needed:

```java
if (isInRange(armPosition, 0, 1000)) {
    System.out.println("Arm position " + armPosition + " is safe.");
}
```

Read it aloud: "if the arm position is in range, say it's safe." This is the pattern you'll use constantly in real OpModes — a well-named boolean method makes the `if` read like a sentence.

### Your turn (12 min)

Write a short program with your own boolean-returning guard:

1. `bothPressed(boolean buttonA, boolean buttonB)` — returns true only when both are pressed (`&&`).
2. `isUnsafe(int temperature)` — returns true when the temperature is too high **or** too low (e.g. `> 80 || < 10`), using `||`.

Call each from `main` with a few different inputs and print the results. Then use one of them inside an `if` so it controls a printed message.

### Why this matters (the bridge) (5 min)

Every TeleOp program you'll write in Tier 4 is full of these: "if gamepad button A **and** the lift is down, raise it." Every autonomous decision is a boolean. The habit of naming a clear boolean method — `isInRange`, `isUnsafe` — is what keeps that robot code readable instead of a tangle of nested `if`s.

### Reflection (5 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. In your own words: when is `&&` true, and when is `||` true?
2. Why is `buttonHeld && !armAtTop` easier to read than nesting one `if` inside another?
3. A method `isInRange` returns a `boolean`. How is that like a Scratch hexagon ("reporter") block?

## Stretch Project (Optional)

Finished your safety guard early? Combine several true/false checks into one overall decision — the way a real robot decides whether it's cleared to start a match.

**Pre-Match Safety Check.** Write a new program, `PreMatchCheck.java`, that decides whether the robot is `clearedToStart`. Write two boolean-returning helper methods:
- `boolean isBatteryOk(int voltage)` — true when voltage is at least 11.
- `boolean sensorsReady(boolean camera, boolean imu)` — true only when **both** sensors are online (`&&`).

In `main`, set up the robot's state, then build one combined boolean with `&&`: the robot is cleared only if the battery is OK **and** the arm is stowed **and** the sensors are ready. Print each check and the final verdict, and use `!` to print a `HOLD:` warning when it's *not* cleared.

A worked version is in `solutions.md` — try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `SafetyCheck.java` — combining true/false facts with `&&`, `||`, `!`
- `RangeChecker.java` — a `boolean`-returning method used directly inside an `if`

Your homework files (`PredictBooleans.java`, `BooleanBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Setup check before the session:**
- No new tools. Same compile-and-run workflow as Lessons 1–7.

**On scope (deliberately held back):**
- **Only `&&`, `||`, `!`** and comparisons that build booleans. Do not teach short-circuit evaluation (the fact that `&&` stops early) — students will *encounter* it, not study it. If asked, one sentence: "Java stops checking as soon as it knows the answer; that matters later, not now."
- **No bitwise `&` / `|`.** If a student types a single `&`, steer them straight back to `&&` without explaining the difference.
- **No ternary `?:`**, no formal truth tables or De Morgan's laws. "Try all the true/false combinations and see" is the right level.
- **The modulo `%` operator is still not introduced** — keep boolean methods built from comparisons (`>`, `<`, `>=`, `==`), not `n % 2 == 0`.

**On the `=` vs `==` trap (important):**
- `if (temperature = 50)` uses a single `=` (assignment) instead of `==` (comparison). Because `temperature` is an `int`, Java catches this: `incompatible types: int cannot be converted to boolean`. Tie the fix to "one `=` means *put this value in*; two `==` means *are these equal?*" Note for yourself: if the variable were a `boolean`, this same slip would **not** error — but that subtlety is beyond today; the `int` case gives a clean teachable error.

**Anticipated questions:**
- *"What's the difference between `&` and `&&`?"* — "Use `&&` for true/false logic. The single `&` is a different, lower-level operator we don't need."
- *"Can I combine more than two conditions?"* — "Yes: `a && b && c`, or mix `&&` and `||`. When you mix them, use brackets to make the grouping obvious, e.g. `(a && b) || c`."
- *"Is `!` the same as `not equal`?"* — "Close but different. `!` flips a whole boolean. `!=` is its own operator meaning 'not equal to'. `!(x == 5)` and `x != 5` mean the same thing."

**Common mistakes to watch for:**
- `=` where `==` was meant (the bug hunt; compiler-caught for `int`s).
- Choosing `||` when the rule needs `&&` (or vice versa). This is a **silent** logic bug — no error, just a wrong answer. The bug hunt includes one (`temperature > 10 || temperature < 50` is true for *every* number). Continue the Lesson 7 habit: read your output critically, because the compiler can't catch a wrong choice of operator.
- A `boolean`-returning method with a branch that doesn't `return` → `missing return statement` (also in the bug hunt). Same iterative-compile behaviour as before: the compiler reveals it only after the `=`/`==` error is fixed.
- Writing `if (isInRange(...) == true)`. It works, but it's redundant — `if (isInRange(...))` already *is* the boolean. Nudge toward the cleaner form.
