package com.sparta.simulation.model;

public class TrainingHub extends Centre {

    
    public TrainingHub(int id) {
        super(id);
        this.setCAPACITY(100);
    }

    @Override
    boolean isCloseable() {
        if (this.getCurrentTrainees().size() < 25 && this.getAgeInMonths() > 2) {
            return true;
        } else {
            return false;
        }
    }
}
