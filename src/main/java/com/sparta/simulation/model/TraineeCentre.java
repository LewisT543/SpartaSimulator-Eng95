package com.sparta.simulation.model;

public class TraineeCentre {

    //-----------------------------------------------------------
    // Variables
    //-----------------------------------------------------------
    private final int CAPACITY = 100;
    private int currentCapacity = 0;
    private int centreID;
    private int returnToWaitingList =0;
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    public TraineeCentre(int centreID) {
        this.centreID = centreID;
    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    public int getCentreID() {
        return centreID;
    }

    public void setCentreID(int centreID) {
        this.centreID = centreID;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    public int getReturnToWaitingList() {
        return returnToWaitingList;
    }

    public void setReturnToWaitingList(int returnTowaitingList) {
        this.returnToWaitingList = returnTowaitingList;
    }

    //-----------------------------------------------------------
    // Methods
    //-----------------------------------------------------------
    public boolean capacityCheck(){
        if(getCurrentCapacity() == CAPACITY) return false;
        else return true;
    }
    
    public void traineeIntake(int intakeAmount){
        for(int i = 0; i<intakeAmount;i++){
            if(capacityCheck()){
                setCurrentCapacity(getCurrentCapacity()+1);
            }
            else setReturnToWaitingList(getReturnToWaitingList()+1);
        }
    }

    @Override
    public String toString() {
        return "centreID: " + centreID + " currentCapacity: " + currentCapacity+"/100";
    }
}
