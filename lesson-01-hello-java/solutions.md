# Lesson 01 — Homework Solutions

Try every task on your own first. The point of the homework is the *struggle* — reading an error and fixing it yourself is the skill we're building. Use this only to check your work afterward.

## 1. Make it yours

This task is personal, so there's no single right answer — yours just needs five working `println` lines, including your favourite food and one thing you'd like to build with a robot. A correct version looks like this:

```java
public class AboutMe {
    public static void main(String[] args) {
        System.out.println("My name is Aanya");
        System.out.println("I am 12 years old");
        System.out.println("My favourite game is Minecraft");
        System.out.println("My favourite food is dosa");
        System.out.println("I want to build a robot that waters plants");
    }
}
```

Check: five lines, every line ends with `;`, every `System` has a capital S, and the class name matches the file name.

## 2. Prediction exercise

`Predict.java` prints:

```
Line 1

Line 3
No newline
```

Why each line:
- `println("Line 1")` → `Line 1`
- `println()` → an empty line (println with nothing still moves to a new line)
- `println("Line " + 3)` → `Line 3` (the `+` joins the text `"Line "` with the number `3`)
- `print("No ")` → `No ` with **no** new line, so the next print continues on the same line
- `println("newline")` → finishes the line as `No newline`

## 3. Error detective

`BugHunt.java` had three errors. The corrected program:

```java
public class BugHunt {
    public static void main(String[] args) {
        System.out.println("Find the bugs!");
        System.out.println("Can you fix them?");
    }
}
```

What each error message said, and the fix:

1. **`error: cannot find symbol` (pointing at `system`)** — `system` was lowercase. Java is case-sensitive; it must be `System`.
2. **`error: ';' expected`** — the first print line was missing its semicolon. Added `;` at the end.
3. **`error: reached end of file while parsing`** — the class was missing its closing brace `}`. When an error points at the end of the file, suspect a missing brace.
