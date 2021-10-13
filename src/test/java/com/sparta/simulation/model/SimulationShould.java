package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        String[] results = sim.processMonths(10);
        assertEquals(5,Integer.valueOf(results[0]));
    }

    @Test
    public void processMonths_FiveMonthsAreInput_ReturnTwoTrainingCentres(){
        sim.processMonths(5);
        assertEquals(2,sim.getTrainingCentres().size());
    }

    @Test
    public void processMonths_OneMonthIsInput_ReturnZeroTrainingCentres() {
        sim.processMonths(1);
        assertEquals(0, sim.getTrainingCentres().size());
    }


    @Test
    public void closeCentre_GivenCentreToClose_ReturnClosedCentre(){
        ArrayList<Centre> trainingCentres = new ArrayList<>();
        trainingCentres.add(new TrainingHub(1));
        sim.setTrainingCentres(trainingCentres);
        sim.closeCentre(0);
        System.out.println(sim.getClosedCentres());
    }

    @Test
    public void checkClosures_IfCentreNeedsToBeClosed_ReturnCallForClosingCentre(){

    }

}
