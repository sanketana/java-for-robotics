# Lesson 06 — Inputs and Outputs

## Lesson Theme

Last session your custom blocks were rigid: `countdown()` always counted from 5. Today they get **inputs and outputs** — you hand a method a value to work with, and a method can hand a value back to you. This is the same jump you made in Scratch when a plain "make a block" grew an input slot, and when you used a rounded **reporter block** that reports a value. It's the first idea of Tier 2, where Java starts to think a little differently from Scratch.

## What You'll Build

A small toolbox of reusable methods that take inputs and give answers back:

- `countdownFrom(int start)` — one method that replaces `countdownFrom5`, `countdownFrom3`, and every other copy you'd have made.
- `printDivider(int width)` — a divider line you can size on the fly.
- `ticksForRotations(int rotations)` — a **reporter** method that *returns* a number your `main` can store in a variable and reuse.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — still pure Java

## What You'll Learn

**Java skills:**
- Give a method an **input slot** by declaring a parameter: `countdownFrom(int start)`
- **Pass an argument** when you call it: `countdownFrom(5)`
- Replace `void` with a **return type** so a method can hand a value back: `public static int ...`
- Use the `return` keyword, and **catch the returned value** in a variable: `int ticks = ticksForRotations(3);`
- Tell apart a method that *does something* (`void`) from one that *hands something back* (a return type)

**Thinking skills:**
- Generalising: one parameterised method beats five hard-coded copies
- The **input → process → output** mental model for a method — the same shape as a Scratch reporter block

## In Class

A 60-minute session.

### Recap and the rigidity problem (7 min)

Recap Lesson 5: you can now bundle lines into named custom blocks. But your `countdown()` had a hidden weakness — it *always* counted from 5. What if you want to count from 3? With what you know so far, you'd copy the whole method and rename it `countdownFrom3()`. Count from 10 as well? A third copy. You've just reinvented the copy-paste mess that methods were supposed to kill.

You solved exactly this in Scratch. When "make a block" wasn't flexible enough, you gave it an **input slot** — a little box you type a value into when you use the block. Java has the same thing, and it's called a **parameter**.

| Scratch | Java |
|---|---|
| "make a block" with an input slot | `public static void countdownFrom(int start)` |
| dragging the block and typing `5` in the slot | `countdownFrom(5);` |
| a rounded **reporter block** that reports a value | a method with a return type: `public static int ticksForRotations(int rotations)` |
| dropping a reporter *into* another slot | `int total = ticksForRotations(3);` |

### Adding an input slot (13 min)

Your teacher rewrites last session's countdown to take an input:

```java
public static void countdownFrom(int start) {
    for (int i = start; i >= 1; i--) {
        System.out.println(i);
    }
    System.out.println("GO!");
}
```

Read the new part, `(int start)`:
- `int` — the kind of value the slot accepts (a whole number).
- `start` — the name the value goes by *inside* the method. Wherever you write `start` in the method, Java uses whatever number was passed in.

Now `main` can call it with different inputs and get different behaviour from the **one** method:

```java
countdownFrom(5);
countdownFrom(3);
```

Two words to keep straight:
- **Parameter** — the slot in the definition: `int start`.
- **Argument** — the actual value you put in when you call: the `5` in `countdownFrom(5)`.

Add `printDivider(int width)` from `CountdownTool.java` and call it with a couple of different widths. One method, many sizes.

### Reporter blocks: returning a value (15 min)

So far every method has been `void` — it *does* something (prints) but hands nothing back. In Scratch, a reporter block is different: it's rounded, and it *reports a value* you can drop into another block. Java does this by replacing `void` with a **return type**:

```java
public static int ticksForRotations(int rotations) {
    return rotations * 1120;
}
```

- `int` (where `void` used to be) — "this method hands back a whole number."
- `return rotations * 1120;` — the value it hands back. `return` also ends the method immediately.

The point of a returned value is that `main` can **catch it** and use it:

```java
int ticks = ticksForRotations(3);
System.out.println("3 rotations = " + ticks + " ticks");
```

Drive the difference home with a contrast:
- A `void` method that *prints* a result: the number flashes by and is gone.
- A method that *returns* a result: you can store it, print it, do more maths on it, or pass it into yet another method.

Then show `higher(int a, int b)` from `PowerMath.java`, which returns from inside an `if/else` (ties straight back to Lesson 3). Run `PowerMath.java` together.

### Your turn (17 min)

Working from `PowerMath.java` as a model, the student writes two methods of their own and calls them from a tidy `main`:

1. `rectangleArea(int width, int height)` — returns `width * height`. Call it for two or three different rectangles, store each result in a variable, and print it.
2. A `void` method `printLabel(String text)` that takes a `String` parameter and prints it inside a little frame of dashes.

Goal: feel the difference in your own fingers — one method *returns a number you keep*, the other *takes a word and does something with it*.

### Why this matters (the bridge) (4 min)

Step back. A method now has a clean shape: values go **in** (parameters), a value can come **out** (return). That input → process → output shape is exactly how the objects you'll build in Tier 3 talk to each other — a `Drivetrain` will be *told* a power (parameter) and will *report* its position (return). You're learning the grammar of how code pieces hand work to each other.

### Vocabulary checkpoint (4 min)

Explain in your own words:
- The difference between a **parameter** and an **argument**
- The difference between a `void` method and one with a **return type**
- Why returning a value is more useful than just printing it

### Reflection (3 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. Last session you might have written `countdownFrom5()` and `countdownFrom3()` as two separate methods. Why is one `countdownFrom(int start)` better?
2. In your own words, what does the word `return` do?
3. Name a Scratch reporter block you've used (for example, a maths or sensing block). How is it like a Java method with a return type?

## Stretch Project (Optional)

Finished the area calculator early? Build a small toolbox of methods that take inputs, return answers, and even **feed their answers into each other** — the composition idea at the heart of today's lesson.

**Match Score Calculator.** In this game each cone scores 3 points and each beacon scores 5. Write a new program, `ScoreCalculator.java`, with these methods:
- `int phaseScore(int cones, int beacons)` — returns the points for one phase.
- `int addScores(int a, int b)` — returns two phase scores added together.
- `int higher(int a, int b)` — returns the larger of two scores.

In `main`, work out an autonomous score and a teleop score with `phaseScore`, then pass **those returned values** into `addScores` to get the total, and into `higher` to find the bigger phase. Print all four numbers.

A worked version is in `solutions.md` — try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `CountdownTool.java` — the parameters demo: `countdownFrom(int start)` and `printDivider(int width)`, each called with different inputs
- `PowerMath.java` — the returns demo: `ticksForRotations(int)` and `higher(int, int)`, with results stored and used

Your homework files (`PredictReturns.java`, `ParamBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Setup check before the session:**
- Confirm the student can still compile and run a plain `.java` file from the VS Code terminal (`javac File.java` then `java File`). Same workflow as Lessons 1–5; no new tools today.

**On scope (deliberately held back):**
- **Keep all parameters `int` or `String`.** `double` (and the motor-power-0.5-vs-0 trap) is its own Tier 2 lesson — do not introduce it here, even though `ticksForRotations` is robot-flavoured.
- **No method overloading.** One name, one method. If a student asks "can two methods share a name?", say "yes, but that's for later — give each job its own name for now."
- **One `return` per method is the clean default.** `higher()` technically has a `return` in each branch of the `if/else`; that's fine and worth showing, but don't open the door to early `return`s scattered through a method yet.
- **Still no objects, still `static`.** Same deferred magic word as Lesson 5.

**On `System.out.print` vs `println`:**
- `printDivider` uses `print` (no `ln`) so the dashes stay on one line, then a single `println()` ends the line. This is the first time they've seen `print` without `ln`. Frame it simply: "`println` drops to a new line after; `print` stays put." Don't over-explain.

**Anticipated questions:**
- *"What's the difference between a parameter and an argument?"* — "The parameter is the empty slot in the definition (`int start`). The argument is the value you actually drop in when you call it (`5`). Slot vs thing-you-put-in-the-slot."
- *"Can a method have more than one input?"* — "Yes — separate them with commas, like `higher(int a, int b)`. Each gets its own type and name."
- *"What if I forget to use the returned value?"* — "Then the value is just thrown away. `ticksForRotations(3);` on its own computes the answer and discards it. To keep it, store it in a variable or print it."
- *"Does a `void` method need a `return`?"* — "No. `void` means it hands nothing back, so there's nothing to return."

**Common mistakes to watch for:**
- Calling a method with the wrong number of arguments, e.g. `countdownFrom()` with the slot left empty → `method countdownFrom ... cannot be applied to given types; required: int, found: no arguments`. (This is in the bug hunt.)
- Declaring a return type but never writing `return` → `missing return statement`. (Also in the bug hunt.)
- Passing the wrong *type* of argument, e.g. a number where a `String` is expected → `incompatible types: int cannot be converted to String`. (Also in the bug hunt.)
- Trying to *use* a `void` method's "result": `int x = printDivider(10);` → error, because `printDivider` returns nothing.
- Confusing the parameter name with a variable in `main`. They're separate; the parameter only exists inside the method.

**On the bug hunt (heads-up):**
- `ParamBugHunt.java` has three planted bugs, but `javac` reports only **two** on the first compile (the argument-count and argument-type errors). The `missing return statement` error surfaces only after those two are fixed and the student recompiles. This is genuine compiler behaviour, not a mistake in the file — and it's a small gift: a natural lesson that debugging is iterative ("fix what you can see, recompile, see what's left"). Both `homework.md` and `solutions.md` flag it, so reassure the student if they're surprised.
