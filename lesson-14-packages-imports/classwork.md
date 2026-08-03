# Lesson 14 — Organising Classes into Packages

## Lesson Theme

Every program so far lived in one folder with no `package` line, and you never once wrote `import` — except, quietly, back in Lesson 2, where you copied `import java.util.Scanner;` on faith. Today that line finally makes sense. A **package** is a named group of classes — a labelled drawer you sort classes into — and **`import`** is how one file borrows a class from another package. This is the last piece of "how real Java projects are laid out," and it's the on-ramp to Tier 4: every FTC file you'll ever write opens with `package org.firstinspires.ftc.teamcode;`. By the end of today, that line has no mystery left.

## What You'll Build

- `robotparts/Motor.java` — the encapsulated `Motor` from last lesson, now declared part of a **package** called `robotparts`, living in a folder of that name.
- `RobotMain.java` — a separate program that **imports** `robotparts.Motor` and drives it, proving one file can borrow a class from another package.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- Still pure Java — but now spanning a package folder

## What You'll Learn

**Java skills:**
- What a **package** is: a named group of classes that must match a folder path
- The `package` declaration — the first line of a file, naming the drawer it belongs to
- **`import`** — using a class from another package by its short name
- Why `String`, `System`, and `Math` never needed an import (`java.lang` is imported for you)
- The choice between importing and writing a class's **fully-qualified name** (`robotparts.Motor`)

**Thinking skills:**
- Packages as *organisation* and as *avoiding name clashes* (two `Motor` classes can coexist in different packages)
- Reading the boilerplate at the top of every real Java file as meaningful, not magic

## In Class

A 60-minute session.

### The line you already wrote (6 min)

Open your Lesson 2 program — the one that read input. Right at the top:

```java
import java.util.Scanner;
```

You copied it without knowing what it did. Now the reveal: `Scanner` isn't a built-in word like `int` — it's a *class*, and it lives in a package called `java.util`. That `import` line is what let you write `Scanner` instead of its full name every time. You've been using packages since your second lesson. Today you learn to make your own.

### What a package is (10 min)

A **package** is a named group of classes — think of it as a labelled drawer. Two reasons they exist:

1. **Organisation.** A real robot project has dozens of classes — motors, sensors, autonomous routines. Dumping them all in one pile is chaos; sorting them into packages (`robotparts`, `sensors`, `auto`) keeps them findable.
2. **Avoiding name clashes.** Java's own library has a `Timer`. The FTC SDK has a `Timer`. Your team might write a `Timer`. Packages keep them apart: `java.util.Timer` and `robotparts.Timer` are different classes with the same short name, and nobody gets confused.

The key rule: **a package matches a folder.** A class in package `robotparts` must live in a folder called `robotparts`. The package name *is* the folder path.

### Declaring a package (12 min)

Open `robotparts/Motor.java`. The very first line is new:

```java
package robotparts;

public class Motor {
    ...
```

`package robotparts;` says "this class belongs to the `robotparts` drawer." And notice *where the file lives*: inside a folder named `robotparts`. The declaration and the folder must agree — that's the rule. (If a file says `package robotparts;` but sits in the wrong folder, it may still compile, but Java won't be able to *run* it — it looks for the class in the folder the package names, and doesn't find it there.)

One more thing to notice: `Motor`, its constructor, and its methods are all `public`. For a class in one package to be usable from *another* package, it has to be public — otherwise the drawer stays shut. Private-vs-public (last lesson) and packages work together: `public` is what makes a class reachable across package lines.

### Borrowing a class with `import` (14 min)

Now open `RobotMain.java`, which lives *outside* the `robotparts` folder:

```java
import robotparts.Motor;

public class RobotMain {

    public static void main(String[] args) {
        Motor left = new Motor("left_motor");
        left.setPower(0.5);
        left.printStatus();
    }
}
```

`import robotparts.Motor;` means "let me use `robotparts`'s `Motor` by its short name." Without it, Java has no idea what `Motor` means — it's in a different drawer.

Compile it — and note the folder now matters:

```
javac robotparts/Motor.java RobotMain.java
java RobotMain
```

Output:

```
left_motor at power 0.5
```

You just used a class from another package. That's the whole mechanic.

Two things worth knowing:
- **You could skip the import** and write the full name every time: `robotparts.Motor left = new robotparts.Motor("left_motor");`. That's the *fully-qualified name*. `import` just saves you from repeating it — same result.
- **Why did `String` and `System` never need an import?** They live in `java.lang`, the one package Java imports into every file automatically. `Scanner` lives in `java.util`, which is *not* automatic — that's why it needed a line and `String` didn't.

### The FTC bridge — decode the real thing (5 min)

Here is the top of a genuine FTC OpMode, the kind you'll write in Tier 4:

```java
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
```

Read it with today's eyes:
- `package org.firstinspires.ftc.teamcode;` — "this file belongs to the `org.firstinspires.ftc.teamcode` drawer" (a longer, dotted package name — deeper nested folders, same idea as `robotparts`). Every FTC file you write lives here.
- `import com.qualcomm.robotcore.eventloop.opmode.OpMode;` — "borrow the `OpMode` class from the SDK's package." `DcMotor` the same. These are exactly like `import robotparts.Motor;`, just from Qualcomm's library instead of your folder.

Nothing on that boilerplate is magic anymore. You can read every line.

### Your turn (5 min)

Add a second class to the package:

1. Create `robotparts/Servo.java` with `package robotparts;` at the top and a small `public` Servo class (a `name`, an `angle`, a `setAngle`, a `printStatus`).
2. In `RobotMain`, add `import robotparts.Servo;`, create a servo, and drive it alongside the motor.
3. Compile with all three files named, and confirm both mechanisms print. One package, two classes — one import per class.

### Reflection (3 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. In your own words, what is a package, and what are the two problems it solves?
2. What does `import robotparts.Motor;` let you do that you couldn't do without it?
3. Why did `Scanner` need an import back in Lesson 2, but `String` never has?

## Stretch Project (Optional)

Finished early? Lay out a mini robot the way a real FTC project is organised.

**Robot Build.** In your `robotparts` package (same folder), make sure you have three `public` mechanism classes, each in its own file: `Motor.java` (has `setPower`), `Servo.java` (has `setAngle`), and `Lift.java` (has `raise(int)`). Then write a driver `RobotBuild.java` *outside* the package that **imports all three**, creates one of each, drives each a little, and prints all three statuses. Compile with every file named:
```
javac robotparts/Motor.java robotparts/Servo.java robotparts/Lift.java RobotBuild.java
java RobotBuild
```
This is a miniature of exactly how a real robot's code is arranged — mechanism classes in a package, a main program importing them. A worked version is in `solutions.md`; try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `robotparts/Motor.java` — the `Motor` class, declared `package robotparts;`, inside a `robotparts` folder
- `RobotMain.java` — imports `robotparts.Motor` and drives it

Your homework files build a fresh `sensors` package; `ImportBugHunt.java` (in the `code/` subfolder) is the bug hunt. Full instructions in `homework.md`.

**How to compile across a package** (run from inside the `code/` folder):
```
javac robotparts/Motor.java RobotMain.java
java RobotMain
```

## Notes for the Teacher

**Setup check before the session:**
- This is the first lesson where **folder layout matters**. `Motor.java` must sit inside a folder named exactly `robotparts`, and `RobotMain.java` must sit *outside* it. If you rearrange the files, the import breaks. Compile from the folder that *contains* `robotparts` (i.e. the `code/` folder), naming both files.
- VS Code's Run button and the Java extension handle package folders automatically once the folder names match the `package` lines — but have students compile from the terminal at least once so they *see* the folder/package correspondence.

**On the Scanner callback (use it):**
- Opening on the Lesson 2 `import` line is the whole motivational hook. Students have *already done* this; the lesson is making the familiar explicit, not introducing something alien. Don't skip straight to `robotparts` — start from the line they already trust.

**On the folder-must-match rule (be precise):**
- With plain `javac`, a file whose `package` line disagrees with its folder can actually *compile*, then fail at `java` time with `NoClassDefFoundError (wrong name)`. Don't over-explain this; the honest one-liner is "the folder has to match the package name, or Java can't find the class to run it." If a student hits the error, that's the cause.

**On scope (deliberately held back):**
- **No `static`, no inheritance.** Packages are purely about *organisation* here.
- **The FTC SDK is shown, not compiled.** The `com.qualcomm...` imports are for *reading* — that code needs the SDK on the classpath, which arrives with the simulator/Tier 4. Do not try to compile the OpMode header in this lesson.
- **One package level deep** for hands-on (`robotparts`, `sensors`). The dotted FTC name (`org.firstinspires.ftc.teamcode`) is decoded by eye only, not built by hand — nested-package folders would be friction with no payoff yet.
- **Repo note:** this lesson's `code/` folder intentionally contains a package subfolder (`robotparts/`), unlike other lessons — because the whole point is a real package. That's expected here, not a style slip.

**Anticipated questions:**
- *"Do I have to use `import`, or can I write the long name?"* — "Both work. `import` just lets you write `Motor` instead of `robotparts.Motor` every single time. Real code almost always imports, so it's the habit to build."
- *"Why is the FTC package name so long — `org.firstinspires.ftc.teamcode`?"* — "It's a dotted path: `org` → `firstinspires` → `ftc` → `teamcode`, nested folders. Long names make clashes basically impossible worldwide — no other organisation uses `org.firstinspires`."
- *"What happens if two packages both have a `Motor`?"* — "They don't clash, because their full names differ (`robotparts.Motor` vs someone else's). You import the one you mean. That's exactly what packages are for."

**Common mistakes to watch for:**
- Putting `Motor.java` in the wrong folder (or the same folder as `RobotMain`) and getting a confusing error. Check the folder matches the `package` line first, always.
- Forgetting the `import` and seeing `cannot find symbol: class Motor` — the exact bug in the homework hunt.
- Making the class or constructor non-`public` and finding it unreachable from another package. Cross-package use needs `public` (ties straight back to last lesson).
- Compiling only `RobotMain.java` without naming `robotparts/Motor.java`. Name both.
