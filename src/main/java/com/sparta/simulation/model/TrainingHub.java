package com.sparta.simulation.model;

public class TrainingHub extends Centre {
    @Override
    boolean isCloseable() {
        if (currentTrainees.size() < 25 && ageInMonths > 2) {
            return true;
        } else {
            return false;
        }
    }
}
