package com.sparta.simulation.model;

import java.util.ArrayList;

public class BootCamp extends Centre {
    private boolean isGracePeriod = false;
    private int consecutiveMonthsBelow;


    public BootCamp(int id) {
        this.setId(id);
        this.setCAPACITY(500);
        setAgeInMonths(0);
        consecutiveMonthsBelow = 0;
//        setCurrentTrainees(new ArrayList<>());
//        setReturnToWaitingList(new ArrayList<>());
    }

//    @Override
    public void addTrainees(ArrayList<Trainee> incomingTrainees) {
        if (incomingTrainees == null) {
            throw new IllegalArgumentException("ArrayList cannot be null");
        }

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

        setAgeInMonths(getAgeInMonths()+1);

        isGracePeriod = getAgeInMonths() <= 2;
        // keep track of months - determine grace period, and the 3 month thing
        // separate counter for the 3 month thing
        // <=25 trainees, increment counter
        // > 25 trainees, reset counter to 0
        if (getCurrentTrainees().size() < 25) {
            consecutiveMonthsBelow++;
        } else {
            consecutiveMonthsBelow = 0;
        }
    }

    @Override
    public boolean isCloseable() {
        return getCurrentTrainees().size() < 25 && !isGracePeriod && consecutiveMonthsBelow >= 3;
    }
}
