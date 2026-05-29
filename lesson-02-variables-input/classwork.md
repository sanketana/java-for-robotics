# Lesson 02 — Variables, Types, and Asking the User

## Lesson Theme

Last session your program could only say things you had already decided. Today it learns to remember values and to ask the person using it — the difference between a sign that says one fixed thing and a calculator that responds to you.

## What You'll Build

You'll store information in typed variables, do arithmetic on it, and then build a small interactive program that asks the user a question, reads their answer, and computes a result.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code) — programs that ask questions need it, so you can type your answers in
- No simulator yet — still pure Java, no robotics kit

## What You'll Learn

**Java skills:**
- Declare and assign variables of the four core types: `int`, `double`, `boolean`, `String`
- Understand that a Java variable's type is fixed when you declare it — unlike a Scratch variable, which holds anything
- Use the mathematical operators `+`, `-`, `*`, `/`, `%`
- Use `Scanner` to read a number the user types (treated as a "tool you switch on" for now, not yet fully explained)
- Join variables into output with `+`

**Thinking skills:**
- The shift from "the program prints what I typed" to "the program computes from values it holds"
- Choosing the right type for the data — you don't store a name in an `int`
- Noticing a surprising result and parking the question: `7 / 2` is not `3.5`, and learning to flag "I'll understand this later" instead of panicking

## In Class

A 60-minute session.

### Recap and the Scratch bridge (6 min)

Quick recap: last time, your program could only say things you'd already typed in. To change the age it printed, you had to edit the code. Today you fix that.

Two Scratch blocks you already know map directly onto today's Java:

| Scratch | What it does | Java today |
|---|---|---|
| `set [score] to (0)` | Makes a variable, puts a value in it | `int score = 0;` |
| `ask [What's your name?] and wait` → `answer` | Gets input from the user | `Scanner` |

The one new rule: in Java, when you make a variable you must say what *kind* of thing it holds — a whole number, a decimal, some text, or a true/false. In Scratch a variable holds anything. In Java each variable has a type, and it sticks.

### Variables and types (10 min)

Your teacher builds `RobotStats.java` while explaining it:

```java
public class RobotStats {
    public static void main(String[] args) {
        String robotName = "Sparky";
        int motorCount = 4;
        double wheelDiameter = 9.0;
        boolean isCompetitionReady = false;

        double wheelCircumference = 3.14159 * wheelDiameter;

        System.out.println("Robot name: " + robotName);
        System.out.println("Number of motors: " + motorCount);
        System.out.println("Wheel diameter: " + wheelDiameter + " cm");
        System.out.println("Wheel circumference: " + wheelCircumference + " cm");
        System.out.println("Competition ready: " + isCompetitionReady);
    }
}
```

Read each variable line left to right: *type*, then *name*, then `=`, then *value*. So `int motorCount = 4;` means "make a whole-number variable called `motorCount` and put 4 in it."

The four types you'll use:
- `String` — text, always in quotes
- `int` — whole numbers
- `double` — decimals (note the `9.0`, written with a `.0` to be honest that it's a decimal)
- `boolean` — just `true` or `false`

You won't do much with `boolean` today — that comes in a later session — but it's a type, so here it is. Notice the last computed line: `wheelCircumference` was *calculated* from the diameter, not typed in. Variables aren't just for storing; they're for computing.

### Math operators (10 min)

You'll explore the five mathematical operators in your own copy, predicting each result before you run it:

```java
int a = 7;
int b = 2;
System.out.println(a + b);   // add
System.out.println(a - b);   // subtract
System.out.println(a * b);   // multiply
System.out.println(a / b);   // divide
System.out.println(a % b);   // remainder ("mod")
```

The new one is `%` — the remainder, the leftover after division. `7 % 2` is 1, because 2 goes into 7 three times with 1 left over. You already used this idea in Scratch whenever you checked "is this number even?"

### The division surprise (4 min)

You'll have just seen `a / b` print `3`, not `3.5`. Java isn't broken. When you divide two whole numbers, Java gives a whole-number answer and throws away the rest. There's a clean reason for this, and it matters a lot later when we set motor power — but that's a puzzle for a future session.

For now, write down the rule: **whole ÷ whole = whole.** If you want a decimal answer, use decimal numbers. Prove it to yourself: change the variables to `double a = 7.0;` and `double b = 2.0;`, run again, and watch it print `3.5`. Park the *why*; you've got the *what*.

### Scanner — the "ask and wait" block (4 min)

So far your program decides everything. Now you'll let the *user* decide. `Scanner` is the Java cousin of Scratch's `ask and wait` block:

```java
import java.util.Scanner;
// ...
Scanner scanner = new Scanner(System.in);
System.out.print("Enter a number: ");
double value = scanner.nextDouble();
```

Two of these lines have words you haven't learned yet — `import` and `new`. Don't worry about them; we unpack them in a later tier. Treat them as the "switch on the question machine" lines and type them as a set. The line doing the real work is `scanner.nextDouble()` — that's "ask and wait", and the answer the user types lands in your variable.

### Build something interactive (16 min)

You'll write `TripCalculator.java` from the skeleton below. It asks for a distance and a speed, then computes how long the robot would take to travel:

```java
import java.util.Scanner;

public class TripCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter distance in cm: ");
        double distance = scanner.nextDouble();

        System.out.print("Enter speed in cm per second: ");
        double speed = scanner.nextDouble();

        double time = distance / speed;

        System.out.println("Travel time: " + time + " seconds");
    }
}
```

Run it and type in some numbers. Now it's a real tool — you didn't edit the code, you *used* the program.

Then a challenge: add a third question. Ask for the number of trips, and print the *total* time. (You'll need a new variable and the `*` operator.)

### Vocabulary checkpoint (6 min)

Explain, in your own words:
- The difference between a Java variable and a Scratch variable
- When you'd use `double` instead of `int`
- What `%` gives you
- What `scanner.nextDouble()` does

Explain it as if you were teaching a friend who only knows Scratch.

### Reflection (4 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. What can your program do today that it couldn't do last session?
2. Java said `7 / 2` is `3`. In your own words, what's the rule, and how do you get `3.5` instead?
3. Which type would you use for each: a robot's name, the number of wheels, the battery voltage, whether the robot is turned on?

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `RobotStats.java` — the program your teacher demonstrates
- `TripCalculator.java` — the interactive program you build

Your homework files (`PredictMath.java`, `TypeBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Before the session:**
- Confirm last session's setup still runs (`javac` / `java` in the terminal).
- Decide how the student will run Scanner programs. The integrated terminal accepts typed input fine; the "Run" code-lens also opens a terminal that accepts input. Test once beforehand so the "hang" while the program waits for input is no surprise — that pause is normal.

**On `import` / `new` / `Scanner`:**
- Resist explaining objects today. Same script as `main` last week: named, framed, deferred. If the student is curious, the one-liner is: "`Scanner` is a little machine that reads what you type. We learn how to build machines like this in Tier 3."
- You may omit `scanner.close()` for now to keep the code minimal. Some setups show a faint "resource leak" warning — it's a warning, not an error, and the program runs. Tell the student to ignore it; we handle cleanup properly later.

**Anticipated questions:**
- *"Why do I have to say the type? Scratch didn't make me."* — "Because Java checks your work. If you say a variable holds a whole number and then try to put a name in it, Java stops you *before* the program runs. Stricter, but it catches mistakes early."
- *"Why does `7 / 2` give `3`?"* — Hold the line: "Real reason, later session. Today's rule: whole ÷ whole = whole. Use decimals if you want a decimal answer." This is a Tier 2 lesson by design — don't deep-dive.
- *"What if the user types letters instead of a number?"* — "The program will crash with an error. That's fine for today; we learn to handle bad input much later. For now, type a number."

**Common mistakes to watch for:**
- Quotes around a number for an `int`/`double` (`int age = "12";`) — type mismatch. A *good* mistake; read the error together.
- Using a variable before declaring it, or a typo in the name — "cannot find symbol".
- Forgetting `import java.util.Scanner;` at the top — "cannot find symbol: Scanner".
- Capitalisation: `scanner` (the variable) vs `Scanner` (the type). Mixing them up is common.
