package com.university.submission;

import java.util.Random;

public class Student {
    private int studentId;
    private String name;
    private Random random;

    public Student(int studentId) {
        this.studentId = studentId;
        this.name = "Student" + studentId;
        random = new Random();
    }

    public boolean submitExam() {
        System.out.println(name + " (ID: " + studentId + ") Submission In Progress");
        try {
            Thread.sleep(random.nextInt(10) + 1);  // hold the thread for 1 - 10 ms
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (random.nextInt(100) >= 5) {
            return true;
        } else {
            return false;
        }

    }
}
