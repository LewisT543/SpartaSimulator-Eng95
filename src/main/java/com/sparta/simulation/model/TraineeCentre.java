package com.sparta.simulation.model;

public class TraineeCentre {

    private final int CAPACITY = 100;

    private int currentCapacity = 0;

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

}
