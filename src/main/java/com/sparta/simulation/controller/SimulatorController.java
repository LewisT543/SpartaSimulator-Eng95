package com.sparta.simulation.controller;

import com.sparta.simulation.model.TraineeCentre;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatorController {
    private int traineeWaitingListLength;
    private ArrayList<TraineeCentre> traineeCentres = new ArrayList<>();
    private ArrayList<String> tableHeaders = new ArrayList<>() {{
        add("Open training centres");
        add("Full training centres");
        add("Total trainees");
        add("Waiting list length");
    }};

    public SimulatorController() {
    }

    public void runSim() {
        ArrayList<String> resultsArrList = new ArrayList<String>(List.of(processMonths()));
        SimulationCLIView.displayResultsTable(tableHeaders, resultsArrList, true);
    }

    public String[] processMonths() {
        int simLength = SimulationCLIView.getIntegerInput(1, 24,
                "a number of months for the simulation to run for: ");
        for (int i = 0; i < simLength; i++) {
            int generatedStudents = ThreadLocalRandom.current().nextInt(50,101);
            traineeWaitingListLength += generatedStudents;
            if (i % 2 == 0) {
                traineeCentres.add(new TraineeCentre(i / 2));
            }
            distributeTraineesToCentres();
        }
        int fullCentres = 0;
        int totalTrainees = 0;
        for (TraineeCentre centre : traineeCentres) {
            if (centre.getCurrentCapacity() == 100)
                fullCentres += 1;
            totalTrainees += centre.getCurrentCapacity();
        }
        String[] results = new String[4];
        results[0] = String.valueOf(traineeCentres.size());
        results[1] = String.valueOf(fullCentres);
        results[2] = String.valueOf(totalTrainees);
        results[3] = String.valueOf(traineeWaitingListLength);
        return results;
    }

    // distribute trainees
    public void distributeTraineesToCentres() {
        for (TraineeCentre centre : traineeCentres) {
            int incomingStudents = ThreadLocalRandom.current().nextInt(0,51);
            System.out.println(centre.toString());
            if (traineeWaitingListLength < incomingStudents){
                centre.traineeIntake(traineeWaitingListLength);
                traineeWaitingListLength = 0;
            } else {
                // all gucci
                centre.traineeIntake(incomingStudents);
                traineeWaitingListLength -= incomingStudents;
            }
            traineeWaitingListLength += centre.getReturnToWaitingList();
            centre.setReturnToWaitingList(0);
        }
        System.out.println();
    }

    //passes along values to update the view's displayResultsTable method
    public void updateResultsTable(){
    }
    //updates view as a whole, may be removed.
    public void updateView(){

    }
}