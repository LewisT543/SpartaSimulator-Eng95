package com.sparta.simulation.model;

import java.util.ArrayList;

public abstract class Centre {
    int CAPACITY;
    int id;
    int ageInMonths;
    ArrayList<Trainee> currentTrainees;
    ArrayList<Trainee> returnToWaitingList;

    void addTrainees(ArrayList<Trainee> incomingTrainees) {
        if (incomingTrainees.size() == 0) {
            return;
        }
        int capacityDiff = CAPACITY - (getCurrentTrainees().size());
        if (capacityDiff >= incomingTrainees.size()) {
            currentTrainees.addAll(incomingTrainees);
        } else {
            ArrayList<Trainee> joining = (ArrayList<Trainee>) incomingTrainees.subList(0, capacityDiff);
            ArrayList<Trainee> remainder = (ArrayList<Trainee>) incomingTrainees.subList(capacityDiff, incomingTrainees.size());
            currentTrainees.addAll(joining);
            returnToWaitingList.addAll(remainder);
        }
    }

    ArrayList<Trainee> closeCentre() {
        System.out.println("Trainee Centre: " + id + " has been closed. Returning " + currentTrainees.size() +
                " trainees to the priority Queue.");
        return currentTrainees;
    }

    abstract boolean isCloseable();

    public ArrayList<Trainee> getCurrentTrainees() {
        return currentTrainees;
    }
}
