# Lesson 14 — Homework

About 35–45 minutes. The files you need are in this lesson's `code/` subfolder.

Today's homework is about **organising classes into packages** and **borrowing them with `import`**. Each build is a fresh program you write and run — and this time the *folder layout* is part of the exercise.

Compile reminder: run `javac` from the folder that *contains* your package folder, and name every `.java` file you need. For example:
```
javac sensors/DistanceSensor.java SensorMain.java
java SensorMain
```

## 1. Build a sensors package

Create a folder called `sensors`. Inside it, write a brand-new class `DistanceSensor.java`:

- first line `package sensors;`
- `private String name` and `private double distance`
- a `public` constructor `DistanceSensor(String name)` starting `distance` at `0.0`
- a `public void setDistance(double d)` and a `public void printReading()` that prints, e.g., `front_distance reads 42.5 cm`

Then, *outside* the `sensors` folder, write `SensorMain.java`:

- `import sensors.DistanceSensor;` at the top
- in `main`, create a sensor, set its distance, and print its reading.

Compile (naming both files) and run. You've now built your own package and imported from it.

## 2. Add a second class to the same package

Inside the same `sensors` folder, add `TouchSensor.java`:

- `package sensors;`
- `private String name` and `private boolean pressed`
- a constructor starting `pressed` at `false`, a `public void press()` that sets it `true`, and a `public void printReading()` that prints, e.g., `bumper pressed? true`.

Now extend `SensorMain.java`: add `import sensors.TouchSensor;`, create a touch sensor, press it, and print. Notice the pattern: **one package can hold many classes, and you write one `import` per class you use.**

## 3. Compile prediction

For each snippet below, predict whether it **compiles** or **fails to compile** — and if it fails, why. Assume `robotparts/Motor.java` (package `robotparts`, a public class) exists, and each snippet is a separate file *outside* that folder. Then check by trying each.

**Snippet A**
```java
import robotparts.Motor;

public class A {
    public static void main(String[] args) {
        Motor m = new Motor("a");
        m.printStatus();
    }
}
```

**Snippet B**
```java
public class B {
    public static void main(String[] args) {
        Motor m = new Motor("b");
        m.printStatus();
    }
}
```

**Snippet C**
```java
public class C {
    public static void main(String[] args) {
        robotparts.Motor m = new robotparts.Motor("c");
        m.printStatus();
    }
}
```

**Snippet D**
```java
public class D {
    public static void main(String[] args) {
        String message = "no import needed for me";
        System.out.println(message);
    }
}
```

(Hint: three of these compile. Ask of each one: *is it using a class from another package by its short name, and if so, did it import that class?* Which snippet uses a short name with no import — and which two avoid needing an import entirely, and how?)

## 4. Error detective

`ImportBugHunt.java` (in the `code/` subfolder) tries to use the `Motor` class from `robotparts`, but it won't compile.

```java
public class ImportBugHunt {

    public static void main(String[] args) {
        Motor arm = new Motor("arm_motor");
        arm.setPower(0.8);
        arm.printStatus();
    }
}
```

Compile it from the `code/` folder:
```
javac robotparts/Motor.java ImportBugHunt.java
```

Work through it and write down what you find:
1. Read the error. What symbol can't Java find, and why doesn't it know what `Motor` is? (Which drawer is `Motor` in, and did this file say to look there?)
2. Fix it by adding the one missing line at the top. Which line, exactly?
3. Compile and run. In one sentence, explain what `import` did for this file.

The one-line explanation you write in step 3 is the takeaway — `import` is nothing more than "let me use this class from another package by its short name."
