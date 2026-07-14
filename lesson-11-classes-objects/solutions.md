# Lesson 11 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. Designing the blueprint yourself — deciding the fields, writing the constructor — is the whole skill this lesson builds. Do it before you look here.

## 1. Motor blueprint

One correct `Motor.java`:

```java
public class Motor {

    String name;
    double power;

    Motor(String name) {
        this.name = name;
        this.power = 0.0;
    }

    void setPower(double newPower) {
        this.power = newPower;
    }

    void printStatus() {
        System.out.println(name + " running at power " + power);
    }
}
```

One correct `MotorDemo.java`:

```java
public class MotorDemo {

    public static void main(String[] args) {
        Motor left = new Motor("left_motor");
        Motor right = new Motor("right_motor");

        left.setPower(0.5);
        right.setPower(0.8);

        left.printStatus();
        right.printStatus();
    }
}
```

Output:

```
left_motor running at power 0.5
right_motor running at power 0.8
```

Setting `left` to `0.5` didn't touch `right` — two independent objects from one blueprint. (This is exactly how two real motors behave: `leftMotor.setPower(...)` never changes the right one.)

## 2. Player scoreboard

One correct `Player.java`:

```java
public class Player {

    String name;
    int score;

    Player(String name) {
        this.name = name;
        this.score = 0;
    }

    void addPoints(int points) {
        this.score = this.score + points;
    }

    void printScore() {
        System.out.println(name + ": " + score);
    }
}
```

One correct `ScoreBoard.java`:

```java
public class ScoreBoard {

    public static void main(String[] args) {
        Player asha = new Player("Asha");
        Player rohan = new Player("Rohan");
        Player meera = new Player("Meera");

        asha.addPoints(20);
        asha.addPoints(10);
        rohan.addPoints(15);
        meera.addPoints(40);

        asha.printScore();
        rohan.printScore();
        meera.printScore();
    }
}
```

Output (Asha scored `20 + 10 = 30`):

```
Asha: 30
Rohan: 15
Meera: 40
```

`Player` is the same shape as `Robot` — different fields, same idea. That sameness *is* the pattern: once you've built one blueprint, every other one follows the same three-part plan (fields, constructor, methods).

## 3. Prediction exercise

Expected output of `PredictObjects.java`:

```
Alpha | top speed 1.2 | score 15
Beta | top speed 1.2 | score 100
```

Why:
- `a` (Alpha) gets `10 + 5 = 15`.
- `b` (Beta) gets `100`.
- They were built with the *same* top speed (`1.2`), but they are **two separate objects**. Adding points to `a` changes only `a`'s own `score` field; `b` is untouched. Same blueprint, independent data.

## 4. Error detective

`ObjectBugHunt.java` hides two bugs — a compile-time error, then a runtime crash.

**Step 1 — compile it. First error:**

```
ObjectBugHunt.java:4: error: constructor Robot in class Robot cannot be applied to given types;
        Robot atlas = new Robot("Atlas");
                      ^
  required: String,double
  found:    String
  reason: actual and formal argument lists differ in length
```

Line 4, **compile-time**. `Robot`'s constructor needs **two** things — a `String` name *and* a `double` top speed (`required: String,double`) — but this line handed it only one (`found: String`). Fix it by giving a top speed too:

```java
Robot atlas = new Robot("Atlas", 1.8);
```

**Step 2 — compile again: it builds. Run it:**

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Robot.addScore(int)" because "<local2>" is null
	at ObjectBugHunt.main(ObjectBugHunt.java:8)
```

Line 8, **run-time**. `nova` was set to `null` and never given a real robot — we wrote `Robot nova = null;` but never `new Robot(...)`. So `nova` points at *no object*, and `nova.addScore(20)` tries to call a method on nothing → `NullPointerException`. (Plain `javac` names it `<local2>`, the second local variable; the line number, 8, is exact. Compiled via VS Code or `javac -g`, it reads `because "nova" is null`.)

**Step 3 — fix `nova` by actually creating it:**

```java
Robot nova = new Robot("Nova", 1.6);
```

Now it runs cleanly and prints both statuses. The takeaway: **declaring an object variable is not the same as creating an object.** Until you `new` it, the variable is `null` — and you already know exactly what happens when you call a method on `null`.

## Stretch Project — Sample Solution

Try it yourself first. `RobotLeague.java` (uses the unchanged `Robot.java`):

```java
public class RobotLeague {

    public static void main(String[] args) {
        Robot titan = new Robot("Titan", 1.5);
        Robot rex = new Robot("Rex", 2.0);
        Robot nova = new Robot("Nova", 1.8);

        // Match 1
        titan.addScore(20);
        rex.addScore(15);
        nova.addScore(25);

        // Match 2
        titan.addScore(30);
        rex.addScore(35);
        nova.addScore(10);

        // Match 3
        titan.addScore(15);
        rex.addScore(20);
        nova.addScore(30);

        titan.printStatus();
        rex.printStatus();
        nova.printStatus();

        if (titan.score >= rex.score && titan.score >= nova.score) {
            System.out.println("League leader: " + titan.name);
        } else if (rex.score >= titan.score && rex.score >= nova.score) {
            System.out.println("League leader: " + rex.name);
        } else {
            System.out.println("League leader: " + nova.name);
        }
    }
}
```

Output:

```
Titan | top speed 1.5 | score 65
Rex | top speed 2.0 | score 70
Nova | top speed 1.8 | score 65
League leader: Rex
```

Three independent objects carry three independent season totals — no `score2` or `score3` anywhere. The leader check reads each robot's `score` field directly (`titan.score`) and uses the `&&` boolean logic from Lesson 8 to find the highest. Titan and Nova tie on `65`; Rex wins with `70`. (Your numbers will differ if you chose different match points — check that the leader line names whichever robot actually has the highest `score`.)
