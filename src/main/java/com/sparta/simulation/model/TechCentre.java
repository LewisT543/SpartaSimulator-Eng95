package com.sparta.simulation.model;

import java.util.ArrayList;

public class TechCentre extends Centre{
    int CAPACITY;
    int id;
    private final String centreType = "TechCentre";
    final int TotalCapacity = 200;
    ArrayList<Simulation.Courses> centreCourseType = new ArrayList<>();
    ArrayList<Trainee> currentTrainees;
    ArrayList<Trainee> returnToWaitingList;

    public void setCentreCourseType(ArrayList<Simulation.Courses> centreCourseType) {
        this.centreCourseType = centreCourseType;
    }

    public ArrayList<Simulation.Courses> getCentreCourseType() {
        return centreCourseType;
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
        for (int i = 0; i < incomingTrainees.size(); i++) {
            if (incomingTrainees.get(i).getTraineeCourse().equals(getCentreCourseType())){

            }
            else{
                returnToWaitingList.add(incomingTrainees.get(i));
            }
        }
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
