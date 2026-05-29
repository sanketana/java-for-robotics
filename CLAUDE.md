# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

This repo builds the **FTC (FIRST Tech Challenge) Java robotics track** for **Sanketana School of Code** — a live 1:1 online coding school for students aged 8–18, based in Bangalore, run by Abhinav. The deliverables are:

- `.java` files (real, compilable OpMode examples)
- `.md` lesson materials (`classwork.md` + `homework.md`) per lesson
- A repo structure that could later be lifted into the actual LMS

### Audience for the repo

This repository is **student- and parent-facing**, not just for teachers. The `classwork.md` and `homework.md` files are written so a student or parent can read them directly. Teacher-only guidance (setup checks, common mistakes, anticipated questions) lives in a clearly marked **"Notes for the Teacher"** section at the bottom of each `classwork.md` — never as a separate file.

### Target student

Aged ~11–16, fluent in Scratch and MIT App Inventor. Has computational thinking. Does not have Java syntax, static typing, or formal OOP.

### Hardware/software

- **Teaching reference**: Alan G. Smith's _Learn Java for FTC_ (free PDF: https://github.com/alan412/LearnJavaForFTC). Targets REV Robotics + FTC SDK.
- **Default environment**: the `virtual_robot` 2D simulator (https://github.com/Beta8397/virtual_robot) with "ProgrammingBoard" config. Students start here — zero hardware investment.
- **Optional later**: goBILDA FTC starter kit + REV Control Hub for students going to actual FTC.

---

## Repo structure

Grouping is **by lesson only** — no top-level `code/` folder, no tier folders. Each lesson folder holds everything a teacher needs for that session, so there is no folder-switching mid-lesson.

Folder names follow `lesson-NN-<short-topic>` — the `NN` keeps lessons ordered, and the short topic slug gives an at-a-glance peek at the session (e.g. `lesson-01-hello-java`, `lesson-02-variables-input`). Keep the slug to 1–3 kebab-case words.

```
java-for-robotics/
├── CLAUDE.md
├── 00-course-overview.md
├── lesson-01-hello-java/
│   ├── classwork.md      # in-class material (student/parent voice) + "Notes for the Teacher"
│   ├── homework.md       # practice tasks before next session
│   ├── solutions.md      # worked homework answers (expected outputs + corrected code)
│   └── code/             # all .java files for this lesson — demos + starter templates
│       └── *.java
├── lesson-02-variables-input/
│   ├── classwork.md
│   ├── homework.md
│   ├── solutions.md
│   └── code/
│       └── *.java
└── ...                   # lesson-03-… … lesson-24-…, same shape
```

Tiers still exist as a **curriculum concept** (see the 4-tier spine below), but they are not folders. Each lesson folder contains three markdown files (`classwork.md`, `homework.md`, `solutions.md`) plus a `code/` subfolder holding all the `.java` files — keeping the lesson root uncluttered. `solutions.md` opens with a "try it yourself first" note, since the repo is student-facing.

---

## Sanketana identity and tone

- **Always**: "Sanketana School of Code" — never "Sanketana Academy"
- **Tagline direction**: "Coding, taught like a craft" — coach over curriculum
- **Audience**: sophisticated parents; courses must have genuine depth, never gimmicky
- **Pedagogy**: Think, Build, Reflect, Share. Minimal foundational priming before hands-on building.
- **Tone in materials**: institutional-warm, not tutoring-shop language. Because the materials are student- and parent-facing, write `classwork.md`/`homework.md` in a direct second-person voice to the student ("You'll build…", "Notice that…"), not as a script addressed to the teacher. Teacher-directed instructions belong only in the "Notes for the Teacher" section.
- **No emojis** in lesson materials unless explicitly requested
- **Indian English** spellings and INR pricing if pricing comes up

---

## Lesson material template

Every lesson's material is split across two files. The nine canonical sections still all appear — eight live in `classwork.md`, the ninth (Homework) is its own file.

### `classwork.md` (student/parent voice)

1. **Lesson Theme** — one-line "what this session is really about" (not just the topic)
2. **What You'll Build** — the central thing the student builds or does (the old "Key Activity")
3. **Tools Used** — IDE, simulator, hardware, libraries, etc.
4. **What You'll Learn** — split into:
   - Java/technical skills
   - Thinking skills (mental models, debugging discipline, decomposition, etc.)
5. **In Class** — a 60-minute session broken into time blocks (the old "Main Class Activities"), written so a reader can see exactly what happens
6. **Reflection** — the prompts the student answers at end of session
7. **Starter Materials** — points to the `.java` skeletons/demos in the lesson folder, virtual_robot setup, worksheets
8. **Notes for the Teacher** — the only teacher-directed section: setup checks before the session, common student mistakes, anticipated questions. Clearly demarcated at the bottom.

### `homework.md` (student/parent voice)

9. **Homework** — practice tasks before next session, with the relevant `.java` files in the lesson's `code/` subfolder.

### `solutions.md` (student/parent voice)

Worked answers to the homework: expected program outputs, corrected versions of the bug-hunt files, and a sample for any open-ended build. Always opens with a "try it yourself first" note so it reads as a check, not a shortcut.

---

## Curriculum: the 4-tier spine

### Tier 1 — Syntax shock absorbers (sessions 1–5)

Bridge from Scratch to Java syntax. Same logic, scarier text. (Originally scoped at 4 sessions; expanded to 5 so conditionals, loops, and method definition each get room — see the lesson breakdown below.)

- Static typing (`int`, `double`, `boolean`, `String`)
- Semicolons, braces, indentation discipline
- `System.out.println` as the feedback loop
- Method calls with dot notation
- Method definition (`public void doX()`) — framed as "custom blocks"
- `if/else`, `while`, `for` — translating from Scratch blocks

Lesson breakdown: L1 print/syntax/errors · L2 variables, types, math operators, `Scanner` input · L3 `if/else` + comparison operators · L4 loops (`while`/`for`) · L5 method definition (custom blocks), as the Tier 1 capstone. Tier 1 grew from 4 to 5 sessions; the **course target stays 24**, to be held by trimming one session later in Tiers 2–4 (not yet chosen). The downstream tier session numbers below are therefore approximate until that trim is decided.

### Tier 2 — Mental model shift (sessions 5–10)

Where Java starts thinking differently.

- Method parameters and return values
- `double` vs `int` and why it bites (motor power = 0.5, not 0)
- Boolean expressions: `&&`, `||`, `!`
- The `null` concept and `NullPointerException`
- Code formatting and comments as habit
- **Reading error messages / stack traces** — treated as a first-class skill
- Debugging via telemetry / println

### Tier 3 — Objects as nouns (sessions 11–18)

OOP via the Scratch-sprite analogy ("a sprite is a class, each sprite on screen is an object").

- Classes and objects, constructors
- Instance variables (fields) vs local variables
- `public` vs `private`, `this`
- `new` keyword, `import` statements and packages
- **Composition lands before inheritance**

### Tier 4 — FTC-specific layer (sessions 19–24)

Now wear the FTC SDK hat.

- Inheritance via `extends OpMode` (frame as "the SDK hands you a template")
- Annotations: `@TeleOp`, `@Autonomous`, `@Override` (label, don't deep-dive)
- The `hardwareMap` as the bridge between robot and code
- `enum` for state machines, `switch` for state machine bodies
- Reading the FTC SDK API docs

### Deliberately NOT taught

This restraint is part of the pedagogy. Do not let scope creep pull these into early sessions:

- Generics (encounter, don't teach)
- Interfaces, abstract classes
- Exception handling (`try/catch`) — only when a real error forces it
- Static methods/fields beyond `Math.abs()`
- Lambdas, functional interfaces
- Threads / concurrency
- Collections beyond `ArrayList`
- Recursion, File I/O

### Course-level outcomes

By end of 24 sessions, the student can:

1. Read a stack trace and isolate a bug independently
2. Write a clean multi-mechanism TeleOp OpMode using mechanism classes
3. Write a basic autonomous OpMode using an enum-based state machine
4. Navigate the FTC SDK docs to find a method they haven't seen before
5. Articulate _why_ OOP helps — not as a definition, but from felt experience

---

## Java code style for `.java` files

All FTC OpMode examples must follow these conventions:

- **Package**: `org.firstinspires.ftc.teamcode` (FTC standard)
- **Imports**: explicit — no wildcards (`import com.qualcomm.robotcore.eventloop.opmode.OpMode;`)
- **Annotations**: `@TeleOp(name = "Lesson 03 — Basic Drive")` so lesson order is visible on the driver station
- **Comments**: short, coach-voice. No tautological comments ("this is a class").
- **Naming**: descriptive over clever. `leftMotor`, `armMotor`, not `m1`, `lm`.
- **Constants**: `private static final double LIFT_POWER = 0.5;` named in caps from Tier 3 onward
- **File grouping**: `.java` files live in the `code/` subfolder of their lesson (`lesson-NN-<topic>/code/`), not in package-mirroring subfolders. Tier 1–2 examples need no `package` line; Tier 3–4 OpModes use `package org.firstinspires.ftc.teamcode`. Mechanism classes are still one per file.
- **No dead code, no commented-out sections** in finished examples
- **All imports complete and compilable** — even if the student won't run it standalone

Reference example (Tier 4 quality):

```java
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Lesson 03 — Basic Drive")
public class BasicDrive extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        leftMotor  = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;

        leftMotor.setPower(drive + turn);
        rightMotor.setPower(drive - turn);

        telemetry.addData("Left power",  drive + turn);
        telemetry.addData("Right power", drive - turn);
    }
}
```

---

## Working norms

1. **Build session-by-session.** Produce one lesson's `classwork.md` + `homework.md` + its `.java` files, then stop and ask for review. Do not batch-generate all 24.
2. **Show, don't tell, in lesson materials.** The "In Class" section should read like a teacher could just _run it_. No generic "introduce the concept" placeholders.
3. **Every concept has a felt-need motivation.** Before introducing a Java feature, the lesson must set up the problem the feature solves. E.g., don't teach classes until the student has written a messy flat OpMode and felt the pain.
4. **Code examples must be compilable.** No pseudocode in `.java` files. If something is illustrative, mark it clearly.
5. **The Scratch bridge is the secret weapon.** When introducing a new Java concept in Tiers 1–3, explicitly reference the Scratch equivalent.
6. **Telemetry and stack-trace reading are woven throughout** — a habit built session by session, not a single "debugging" lesson.
7. **The simulator (virtual_robot) is the default environment** through at least Tier 3. Hardware is optional.

---

## Prior work from chat

These exist as polished outputs in a previous Claude.ai conversation — do not re-derive. Ask Abhinav to paste if needed:

- Worked example: 4-stage FTC code progression (basic drive, drive+arm flat, refactored with mechanism classes, autonomous state machine). All four code blocks are competition-quality.
- The 4-tier Java concept map (reproduced above)
- The "deliberately NOT teach" list (reproduced above)
