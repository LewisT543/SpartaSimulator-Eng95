package com.sparta.simulation.model;

import java.util.ArrayList;

public class TrainingHub extends Centre {

    // TODO: delete this once it's been added to Centre class
    public void setCurrentTrainees(ArrayList<Trainee> trainees) {
        this.currentTrainees = trainees;
    }

    // TODO: delete this once it's been added to Centre class
    public void setAgeInMonths(int age) {
        this.ageInMonths = age;
    }

    @Override
    boolean isCloseable() {
        if (currentTrainees.size() < 25 && ageInMonths > 2) {
            return true;
        } else {
            return false;
        }
    }
}
