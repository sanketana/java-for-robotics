# Lesson 10 — Reading the Error Message

## Lesson Theme

For nine lessons, when something broke, red text appeared and often a teacher helped you decode it. Today you get the decoder ring yourself. Java has exactly **two kinds** of error, and they announce themselves in two different ways: a **compiler error** stops you *before* the program runs, and a **runtime crash** (a stack trace, like last lesson's `NullPointerException`) stops you *while* it runs. Once you can tell them apart at a glance and read each for its one most useful fact — **where** — you stop guessing and start fixing.

## What You'll Build

- `ErrorZoo.java` — a small program that works perfectly. You'll break it **four different ways**, one at a time, and write down the exact message each break produces. This becomes your personal error catalogue.
- `TraceReader.java` — a program that crashes **two method calls deep**, so for the first time you read a *multi-line* stack trace and learn to find **your** line inside the call stack.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- Still pure Java — no simulator

## What You'll Learn

**Java skills:**
- The two categories of error: **compile-time** (won't build) vs **run-time** (built, then crashed)
- The anatomy of a compiler error: `File.java:line: error: message`, with a caret `^` pointing at the exact spot
- How to read a **multi-line** stack trace: the top line is where it blew up; scan **down** to find the line *you* wrote
- Recognising the handful of most common messages by sight: `';' expected`, `cannot find symbol`, `incompatible types`, `/ by zero`

**Thinking skills:**
- **Read before you change** — resist the urge to randomly edit; the message already told you where to look
- **One error at a time** — fix the top one and recompile, because a single typo can spawn a pile of fake-looking errors
- Building a "I've seen this before" catalogue, so next time the same message is a two-second fix

## In Class

A 60-minute session.

### Recap: last lesson's crash (5 min)

Last lesson you read one stack trace — the `NullPointerException` — for three facts: *what* went wrong, *what it was doing*, and *where* (`File.java:line`). Today you learn that crash was just **one** of Java's two error families, and you meet the other one properly.

### The two families (8 min)

Put this on the board — it's the whole mental model:

| | Compile-time error | Run-time crash |
|---|---|---|
| When | Before the program runs | While the program is running |
| Who reports it | The **compiler** (`javac` / the red squiggle) | The **running program**, as a stack trace |
| Did anything run? | No — it never started | Yes — it ran up to the bad line, then stopped |
| Looks like | `File.java:4: error: ...` | `Exception in thread "main" ...` |
| Examples | `';' expected`, `cannot find symbol`, `incompatible types` | `NullPointerException`, `ArithmeticException: / by zero` |

The tell: a **compiler error has a line number followed by `error:`** and your program produces *no output at all*. A **runtime crash** starts with `Exception in thread "main"` and usually appears *after* some of your output has already printed.

### Break the zoo (18 min)

Open `ErrorZoo.java` and run it — it prints cleanly:

```
Matches played: 5
Total points: 120
Average per match: 24
```

Now break it four ways, **one at a time**. After each break, run it, read the message aloud, write it in your catalogue, then undo the break before the next one.

**Break 1 — delete the semicolon** after `int matches = 5`:
```
ErrorZoo.java:4: error: ';' expected
        int matches = 5
                       ^
```
Compile-time. The `^` points just past where the `;` should be. Line 4.

**Break 2 — mistype a name:** change `matches` to `Matches` in the division line:
```
ErrorZoo.java:6: error: cannot find symbol
        int average = totalPoints / Matches;
                                    ^
  symbol:   variable Matches
```
Compile-time. `cannot find symbol` = "you used a name I've never been introduced to." Java is case-sensitive, so `Matches` and `matches` are different names.

**Break 3 — mismatch a type:** change `int average` to `String average`:
```
ErrorZoo.java:6: error: incompatible types: int cannot be converted to String
```
Compile-time. You promised a `String` but handed it an `int` — the same family of error you met in Lesson 7.

**Break 4 — the sneaky one:** set `int matches = 0` (leave everything else correct). It **compiles fine**, prints two lines, then:
```
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at ErrorZoo.main(ErrorZoo.java:6)
```
Run-time. The compiler couldn't know `matches` would be `0` — only the running program found out. Dividing an `int` by zero crashes. Notice this one printed output *first*, then crashed. That's the runtime giveaway.

Four breaks, two families: the first three never ran; the fourth ran, then died.

### Reading a deeper trace (14 min)

Last lesson's crash was one line deep. Real programs call methods that call other methods, so real traces are taller. Open `TraceReader.java`:

```java
public static void main(String[] args) {
    int avg = averageScore(50, 0);      // line 5
}
public static int averageScore(int total, int matches) {
    return dividePoints(total, matches); // line 10
}
public static int dividePoints(int total, int matches) {
    return total / matches;              // line 14
}
```

Run it:
```
Starting up...
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at TraceReader.dividePoints(TraceReader.java:14)
	at TraceReader.averageScore(TraceReader.java:10)
	at TraceReader.main(TraceReader.java:5)
```

Read it **top to bottom** as a story:
- **Top line (line 14)** — where it actually blew up: inside `dividePoints`, dividing by zero.
- **Next line (line 10)** — who called `dividePoints`: `averageScore`.
- **Bottom line (line 5)** — who started it all: `main`.

The top frame tells you *where the crash happened*; the frames below tell you *how the program got there*. The zero came from `main` (line 5), travelled through `averageScore`, and detonated in `dividePoints`. When you write bigger programs — and in Tier 4, when the FTC SDK calls *your* code — this "read the stack top-down, find my own file" skill is how you locate the real culprit.

### Your turn (8 min)

Take any program you've written that currently works. Break it **three different ways** on purpose — but **predict the message first**, then run to check:

1. Delete a semicolon somewhere. Predict: compile or runtime? What message?
2. Rename one variable so a use no longer matches its declaration. Predict the message.
3. Make one line divide by a variable you can set to `0`. Predict: which family, and does any output print first?

Getting the *prediction* right is the skill — the message just confirms you read your own code correctly.

### Why this matters (the bridge) (4 min)

When you build a real FTC OpMode, the Android build spits out these exact compiler messages — `cannot find symbol` when you mistype a motor's method, `';' expected` when you drop a semicolon. And when the robot runs, a runtime crash shows up as a stack trace **on the Driver Station screen**, mid-match. Teams that can read that trace fix the robot in the pit in two minutes. Teams that can't, guess for an hour. You're building the two-minute skill now.

### Reflection (3 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. What is the fastest way to tell a compile-time error from a runtime crash just by looking at the output?
2. In a multi-line stack trace, which line tells you *where* the crash happened — the top one or the bottom one? What does the other end tell you?
3. Why could the compiler *not* warn you about `/ by zero` in advance?

## Stretch Project (Optional)

Finished early? Build a program with a **deep** call chain and read a trace that includes Java's own library code.

**Score Pipeline.** Write a new program, `ScorePipeline.java`, that pretends to load match scores that arrive as text. Write a method `int toNumber(String raw)` that returns `Integer.parseInt(raw)` (this turns text like `"20"` into the number `20`). Write a method `int totalFromRaw(String a, String b, String c)` that returns `toNumber(a) + toNumber(b) + toNumber(c)`. In `main`, call it with three values where **one is not a real number** — like `"10"`, `"20"`, `"3x"`. Run it and read the crash carefully: the trace will have several lines starting with `java.base/...` (that's Java's *own* code) on top, and *your* files below. Your job is to **scan down past the `java.base` lines to the first line that names your file** — that's where your bug is. Write down: which of your methods, and which value, caused it.

A worked version is in `solutions.md` — try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `ErrorZoo.java` — a clean program to break four ways (semicolon, name, type, divide-by-zero)
- `TraceReader.java` — crashes two method calls deep, for reading a multi-line trace

Your homework file (`TripleFault.java`) is in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Setup check before the session:**
- No new tools. Same compile-and-run workflow as Lessons 1–9. The learning happens *by breaking things*, so make sure each student breaks `ErrorZoo` themselves rather than watching — the muscle memory of "message → line number → fix" is the goal.

**On the `<local1>` naming (carried over from Lesson 9):**
- If a student compiles by hand with plain `javac`, a `NullPointerException` message may still say `<local1>` instead of the variable name. None of *this* lesson's crashes (`/ by zero`, `cannot find symbol`, etc.) hide names that way — they all name the variable or the exact spot. If it comes up, the same advice holds: lean on the line number.

**Teaching the two-family tell:**
- The single most useful diagnostic for a beginner: *did any of my output print?* No output at all → compiler error (it never ran). Some output, then red → runtime crash. Drill this; it collapses most "I'm stuck" moments instantly.

**On `ErrorZoo` break order:**
- Do them one at a time and **undo between breaks**, or the compiler will report several at once and the lesson gets muddy. The point is a clean one-message-per-break catalogue.

**On the multi-line trace:**
- Beginners instinctively read the *bottom* line (`main`) first because it's their familiar entry point. Retrain them: the **top** frame is where it crashed. `main` at the bottom just means "this is where the program started." Have them trace the value (the `0`) *downward* through the frames as a story.

**On scope (deliberately held back):**
- **Still no `try/catch`.** The response to a runtime crash is *read the trace and fix the cause*, exactly as in Lesson 9. Catching would hide the very message we're learning to read.
- **Arrays are not taught here.** The runtime examples are chosen to need no arrays: `/ by zero` for the in-class files, and `Integer.parseInt` for the stretch. If a student asks about `ArrayIndexOutOfBoundsException`, note it's "the same idea — a runtime crash with a stack trace — for a thing called an array we'll meet later."
- `Integer.parseInt` in the stretch is used as a black box ("turns text into a number"); no need to explain parsing, just that it crashes on non-numbers.

**Anticipated questions:**
- *"Why did break 4 print some lines but break 1 printed nothing?"* — "Break 1 is a compiler error: the program never ran, so nothing printed. Break 4 compiled fine and ran until the exact line that divided by zero — everything above that line already printed."
- *"There are five lines in the stretch trace before my file. Did I break Java?"* — "No. Those `java.base/...` lines are Java's own code doing the parsing. Your bug is the first line that names *your* file — scan down to it."
- *"`cannot find symbol` — what's a symbol?"* — "A name. It means you used a name Java was never introduced to — usually a typo or a wrong capital letter."

**Common mistakes to watch for:**
- Editing at random after reading the *word* `error` but not the *line number*. Force the habit: finger on the line number first.
- Fixing a lower error before the top one and getting confused when the list changes. One at a time, top-down, recompile.
- Confusing `incompatible types` (compile-time, from Lesson 7) with a runtime problem. If it has `File.java:line: error:`, it never ran.
