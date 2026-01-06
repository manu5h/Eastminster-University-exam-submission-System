import com.university.submission.NewSubmissionSystem;

void main() {
    int numberOfStudents = 1000;
    int poolSize = Runtime.getRuntime().availableProcessors() * 2;
    NewSubmissionSystem newSystem = new NewSubmissionSystem(poolSize);

    newSystem.processSubmission(numberOfStudents);

    newSystem.shutdown();
}