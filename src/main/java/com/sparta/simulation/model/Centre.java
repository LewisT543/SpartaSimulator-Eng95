package com.sparta.simulation.model;

import java.util.ArrayList;

public abstract class Centre {
    private int CAPACITY;
    private int id;
    private int ageInMonths;
    private int thisMonthIntake;
    private ArrayList<Trainee> currentTrainees = new ArrayList<>();
    private ArrayList<Trainee> returnToWaitingList = new ArrayList<>();

    public Centre() {
    }

    public Centre(int id) {
        this.id = id;
    }

    boolean addTrainee(Trainee trainee) {
        if (getCurrentTrainees().size() == CAPACITY || thisMonthIntake == 0)
           return false;
        currentTrainees.add(trainee);
        return true;
    }

    ArrayList<Trainee> closeCentre() {
        System.out.println("Trainee Centre: " + id + " has been closed. Returning " + currentTrainees.size() +
                " trainees to the priority Queue.");
        return currentTrainees;
    }

    abstract boolean isCloseable();

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

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(int ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public ArrayList<Trainee> getCurrentTrainees() {
        return currentTrainees;
    }

    public void setCurrentTrainees(ArrayList<Trainee> currentTrainees) {
        this.currentTrainees = currentTrainees;
    }

    public ArrayList<Trainee> getReturnToWaitingList() {
        return returnToWaitingList;
    }

    public void setReturnToWaitingList(ArrayList<Trainee> returnToWaitingList) {
        this.returnToWaitingList = returnToWaitingList;
    }

    public int getThisMonthIntake() {
        return thisMonthIntake;
    }

    public void setThisMonthIntake(int thisMonthIntake) {
        this.thisMonthIntake = thisMonthIntake;
    }

    @Override
    public String toString() {
        return "Centre{" +
                "id=" + id +
                ", ageInMonths=" + getAgeInMonths() +
                ", currentTrainees=" + currentTrainees.size() + "/" + CAPACITY +
                '}';
    }
}