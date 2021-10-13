package com.sparta.simulation.model;

import java.util.ArrayList;

public class TechCentre extends TrainingCentre{
    int CAPACITY;
    int id;
    final int TotalCapacity = 200;
    ArrayList<Simulation.Courses> centreType = new ArrayList<>();

    public void setCentreType(ArrayList<Simulation.Courses> centreType) {
        this.centreType = centreType;
    }

    public ArrayList<Simulation.Courses> getCentreType() {
        return centreType;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(int CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Concrete method
    void addTrainees(ArrayList<Trainee> incomingTrainees) {
        // Try and add as many as randomGen dictates up to CAPACITY.
        // If it cannot add all in randomGen it will add them to returnToWaitingList
    }
    ArrayList<Trainee> closeCentre() {
        return currentTrainees;
    }


    @Override
    boolean isCloseable() {
        boolean result = false;
        if (getCAPACITY() < 25){
            result = true;
        }
        return result;
    }
}
