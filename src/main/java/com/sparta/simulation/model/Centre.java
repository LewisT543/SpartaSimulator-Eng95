package com.sparta.simulation.model;

import java.util.ArrayList;

public abstract class Centre {
    // Variables
    int CAPACITY;
    int id;
    int ageInMonths;
    ArrayList<Trainee> currentTrainees;
    ArrayList<Trainee> returnToWaitingList;

    // Concrete method
    void addTrainees(ArrayList<Trainee> incomingTrainees) {
        // Try and add as many as randomGen dictates up to CAPACITY.
        // If it cannot add all in randomGen it will add them to returnToWaitingList
    }
    ArrayList<Trainee> closeCentre() {
        return currentTrainees;
    }

    // Abstract method
    abstract boolean isCloseable();

    public ArrayList<Trainee> getCurrentTrainees() {
        return currentTrainees;
    }

    public ArrayList<Trainee> getReturnToWaitingList() {
        return returnToWaitingList;
    }
}
