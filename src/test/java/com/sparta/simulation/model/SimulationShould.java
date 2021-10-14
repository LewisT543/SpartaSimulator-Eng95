package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationShould {

    private Simulation sim;

    @BeforeEach
    public void setUp() {
        sim = new Simulation();
    }

    @Test
    public void processMonths_TenMonthsAreInput_ReturnFiveTrainingCentres(){
        String[] results = sim.processMonths(10, "t");
        assertEquals(5,Integer.valueOf(results[0]));
    }

    @Test
    public void processMonths_FiveMonthsAreInput_ReturnTwoTrainingCentres(){
        sim.processMonths(5, "t");
        assertEquals(2,sim.getTrainingCentres().size());
    }

    @Test
    public void processMonths_OneMonthIsInput_ReturnZeroTrainingCentres() {
        sim.processMonths(1, "t");
        assertEquals(0, sim.getTrainingCentres().size());
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
        traineeArrayList.add(new Trainee(1));
        ArrayList<Centre> trainingCentres = new ArrayList<>();
        trainingCentres.add(new TrainingHub(1));
        sim.setTrainingCentres(trainingCentres);
        trainingCentres.get(0).setAgeInMonths(3);
        trainingCentres.get(0).setCurrentTrainees(traineeArrayList);
        sim.checkClosures();
        assertEquals(1,sim.getReallocatedTrainees().size());
    }
    

}
