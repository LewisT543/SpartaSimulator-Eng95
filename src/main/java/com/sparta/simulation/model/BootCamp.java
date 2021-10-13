package com.sparta.simulation.model;

import java.util.ArrayList;

public class BootCamp extends Centre {
    int lifespan;
    boolean isGracePeriod = false;

    public BootCamp(int id, int capacity) {
        this.setId(id);
        this.setCAPACITY(capacity);
        lifespan = 0;
        setCurrentTrainees(new ArrayList<>());
        setReturnToWaitingList(new ArrayList<>());
    }

    @Override
    public void addTrainees(ArrayList<Trainee> incomingTrainees) {
        // add trainees (when we can)
        // excess added to separate ArrayList

        incomingTrainees.forEach((trainee) -> {
            //logic here
            if (getCurrentTrainees().size() < getCAPACITY()) {
                getCurrentTrainees().add(trainee);
            } else {
                getReturnToWaitingList().add(trainee);
            }
        });

        lifespan++;
        isGracePeriod = lifespan <= 2;
        // keep track of months - determine grace period, and the 3 month thing
    }

    @Override
    public boolean isCloseable() {
        return getCurrentTrainees().size() <= 25 && !isGracePeriod;
    }
}
