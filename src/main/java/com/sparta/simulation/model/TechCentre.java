package com.sparta.simulation.model;

import java.util.ArrayList;

public class TechCentre extends Centre{
    int CAPACITY = 200;
    private final String centreType = "TechCentre";
    private String centreCourseType;
    private int gracePeriod = 0;

    public String getCentreCourseType() {
        return centreCourseType;
    }

    public void setCentreCourseType(String centreCourseType) {
        this.centreCourseType = centreCourseType;
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
                    getCurrentTrainees().add(incomingTrainee);
                } else {
                    getReturnToWaitingList().add(incomingTrainee);
                }
            }
        } else {
            ArrayList<Trainee> joining = (ArrayList<Trainee>) incomingTrainees.subList(0, capacityDiff);
            ArrayList<Trainee> remainder = (ArrayList<Trainee>) incomingTrainees.subList(capacityDiff, incomingTrainees.size());
            getCurrentTrainees().addAll(joining);
            getReturnToWaitingList().addAll(remainder);
        }

        }



    ArrayList<Trainee> closeCentre() {
        return getCurrentTrainees();
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
