package com.sparta.simulation.controller;

import com.sparta.simulation.model.Simulation;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SimulatorController {
    private final int MAXIMUM_MONTHS = 120;
    private ArrayList<String> tableHeaders = new ArrayList<>() {{
        add("Open centres");
        add("Closed centres");
        add("Full centres");
        add("Total trainees");
        add("Waiting list length");
    }};
    private LinkedHashMap<String, String> RESULTS_OPTIONS = new LinkedHashMap<>() {{
        put("t", "All results displayed at end of simulation.");
        put("m", "Month-by-month breakdown with summary at end.");
    }};


    public SimulatorController() {
    }

    public void runSim() {
        // THIS IS BROKE AS HELL, DON'T WORRY ABOUT IT :D
        Simulation sim = new Simulation();
        String resultsChoice = SimulationCLIView.getInput(RESULTS_OPTIONS, "a results output method.");
        int simLength = SimulationCLIView.getIntegerInput(1, MAXIMUM_MONTHS,
                "a number of months for the simulation to run for (1-120): ");
        String[] resArr = sim.processMonths(simLength, resultsChoice);
        SimulationCLIView.displayAllResults(sim, tableHeaders);
        ArrayList<String> resultsArrList = new ArrayList<>(List.of(resArr));
        System.out.println("Final results:");
        SimulationCLIView.displayResultsTable(tableHeaders, resultsArrList, true);
    }

    //passes along values to update the view's displayResultsTable method
    public void updateResultsTable(){
    }
    //updates view as a whole, may be removed.
    public void updateView(){

    }
}
