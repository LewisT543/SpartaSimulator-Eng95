package com.sparta.simulation.model;

import java.util.ArrayList;

public class TechCentre extends Centre{
    int CAPACITY = 200;
    int id;
    private final String centreType = "TechCentre";
    private String centreCourseType;
    private ArrayList<Trainee> currentTrainees = new ArrayList<>();
    private int gracePeriod = 0;
    ArrayList<Trainee> returnToWaitingList = new ArrayList<>();

    public String getCentreCourseType() {
        return centreCourseType;
    }

    public void setCentreCourseType(String centreCourseType) {
        this.centreCourseType = centreCourseType;
    }

    @Override
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

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    // Concrete method
    void addTrainees(ArrayList<Trainee> incomingTrainees) {
        if (incomingTrainees.size() == 0) { return; }
        int capacityDiff = CAPACITY - (getCurrentTrainees().size());
        if (capacityDiff >= incomingTrainees.size()) {
            for (Trainee incomingTrainee : incomingTrainees) {
                if (incomingTrainee.getTraineeCourse().name().equals(getCentreCourseType())) {
                    currentTrainees.add(incomingTrainee);
                } else {
                    returnToWaitingList.add(incomingTrainee);
                }
            }
        } else {
            ArrayList<Trainee> joining = (ArrayList<Trainee>) incomingTrainees.subList(0, capacityDiff);
            ArrayList<Trainee> remainder = (ArrayList<Trainee>) incomingTrainees.subList(capacityDiff, incomingTrainees.size());
            currentTrainees.addAll(joining);
            returnToWaitingList.addAll(remainder);
        }

        }



    ArrayList<Trainee> closeCentre() {
        return currentTrainees;
    }


    @Override
    boolean isCloseable() {
        boolean result = false;
        setGracePeriod(getGracePeriod()+1);
        if (getCurrentTrainees().size() < 25 && getGracePeriod() > 2){
            result = true;
        }
        return result;
    }
}
