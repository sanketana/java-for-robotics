# Lesson 11 — From Loose Variables to Things

## Lesson Theme

Until now every program has been a pile of loose variables and helper methods. Today Java learns to think in **nouns**. You'll bundle a robot's data (its name, its speed, its score) and its behaviour (score some points, print a status line) into a single **blueprint** called a *class* — and then stamp out as many robots as you like from it, each with its own data. This is the biggest new idea in the whole course, and Scratch has been quietly preparing you for it the entire time.

## What You'll Build

- `Robot.java` — a blueprint for "a robot": three fields it remembers (`name`, `topSpeed`, `score`), a constructor that sets one up, and two methods (`addScore`, `printStatus`).
- `RobotDemo.java` — a program that creates **two** robots from that one blueprint, scores them differently, and proves each keeps its *own* data.

## Tools Used

- Visual Studio Code with the Java Extension Pack
- The terminal (built into VS Code)
- Still pure Java — no simulator yet

## What You'll Learn

**Java skills:**
- What a **class** is: a blueprint that bundles data + behaviour
- **Fields** (instance variables) — a variable that belongs to *each object*, not to a method
- The **constructor** — the special setup code that runs when you write `new Robot(...)`
- The `new` keyword — how you actually create an object from a blueprint
- Calling methods and reading data on an object with **dot notation** (`titan.addScore(30)`)

**Thinking skills:**
- The blueprint-vs-instance distinction: **one class, many objects**
- Modelling a problem as *things with properties*, not scattered variables
- Recognising that each object owns its own state — changing one doesn't touch another

## In Class

A 60-minute session.

### The pain of loose variables (8 min)

Start from what they know. To track one robot, you might write:

```java
String name = "Titan";
double topSpeed = 1.5;
int score = 0;
```

Now track a **second** robot. Suddenly you need `name2`, `topSpeed2`, `score2` — and a third means `name3`, `topSpeed3`... A ten-robot league would be a swamp of numbered variables, and nothing links `name2` to `score2` except that you *remember* they go together. There has to be a better way — and you've already used it, in Scratch.

### The Scratch bridge (10 min)

In Scratch, you never made `sprite1x`, `sprite2x`, `sprite3x`. You made **one sprite** — with its costume and its scripts and its own variables ("for this sprite only") — and then you stamped copies of it onto the stage. Each copy moved and scored independently.

That is *exactly* what a class and an object are:

| Scratch | Java |
|---|---|
| a sprite **type** (its costume + scripts + "for this sprite only" variables) | a **class** — the blueprint |
| one sprite **on the stage** | an **object** — one thing made from the blueprint |
| "for this sprite only" variable | a **field** (instance variable) |
| a sprite's script/block | a **method** |

You've been writing objects for years without the word for it. Java just makes the blueprint visible, in its own file.

### Reading the blueprint (14 min)

Open `Robot.java` and walk it top to bottom:

```java
public class Robot {

    String name;
    double topSpeed;
    int score;

    Robot(String name, double topSpeed) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.score = 0;
    }

    void addScore(int points) {
        this.score = this.score + points;
    }

    void printStatus() {
        System.out.println(name + " | top speed " + topSpeed + " | score " + score);
    }
}
```

Three parts:

1. **The fields** (`name`, `topSpeed`, `score`) — these are the "for this sprite only" variables. Every robot you stamp out gets its *own* copy. They sit at the top of the class, not inside any method, because they belong to the *object*, not to one method call.
2. **The constructor** — `Robot(String name, double topSpeed)`. It has the same name as the class and no return type. It's the setup code that runs *once*, the moment you create a robot. `this.name = name` means "set **my own** `name` field to the name I was handed." (`this` = "this particular object, me." We'll lean on it more next lesson; for now read it as "my own.") Notice we start every robot's score at `0`.
3. **The methods** — `addScore` and `printStatus` are the robot's scripts. They work on *this* robot's own fields.

### Stamping out robots (13 min)

Now open `RobotDemo.java` — the program that *uses* the blueprint:

```java
Robot titan = new Robot("Titan", 1.5);
Robot rex = new Robot("Rex", 2.0);

titan.addScore(30);
rex.addScore(45);
titan.addScore(10);

titan.printStatus();
rex.printStatus();
```

`new Robot("Titan", 1.5)` is the stamp: it creates one robot object and runs the constructor to set it up. `Robot titan = ...` stores it in a variable so you can talk to it later.

Then dot notation is how you *talk to a specific robot*: `titan.addScore(30)` means "hey Titan, add 30 to **your** score." Run it:

```
Titan | top speed 1.5 | score 40
Rex | top speed 2.0 | score 45
```

Sit with that result. Titan scored `30 + 10 = 40`; Rex scored `45`. Adding to Titan **never touched** Rex, even though both came from the same blueprint — just like two sprites of the same type move independently. That independence is the whole point of objects.

### Your turn (10 min)

Add a third robot without touching `Robot.java` at all:

1. In `RobotDemo`, create `Robot nova = new Robot("Nova", 1.8);`.
2. Give Nova two score additions of your choosing.
3. Print all three statuses. Confirm Nova's score is independent of Titan's and Rex's.

The lesson in your fingers: once the blueprint exists, adding another object is *one line*. That's what loose variables could never give you.

### Why this matters (the bridge) (3 min)

In Tier 4, an FTC robot is built from objects exactly like this. A `DcMotor` is a class; `leftMotor` and `rightMotor` are two objects of it, each remembering its own power and direction — `leftMotor.setPower(0.5)` talks to one motor the way `titan.addScore(30)` talked to one robot. Every mechanism on a real robot is an object. You just learned the shape of all of it.

### Reflection (2 min)

Answer the reflection prompts below.

## Reflection

Write your answers:

1. In the Scratch picture, what plays the role of the *class*, and what plays the role of an *object*?
2. Why did adding to Titan's score leave Rex's score unchanged, even though both are `Robot`s?
3. What is a field, and how is it different from a variable you declare inside a method?

## Stretch Project (Optional)

Finished early? Run a mini-season with a small fleet.

**Robot League.** Using the same `Robot.java` blueprint (don't change it), write a new program `RobotLeague.java` that:
- creates **three** robots with different names and top speeds,
- plays three "matches" — in each match, add some points to each robot (your choice of numbers),
- after the matches, prints every robot's final status,
- and finally prints one line naming which robot has the highest score (a plain `if`/else-if comparison of the three `score` fields — you can read a robot's score with `titan.score`).

Not one numbered variable like `score2` allowed — three independent objects doing the work. A worked version is in `solutions.md`; try it yourself first.

## Starter Materials

These files are in this lesson's `code/` subfolder:

- `Robot.java` — the blueprint class (fields, constructor, `addScore`, `printStatus`)
- `RobotDemo.java` — creates two robots and shows their independence

Your homework files (`PredictObjects.java`, `ObjectBugHunt.java`) are in the same `code/` subfolder, with full instructions in `homework.md`.

**How to compile a two-file program:** because the program now spans two files, compile both together, then run the one with `main`:
```
javac Robot.java RobotDemo.java
java RobotDemo
```

## Notes for the Teacher

**Setup check before the session:**
- This is the first multi-file program. Make sure students compile with **both** file names (`javac Robot.java RobotDemo.java`) — a common first stumble is compiling only `RobotDemo.java` and getting a "cannot find symbol: class Robot" error. If VS Code's Run button is used, it handles this automatically.
- `Robot.java` and `RobotDemo.java` must sit in the **same folder**.

**On the enormity of this lesson:**
- This is the single biggest conceptual jump in the course. Do not rush the Scratch bridge — it is the ladder students climb. If a student can say "a class is a sprite type and an object is one sprite on the stage," the rest follows. Spend the time there.

**On `this` (deliberately light):**
- Introduce `this.name = name` as "set *my own* field to the value I was handed," and move on. The full "why we need `this`" (a field and a parameter sharing a name) is next lesson. Resist a deep-dive now; the constructor idiom is enough.

**On scope (deliberately held back):**
- **No `private` / no encapsulation yet.** Fields are left package-visible on purpose so the stretch can read `titan.score` directly. `public` vs `private` and *why* we hide fields is the next Tier 3 lesson — introducing it now would need a "why" the students haven't felt yet.
- **No `package` line, no `import`.** These stay pure-Java and single-folder so compiling is one command. Packages and imports arrive later in Tier 3, with a real reason attached.
- **No `static`** on the new methods — that's the whole point. If a student asks why `main` says `static` but `addScore` doesn't, say: "`static` means *belongs to the blueprint, not to one object*. `main` isn't about any one robot, so it's static. `addScore` is always about *one particular robot*, so it isn't." Keep it to that.
- **No inheritance, no `extends`, no `toString()`** — `printStatus()` is a plain method on purpose.

**Anticipated questions:**
- *"Why does the constructor have no return type, not even `void`?"* — "Because it isn't a normal method — its only job is to set up a new object. Java knows that, so it doesn't ask for a return type. The name matching the class is how Java recognises it."
- *"Could two robots have the same name?"* — "Yes — they're separate objects that happen to hold the same text in their `name` field, exactly like two sprites wearing the same costume. They're still independent."
- *"Where does the score `0` come from?"* — "The constructor sets `this.score = 0` for every new robot, so each one starts a fresh season."

**Common mistakes to watch for:**
- Compiling only one file (see setup note).
- Trying to call `Robot.addScore(...)` (on the class) instead of `titan.addScore(...)` (on an object). Re-anchor: "you talk to a *robot*, not to the blueprint."
- Expecting a change to one object to change another. The independence result in `RobotDemo` is the antidote — have them predict it before running.
- Forgetting `new` and declaring `Robot titan;` alone, or `Robot titan = null;`. That variable holds *no robot yet* — and calling a method on it is a `NullPointerException`, the exact crash from Lessons 9–10. The homework bug hunt makes this connection on purpose.
