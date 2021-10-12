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
        String[] results = sim.processMonths(5);
        assertEquals(2,Integer.valueOf(results[0]));
    }

    @Test
    public void distributeTraineesToCentre_IncomingStudentsIsLargerThanTraineeWaitingList_ReturnWaitingListMinusTheDistributedTrainees() {
        ArrayList<TraineeCentre> trainingCentres = new ArrayList<>();
        trainingCentres.add(new TraineeCentre(1));
        sim.setTrainingCentres(trainingCentres);
        sim.setTraineeWaitingListLength(3);
        sim.distributeTraineesToCentres(1234L); // this give me 5
        assertEquals(0,sim.getTraineeWaitingListLength());
    }
    
}
