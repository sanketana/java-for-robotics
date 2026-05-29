# Lesson 03 — Homework

About 30 minutes. The files you need are in this lesson's `code/` subfolder.

## 1. Add a decision to your calculator

Open your calculator from Lesson 2 (or `DistanceZone.java`) and give it a new rule. For example: after dividing two numbers, warn the user if the second number was `0` ("Can't divide by zero!"); or in the distance program, add a fourth zone of your choice. The goal is to write at least one new `if` (with an `else` or `else if`) that reacts to the user's input.

## 2. Prediction exercise

Before running `PredictDecisions.java`, write down what each line prints. Then run it and check.

```java
public class PredictDecisions {
    public static void main(String[] args) {
        int score = 7;

        System.out.println(score > 5);
        System.out.println(score == 10);
        System.out.println(score != 7);

        if (score >= 7) {
            System.out.println("You qualified!");
        } else {
            System.out.println("Try again.");
        }
    }
}
```

## 3. Error detective

`DecisionBugHunt.java` has three errors. Find them, fix them, and write down what each error message said.

```java
public class DecisionBugHunt {
    public static void main(String[] args) {
        int temperature = 40;

        if (temperature = 40) {
            System.out.println("Motor is overheating!")
        } else {
            System.out.println("Temp is fine: " + temperatur);
        }
    }
}
```

(One is the `=` vs `==` trap from class, one line is missing a semicolon, and one variable name is misspelled — but try to find them from the error messages before checking the solutions.)
