# Lesson 13 — An Object That Protects Itself

## Lesson Theme

For two lessons, an object's fields have been wide open — anyone could write `motor.power = 50.0`, a value a real motor would flatly reject. The object had no say in its own data. Today it gets one. You'll lock the fields with **`private`** so no outsider can touch them, and open a controlled **`public`** doorway — a `getPower` to read and a *guarded* `setPower` to write — that refuses illegal values. This is **encapsulation**, and it's the first time you'll *feel* why hiding data is one of the best ideas in programming, not just a rule someone made up.

## What You'll Build

- `Motor.java` — a motor whose `name` and `power` are `private`, with a `setPower` that **clamps** any value into the legal −1.0…1.0 range, plus `getPower`/`getName` to read safely.
- `MotorDemo.java` — sets a good power, then tries an illegal `3.0` and watches the object quietly clamp it to `1.0`, refusing to hold a bad value.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- Still pure Java — no simulator yet

## What You'll Learn

**Java skills:**
- `private` — a field only the object's own methods can touch
- `public` — the controlled doorway other code is allowed to use
- **Getters** (read a private field) and **setters** (write it — *with a guard*)
- Why a guarded setter is safer than a bare, open field

**Thinking skills:**
- Encapsulation as *protecting an object's integrity* — making bad states impossible
- The design instinct: don't trust callers to always pass good data; let the object defend itself
- The felt answer to "why does hiding data actually help?"

## In Class

A 60-minute session.

### The open-door problem (7 min)

Recap: your objects have fields, and any code can read or write them directly — `left.power = 0.5`. Now push it: what's stopping someone writing `left.power = 50.0`? Nothing. A real FTC motor only accepts a power between **−1.0 and 1.0**; hand it `50.0` and at best it ignores you, at worst you get strange behaviour mid-match. The bare field trusts every caller to be careful — and callers make mistakes. There has to be a way for the object to *refuse* bad data. There is.

### The Scratch bridge (8 min)

Think back to Scratch. A sprite's "for this sprite only" variables could only be changed by *that sprite's own scripts*. No other sprite could reach across the stage and set them directly — if it wanted a change, it sent a message and let the owner's scripts decide.

That is encapsulation exactly:

| Scratch | Java |
|---|---|
| "for this sprite only" variable | a **`private`** field |
| only the sprite's own scripts change it | only the class's own methods touch it |
| other sprites send a message | other code calls a **`public`** method |
| the owner's script decides what to do | the method **guards** the change |

You've lived this rule before. Java just gives it two keywords: `private` and `public`.

### Locking the fields (12 min)

Open `Motor.java` and read the top:

```java
public class Motor {

    private String name;
    private double power;
    ...
```

`private` means: **only Motor's own methods can touch `name` and `power`.** Any code outside the class is locked out — it can't read them, can't write them. That sounds restrictive, and that's the point: the object stops trusting outsiders with its raw data.

But if nobody outside can touch `power`, how does the driver set the motor's power? Through a **doorway** — a `public` method the class chooses to expose.

### The guarded doorway (15 min)

Here's the doorway, and the whole idea of the lesson:

```java
public void setPower(double newPower) {
    if (newPower > 1.0) {
        newPower = 1.0;
    }
    if (newPower < -1.0) {
        newPower = -1.0;
    }
    this.power = newPower;
}
```

`setPower` is `public`, so anyone can *call* it — but they can't set `power` directly, they can only *ask* `setPower` to. And `setPower` **guards** the value first: anything above `1.0` becomes `1.0`, anything below `−1.0` becomes `−1.0`. Only then does it touch the field. The object literally cannot hold an illegal power, no matter what a caller hands it.

To read the value, there's a getter:

```java
public double getPower() {
    return this.power;
}
```

Now run `MotorDemo.java`:

```
left_motor at power 0.5
left_motor at power 1.0
Reading power through the doorway: 1.0
```

The `0.5` went in fine. The illegal `3.0`? Clamped to `1.0` — the guard caught it. And `getPower()` reads the safe value back. The motor defended itself, and the caller never even had to know.

One more thing to try live: uncomment (or type) `left.power = 3.0;` in `MotorDemo` and compile. It **won't build**:

```
error: power has private access in Motor
```

The compiler slams the door. That error isn't a problem — it's encapsulation *working*. There's simply no way in except through the doorway you designed.

### Your turn (10 min)

Add a second guarded doorway to `Motor.java`:

1. Add a `private int` field `encoderTicks` (a position counter), starting at `0` in the constructor.
2. Add a `public void addTicks(int ticks)` that only ever *increases* the count — if someone passes a negative number, ignore it (add `0`). Add a `getEncoderTicks()` to read it.
3. In `MotorDemo`, prove it: add some ticks, try to add a negative number, and confirm the count never went down.

The habit you're building: every field is `private`, and every way to change it is a method that *checks first*.

### Why this matters (the bridge) (3 min)

In Tier 4, the real FTC `DcMotor` class does precisely this. Its power is private; you set it through `setPower(...)`, and the SDK guards the range for you. You never reach inside a motor object and flip a raw field — you go through its doorways. Every well-built mechanism class you write for a robot will follow the pattern you just learned: private fields, public guarded methods.

### Reflection (2 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. What does `private` stop other code from doing, and why is that a *good* thing here?
2. What is a "guard" in a setter, and what did `setPower` do with the illegal value `3.0`?
3. In the Scratch picture, what plays the role of a `private` field, and what plays the role of a `public` method?

## Stretch Project (Optional)

Finished early? Build an object that guards a value from *both* sides.

**Fuel Tank.** Write a new blueprint `FuelTank.java` with a `private String name` and a `private int level` (0 to 100). Give it:
- a constructor starting `level` at `0`,
- `public void fill(int amount)` that adds fuel but never lets `level` go above `100` (cap it),
- `public void use(int amount)` that removes fuel but never lets `level` go below `0` (floor it),
- a `public int getLevel()` and a `printStatus()`.

Then write `FuelDemo.java` that over-fills the tank (e.g. fill 70, then 50) and confirms it stops at `100`, and over-uses it (e.g. use 30, then 200) and confirms it stops at `0`. A worked version is in `solutions.md` — try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `Motor.java` — private fields, a clamping `setPower`, and `getPower`/`getName` accessors
- `MotorDemo.java` — sets a legal power, then an illegal one, and shows the guard clamp it

Your homework files (`PredictPrivate.java`, `PrivateBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

Reminder — two-file compile:
```
javac Motor.java MotorDemo.java
java MotorDemo
```

## Notes for the Teacher

**Setup check before the session:**
- Same two-file compile as Lessons 11–12. Make sure students actually *try* the `left.power = 3.0;` line and see the `private access` compile error with their own eyes — that error is a highlight of the lesson, not an accident to avoid.

**On the felt-need (don't skip it):**
- Course outcome #5 is "articulate *why* OOP helps — from felt experience, not a definition." This lesson is where that starts to pay off. Spend real time on the open-door problem *before* showing `private`. If a student can say "the object won't let me give it a bad value," the concept has landed.

**On getters/setters (keep it honest):**
- A getter that just `return`s and a setter that just assigns can feel like pointless ceremony — and if there's no guard, it nearly is. The whole justification is the **guard**: `setPower` isn't ceremony, it's a bouncer. Lead with the clamping example, not with a bare getter/setter, or the idea feels like busywork.
- If a student asks "why not just make the field public and be careful?" — "Because *every* piece of code that touches it has to be careful, forever, including code you haven't written yet. Put the check in one doorway and it's guaranteed everywhere."

**On scope (deliberately held back):**
- **No `static final` named constants yet.** The clamp bounds are plain `1.0` / `-1.0` literals on purpose. Named caps-constants (`private static final double MAX_POWER = 1.0;`) arrive in Tier 4, so we don't have to explain `static` here. If a student flags the "magic numbers," say "good eye — we'll give those names later; for now the comment says what they mean."
- **No `package` / `import`** — still single-folder pure Java.
- **No inheritance.** `Motor` stands alone.
- **Getters/setters written by hand**, not generated by the IDE — students should feel the pattern before they automate it.

**Anticipated questions:**
- *"Why is the constructor `public`?"* — "So other code is allowed to *create* a Motor with `new`. If the constructor were private, nobody outside could make one. Public constructor, private fields — you can build one, but you can't reach inside it."
- *"Does `getPower` need a guard too?"* — "No — reading a value can't corrupt it. Guards go on the ways *in* (setters), not the ways *out* (getters)."
- *"What if I want a field nobody can change from outside at all?"* — "Then don't write a setter — only a getter, like the `Score` class in your homework. Encapsulation lets you allow reading but forbid writing."

**Common mistakes to watch for:**
- Marking the fields `private` but forgetting to make the methods `public` — then the demo can't even call `setPower`. Both keywords matter.
- Putting the guard *after* assigning the field (`this.power = newPower;` then the `if`s) — the field briefly held a bad value and, worse, the guard never corrects it. Guard first, assign last.
- Writing a getter that returns the wrong field, or a setter with no `this.` (a callback to Lesson 12's shadowing bug — it can resurface here).
