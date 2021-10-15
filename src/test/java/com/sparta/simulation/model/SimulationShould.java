package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationShould {

    private Simulation sim;

    @BeforeEach
    public void setUp() {
        sim = new Simulation();
    }

    @Test
    public void processMonths_TenMonthsAreInput_ReturnTenTrainingCentres(){
        String[] results = sim.processMonths(10, "t");
        assertEquals(10,sim.getTotalTrainingCentres());
    }

    @Test
    public void processMonths_FiveMonthsAreInput_ReturnFiveTrainingCentres(){
        sim.processMonths(5, "t");
        assertEquals(5,sim.getTotalTrainingCentres());
    }

    @Test
    public void processMonths_OneMonthIsInput_ReturnOneTrainingCentres() {
        sim.processMonths(1, "t");
        assertEquals(1, sim.getTotalTrainingCentres());
    }


    @Test
    public void closeCentre_GivenCentreToClose_ReturnClosedCentre(){
        ArrayList<Centre> trainingCentres = new ArrayList<>();
        trainingCentres.add(new TrainingHub(1));
        sim.setTrainingCentres(trainingCentres);
        sim.closeCentre(0);
        assertEquals(1,sim.getClosedCentres().size());
    }

    @Test
    public void closeCentre_GivenCentreToCloseAndOneToStayOpen_ReturnClosedCentre(){
        ArrayList<Centre> trainingCentres = new ArrayList<>();
        trainingCentres.add(new TrainingHub(1));
        trainingCentres.add(new TrainingHub(2));
        sim.setTrainingCentres(trainingCentres);
        sim.closeCentre(0);
        assertEquals(1,sim.getClosedCentres().size());
        assertEquals(1,sim.getTrainingCentres().size());
    }

    @Test
    public void checkClosures_GivenCentreToClose_ReturnTraineesToDeque(){
        ArrayList<Trainee> traineeArrayList = new ArrayList<>();
        traineeArrayList.add(new Trainee(1, 0));
        ArrayList<Centre> trainingCentres = new ArrayList<>();
        trainingCentres.add(new TrainingHub(1));
        sim.setTrainingCentres(trainingCentres);
        trainingCentres.get(0).setAgeInMonths(3);
        trainingCentres.get(0).setCurrentTrainees(traineeArrayList);
        sim.checkClosures();
        assertEquals(1,sim.getReallocatedTrainees().size());
    }

    @Test
    public void findTwelveMonthTrainees_GivenZeroTraineesInCentres_ReturnLengthZeroArray(){
        sim.generateCentre();
        assertEquals(0, sim.findTwelveMonthTrainees(12).size());
        assertEquals(0, sim.getTrainingCentres().get(0).getCurrentTrainees().size());
    }

    @Test
    public void findTwelveMonthTrainees_GivenOneTraineeWithTwelveMonths_ReturnTrainee(){
        sim.generateCentre();
        Trainee trainee1 = new Trainee(1, 0);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee1);
        assertEquals(trainee1, sim.findTwelveMonthTrainees(12).get(0));
        assertEquals(0, sim.getTrainingCentres().get(0).getCurrentTrainees().size());
    }

    @Test
    public void findTwelveMonthTrainees_Given3TraineesWithTwelveMonths_ReturnLength3Array(){
        sim.generateCentre();
        Trainee trainee1 = new Trainee(1, 0);
        Trainee trainee2 = new Trainee(2, 0);
        Trainee trainee3 = new Trainee(3, 1);
        Trainee trainee4 = new Trainee(4, 12);
        Trainee trainee5 = new Trainee(5, 0);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee1);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee2);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee3);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee4);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee5);
        assertEquals(3, sim.findTwelveMonthTrainees(12).size());
    }

    @Test
    public void findTwelveMonthTrainees_Given5TraineesWith12MonthsStartingAtDifferentTicks_ReturnLength4Array(){
        sim.generateCentre();
        Trainee trainee1 = new Trainee(1, 5);
        Trainee trainee2 = new Trainee(2, 5);
        Trainee trainee3 = new Trainee(3, 1);
        Trainee trainee4 = new Trainee(4, 12);
        Trainee trainee5 = new Trainee(5, 2);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee1);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee2);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee3);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee4);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee5);
        assertEquals(4, sim.findTwelveMonthTrainees(17).size());
        assertEquals(1, sim.getTrainingCentres().get(0).getCurrentTrainees().size());
    }

    @Test
    public void findTwelveMonthTrainees_Given2TraineesWith12MonthsStartingAtDifferentTicks_ReturnLength1Array(){
        sim.generateCentre();
        Trainee trainee1 = new Trainee(1, 1);
        Trainee trainee2 = new Trainee(2, 2);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee1);
        sim.getTrainingCentres().get(0).getCurrentTrainees().add(trainee2);
        assertEquals(1, sim.findTwelveMonthTrainees(13).size());
        assertEquals(1, sim.getTrainingCentres().get(0).getCurrentTrainees().size());
    }

    //tests for add to bench go here
    @Test
    public void addToBench_givenArrayOfTraineesToBeBenched_ReturnBenchedTrainees(){
        //bench is a static class. State should be reset between tests
        Bench.resetState();


        ArrayList<Trainee> traineeArrLst = new ArrayList<>();
        sim.generateCentre();
        traineeArrLst.add(new Trainee(1, 5));
        traineeArrLst.add(new Trainee(2, 5));
        traineeArrLst.add(new Trainee(3, 12));
        traineeArrLst.add(new Trainee(4, 12));
        traineeArrLst.add(new Trainee(5, 17));
        sim.addToBench(traineeArrLst);
        assertEquals(5,Bench.getTotalSize());
    }

    @Test
    public void addToBench_GivenJavaTrainee_ReturnTheTraineeInThatBench(){
        Bench.resetState();
        ArrayList<Trainee> traineeArrLst = new ArrayList<>();
        sim.generateCentre();
        traineeArrLst.add(new Trainee(1, 5,Simulation.Courses.JAVA));
        sim.addToBench(traineeArrLst);
        assertEquals(1,Bench.getJavaTrainees().size());

    }

    @Test
    public void addToBench_GivenDataTrainee_ReturnTheTraineeInThatBench(){
        Bench.resetState();
        ArrayList<Trainee> traineeArrLst = new ArrayList<>();
        sim.generateCentre();
        traineeArrLst.add(new Trainee(2, 5,Simulation.Courses.DATA));
        sim.addToBench(traineeArrLst);
        assertEquals(1,Bench.getDataTrainees().size());

    }
    @Test
    public void addToBench_GivenCSharpTrainee_ReturnTheTraineeInThatBench(){
        Bench.resetState();
        ArrayList<Trainee> traineeArrLst = new ArrayList<>();
        sim.generateCentre();
        traineeArrLst.add(new Trainee(3, 1,Simulation.Courses.CSHARP));
        sim.addToBench(traineeArrLst);
        assertEquals(1,Bench.getcSharpTrainees().size());

    }

    @Test
    public void addToBench_GivenDevOpsTrainee_ReturnTheTraineeInThatBench(){
        Bench.resetState();
        ArrayList<Trainee> traineeArrLst = new ArrayList<>();
        sim.generateCentre();
        traineeArrLst.add(new Trainee(4, 12,Simulation.Courses.DEVOPS));
        sim.addToBench(traineeArrLst);
        assertEquals(1,Bench.getDevOpsTrainees().size());

    }

    @Test
    public void addToBench_GivenBusinessTrainee_ReturnTheTraineeInThatBench(){
        Bench.resetState();
        ArrayList<Trainee> traineeArrLst = new ArrayList<>();
        sim.generateCentre();
        traineeArrLst.add(new Trainee(5, 2, Simulation.Courses.BUSINESS));
        sim.addToBench(traineeArrLst);
        assertEquals(1,Bench.getBusinessTrainees().size());

    }

}




