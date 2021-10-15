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
    public void getTraineeCourses_GivenAppropriateInputs_ReturnGetTraineeCourseIsCorrect(){
        //TODO: the test for this method needs to be re-evaluated
        //should be a test of return of object of instance Simulation.Courses ?
        trainee = new Trainee(1, 1, Simulation.Courses.DATA);
        assertEquals(Simulation.Courses.DATA,trainee.getTraineeCourse());
    }

    @Test
    public void toString_GivenAppropriateInputs_ReturnToStringIsCorrect(){
        trainee = new Trainee(1, 1, Simulation.Courses.DATA);
        System.out.println(trainee.toString());
        assertEquals("traineeID: 1 traineeCourse: DATA",trainee.toString());
    }

}
