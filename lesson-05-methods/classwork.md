# Lesson 05 — Custom Blocks

## Lesson Theme

Your programs are getting long, and you're starting to copy-paste the same lines. Today you learn to make your own blocks — name a chunk of work once, then use it by name — exactly like the "make a block" feature in Scratch. This is the last piece of Tier 1, and it's the idea everything in Tier 3 is built on.

## What You'll Build

A robot startup sequence whose `main` reads like a short summary — `printBanner(); countdown();` — with the details tucked into custom blocks. Then you'll take a repetitive program and clean it up by pulling the repeated lines into a method of your own.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — still pure Java

## What You'll Learn

**Java skills:**
- Define your own method ("custom block") and call it from `main`
- Read the shape `public static void doSomething() { ... }` — and treat `static` as a deferred magic word, the way you already treat `main`'s keywords
- Understand that `void` means "does something, returns nothing"
- See that *defining* a method doesn't run it — *calling* it does
- Call one method several times, and call methods in any order you choose

**Thinking skills:**
- Spotting repetition as a signal to make a block ("I've typed this twice — name it")
- Decomposition: breaking one big task into named smaller ones
- Reading `main` as a table of contents, with the details one level down

## In Class

A 60-minute session.

### Recap and the mess (7 min)

Recap Lesson 4: you can now repeat actions with loops. But your programs are getting long, and the same lines keep showing up. Look at `StatusReport.java` together — the same divider line is copy-pasted three times. If you wanted to change that divider, you'd have to fix it in three places.

You've solved this before. In Scratch, when you found yourself dragging the same stack of blocks again and again, you used **"make a block"** — you gave the stack a name and then used the name. Java has exactly that, and it's called a **method**.

| Scratch | Java |
|---|---|
| "make a block" → define `my block` | `public static void printDivider() { ... }` |
| dragging the `my block` block | `printDivider();` |

### Your first custom block (13 min)

Your teacher builds a method and calls it:

```java
public class StartupSequence {

    public static void main(String[] args) {
        printBanner();
    }

    public static void printBanner() {
        System.out.println("=================");
        System.out.println("  SANKETANA BOT  ");
        System.out.println("=================");
    }
}
```

Read the new line, `public static void printBanner()`:
- `void` — this block *does* something but doesn't *hand back* a value (next tier, some blocks will).
- `printBanner` — the name you choose; the `()` is where inputs will go later (empty for now).
- `static` — a magic word your custom blocks need, just like `main` has it. We'll unpack what it means in a later tier. For now: **custom blocks get `static`, like `main` does.**

The crucial point: writing the method *defines* it; it doesn't run. It runs only when `main` *calls* it with `printBanner();`. Comment out the call and run — nothing prints. Put it back — the banner appears.

### Make main read like a summary (12 min)

Now add a second block and watch `main` become a clean summary. You'll reuse the countdown from last session:

```java
public static void main(String[] args) {
    printBanner();
    countdown();
    System.out.println("Robot ready!");
}

public static void countdown() {
    for (int i = 5; i >= 1; i--) {
        System.out.println(i);
    }
    System.out.println("GO!");
}
```

Read `main` out loud: "print the banner, run the countdown, say ready." That's the whole point — `main` tells you *what* happens; each method holds *how*. This is the full `StartupSequence.java` in your `code/` folder.

### Your turn: clean up the mess (16 min)

Open `StatusReport.java`. The divider line `-----------------------------` appears three times. Your job: pull it into a custom block.

1. Write a method `printDivider()` that prints the divider line once.
2. Replace each copy-pasted divider line with a call to `printDivider();`.
3. Run it — the output should look identical, but the code is shorter and there's now *one* place to change the divider.

Then a stretch: add a `printTitle()` method that prints a heading at the top, and call it from `main`.

This is refactoring — changing how code is organised without changing what it does. It's something you'll do for the rest of your coding life.

### Why this matters (the bridge) (5 min)

Step back. You just took scattered logic and bundled it into named blocks. In a few sessions, you'll learn that an **object** is essentially a bundle of these blocks *plus* the data they work on — a `Drivetrain` that knows how to `drive()` and `stop()`. Methods are the first half of that idea. You're already thinking the way objects work.

### Vocabulary checkpoint (4 min)

Explain in your own words:
- The difference between *defining* a method and *calling* it
- What `void` means
- Why making `main` short and calling methods makes a program easier to read

### Reflection (3 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. What can your program do today that it couldn't do last session? (Hint: it's about *organisation*, not new powers.)
2. In `StatusReport`, why is it better to have the divider in one method than copied three times?
3. Where else in your past programs did you repeat lines that a custom block could have replaced?

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `StartupSequence.java` — the program your teacher demonstrates (two methods + a tidy `main`)
- `StatusReport.java` — the repetitive program you refactor into a method

Your homework files (`PredictMethods.java`, `MethodBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**On `static` (important):**
- In a Tier 1 single-file program, a method called from `main` must be `static` (because `main` is static and there's no object yet). We are *not teaching* what `static` means — that's a Tier 3 conversation. Treat it exactly like `main`'s keywords in Lesson 1: a deferred magic word. The whole framing is "custom blocks get `static`, just like `main`." If a student presses, the honest one-liner is: "It's tied to the fact we don't have objects yet — that's a few sessions away."

**On scope (deliberately held back):**
- **No parameters or return values today.** Every method is no-argument and `void`. Parameters and returns are the opening lesson of Tier 2 (next session), and they're the bigger mental shift — keeping them out keeps today focused on the "named block" idea.
- **No method overloading, no recursion.** One simple method per job.

**Anticipated questions:**
- *"Why didn't my method do anything?"* — "You defined it but never called it. Defining is writing the recipe; calling is cooking it. Add `methodName();` in `main`."
- *"Why `static` again?"* — "Same magic word `main` uses. We'll learn what it really means when we get to objects. For now, copy the pattern."
- *"Can a method call another method?"* — "Yes — `main` calls your methods, and your methods can call each other. Just avoid a method calling itself for now."
- *"Does the order I define methods in matter?"* — "No. Java reads the whole file first, so a method can be defined below `main` and still be called from it. What matters is the order you *call* them."

**Common mistakes to watch for:**
- Defining a method but forgetting to call it (nothing happens) — the number-one beginner method bug.
- Omitting `static` on the method, then calling it from `main` → `non-static method ... cannot be referenced from a static context`. This is in the bug hunt; tie the fix back to "custom blocks get `static`."
- Calling a method by the wrong name (typo) → `cannot find symbol`.
- Trying to define a method *inside* `main`. Methods live side by side inside the class, not nested inside each other.
- Forgetting the `()` on a call.
