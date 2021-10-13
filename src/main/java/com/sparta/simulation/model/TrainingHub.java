package com.sparta.simulation.model;

public class TrainingHub extends Centre {

    @Override
    boolean isCloseable() {
        if (this.getCurrentTrainees().size() < 25 && this.getAgeInMonths() > 2) {
            return true;
        } else {
            return false;
        }
    }
}
