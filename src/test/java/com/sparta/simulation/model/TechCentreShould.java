package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TechCentreShould {
private TechCentre techCentre;
    @BeforeEach
    public void setUp(){
        techCentre = new TechCentre(1);
    }

     @Test
    public void test_GivenCurrentTraineesLowerThan25AndGracePeriodLessThan2_ReturnFalse(){

         ArrayList<Trainee> testTraineeList = new ArrayList<>();
         for (int i = 0; i < 20; i++) {
             testTraineeList.add(new Trainee(i, 1));
         }
         techCentre.setCurrentTrainees(testTraineeList);
         techCentre.isCloseable();
         assertFalse(techCentre.isCloseable());
     }

    @Test
    public void test_GivenCurrentTraineesLowerThan25AndGracePeriodGreaterThan2_ReturnTrue(){
        techCentre.setAgeInMonths(3);
        ArrayList<Trainee> testTraineeList = new ArrayList<>();
        boolean expectedResult = false;
        for (int i = 0; i < 20; i++) {
            testTraineeList.add( new Trainee(i, 1));
        }
        techCentre.setCurrentTrainees(testTraineeList);

        expectedResult = techCentre.isCloseable();


        assertTrue(expectedResult);
    }

    @Test
    public void test_GivenCurrentTraineesGreaterThan25AndGracePeriodLowerThan2_ReturnFalse(){
        techCentre.setAgeInMonths(1);
        ArrayList<Trainee> testTraineeList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testTraineeList.add( new Trainee(i, 1));
        }
        techCentre.setCurrentTrainees(testTraineeList);



        assertFalse(techCentre.isCloseable());
    }


}
