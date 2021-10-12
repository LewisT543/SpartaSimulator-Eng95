package com.sparta.simulation.controller;

import com.sparta.simulation.model.Simulation;
import com.sparta.simulation.model.TraineeCentre;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatorController {
    private ArrayList<String> tableHeaders = new ArrayList<>() {{
        add("Open training centres");
        add("Full training centres");
        add("Total trainees");
        add("Waiting list length");
    }};

    public SimulatorController() {
    }

    public void runSim() {
        Simulation sim = new Simulation();
        int simLength = SimulationCLIView.getIntegerInput(1, 24,
                "a number of months for the simulation to run for: ");
        ArrayList<String> resultsArrList = new ArrayList<String>(List.of(sim.processMonths(simLength)));
        SimulationCLIView.displayResultsTable(tableHeaders, resultsArrList, true);
    }

    //passes along values to update the view's displayResultsTable method
    public void updateResultsTable(){
    }
    //updates view as a whole, may be removed.
    public void updateView(){

    }
}
