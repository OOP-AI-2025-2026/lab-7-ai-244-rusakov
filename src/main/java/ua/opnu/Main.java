package ua.opnu;

import java.util.*;
import java.util.function.*;

class Student {
    private String name;
    private String group;
    private int[] marks;

    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    // Method to check if a student has any failing grades (below 60)
    public boolean hasDebts() {
        for (int mark : marks) {
            if (mark < 60) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + group + ")";
    }
}

public class Main {
    public static void main(String[] args) {

        // ---------- Task 1 ----------
        System.out.println("=== Task 1: Prime Number Check ===");
        // Predicate that determines if a number is prime
        Predicate<Integer> isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };

        System.out.println("Prime numbers from 1 to 20:");
        for (int i = 1; i <= 20; i++) {
            if (isPrime.test(i)) System.out.print(i + " ");
        }
        System.out.println("\n");


        // ---------- Task 2 ----------
        System.out.println("=== Task 2: Filtering Students ===");
        // Array of students with different scores
        Student[] students = {
                new Student("John Smith", "IT-11", new int[]{85, 90, 78}),
                new Student("Emily Johnson", "IT-12", new int[]{55, 60, 70}),
                new Student("Michael Brown", "IT-13", new int[]{40, 59, 62}),
                new Student("Sophia Williams", "IT-14", new int[]{95, 100, 98})
        };

        // Predicate that checks if a student has no failing grades
        Predicate<Student> noDebts = s -> !s.hasDebts();

        // Use Stream API to filter students who have no debts
        Student[] filtered = Arrays.stream(students)
                .filter(noDebts)
                .toArray(Student[]::new);

        System.out.println("Students without failing grades:");
        for (Student s : filtered) System.out.println(s);
        System.out.println();


        // ---------- Task 3 ----------
        System.out.println("=== Task 3: Combining Two Predicates ===");
        // Two predicates: "even" and "greater than 3"
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> greaterThan3 = n -> n > 3;

        // List of numbers that satisfy both conditions
        List<Integer> filteredNums = new ArrayList<>();
        for (Integer n : nums) {
            if (even.and(greaterThan3).test(n)) filteredNums.add(n);
        }
        System.out.println("Numbers that are even and greater than 3: " + filteredNums + "\n");


        // ---------- Task 4 ----------
        System.out.println("=== Task 4: Using Consumer with Students ===");
        // Consumer that prints student info
        Consumer<Student> printStudent = s ->
                System.out.println("Student: " + s.getName() + " (" + s.getGroup() + ")");
        System.out.println("List of students:");
        Arrays.stream(students).forEach(printStudent);
        System.out.println();


        // ---------- Task 5 ----------
        System.out.println("=== Task 5: Predicate + Consumer ===");
        // Predicate checks if number > 5
        Predicate<Integer> greaterThan5 = n -> n > 5;
        // Consumer prints message only when predicate is true
        Consumer<Integer> printIfTrue = n -> System.out.println("Number " + n + " is greater than 5");

        // Create stream from primitive int array and box to Integer
        int[] primitiveNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(primitiveNums)
                .boxed()
                .filter(greaterThan5)
                .forEach(printIfTrue);
        System.out.println();


        // ---------- Task 6 ----------
        System.out.println("=== Task 6: Function for Computing 2^n ===");
        // Function that returns 2 raised to the power of n
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        int[] base = {0, 1, 2, 3, 4, 5, 6};
        List<Integer> powers = Arrays.stream(base)
                .boxed()
                .map(powerOfTwo)
                .toList();

        System.out.println("2^n for each element: " + powers);
        System.out.println();


        // ---------- Task 7 ----------
        System.out.println("=== Task 7: Function stringify() — Numbers to Words ===");
        // Function that converts digits to their English words
        Function<Integer, String> toWord = n -> switch (n) {
            case 0 -> "zero";
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            default -> "unknown";
        };

        int[] digits = {0,1,2,3,4,5,6,7,8,9};
        List<String> words = Arrays.stream(digits)
                .boxed()
                .map(toWord)
                .toList();

        System.out.println("Converting numbers to words:");
        System.out.println(words);
    }
}
