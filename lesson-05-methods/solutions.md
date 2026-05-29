# Lesson 05 — Homework Solutions

Try every task on your own first. Reading an error and fixing it yourself is the skill we're building — use this only to check your work afterward.

## 1. Build your own sequence

Open-ended, so there's no single right answer. A correct version has a short `main` that calls two or more `static` methods you defined. For example:

```java
public class MorningRoutine {
    public static void main(String[] args) {
        brushTeeth();
        eatBreakfast();
        packBag();
    }

    public static void brushTeeth() {
        System.out.println("Brushing teeth...");
    }

    public static void eatBreakfast() {
        System.out.println("Eating breakfast...");
    }

    public static void packBag() {
        System.out.println("Packing bag...");
    }
}
```

Check: each method is `public static void`, and each one is actually *called* from `main` (defining it isn't enough).

## 2. Prediction exercise

`PredictMethods.java` prints:

```
Hi!
Hi!
Middle
Go team!
```

Why: `main` runs top to bottom. It calls `sayHi()` twice (two `Hi!` lines), prints `Middle`, then calls `cheer()` (`Go team!`). The methods print in the order `main` calls them — not in the order they're written in the file.

## 3. Error detective

`MethodBugHunt.java` had three errors. The corrected program:

```java
public class MethodBugHunt {
    public static void main(String[] args) {
        greet();
        countdown();
    }

    public static void greet() {
        System.out.println("Hello, driver!");
    }

    public static void countdown() {
        for (int i = 3; i >= 1; i--) {
            System.out.println(i);
        }
    }
}
```

What each error message said, and the fix:

1. **`error: cannot find symbol` (pointing at `greets`)** — `main` called `greets()`, but the method is named `greet()`. Names must match exactly.
2. **`error: non-static method countdown() cannot be referenced from a static context`** — `countdown()` was declared `public void`, missing `static`. Since `main` is static and there's no object, custom blocks need `static` too. Changed it to `public static void countdown()`.
3. **`error: ';' expected`** — the `System.out.println(i)` line inside the loop was missing its semicolon. Added `;`.
