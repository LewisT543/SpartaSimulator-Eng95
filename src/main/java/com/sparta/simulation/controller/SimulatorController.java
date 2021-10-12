package com.sparta.simulation.controller;

import com.sparta.simulation.model.TraineeCentre;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.concurrent.ThreadLocalRandom;

public class SimulatorController {
    private SimulationCLIView view;
    private TraineeCentre traineeCentreModel;

    public SimulatorController(SimulationCLIView view, TraineeCentre traineeCentreModel) {
        this.view = view;
        this.traineeCentreModel = traineeCentreModel;
    }

    //only getters implemented for the View class in case it needs to see
    //setters not included as the view at this stage doesn't need them
    public int getCentreCurrentCapacity(){
        return traineeCentreModel.getCurrentCapacity();
    }

    public int getCentreWaitingList(){
        return traineeCentreModel.getReturnToWaitingList();
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
