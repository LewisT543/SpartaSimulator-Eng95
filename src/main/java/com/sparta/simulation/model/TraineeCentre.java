package com.sparta.simulation.model;

public class TraineeCentre {

    //-----------------------------------------------------------
    // Variables
    //-----------------------------------------------------------
    private final int CAPACITY = 100;
    private int numberOfTrainees = 0;
    private int centreID;
    private int returnToWaitingList = 0;
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

    public int getNumberOfTrainees() {
        return numberOfTrainees;
    }

    public void setNumberOfTrainees(int numberOfTrainees) {
        if (numberOfTrainees > CAPACITY) {
            this.numberOfTrainees = CAPACITY;
        } else if (numberOfTrainees < 0) {
            this.numberOfTrainees = 0;
        } else {
            this.numberOfTrainees = numberOfTrainees;
        }
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
        if(getNumberOfTrainees() >= CAPACITY) return false;
        else return true;
    }
    
    public void traineeIntake(int intakeAmount){
        try{
            if (intakeAmount < 0){
                throw new IllegalArgumentException("Invalid Trainee Centre intake amount.");
            }
        }catch (IllegalArgumentException iae){
            iae.getStackTrace();
            return;
        }

        int capacityDiff = CAPACITY - getNumberOfTrainees();

        if(capacityDiff >= intakeAmount){
            setNumberOfTrainees(getNumberOfTrainees() + intakeAmount);
        }
        else {
            setNumberOfTrainees(CAPACITY);
            setReturnToWaitingList(getReturnToWaitingList() + (intakeAmount - capacityDiff));
        }
        return;
    }

    @Override
    public String toString() {
        return "centreID: " + centreID + " currentCapacity: " + numberOfTrainees +"/100";
    }
}
