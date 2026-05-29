# Lesson 03 — Making Decisions

## Lesson Theme

So far your programs always do the same thing, no matter what. Today they learn to *choose* — to react differently depending on what's true, the way a robot behaves differently when the battery is low or the wall is close.

## What You'll Build

A program that reads a value and responds differently depending on it: a battery checker that warns when voltage is low, and a distance classifier that sorts a sensor reading into "too close", "good", or "too far".

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- No simulator yet — still pure Java

## What You'll Learn

**Java skills:**
- Compare values with `==`, `!=`, `<`, `>`, `<=`, `>=`, and see that a comparison produces a `boolean` (the `true`/`false` type from Lesson 2)
- Write an `if` statement that runs code only when a condition is true
- Add an `else` for the "otherwise" case
- Chain decisions with `else if` to sort a value into ranges
- Spot the single most common decision bug: `=` (assign) vs `==` (compare)

**Thinking skills:**
- Translating a real-world rule ("if the battery is below 11 volts, warn me") into a precise condition
- Realising the order of your tests matters — Java takes the *first* one that's true
- Reading a condition out loud to check it says what you meant

## In Class

A 60-minute session.

### Recap and the Scratch bridge (6 min)

Recap Lesson 2: your program can now compute and remember, and it can ask the user a question. But it always does the same thing with the answer. A real tool reacts.

You've made decisions before — every Scratch project with an `if ... then` block:

| Scratch | Java |
|---|---|
| `if < (score) > (5) > then` | `if (score > 5) { ... }` |
| `if ... then ... else` | `if (...) { ... } else { ... }` |
| the `>` , `<` , `=` operator blocks | `>`, `<`, `==` |

Same idea, typed out. The one trap to watch: in Java, "is it equal?" is written `==` (two equals signs), because a single `=` already means "put this value in here."

### Comparison operators (10 min)

Before deciding, you need to *ask a true/false question*. That's what comparison operators do — and the answer is a `boolean`, the type we met last session that finally gets a job today.

Try these and read each result:

```java
int score = 7;
System.out.println(score > 5);    // true
System.out.println(score < 5);    // false
System.out.println(score == 7);   // true  -- note the double equals
System.out.println(score != 7);   // false -- "not equal"
System.out.println(score >= 7);   // true  -- greater than OR equal
System.out.println(score <= 6);   // false -- less than OR equal
```

The six operators: `>`, `<`, `>=`, `<=`, `==` (equal), `!=` (not equal). Each one hands back `true` or `false`. Say each out loud — "is score greater than 5? true."

### The if statement (10 min)

An `if` runs a block of code *only when* its condition is true. Your teacher builds this and runs it with a few different voltages:

```java
double voltage = 10.5;

if (voltage < 11.0) {
    System.out.println("Battery low - charge before the match.");
}
```

Notice the shape:
- `if`, then the condition in round brackets `( )`
- then a block in curly braces `{ }` — this runs only if the condition is true
- no semicolon after the `)` — the block is the body, not a statement to end

Run it with `voltage = 10.5` (you see the warning) and again with `voltage = 12.0` (nothing prints). The decision is working.

### Adding else (8 min)

Right now, a good battery prints *nothing* — not very helpful. `else` gives you the "otherwise" branch:

```java
if (voltage < 11.0) {
    System.out.println("Battery low - charge before the match.");
} else {
    System.out.println("Battery good - ready to go.");
}
```

Exactly one of the two blocks runs, every time. This is the full `BatteryCheck.java` in your `code/` folder. Run it, type different voltages, and confirm you always get exactly one message.

### Chaining with else if (12 min)

Two outcomes is often not enough. A robot reading the distance to a wall has *three* cases: too close, just right, too far. You'll build `DistanceZone.java`:

```java
if (distance < 10.0) {
    System.out.println("Too close - back up!");
} else if (distance < 30.0) {
    System.out.println("Good distance - hold position.");
} else {
    System.out.println("Too far - move closer.");
}
```

The key idea: Java checks the tests **top to bottom and stops at the first true one.** That's why `else if (distance < 30.0)` doesn't need to also say "and 10 or more" — if Java reached that line, the first test was already false, so the distance must be 10 or more.

Test the boundaries on purpose: type `5`, `10`, `25`, `30`, `50`. Watching what happens exactly at `10` and `30` is where the real understanding lands.

### The == vs = trap (6 min)

This is the bug that catches everyone, so meet it deliberately. In a condition you must use `==`:

```java
if (temperature == 40) { ... }   // correct: "is temperature equal to 40?"
if (temperature = 40)  { ... }   // wrong:   this tries to ASSIGN 40
```

Type the wrong one on purpose and read the error: `incompatible types: int cannot be converted to boolean`. That message is Java's way of saying "you gave me an assignment where I expected a true/false question." When you see it, check your `==`.

### Vocabulary checkpoint (4 min)

Explain in your own words:
- The difference between `=` and `==`
- What a comparison like `score > 5` produces
- Why `else if` chains don't need to repeat the previous condition

### Reflection (4 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. What can your program do today that it couldn't do last session?
2. In `DistanceZone`, why does the middle branch only need `distance < 30.0` and not also `distance >= 10.0`?
3. Describe a decision a real robot might make, and write the `if` condition for it.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `BatteryCheck.java` — the program your teacher demonstrates (`if`/`else`)
- `DistanceZone.java` — the classifier you build (`else if` chain)

Your homework files (`PredictDecisions.java`, `DecisionBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

## Notes for the Teacher

**Before the session:**
- Confirm the student's setup still compiles and runs from Lesson 2.
- Have a few voltage/distance values ready so you can demonstrate each branch live — seeing the *same program* print different things is the lightbulb moment.

**On scope (what we are deliberately holding back):**
- **No `&&`, `||`, `!`** today. Combining conditions ("battery low AND match starting") is a Tier 2 lesson. If a student wants two conditions, nudge them toward nested `if`s or an `else if` chain for now.
- **No `switch`** — that arrives in Tier 4 with state machines.
- Keep comparisons to numbers. Comparing `String`s with `==` is a classic trap (`.equals()` is the right tool), but `String` comparison isn't needed yet — steer examples to numeric values and avoid it.

**Anticipated questions:**
- *"Why two equals signs?"* — "One `=` already has a job: putting a value into a variable. Java needed a different symbol for 'are these the same?', so it uses `==`."
- *"What if I want 'between 10 and 30'?"* — "Today, use the `else if` order trick: if the first test (`< 10`) was false, you already know it's 10 or more, so the next test only checks the top end. Combining conditions directly comes in a later session."
- *"Can I have an `if` with no `else`?"* — "Yes. `else` is optional. Use it when there's an 'otherwise' worth handling."

**Common mistakes to watch for:**
- `=` instead of `==` in a condition — the error is `incompatible types: int cannot be converted to boolean`. This is the headline bug of the lesson; let them hit it and read it.
- A stray semicolon right after the condition: `if (x > 5);` — the body then never depends on the condition. Worth showing once if it comes up.
- `else if` ordering: putting the wider test first (`< 30` before `< 10`) so the narrow case can never be reached. Test boundary values to expose it.
- Forgetting the closing brace of the `if` block before `else`.
