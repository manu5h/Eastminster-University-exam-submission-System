package com.university.submission;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SubmissionStats {
    private AtomicInteger successful;    //Initialize the variables
    private AtomicInteger failed;
    private AtomicLong startTime;
    private AtomicLong endTime;

    public SubmissionStats() {  // create a constructor
        successful = new AtomicInteger(0);
        failed = new AtomicInteger(0);
        startTime = new AtomicLong(0);
        endTime = new AtomicLong(0);
    }

    public void recordSuccess() {
        successful.incrementAndGet();
    }

    public void recordFailure() {
        failed.incrementAndGet();
    }

    public void setStartTime() {
        this.startTime.set(System.currentTimeMillis());
    }

    public void setEndTime() {
        this.endTime.set(System.currentTimeMillis());
    }

    public int getTotalSubmissions() {
        return (successful.get() + failed.get());
    }

    public int getSuccessfulSubmissionCount() {
        return (successful.get());
    }

    public int getFailedSubmissionCount() {
        return (failed.get());
    }

    public double getSuccessRate() {
        return (successful.get() * 100.0) / getTotalSubmissions();
    }

    public double getFailedRate() {
        return (failed.get() * 100.0) / getTotalSubmissions();
    }

    public double getTotalSubmissionTime() {
        return (endTime.get() - startTime.get()) / 1000.0;
    }

    public double getThroughPut(){
        return getTotalSubmissions()/getTotalSubmissionTime();
    }

    public void printResult() {
        System.out.println("\n--- Eastminster Exam Submission Report ---");
        System.out.println("Total Students: " + getTotalSubmissions());
        System.out.println("Successful submission count: " + getSuccessfulSubmissionCount());
        System.out.println("Failed submission count: " + getFailedSubmissionCount());
        System.out.println("Success Rate: " + getSuccessRate()+"%");
        System.out.println("Failed Rate: " + getFailedRate()+"%");
        System.out.println(("Total submission time " + getTotalSubmissionTime()+" Secs"));
        System.out.println("The system throughput is: " + getThroughPut() + " submissions per seconds" );
    }
}
