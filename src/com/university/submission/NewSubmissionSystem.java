package com.university.submission;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NewSubmissionSystem {
    ExecutorService service;
    private SubmissionStats stats;

    public NewSubmissionSystem(int poolSize) {
        this.service = Executors.newFixedThreadPool(poolSize);  // initialize a fixed thread pool
        this.stats = new SubmissionStats();  // create a stat object
    }

    public void processSubmission(int numberOfStudents) {
        stats.setStartTime();  // call start function to record start time
        System.out.println("\nThe Submission Process for all submission in Progress...");
        CountDownLatch latch = new CountDownLatch(numberOfStudents);// create a latch for wait for all students' submissions done

        for (int i = 0; i < numberOfStudents; i++) {
            final int studentId = i + 1;
            service.submit(() -> {  // add task to a thread
                try {   // task of the thread
                    Student student = new Student(studentId);
                    boolean success = student.submitExam();
                    if (success) {
                        stats.recordSuccess();
                    } else {
                        stats.recordFailure();
                    }
                } finally {
                    latch.countDown(); // latch counter drops by one
                }
            });
        }
        try {
            latch.await();  // block main thread until all tasks done (until latch countdown = 0)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        stats.setEndTime();
        System.out.println("All Submissions Process Complete!!!");
        stats.printResult();
    }

    public void shutdown() {
        service.shutdown();  // shutdown the created pool of threads
        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {  //if thread is stuck, this use to shut down immediately
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The System Shutdown Complete...");
    }
}
