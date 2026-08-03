# Lesson 12 — What the Object Remembers

## Lesson Theme

Last lesson you built blueprints and stamped out objects. This lesson goes *inside* an object to answer two questions that trip up almost every new Java programmer: **what does an object remember, and for how long?** A *field* lives as long as the object does; a *local variable* is born and dies inside a single method call. Confuse the two and you get the strangest kind of bug — one where your code runs perfectly, never crashes, and simply *does nothing*. The word `this`, which you copied on faith last lesson, is what keeps the two straight.

## What You'll Build

- `Lift.java` — a robot lift whose height (`position`) is a **field**, so it *remembers* and adds up across many `raise(...)` calls.
- `LiftDemo.java` — raises one lift twice, watches its height climb `0 → 100 → 150`, then renames it — proving the object holds onto its data between calls.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- Still pure Java — no simulator yet

## What You'll Learn

**Java skills:**
- The difference between a **field** (belongs to the object; lives as long as the object) and a **local variable** (belongs to one method call; vanishes when the method returns)
- **Variable shadowing** — when a parameter or local has the same name as a field, it *hides* the field inside that method
- What `this` really does: reach past a look-alike local to **the object's own field**
- Why `position = position` or a stray `int position = ...` inside a method is a silent do-nothing bug

**Thinking skills:**
- Tracing *where a value lives and how long it survives* — the mental model behind every object
- Recognising the "I wrote to a local instead of the field" bug on sight, without a crash to guide you

## In Class

A 60-minute session.

### Recap and a puzzle (6 min)

Recap: last lesson, `Robot` had fields (`name`, `score`) and each object kept its own. Now a puzzle to open the session. Here's a lift with a `raise` method — but written a slightly different way:

```java
void raise(int amount) {
    int position = this.position + amount;   // looks reasonable...
}
```

Ask the class: *if I raise this lift by 100, then by 50, what height does it end at?* Take a vote. Most will say 150. Hold that prediction — by the end you'll see it actually ends at **0**, and exactly why.

### Fields vs locals (12 min)

Two kinds of variable, and the difference is *lifetime*:

| | Field (instance variable) | Local variable |
|---|---|---|
| Declared | at the top of the class, outside any method | inside a method (or as a parameter) |
| Belongs to | the **object** | one **method call** |
| Lives | as long as the object exists | only until that method returns |
| Seen by | every method in the class | only the method it's declared in |

Open `Lift.java`. `name` and `position` are **fields** — declared at the top, not inside any method. That's why `raise` can add to `position` and `printStatus` can read it later: the field outlives each call. It's the object's memory.

A **local** is different. If you declare `int target` inside `raise`, it exists only while `raise` runs; the moment `raise` returns, `target` is gone. It can't remember anything for next time, and no other method can see it. Locals are scratch paper; fields are the notebook.

This is why last lesson's running score *worked*: `score` was a field, so `addScore` could keep adding to the same remembered value. If `score` had been a local inside `addScore`, it would reset to nothing every call.

### The correct lift, and why it remembers (10 min)

Now read the real `Lift.java`:

```java
public class Lift {

    String name;
    int position;

    Lift(String name) {
        this.name = name;
        this.position = 0;
    }

    void raise(int amount) {
        this.position = this.position + amount;
    }
    ...
}
```

`raise` writes `this.position = this.position + amount`. `this.position` is *the field* — the lift's remembered height. So raising by 100 then 50 leaves the field at 150. Run `LiftDemo.java`:

```
arm_lift at height 150
main_lift at height 150
```

The height climbed and *stayed* climbed between calls, because it lives in a field. That's the object remembering.

### What `this` is really for (12 min)

Look at `rename`:

```java
void rename(String name) {
    this.name = name;
}
```

Here's the subtlety. The parameter is *also* called `name`. Inside this method, the plain word `name` now means **the parameter**, not the field — the parameter **shadows** (hides) the field. So:

- `name` = the parameter (the new value handed in)
- `this.name` = the object's own field

`this.name = name` reads as "set **my own** name field to the value I was handed." That's what `this` is for: when a local or parameter has the same name as a field, `this.` is how you reach the field anyway.

Now return to the opening puzzle. That broken `raise` was:

```java
void raise(int amount) {
    int position = this.position + amount;   // declares a NEW local called position
}
```

`int position = ...` declares a brand-new **local** variable called `position`. It computes the right number — and then the method ends and the local is thrown away. The **field** `this.position` was never touched. Raise it a hundred times and it still reads 0. No crash, no error, no output clue — the lift just never moves. This is the single most common object bug in Java, and now you can see it: *the code wrote to a local instead of the field.*

### Your turn (10 min)

In `LiftDemo`, prove the lifetime rules yourself:

1. Create a second lift with a different name. Raise it by amounts of your choosing, and confirm its height is completely independent of the first lift's.
2. Add a `lower(int amount)` method to `Lift.java` that *subtracts* from `this.position`. Raise a lift to some height, lower it, and print — confirm the field tracks the running total correctly across both kinds of call.

The point in your fingers: as long as it's a field and you write to `this.position`, the object keeps a faithful running memory.

### Why this matters (the bridge) (3 min)

In Tier 4, a real lift or arm on an FTC robot works exactly this way: an object with a `position` field that persists across the whole match, updated call after call as the driver moves it. And the "wrote to a local, motor never moved" bug is one your future FTC self *will* hit. You just learned to see it before it wastes an afternoon.

### Reflection (2 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. In your own words, what is the difference in *lifetime* between a field and a local variable?
2. In `rename(String name)`, what does plain `name` refer to, and what does `this.name` refer to?
3. Why does `int position = this.position + amount;` inside a method leave the object's height unchanged?

## Stretch Project (Optional)

Finished early? Build an object that has to remember **two** things at once, across a whole run.

**Odometer.** Write a new blueprint `Rover.java` with a `String name` and **two** `int` fields that persist: `distance` (total cm travelled) and `batteryUsed`. Give it a constructor that starts both counters at `0`, and a method `void move(int cm)` that adds `cm` to `distance` **and** adds `cm / 10` to `batteryUsed` (a rough "battery cost" — remember integer division from Lesson 7). Add a `printStatus()`. Then write `RoverRun.java` that creates **two** rovers, moves each a few times by different amounts, and prints both. Confirm each rover's two counters climb independently of the other rover's. A worked version is in `solutions.md` — try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `Lift.java` — a lift blueprint whose `position` field persists across `raise` calls; shows `this` resolving a name clash in `rename`
- `LiftDemo.java` — raises and renames one lift to show the field remembering

Your homework files (`PredictThis.java`, `ThisBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

Reminder — two-file compile:
```
javac Lift.java LiftDemo.java
java LiftDemo
```

## Notes for the Teacher

**Setup check before the session:**
- Same two-file compile as Lesson 11. `Lift.java` and its demo must sit in the same folder.

**On running the opening puzzle:**
- Actually take the vote on "what height?" *before* revealing anything, and don't correct it yet. The gap between "obviously 150" and the real answer "0" is what makes the fields-vs-locals distinction land. Reveal it at the end of the `this` block, not before.

**On `this` (now the full story):**
- Last lesson `this` was "my own field," taught on faith. This lesson earns it: `this` matters *specifically* when a parameter or local shadows a field. The two live demos are the self-shadowing `raise` (writes a local) and `rename` (parameter shares the field's name). If a student asks "could we just name the parameter something else, like `newName`, and skip `this`?" — yes! Say so: "Absolutely — many programmers do. `this` is what lets you reuse the name cleanly, and you'll see both styles in real code, so you need to read it." That's the honest answer and it demystifies `this`.

**On the silent-bug theme:**
- This bug has *no crash and no error message*, so the only tool is understanding scope. This deliberately reinforces the "when nothing crashes, reason about the values" instinct from earlier sessions, without making a whole lesson of debugging.

**On scope (deliberately held back):**
- **No `private` / no encapsulation yet.** That's next lesson, with its own felt-need ("who is *allowed* to change a field"). Here fields stay openly accessible; the lesson is about *lifetime and shadowing*, not access control.
- **No `package` / `import`** — still single-folder pure Java.
- **No `static`** on the blueprint's methods (only `main` in the bug-hunt file is static, as always).

**Anticipated questions:**
- *"Why not always write `this.` everywhere, even when there's no clash?"* — "You can, and some teams do for consistency. It's required only when a local or parameter shadows the field; otherwise Java already knows `position` means the field."
- *"Where does the local `position` in the broken `raise` go?"* — "It's created when `raise` starts and destroyed when `raise` ends. It never had anything to do with the field that shares its name — that's the trap."
- *"Does `printStatus` need `this.`?"* — "No — there's no local called `name` or `position` in `printStatus`, so there's nothing shadowing the fields. Plain `name` already means the field."

**Common mistakes to watch for:**
- Declaring a field's value inside a method by accident (`int position = ...`), creating a shadowing local. This is the whole lesson; the bug hunt drills it.
- Expecting a local to persist between method calls. Re-anchor: "locals are scratch paper, thrown away at the end of every call."
- Writing `name = name` in a setter and wondering why nothing changes. Have them name the two things out loud: "left side is... the parameter. Right side is... the parameter. The field never got touched."
