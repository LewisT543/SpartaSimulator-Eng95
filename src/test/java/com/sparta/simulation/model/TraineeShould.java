package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraineeShould {

    private Trainee trainee;
    @BeforeEach
    public void setUp() {trainee = new Trainee(1);}

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

    @Test
    public void generateRandomInt_GivenSetLongSeed_ReturnTwo(){
        assertEquals(2,trainee.generateRandomInt(13L));
    }

    @Test
    public void setTraineeCourse_GivenSetLongSeed_Return(){
        assertEquals(Simulation.Courses.DEVOPS,trainee.setTraineeCourse(13L));
    }



}
