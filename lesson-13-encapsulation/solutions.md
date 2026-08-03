# Lesson 13 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. The instinct this lesson builds — lock the field, guard the doorway — only sticks if you design the guards yourself before reading these.

## 1. Lift that can't leave its legal range

One correct `Lift.java`:

```java
public class Lift {

    private String name;
    private int position;

    public Lift(String name) {
        this.name = name;
        this.position = 0;
    }

    public void setPosition(int newPosition) {
        if (newPosition > 2000) {
            newPosition = 2000;
        }
        if (newPosition < 0) {
            newPosition = 0;
        }
        this.position = newPosition;
    }

    public int getPosition() {
        return this.position;
    }

    public void printStatus() {
        System.out.println(name + " at height " + position);
    }
}
```

One correct `LiftDemo.java`:

```java
public class LiftDemo {

    public static void main(String[] args) {
        Lift arm = new Lift("arm_lift");

        arm.setPosition(500);
        System.out.println(arm.getPosition());

        arm.setPosition(9999);
        System.out.println(arm.getPosition());

        arm.setPosition(-50);
        System.out.println(arm.getPosition());
    }
}
```

Output:

```
500
2000
0
```

`500` is legal and goes straight in. `9999` is clamped down to the ceiling `2000`; `-50` is clamped up to the floor `0`. The guard runs *before* the field is assigned, so the lift can never hold an out-of-range height.

## 2. Score that outsiders can read but never set

One correct `Score.java`:

```java
public class Score {

    private String team;
    private int points;

    public Score(String team) {
        this.team = team;
        this.points = 0;
    }

    public void addPoints(int amount) {
        this.points = this.points + amount;
    }

    public int getPoints() {
        return this.points;
    }

    public void printScore() {
        System.out.println(team + ": " + points);
    }
}
```

One correct `ScoreDemo.java`:

```java
public class ScoreDemo {

    public static void main(String[] args) {
        Score red = new Score("Red");

        red.addPoints(30);
        red.addPoints(15);

        System.out.println("Red has " + red.getPoints() + " points.");
        red.printScore();
    }
}
```

Output:

```
Red has 45 points.
Red: 45
```

And if you add `red.points = 999;`, it won't compile:

```
error: points has private access in Score
```

That refusal is the whole point. With a private field and **no setter**, the score literally cannot be set to an arbitrary value — it can only climb through `addPoints`. Encapsulation here enforces a *rule of the game* ("you earn points, you don't just declare them"), not merely a numeric range. Allowing reading while forbidding writing is a normal, powerful choice: getter yes, setter no.

## 3. Prediction exercise

Expected output of `PredictPrivate.java`:

```
0.4
1.0
-1.0
```

Why: `setPower` clamps to the range −1.0…1.0.
- `0.4` is already inside the range → stays `0.4`.
- `2.5` is above the ceiling → clamped to `1.0`.
- `-9.0` is below the floor → clamped to `-1.0`.

The object only ever reports a legal power, because every value passes through the guard on the way in.

## 4. Error detective

`PrivateBugHunt.java` doesn't compile — two errors, both the same kind:

```
PrivateBugHunt.java:6: error: power has private access in Motor
        arm.power = 0.7;
           ^
PrivateBugHunt.java:7: error: power has private access in Motor
        System.out.println("Arm power: " + arm.power);
                                              ^
```

Both lines try to touch `power` directly from *outside* `Motor` — line 6 to write it, line 7 to read it — and `power` is `private`, so the compiler locks them out.

**Fix the write (step 2):** go through the setter doorway.
```java
arm.setPower(0.7);
```

**Fix the read (step 3):** go through the getter doorway.
```java
System.out.println("Arm power: " + arm.getPower());
```

Fixed program runs:

```
Arm power: 0.7
arm_motor at power 0.7
```

**Why the compiler stopped you, and why that's good (step 4):** *`power` is private, so the only way to change or read it is through Motor's own public methods — which means the guard in `setPower` can never be bypassed, and no outside code can put the motor into an illegal state.* A compile error here is the object protecting itself before the program ever runs — far better than a bad value sneaking through at runtime.

## Stretch Project — Sample Solution

Try it yourself first. `FuelTank.java`:

```java
public class FuelTank {

    private String name;
    private int level;

    public FuelTank(String name) {
        this.name = name;
        this.level = 0;
    }

    public void fill(int amount) {
        this.level = this.level + amount;
        if (this.level > 100) {
            this.level = 100;
        }
    }

    public void use(int amount) {
        this.level = this.level - amount;
        if (this.level < 0) {
            this.level = 0;
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void printStatus() {
        System.out.println(name + " fuel: " + level);
    }
}
```

`FuelDemo.java`:

```java
public class FuelDemo {

    public static void main(String[] args) {
        FuelTank tank = new FuelTank("tank_1");

        tank.fill(70);
        tank.fill(50);      // 70 + 50 = 120, capped at 100
        tank.printStatus();

        tank.use(30);
        tank.use(200);      // 70 - 200 = negative, floored at 0
        tank.printStatus();
    }
}
```

Output:

```
tank_1 fuel: 100
tank_1 fuel: 0
```

This tank is guarded on **both** sides. `fill` adds fuel but caps at `100`, so over-filling (70 + 50 = 120) stops at `100`. `use` removes fuel but floors at `0`, so over-using (100 − 30 = 70, then 70 − 200 = −130) stops at `0`. Notice the guard here runs *after* the arithmetic — we do the add/subtract, then check the boundary and correct if we overshot. Either style is fine as long as the field is corrected before the method returns; the object is never left holding an impossible fuel level.
