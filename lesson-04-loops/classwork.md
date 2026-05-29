# Lesson 04 — Doing It Again

## Lesson Theme

Whenever you've needed to repeat something, you've copied and pasted. Today the computer does the repeating for you — counting down a timer, or driving forward step by step until the robot reaches its target.

## What You'll Build

Two programs: a countdown that prints `5 4 3 2 1 GO!`, and a "drive to target" program that steps a robot's position forward until it reaches a distance the user chooses.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — still pure Java

## What You'll Learn

**Java skills:**
- Write a `for` loop for counted repetition ("do this N times")
- Write a `while` loop for conditional repetition ("keep going until something changes")
- Use a loop counter and the `++` / `--` shortcuts (`i++` means `i = i + 1`)
- Choose between `for` and `while` based on whether you know the count in advance
- Recognise an **infinite loop**, understand why it happens, and stop it with Ctrl+C

**Thinking skills:**
- Tracing a loop by hand — what is the counter on each pass?
- Seeing that a loop's condition must *eventually* become false, or it never ends
- Picking the right loop for the job

## In Class

A 60-minute session.

### Recap and the Scratch bridge (6 min)

Recap Lesson 3: your program can now make decisions. But imagine printing a countdown — `5`, `4`, `3`, `2`, `1` — you'd write five `println` lines. A hundred? No chance. Repetition is exactly what loops are for, and you've used them since your first Scratch project:

| Scratch | Java | When to use |
|---|---|---|
| `repeat (10)` | `for` loop | you know how many times |
| `repeat until < >` | `while` loop | you repeat until something changes |
| `forever` | `while (true)` | runs without stopping (careful!) |

### The for loop (14 min)

A `for` loop is for counted repetition. Your teacher builds `Countdown.java`:

```java
for (int i = 5; i >= 1; i--) {
    System.out.println(i);
}
System.out.println("GO!");
```

The header has three parts, separated by semicolons:
1. **Start:** `int i = 5` — make a counter, begin at 5
2. **Keep going while:** `i >= 1` — a condition, just like Lesson 3
3. **Change each time:** `i--` — subtract 1 after every pass

`i--` is shorthand for `i = i - 1`. Its partner `i++` means `i = i + 1`. You'll use `i++` constantly.

Trace it together out loud: i is 5 (print 5), then 4, 3, 2, 1 (print each), then i becomes 0 — `0 >= 1` is false, so the loop stops and `GO!` prints. Then change it to count *up*: `for (int i = 1; i <= 5; i++)`.

### The while loop (14 min)

Sometimes you don't know the count in advance — you just repeat *until* something is true. That's a `while` loop. You'll build `DriveToTarget.java`:

```java
double position = 0.0;
double step = 10.0;

while (position < target) {
    position = position + step;
    System.out.println("Position: " + position + " cm");
}
System.out.println("Target reached!");
```

Read it as: "while we haven't reached the target, move one step forward and report." Each pass adds `step` to `position`. Eventually `position < target` becomes false and the loop ends.

Run it with a target of `35`. Watch the position climb 10, 20, 30, 40 — note it overshoots to 40 because each step is 10. That's a real robotics detail worth noticing, not a bug.

### for vs while (8 min)

The key question: **do you know how many times in advance?**

- **Known count** → `for`. "Blink the light 3 times." "Count down from 5."
- **Until a condition** → `while`. "Drive until you reach the wall." "Keep asking until the user types a valid number."

Have the student classify a few out loud: "spin the intake 4 times" (for), "lift the arm until the sensor triggers" (while), "print every number from 1 to 100" (for).

### Infinite loops, on purpose (8 min)

Every loop's condition must *eventually* become false. If it can't, the loop runs forever. Make one deliberately:

```java
int count = 5;
while (count > 0) {
    System.out.println("Counting: " + count);
    // oops - we never change count, so count > 0 is always true
}
```

Run it. It floods the screen. **Press Ctrl+C in the terminal to stop it** — an essential skill. Then fix it by adding `count--;` inside the loop so the condition can become false. The lesson: when a program hangs or spams forever, suspect a loop whose condition never changes.

### Vocabulary checkpoint (6 min)

Explain in your own words:
- The three parts of a `for` loop header
- What `i++` does
- How you decide between `for` and `while`
- What causes an infinite loop, and how you stop one

### Reflection (4 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. What can your program do today that it couldn't do last session?
2. In `DriveToTarget`, why did the position overshoot the target? What would make it stop closer?
3. Give one example of a job for a `for` loop and one for a `while` loop, and say why each fits.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `Countdown.java` — the `for` loop your teacher demonstrates
- `DriveToTarget.java` — the `while` loop you build

Your homework files (`PredictLoops.java`, `LoopBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Before the session:**
- Confirm the student knows how to find and use the terminal — the infinite-loop activity needs Ctrl+C, so make sure focus is in the terminal panel when they press it.
- Have a couple of target values ready for `DriveToTarget` (one that divides evenly by the step, like 30, and one that doesn't, like 35) to show the overshoot.

**On `++` / `--` and `static`:**
- `++` and `--` are new but intuitive — frame them purely as "add one / subtract one". Don't go into pre- vs post-increment; it doesn't matter in these examples.
- Methods (`static` custom blocks) are *next* session, not this one. Keep everything inside `main` today. If a student's program gets long, that's good — it sets up the felt need for Lesson 5.

**On scope (deliberately held back):**
- **No nested loops** yet unless a student races ahead — one loop at a time keeps tracing manageable.
- **No `break` / `continue`** — design the loop condition to do the work instead.
- Still **no `&&` / `||`** in loop conditions (Tier 2). A single comparison is enough for every example here.

**Anticipated questions:**
- *"Why does it overshoot the target?"* — "Each step is 10 cm, so it jumps 30 → 40 and only then notices it passed 35. Real robots face this exact problem; smaller steps get closer. A great thing to notice."
- *"What's the difference between `for` and `while`?"* — "Same idea — repeat — but `for` is tidy when you know the count, and `while` is right when you only know the stopping condition."
- *"My program won't stop!"* — "You've got an infinite loop. Press Ctrl+C, then check: does anything inside the loop eventually make the condition false?"

**Common mistakes to watch for:**
- Forgetting to change the counter (`i++` / `count--`), causing an infinite loop — the headline loop bug. Let them hit it in the bug hunt.
- Using commas instead of semicolons in the `for` header (`for (int i = 0, i < 3, i++)`) — a compile error.
- Off-by-one: `i <= 5` vs `i < 5`. Tracing the first and last pass by hand catches these.
- A stray semicolon after the loop header: `while (x < 5);` — the body then runs zero times or loops emptily.
