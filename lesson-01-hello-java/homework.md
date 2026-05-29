# Lesson 01 — Homework

About 30 minutes. The files you need are in this lesson's `code/` subfolder.

## 1. Make it yours

Modify `AboutMe.java` so it prints **five** lines instead of three. Add your favourite food and one thing you'd like to build with a robot. Compile and run it.

## 2. Prediction exercise

Before running `Predict.java`, write down what you think it will print. Then run it and check how close you were.

```java
public class Predict {
    public static void main(String[] args) {
        System.out.println("Line 1");
        System.out.println();
        System.out.println("Line " + 3);
        System.out.print("No ");
        System.out.println("newline");
    }
}
```

## 3. Error detective

`BugHunt.java` has three errors. Find them, fix them, and write down what each error message said.

```java
public class BugHunt {
    public static void main(String[] args) {
        system.out.println("Find the bugs!")
        System.out.println("Can you fix them?");
    }
```

(There's a lowercase `system`, a missing semicolon, and a missing closing brace — but try to find them from the error messages before checking here.)
