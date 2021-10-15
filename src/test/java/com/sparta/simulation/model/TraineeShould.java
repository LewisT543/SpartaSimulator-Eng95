package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.UtilityMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraineeShould {

    private Trainee trainee;


    @Test
    public void getTraineeID_GivenAppropriateInputs_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(1, 1);
        assertEquals(1,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs2_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(2, 1);
        assertEquals(2,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs3_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(3, 1);
        assertEquals(3,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs4_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(4, 1);
        assertEquals(4,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs5_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(5, 1);
        assertEquals(5,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs6_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(6, 1);
        assertEquals(6,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs7_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(7, 1);
        assertEquals(7,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs8_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(8, 1);
        assertEquals(8,trainee.getTraineeID());
    }
    @Test
    public void getTraineeID_GivenAppropriateInputs9_ReturnGetTraineeIDIsCorrect(){
        trainee = new Trainee(9, 1);
        assertEquals(9,trainee.getTraineeID());
    }

    @Test
    public void getTraineeCourses_GivenAppropriateInputs_ReturnGetTraineeCourseIsCorrect(){
        //TODO: the test for this method needs to be re-evaluated
        //should be a test of return of object of instance Simulation.Courses ?
        trainee = new Trainee(1, 1, Simulation.Courses.DATA);
        assertEquals(Simulation.Courses.DATA,trainee.getTraineeCourse());
    }

    @Test
    public void toString_GivenAppropriateInputs_ReturnToStringIsCorrect(){
        trainee = new Trainee(1, 1, Simulation.Courses.DATA);
        assertEquals("traineeID: 1 traineeCourse: DATA",trainee.toString());
    }

}
