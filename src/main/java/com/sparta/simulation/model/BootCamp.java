package com.sparta.simulation.model;

import java.util.ArrayList;

public class BootCamp extends Centre {
    int lifespan;
    boolean isGracePeriod = false;

    public BootCamp(int id, int capacity) {
        this.id = id;
        this.CAPACITY = capacity;
        lifespan = 0;
        currentTrainees = new ArrayList<>();
        returnToWaitingList = new ArrayList<>();
    }

    @Override
    public void addTrainees(ArrayList<Trainee> incomingTrainees) {
        // add trainees (when we can)
        // excess added to separate ArrayList

        incomingTrainees.forEach((trainee) -> {
            //logic here
            if (currentTrainees.size() < CAPACITY) {
                currentTrainees.add(trainee);
            } else {
                returnToWaitingList.add(trainee);
            }
        });

        lifespan++;
        isGracePeriod = lifespan <= 2;
        // keep track of months - determine grace period, and the 3 month thing
    }

    @Override
    public boolean isCloseable() {
        return currentTrainees.size() <= 25 && !isGracePeriod;
    }
}
