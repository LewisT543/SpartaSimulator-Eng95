package com.sparta.simulation.controller;

import com.sparta.simulation.model.TraineeCentre;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatorController {
    private TraineeCentre traineeCentreModel;
    private int traineeWaitingListLength;
    private ArrayList<TraineeCentre> traineeCentres;

    public SimulatorController(TraineeCentre traineeCentreModel) {
        this.traineeCentreModel = traineeCentreModel;
    }

    public String[] runSimulation() {
        int simLength = SimulationCLIView.getIntegerInput(0, 24,
                "a number of months for the simulation to run for: ");
        traineeCentres.add(new TraineeCentre(0));
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
            totalTrainees += getCentreCurrentCapacity();
        }
        String[] results = new String[4];
        results[0] =String.valueOf(traineeCentres);
        results[1] =String.valueOf(fullCentres);
        results[2] =String.valueOf(totalTrainees);
        results[3] = String.valueOf(traineeWaitingListLength);
    }

    // distribute trainees
    public void distributeTraineesToCentres() {
        for (TraineeCentre centre : traineeCentres) {
            int incomingStudents = ThreadLocalRandom.current().nextInt(0,51);
            if (traineeWaitingListLength == 0) {
                centre.traineeIntake(incomingStudents);
            } else if (traineeWaitingListLength < incomingStudents){
                centre.traineeIntake(traineeWaitingListLength);
                traineeWaitingListLength = 0;
                centre.traineeIntake(incomingStudents);
            } else {
                // waiting list >= incoming
                centre.traineeIntake(incomingStudents);
                traineeWaitingListLength -= incomingStudents;
            }
            traineeWaitingListLength += centre.getReturnToWaitingList();
        }
    }


    //only getters implemented for the View class in case it needs to see
    //setters not included as the view at this stage doesn't need them
    public int getCentreCurrentCapacity(){
        return traineeCentreModel.getCurrentCapacity();
    }

    public int getCentreWaitingList(){
        return traineeCentreModel.getReturnToWaitingList();
    }
    //currently missing a line to run to model simulation method.
    public void userInputValidation(){
        int NumMonths = SimulationCLIView.getIntegerInput(0,24, "A number from 0-24.");
    }

    //Random number of students created and run inside the traineeIntake class
    public void setCentreIntake(){
        int studentNum = ThreadLocalRandom.current().nextInt(50,101);
        traineeCentreModel.traineeIntake(studentNum);
    }

    //returns the traineeCentre's information (ID, and current capacity)
    public String getCentreInfo(){
        return traineeCentreModel.toString();
    }
    //passes along values to update the view's displayResultsTable method
    public void updateResultsTable(){
    }
    //updates view as a whole, may be removed.
    public void updateView(){

    }
}
