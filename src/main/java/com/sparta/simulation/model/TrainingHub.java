package com.sparta.simulation.model;

public class TrainingHub extends Centre {
    private final int CAPACITY = 100;
    
    public TrainingHub(int id) {
        super(id);
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
