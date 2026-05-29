# Lesson 01 — Hello Java

## Lesson Theme

Your first Scratch program made a cat say "Hello!" Your first Java program does the same thing — just with typed words instead of dragged blocks.

## What You'll Build

You'll write, run, and modify a Java program that prints messages to the screen. Then you'll break it on purpose and learn to read what Java tells you when something goes wrong.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — this is pure Java, no robotics kit

## What You'll Learn

**Java skills:**
- The shape of a minimal Java program (`class`, the `main` method, `System.out.println`)
- How to write and run a `.java` file from VS Code
- That Java needs exact spelling, capitalisation, and punctuation
- How to read a basic error message and connect it to the line that caused it

**Thinking skills:**
- Precision: block coding forgives small slips; text coding does not
- Reading error messages as information, not punishment — the computer is talking *to* you, not shouting *at* you
- Predicting what a program will print before you run it

## In Class

A 60-minute session.

### The Scratch bridge (8 min)

You'll start from something you already know. Look at a Scratch "say" block — it makes a sprite say something. Today you'll do the exact same thing, but by typing the instruction instead of dragging a block. Same logic, different surface:

| Scratch | Java |
|---|---|
| `say "Hello!"` | `System.out.println("Hello!");` |
| The block is orange and snaps in | The line ends with `;` and lives inside braces `{}` |
| You drag it under "when green flag clicked" | You type it inside `public static void main(...)` |

For now, think of `public static void main(...)` as the green flag — the place Java starts running. You'll unpack what every word means over the coming sessions.

### Watch it built (7 min)

Your teacher builds the first program in front of you, line by line, explaining each choice:

```java
public class HelloJava {
    public static void main(String[] args) {
        System.out.println("Hello, I am learning Java!");
    }
}
```

Three things to notice:
- The file name matches the class name — `HelloJava.java` holds `class HelloJava`.
- Every opening brace `{` has a closing brace `}`, the way every Scratch "if" block has a mouth that closes.
- The semicolon `;` at the end is like a full stop — it tells Java the instruction is finished.

The program runs in the terminal with `javac HelloJava.java && java HelloJava`. The line that appears is your "say" block, working.

### Your turn (13 min)

You'll write your own program, `AboutMe.java`, that prints three lines about yourself:

```java
public class AboutMe {
    public static void main(String[] args) {
        System.out.println("My name is ___");
        System.out.println("I am ___ years old");
        System.out.println("My favourite game is ___");
    }
}
```

You'll almost certainly hit an error — that's expected and useful. When you do, the first move is always the same: **read the message, find the line number it mentions, and look there.** Common first-week slips are a missing semicolon, a class name that doesn't match the file name, a lowercase `s` in `System`, or a missing closing brace.

### Break it on purpose (10 min)

This is the most important part of the lesson. You'll *introduce* errors deliberately and read what Java says each time:

1. **Delete a semicolon**, then compile. You'll see `error: ';' expected` — and the line number.
2. **Change `System` to `system`** (lowercase), then compile. Java cares about capital letters; Scratch blocks never did.
3. **Delete a closing brace `}`**, then compile. This error may point to the *end* of the file — because Java read all the way down still looking for the brace. When an error seems to point somewhere strange, suspect a missing brace.
4. **Rename the class** so it no longer matches the file name, then compile.

The goal isn't to memorise these errors. It's to get comfortable reading them — the single most useful skill in programming, more useful than memorising syntax.

### Experiments (10 min)

Modify your program to explore:

1. **A blank line:** `System.out.println();` — what prints with no text?
2. **A number:** `System.out.println(42);` — no quotes needed. (Quotes mean text; no quotes means a number. More on this next session.)
3. **Two things on one line:** try `System.out.print()` (no `ln`) and compare it to `println`.
4. **Joining text with `+`:** `System.out.println("My age is " + 14);` — the `+` glues things together, like the "join" block in Scratch.

### Vocabulary checkpoint (7 min)

You'll explain, in your own words:
- What `println` does
- What happens if you forget a semicolon
- Why the file name must match the class name
- What the `main` method is

Explain it the way you'd explain it to a friend — not as a textbook definition.

### Reflection (5 min)

Answer the reflection prompts below.

## Reflection

Write your answers (in a shared doc or a notebook):

1. What surprised you about Java compared to Scratch?
2. When you saw an error, what did you do first? What will you do next time?
3. Write one line of Java from memory, without looking. Does it compile?

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `HelloJava.java` — the program your teacher demonstrates
- `AboutMe.java` — your skeleton to fill in (replace the `___` blanks)

Your homework files (`Predict.java`, `BugHunt.java`) are in the same `code/` subfolder, with the full instructions in `homework.md`.

## Notes for the Teacher

**Before the session:**
- Confirm VS Code with the Java Extension Pack is installed and working on the student's machine. Test `javac` and `java` in the terminal. If Java is missing, install OpenJDK 17 (LTS) — the session cannot start without it.
- Have the student's previous Scratch project open (or a simple prepared one with a "say" block) for the opening bridge.
- Have both `.java` files ready to paste in case of setup trouble, but default to having the student type.

**Anticipated questions:**
- *"What does `public static void main(String[] args)` mean?"* — "Each word has a meaning we'll learn over the next few sessions. For now, treat the whole line as the green flag — Java needs it to know where to start."
- *"Why `System.out.println` and not just `print`?"* — "Java organises everything into boxes. `System` is a box, `out` is a smaller box inside it, and `println` is the action. It's verbose, but we'll see why this organisation helps later."
- *"Do I have to type all this every time?"* — "For now, yes. Typing builds muscle memory — like practising scales before playing a song."

**Common mistakes to watch for:**
- Copying code from a PDF or website brings invisible "smart quotes" (`“”` instead of `""`). If the error mentions an illegal character, check the quotes.
- Confusing the two terminal steps: `javac` to compile, then `java` to run.
- Forgetting to save before compiling — VS Code may not auto-save.
