package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.CourseException;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class BenchShould {

    @BeforeEach
    public void setUp() {
        // reset Bench to original state (since it's static)
        Bench.resetState();
    }

    // testing addTrainee
    // adding a trainee of a specific course - that queue must increase by 1
    @Test
    public void addTrainee_TraineeOfStreamJava_JavaQueueSizeIncreasesByOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.JAVA);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getJavaTrainees().size());
    }

    @Test
    public void addTrainee_TraineeOfStreamData_DataQueueSizeIncreasesByOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.DATA);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getDataTrainees().size());
    }

    @Test
    public void addTrainee_TraineeOfStreamCSharp_CSharpQueueSizeIncreasesByOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.CSHARP);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getcSharpTrainees().size());
    }

    @Test
    public void addTrainee_TraineeOfStreamBusiness_BusinessQueueSizeIncreasesByOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.BUSINESS);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getBusinessTrainees().size());
    }

    // make sure the other queues are not affected when you add a trainee to a different queue
    @Test
    public void addTrainee_TraineeOfStreamJava_DevOpsQueueSizeStaysAtZero() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.JAVA);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(0, Bench.getDevOpsTrainees().size());
    }

    @Test
    public void addTrainee_TraineeOfStreamJava_BusinessQueueSizeStaysAtZero() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.JAVA);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(0, Bench.getBusinessTrainees().size());
    }

    @Test
    public void addTrainee_TraineeOfStreamJava_CSharpQueueSizeStaysAtZero() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.JAVA);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(0, Bench.getcSharpTrainees().size());
    }

    @Test
    public void addTrainee_TraineeOfStreamJava_DataQueueSizeStaysAtZero() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.JAVA);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(0, Bench.getDataTrainees().size());
    }

    @Test
    public void addTrainee_TraineeOfStreamData_TotalNumberOfTraineesOnBenchIncreasesToOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.DATA);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getTotalSize());
    }

    @Test
    public void addTrainee_TraineeOfStreamBusiness_TotalNumberOfTraineesOnBenchIncreasesToOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.BUSINESS);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getTotalSize());
    }

    @Test
    public void addTrainee_TraineeOfStreamDevOps_TotalNumberOfTraineesOnBenchIncreasesToOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.DEVOPS);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getTotalSize());
    }

    @Test
    public void addTrainee_TraineeOfStreamCSharp_TotalNumberOfTraineesOnBenchIncreasesToOne() {
        // create a trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.CSHARP);

        // add the trainee to the bench
        Bench.addTrainee(trainee);

        assertEquals(1, Bench.getTotalSize());
    }

    // removing trainees
    @Test
    public void removeTrainee_AddJavaTrainee_ParameterJava_ReturnJavaTrainee() {
        // create a new trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.JAVA);

        // add the trainee
        Bench.addTrainee(trainee);

        Trainee removedTrainee = Bench.removeTrainee(Simulation.Courses.JAVA);

        assertEquals(Simulation.Courses.JAVA, removedTrainee.getTraineeCourse());
    }

    @Test
    public void removeTrainee_AddJavaTrainee_ParameterJava_NumberOnBenchDecreasesByOne() {
        // create a new trainee
        Trainee trainee = new Trainee(123, Simulation.Courses.JAVA);

        // add the trainee
        Bench.addTrainee(trainee);

        Bench.removeTrainee(Simulation.Courses.JAVA);

        assertEquals(0, Bench.getTotalSize());
    }

    @Test
    public void removeTrainee_TraineeJavaDoesNotExist_ReturnNull() {
        Trainee removedTrainee = Bench.removeTrainee(Simulation.Courses.JAVA);

        assertNull(removedTrainee);
    }

    @Test
    public void removeTrainee_TraineeJavaDoesNotExist_TotalNumberOnBenchIsZero() {
        Bench.removeTrainee(Simulation.Courses.JAVA);

        assertEquals(0, Bench.getTotalSize());
    }

    @AfterAll
    public static void resetState_StreamsShouldEmptyWhenCalled() {
        Trainee trainee = new Trainee(123, 1);
        Bench.addTrainee(trainee);
        Bench.resetState();

        final int[] newBenchState = {
                Bench.getTotalSize(), Bench.getBusinessTrainees().size(),
                Bench.getDataTrainees().size(), Bench.getJavaTrainees().size(),
                Bench.getcSharpTrainees().size(), Bench.getDevOpsTrainees().size()
        };
        assertEquals(0, IntStream.of(newBenchState).sum());
    }


}
