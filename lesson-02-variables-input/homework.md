# Lesson 02 — Homework

About 30 minutes. The files you need are in this lesson's `code/` subfolder.

## 1. Build your own calculator

Write a new program that asks the user for two numbers and prints their sum, difference, product, and remainder. This is the first program that's genuinely *yours* — change the questions and messages to whatever you like: a snack-sharing splitter, a game-score adder, a pocket-money divider. With variables and `Scanner`, you can now build real interactive tools.

## 2. Prediction exercise

Before running `PredictMath.java`, write down what you think each line prints. Then run it and check.

```java
public class PredictMath {
    public static void main(String[] args) {
        int a = 7;
        int b = 2;
        System.out.println(a + b);
        System.out.println(a / b);
        System.out.println(a % b);

        double x = 7.0;
        double y = 2.0;
        System.out.println(x / y);
    }
}
```

## 3. Error detective

`TypeBugHunt.java` has three errors. Find them, fix them, and write down what each error message said.

```java
public class TypeBugHunt {
    public static void main(String[] args) {
        int age = "twelve";
        double height = 1.5
        System.out.println("Age: " + age);
        System.out.println("Height: " + heigth);
    }
}
```

(One value has the wrong type for its variable, one line is missing a semicolon, and one variable name is misspelled — but try to find them from the error messages before checking here.)
