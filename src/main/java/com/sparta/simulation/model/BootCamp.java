package com.sparta.simulation.model;

public class BootCamp extends TrainingCentre {
    int currentMonth;

    public BootCamp(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    @Override
    boolean isCloseable() {
        return false;
    }
}
