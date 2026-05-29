# FTC Java Robotics — Course Overview

## What this course is

This is a 24-session course that takes a student from block-based coding (Scratch, MIT App Inventor) to writing real Java code for competitive robotics. By the end, students write the same Java that powers robots at FIRST Tech Challenge — the world's largest robotics competition for secondary school students.

The course is taught 1:1, twice a week, in 60-minute live sessions. There is no classroom to keep pace with. Every session adapts to where the student actually is.

## Who this course is for

Students aged 11–16 who have completed meaningful work in Scratch or MIT App Inventor. They should be comfortable with variables, loops, conditionals, and events in a block-based environment. They do not need any prior experience with Java, text-based coding, or robotics hardware.

This is not a "learn to code" course. It is a course for students who already think computationally and are ready for a more powerful language and a more demanding context.

## What the student will build

The course is organised into four tiers, taught over 24 sessions:

| Tier | Sessions | Focus |
|------|----------|-------|
| **Tier 1 — Syntax** | 1–5 | Translating familiar Scratch logic into Java text: printing and reading errors, variables and types, user input, conditionals, loops, and writing your own methods |
| **Tier 2 — Mental Model** | 6–11 | Where Java starts to think differently: method parameters and return values, `int` vs `double`, boolean logic, the `null` concept, and reading error messages and stack traces |
| **Tier 3 — Objects** | 12–18 | Object-oriented programming through robotics: classes and objects, instance fields, `public`/`private`, and building mechanisms by composition |
| **Tier 4 — FTC SDK** | 19–24 | The official FTC software: OpModes, annotations, the `hardwareMap`, `enum`/`switch` state machines, and the final TeleOp + autonomous project |

Each tier in more depth:

**Tier 1 — Syntax: reading and writing Java (Sessions 1–5)**
Students translate familiar Scratch logic into Java syntax. They write programs that use variables, conditionals, loops, and methods — the same ideas they already know, now expressed in text. By the end of this stage, a student can read a short Java program and predict what it does.

**Tier 2 — Mental Model: thinking in Java (Sessions 6–11)**
Students encounter the ideas that make Java genuinely different from Scratch: typed return values, null references, boolean logic, and error messages as a tool rather than a threat. They learn to read a stack trace the way a mechanic reads a diagnostic — calmly, systematically. Debugging becomes a habit, not a crisis.

**Tier 3 — Objects and structure (Sessions 12–18)**
Students learn object-oriented programming through the lens of robotics. A motor is an object. A drivetrain is an object that contains motors. A robot is an object that contains a drivetrain, an arm, and a claw. They build these structures themselves, experiencing firsthand why organisation matters as code grows.

**Tier 4 — The FTC SDK (Sessions 19–24)**
Students apply everything they have learned inside the official FIRST Tech Challenge software development kit. They write TeleOp programs (driver-controlled), autonomous routines (robot acts alone), and state machines (the robot makes decisions). The code they write in this stage is the same code that runs on competition robots.

## The final project

In the last two sessions, each student builds a complete robot program from scratch: a TeleOp mode for driver control and an autonomous routine that scores game elements without human input. This is presented in a short Demo Day session where the student walks through their code, explains their design decisions, and demonstrates the robot (in simulation or on hardware) to a small audience.

## How we teach — no hardware required

Students begin in a software simulator called virtual_robot, which provides a realistic 2D robot on screen. Every concept from Sessions 1 through 18 is taught entirely in simulation. There is no hardware to purchase, configure, or debug during the learning phase.

For students who later join or form an FTC team, the transition to physical hardware is seamless — the Java code is identical. We can advise on hardware kits (goBILDA starter kit + REV Control Hub) at that stage, but hardware is never a prerequisite for the course.

## Tools

- **Editor**: Visual Studio Code with the Java Extension Pack
- **Simulator**: virtual_robot (open-source, runs on any laptop)
- **Language**: Java (the official language of FTC)
- **Reference text**: _Learn Java for FTC_ by Alan G. Smith (free)

## Prerequisites

- Comfortable with Scratch or MIT App Inventor (variables, loops, conditionals, events)
- A laptop (Windows, Mac, or Linux) capable of running VS Code
- No prior Java or robotics experience needed

## What the student will be able to do after this course

1. Read a Java error message and locate the bug independently
2. Write a clean, multi-mechanism TeleOp program using well-structured classes
3. Write an autonomous routine that uses a state machine to make decisions
4. Navigate the official FTC SDK documentation to find methods and classes they have not seen before
5. Explain, from experience, why organising code into objects makes complex programs manageable

## Course details

- **Format**: 1:1 live online sessions
- **Duration**: 24 sessions (approximately 12 weeks at 2 sessions per week)
- **Session length**: 60 minutes
- **Between-session practice**: 30–45 minutes of guided homework
