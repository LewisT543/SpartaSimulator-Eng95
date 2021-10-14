package com.sparta.simulation.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TechCentreShould {
    private Simulation testSimulationObj = new Simulation();
     private TechCentre testTechCentreObj = new TechCentre(1, JAVA);

     @Test
    public void test_GivenCurrentTraineesLowerThan25AndGracePeriodLessThan2_ReturnFalse(){
         ArrayList<Trainee> testTraineeList = new ArrayList<>();
         for (int i = 0; i < 20; i++) {
             testTraineeList.add(new Trainee(i, Simulation.Courses.JAVA));
         }
         testTechCentreObj.setCurrentTrainees(testTraineeList);
         testTechCentreObj.isCloseable();
         boolean expectedResult = false;
         assertFalse(testTechCentreObj.isCloseable());
     }

    @Test
    public void test_GivenCurrentTraineesHigherThan25AndGracePeriodGreaterThan2_ReturnTrue(){
        ArrayList<Trainee> testTraineeList = new ArrayList<>();
        boolean expectedResult = false;
        for (int i = 0; i < 20; i++) {
            testTraineeList.add(new Trainee(i, Simulation.Courses.JAVA));
        }
        testTechCentreObj.setCurrentTrainees(testTraineeList);

        for (int i = 0; i < 4; i++) {
            expectedResult = testTechCentreObj.isCloseable();
        }

        assertTrue(expectedResult);
    }


}
