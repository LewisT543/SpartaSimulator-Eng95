package com.sparta.simulation.controller;

import com.sparta.simulation.model.Simulation;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.ArrayList;
import java.util.List;

public class SimulatorController {
    private ArrayList<String> tableHeaders = new ArrayList<>() {{
        add("Open training centres");
        add("Full training centres");
        add("Total trainees");
        add("Waiting list length");
    }};

    //public

    public SimulatorController() {
    }

    public void runSim() {
        Simulation sim = new Simulation();
        int simLength = SimulationCLIView.getIntegerInput(1, 60,
                "a number of months for the simulation to run for (1-60): ");
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
