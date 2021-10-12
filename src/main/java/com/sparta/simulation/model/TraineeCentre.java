package com.sparta.simulation.model;

import java.security.cert.CertificateParsingException;

public class TraineeCentre {

    //-----------------------------------------------------------
    // Variables
    //-----------------------------------------------------------
    private final int CAPACITY = 100;
    private int currentCapacity = 0;
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

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        if (currentCapacity > CAPACITY) {
            this.currentCapacity = CAPACITY;
        } else if (currentCapacity < 0) {
            this.currentCapacity = 0;
        } else {
            this.currentCapacity = currentCapacity;
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
        if(getCurrentCapacity() >= CAPACITY) return false;
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

        int capacityDiff = CAPACITY - getCurrentCapacity();

        if(capacityDiff >= intakeAmount){
            setCurrentCapacity(getCurrentCapacity() + intakeAmount);
        }
        else {
            setCurrentCapacity(CAPACITY);
            setReturnToWaitingList(getReturnToWaitingList() + (intakeAmount - capacityDiff));
        }
        return;
    }

    @Override
    public String toString() {
        return "centreID: " + centreID + " currentCapacity: " + currentCapacity+"/100";
    }
}
