# Lesson 05 — Homework

About 30 minutes. The files you need are in this lesson's `code/` subfolder.

## 1. Build your own sequence

Write a program whose `main` calls **at least two** custom methods you define. Some ideas: a `morningRoutine()` program (`brushTeeth(); eatBreakfast(); packBag();`), or your own robot `startup()` made of smaller blocks. Keep `main` short and readable — the details live inside your methods. Every method should be `public static void` for now, and remember to *call* each one from `main`.

## 2. Prediction exercise

Before running `PredictMethods.java`, write down exactly what it prints, in order. Then run it and check.

```java
public class PredictMethods {
    public static void main(String[] args) {
        sayHi();
        sayHi();
        System.out.println("Middle");
        cheer();
    }

    public static void sayHi() {
        System.out.println("Hi!");
    }

    public static void cheer() {
        System.out.println("Go team!");
    }
}
```

(Hint: a method only runs when it's *called*. Defining `cheer()` above `main` or below it makes no difference — what matters is when `main` calls it.)

## 3. Error detective

`MethodBugHunt.java` has three errors. Find them, fix them, and write down what each error message said.

```java
public class MethodBugHunt {
    public static void main(String[] args) {
        greets();
        countdown();
    }

    public static void greet() {
        System.out.println("Hello, driver!");
    }

    public void countdown() {
        for (int i = 3; i >= 1; i--) {
            System.out.println(i)
        }
    }
}
```

(One method is called by the wrong name; one method is missing the `static` magic word; and one line is missing a semicolon — but try to find them from the error messages before checking the solutions.)
