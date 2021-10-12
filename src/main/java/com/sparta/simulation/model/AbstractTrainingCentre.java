package com.sparta.simulation.model;

public abstract class AbstractTrainingCentre {
    private int CAPACITY;
    private int id;
    private ArrayList<Trainee> currentTrainees;
    private ArrayList<Trainee> returnToWaitingList;
}