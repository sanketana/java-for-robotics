# Lesson 09 — Pointing at Nothing

## Lesson Theme

Every variable you've made so far held *something* — a number, a true/false, a word. Today you meet a value that Scratch has no word for: `null`, a variable that points at **nothing at all**. Ask a "nothing" to do something and your program crashes with the most common error in all of Java: the `NullPointerException`. You'll learn what `null` is, how to *read the crash* it produces, and how to stop it happening. This exact crash is the one that greets almost every new FTC programmer — so meeting it now, calmly, is a gift to your future self.

## What You'll Build

- `NameTagCrash.java` — a program that crashes **on purpose**, so you can take apart a real stack trace and see exactly what it tells you.
- `NullGuard.java` — the same idea made safe with an `if (name != null)` check, plus a method that hands back `null` when it has no answer and a caller that checks before trusting it.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — still pure Java

## What You'll Learn

**Java skills:**
- What `null` means: a reference that points at nothing
- Why `int`, `double`, and `boolean` can never be `null`, but a `String` (and every object later) can
- That calling a method on `null` throws a `NullPointerException` — a **runtime crash**, not a compiler error
- How to **read a stack trace**: the exception type, what it was trying to do, and the `File.java:line` that pinpoints the crash
- How to guard with `if (name != null) { ... }`

**Thinking skills:**
- Reading an error message like a mechanic reads a diagnostic — calmly, top to bottom, for the facts it gives you
- The habit of asking "could this be null?" before trusting a value

## In Class

A 60-minute session.

### Recap and the crash (7 min)

Recap: you've built variables holding numbers, booleans, and words, and methods that hand values back. Now your teacher runs `NameTagCrash.java`. It looks harmless:

```java
String robotName = null;
System.out.println("Starting up...");
System.out.println("Robot name has " + robotName.length() + " letters.");
```

It prints `Starting up...` and then **explodes**:

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "robotName" is null
	at NameTagCrash.main(NameTagCrash.java:10)
```

Don't explain it yet. Just sit with it for a moment. This red text is not the enemy — by the end of the session it's going to feel like a helpful note.

### What null means (10 min)

`null` is Java's way of saying "this variable points at **nothing**." Not zero, not an empty word — genuinely nothing.

In Scratch, this never happened. A Scratch variable always holds *some* value: a number starts at `0`, a text variable at `""` (empty, but still a thing). Java is different, and this is one of the few places Scratch gives you no map:

| | Scratch | Java |
|---|---|---|
| an "empty" variable | `0` or `""` — still a value | `null` — no value at all |
| using it | harmless | crash, if you call a method on it |

One crucial split:
- **Primitives can't be null.** An `int` is always some whole number, a `boolean` always `true` or `false`. They *are* a value.
- **Reference types can be null.** A `String` is a *reference* — it points at a word stored somewhere. If it points at nothing (`null`), there's no word to ask questions of. Every object you build in Tier 3 will be a reference type too, so this matters far beyond `String`.

Asking `robotName.length()` means "String, tell me your length." If `robotName` is `null`, there is no String to ask — so Java stops with a `NullPointerException`.

### Reading the stack trace (13 min)

This is a skill you'll use for the rest of the course, so slow down and read the crash line by line. There are three facts that matter, and they're always there:

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "robotName" is null
	at NameTagCrash.main(NameTagCrash.java:10)
```

1. **What went wrong:** `NullPointerException` — you tried to use something that was `null`.
2. **What it was trying to do:** `Cannot invoke "String.length()" ... because "robotName" is null` — it was about to call `length()` on `robotName`, which was null.
3. **Where** (the most useful part): `(NameTagCrash.java:10)` — file `NameTagCrash.java`, **line 10**. Go straight there.

Read it as a sentence: *"On line 10, I tried to call `.length()` on `robotName`, but `robotName` was pointing at nothing."* That's the whole story. The fix always starts by going to that line number.

> **Name tip:** whether you see the variable's real name (`robotName`) or a placeholder like `<local1>` depends on how the file was compiled. VS Code's Run button, and `javac -g`, keep the names; a plain `javac` may show `<local1>` instead. Either way, the line number is exact — that's your anchor.

### Guarding against null (15 min)

Now the fix. Open `NullGuard.java`. The rule: **check for null before you use it.**

```java
if (robotName != null) {
    System.out.println("Robot name has " + robotName.length() + " letters.");
} else {
    System.out.println("No robot name set yet.");
}
```

`!= null` means "is not nothing." Only the safe branch calls `.length()`, so the crash can't happen.

Then look at `nicknameFor(...)`. Sometimes a method genuinely has *no answer* to give — and returning `null` is how it says so:

```java
public static String nicknameFor(int robotId) {
    if (robotId == 1) {
        return "T-Rex";
    }
    return null;   // no nickname on file
}
```

That means the *caller* must check the result before trusting it — the same `!= null` guard. Run `NullGuard.java` and notice the last line prints `Robot 99 nickname: null` without crashing. That's the key contrast: **printing a null value is safe; calling a method on it is what crashes.**

### Your turn (10 min)

Write a short program of your own that stays safe:

1. Make a `String teamName` and set it to `null`. Print a message that uses `teamName.length()` **only** inside an `if (teamName != null)` guard, with a friendly `else`.
2. Change `teamName` to a real word and run again — now the other branch runs. Prove both paths work.

### Why this matters (the bridge) (5 min)

In Tier 4, you'll ask the robot for its hardware like this: `motor = hardwareMap.get(DcMotor.class, "left_motor");`. If that name doesn't exactly match your robot's configuration, `hardwareMap` hands back — you guessed it — `null`. The very next time you use `motor`, you get a `NullPointerException`. Almost every FTC team hits this in their first week. You just learned to read that crash and find the line before it ever happens to you.

### Reflection (5 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. In your own words, what is the difference between a variable that holds `0` and a variable that is `null`?
2. A stack trace gives you three useful facts. What are they, and which one do you use first?
3. Why can an `int` never be `null`, but a `String` can?

## Stretch Project (Optional)

Finished your safe program early? Build something that has to survive a whole list of maybe-missing values.

**Team Roster.** Write a new program, `TeamRoster.java`, with a method `String roleForId(int id)` that returns a robot's role for a few known IDs (say `1` → `"Scorer"`, `2` → `"Defender"`, `4` → `"Support"`) and `null` for any other ID. In `main`, use a `for` loop (from Lesson 4) to look up IDs `1` through `5`. For each one, print the role if it exists, or `Robot N: (no role assigned)` if the lookup came back `null`. Not a single crash allowed — every lookup must be guarded.

A worked version is in `solutions.md` — try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `NameTagCrash.java` — crashes on purpose so you can read a real stack trace
- `NullGuard.java` — the safe version, with `!= null` guards and a method that may return `null`

Your homework files (`PredictNull.java`, `NullBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Setup check before the session:**
- No new tools. Same compile-and-run workflow as Lessons 1–8. Running the crashing program is the whole point — make sure the student runs it themselves and sees the red trace.

**On the stack-trace message (important detail):**
- With a plain `javac File.java`, JDK 17's "helpful NullPointerException" names the variable as a placeholder — `because "<local1>" is null` — because the local-variable name table isn't included by default. Compiling with `javac -g` (or running via VS Code's Run button, which includes debug info) shows the real name, `because "robotName" is null`. Teach the student to lean on the **line number**, which is always exact, and treat the friendly variable name as a bonus. If your student compiles by hand and sees `<local1>`, that's expected — tell them "the first local variable declared in that method; line 10 shows which one."

**On scope (deliberately held back):**
- **No `try/catch`.** This is a firm line (see the course's "not taught" list). The response to a null is to *prevent it* with a guard and *read the trace*, not to catch an exception. If a student has seen `try/catch` elsewhere and asks, say: "That's a tool for later. Here, the right fix is to check for null before we use the value — catching the crash would just hide the real problem."
- **No `Optional`, no `Objects.requireNonNull`, no `?.`** (Java has no null-safe operator anyway).
- **Only `String` methods** as the vehicle — `.length()` and `.toUpperCase()`. Objects with their own methods arrive in Tier 3; the null lesson transfers directly.

**Anticipated questions:**
- *"Why did some lines print before it crashed?"* — "A program runs top to bottom and stops the instant it hits the bad line. Everything above line 10 already ran; nothing below it did. That's why the line number is so useful."
- *"Isn't `null` the same as an empty word `\"\"`?"* — "No — and this trips people up. `\"\"` is a real (empty) String; you *can* ask it its length (it's 0). `null` is no String at all; asking it anything crashes."
- *"Why didn't `System.out.println(missing)` crash when `missing` was null?"* — "Printing is special: Java quietly turns a null into the text `null` for you. It only crashes when you call a *method* on the null value, like `.length()`."

**Common mistakes to watch for:**
- Reading the crash bottom-up or panicking at the red text. Train the calm top-to-bottom read: what, doing what, where.
- Guarding with `== null` when they mean `!= null` (or putting the real work in the wrong branch). Have them read it aloud: "if name is not null, then use it."
- Assuming a method never returns null. If a method *can* return null (like `nicknameFor`), the caller must check — this is the habit that prevents the Tier 4 `hardwareMap` crash.
