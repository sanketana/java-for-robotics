# Lesson 09 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. Reading a stack trace and finding the crashing line yourself is the whole skill this lesson is building — do that before looking here.

## 1. Safe robot greeting

One correct version of `RobotGreeting.java` (with a real name):

```java
public class RobotGreeting {

    public static void main(String[] args) {
        String robotName = "Titan";

        if (robotName != null) {
            System.out.println(robotName + " has " + robotName.length() + " letters.");
        } else {
            System.out.println("No robot name set yet.");
        }
    }
}
```

With `robotName = "Titan"`:

```
Titan has 5 letters.
```

With `robotName = null`:

```
No robot name set yet.
```

The same program handles both cases without ever crashing, because `.length()` is only reached inside the `!= null` guard.

## 2. Locker lookup

One correct version of `LockerLookup.java`:

```java
public class LockerLookup {

    public static void main(String[] args) {
        String owner1 = lockerOwner(12);
        if (owner1 != null) {
            System.out.println("Locker 12: " + owner1);
        } else {
            System.out.println("Locker 12 is empty.");
        }

        String owner2 = lockerOwner(7);
        if (owner2 != null) {
            System.out.println("Locker 7: " + owner2);
        } else {
            System.out.println("Locker 7 is empty.");
        }
    }

    public static String lockerOwner(int lockerNumber) {
        if (lockerNumber == 12) {
            return "Meera";
        }
        return null;
    }
}
```

Expected output:

```
Locker 12: Meera
Locker 7 is empty.
```

`lockerOwner(7)` returns `null` — its way of saying "no owner." Because the caller checks with `!= null`, that null never reaches a method call.

## 3. Prediction exercise

Expected behaviour of `PredictNull.java`:

```
Phase: Autonomous
Length: 10
Next phase set? false
```

...and then it **crashes** — it never prints `Done`. The crash (compiled with a plain `javac`):

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "<local2>" is null
	at PredictNull.main(PredictNull.java:10)
```

Why:
- `"Autonomous"` is a real String, so `phase.length()` is `10`.
- `next` is `null`, so `next != null` is `false` — that line prints fine (it never calls a method on `next`).
- `next.length()` on line 10 calls a method on `null` → `NullPointerException`. The program stops immediately, so `Done` (line 11) never runs.

(If you compiled with VS Code's Run button or `javac -g`, the message names the variable — `because "next" is null` — instead of `<local2>`. The line number, `10`, is the same either way.)

## 4. Error detective

`NullBugHunt.java` hides **two** null bugs — the second only appears once the first is fixed.

**Step 1 — run it. First crash:**

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "<local1>" is null
	at NullBugHunt.main(NullBugHunt.java:5)
```

Line 5 calls `driver.length()`. Why is `driver` null? Because `driverForRobot(2)` has no entry for robot `2`, so it returns `null`.

**Step 2 — fix the first crash with a guard:**

```java
String driver = driverForRobot(2);
if (driver != null) {
    System.out.println("Driver name has " + driver.length() + " letters.");
} else {
    System.out.println("No driver assigned to that robot.");
}
```

**Step 3 — run again. A second crash appears:**

```
Backup driver: null
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.toUpperCase()" because "<local2>" is null
	at NullBugHunt.main(...)
```

`backup` is `null`, so `backup.toUpperCase()` crashes. Fix it the same way — guard it, or give `backup` a real value:

```java
String backup = null;
System.out.println("Backup driver: " + backup);
if (backup != null) {
    System.out.println("Backup in caps: " + backup.toUpperCase());
} else {
    System.out.println("No backup driver.");
}
```

**Step 4 — why did `System.out.println("Backup driver: " + backup)` NOT crash?**
Because printing a null is safe — Java quietly turns `null` into the text `"null"`. The crash only comes from *calling a method* on the null value (`.toUpperCase()`), not from printing it. That's the key distinction of the whole lesson: **null is harmless until you ask it to *do* something.**

## Stretch Project — Sample Solution

Try it yourself first. `TeamRoster.java`:

```java
public class TeamRoster {

    public static void main(String[] args) {
        for (int id = 1; id <= 5; id++) {
            String role = roleForId(id);
            if (role != null) {
                System.out.println("Robot " + id + ": " + role);
            } else {
                System.out.println("Robot " + id + ": (no role assigned)");
            }
        }
    }

    public static String roleForId(int id) {
        if (id == 1) {
            return "Scorer";
        }
        if (id == 2) {
            return "Defender";
        }
        if (id == 4) {
            return "Support";
        }
        return null;
    }
}
```

Output:

```
Robot 1: Scorer
Robot 2: Defender
Robot 3: (no role assigned)
Robot 4: Support
Robot 5: (no role assigned)
```

The `for` loop (Lesson 4) runs five lookups; `roleForId` returns `null` for the IDs it doesn't know; and the `!= null` guard turns each missing role into a tidy message instead of a crash. Five lookups, three of them "hits," zero crashes — because every result is checked before it's used.
