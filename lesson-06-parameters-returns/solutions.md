# Lesson 06 — Solutions

**Try it yourself first.** These are here so you can *check* your work, not replace it. If a program won't run, read the error message and try to fix it before you look below — that struggle is where the learning happens.

## 1. The greeter

One correct version of `Greeter.java`:

```java
public class Greeter {

    public static void main(String[] args) {
        greet("Asha");
        greet("Ravi");
        greet("Meera");
    }

    public static void greet(String name) {
        System.out.println("Hello, " + name + "! Ready to drive?");
    }
}
```

Expected output:

```
Hello, Asha! Ready to drive?
Hello, Ravi! Ready to drive?
Hello, Meera! Ready to drive?
```

The key idea: there is only **one** `greet` method. The different names are *arguments* you pass in.

## 2. The calculator method

One correct version of `AreaCalculator.java`:

```java
public class AreaCalculator {

    public static void main(String[] args) {
        int area1 = rectangleArea(4, 6);
        System.out.println("A 4 by 6 rectangle has area " + area1);

        int area2 = rectangleArea(5, 5);
        System.out.println("A 5 by 5 rectangle has area " + area2);

        int area3 = rectangleArea(2, 9);
        System.out.println("A 2 by 9 rectangle has area " + area3);
    }

    public static int rectangleArea(int width, int height) {
        return width * height;
    }
}
```

Expected output:

```
A 4 by 6 rectangle has area 24
A 5 by 5 rectangle has area 25
A 2 by 9 rectangle has area 18
```

Note the return type is `int`, not `void`, and each answer is **caught** in a variable (`area1`, `area2`, `area3`) before being printed.

## 3. The decision reporter

One correct version of `BatteryCheck.java`:

```java
public class BatteryCheck {

    public static void main(String[] args) {
        int fuller = higherCharge(62, 88);
        System.out.println("The fuller battery is at " + fuller + "%");
    }

    public static int higherCharge(int robotA, int robotB) {
        if (robotA > robotB) {
            return robotA;
        } else {
            return robotB;
        }
    }
}
```

Expected output:

```
The fuller battery is at 88%
```

## 4. Prediction exercise

Expected output of `PredictReturns.java`:

```
8
16
20
Hello, Asha!
```

Why:
- `int a = doubleIt(4);` → `a` becomes `8`.
- `int b = doubleIt(a);` → `doubleIt(8)` → `b` becomes `16`.
- `System.out.println(a);` prints `8`.
- `System.out.println(b);` prints `16`.
- `System.out.println(doubleIt(10));` → `doubleIt(10)` returns `20`, which is printed directly.
- `greet("Asha");` prints `Hello, Asha!`.

## 5. Error detective

`ParamBugHunt.java` has three errors. A quirk to know: the first compile only shows **two** of them (Errors 1 and 3 below). Java reports the third (the missing return) only after those two are fixed and you compile again. If you saw two, fixed them, and a new error appeared — that's expected, not a new bug you introduced.

**Error 1 — calling with no argument.**
`printDivider();` is called with an empty slot, but `printDivider` needs an `int width`.
Message (roughly):
```
method printDivider in class ParamBugHunt cannot be applied to given types;
  required: int
  found:    no arguments
```
Fix: pass a width, e.g. `printDivider(15);`

**Error 2 — missing return.**
`addUp` promises `public static int` but never uses `return`, so it hands nothing back.
Message:
```
missing return statement
```
Fix: return the sum.
```java
public static int addUp(int a, int b) {
    System.out.println("Adding...");
    return a + b;
}
```

**Error 3 — wrong argument type.**
`celebrate(2026);` passes a number, but `celebrate` expects a `String team`.
Message (roughly):
```
incompatible types: int cannot be converted to String
```
Fix: pass text, e.g. `celebrate("winners");`

The corrected program runs as:

```
===============
Adding...
Total: 12
Great job, winners!
```

(Your divider length depends on the width you chose in the fix.)
