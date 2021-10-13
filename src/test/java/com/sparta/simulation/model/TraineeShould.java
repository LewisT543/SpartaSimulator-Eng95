package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraineeShould {

    private Trainee trainee;
    @BeforeEach
    public void setUp() {trainee = new Trainee(1, Simulation.Courses.DATA);}

    @Test
    public void getTraineeID_GivenAppropriateInputs_ReturnGetTraineeIDIsCorrect(){
        assertEquals(1,trainee.getTraineeID());
    }

    @Test
    public void getTraineeCourses_GivenAppropriateInputs_ReturnGetTraineeCourseIsCorrect(){
        assertEquals(Simulation.Courses.DATA,trainee.getTraineeCourse());
    }

    @Test
    public void toString_GivenAppropriateInputs_ReturnToStringIsCorrect(){
        System.out.println(trainee.toString());
        assertEquals("traineeID: 1 traineeCourse: DATA",trainee.toString());
    }







}
