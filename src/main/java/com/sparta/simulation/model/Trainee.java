package com.sparta.simulation.model;

public class Trainee{

    private final int traineeID;
    private final Simulation.Courses traineeCourse;

    public Trainee(int traineeID, Simulation.Courses traineeCourse) {
        this.traineeID = traineeID;
        this.traineeCourse = traineeCourse;
    }

    public Simulation.Courses getTraineeCourse() {
        return traineeCourse;
    }

    public int getTraineeID() {
        return traineeID;
    }

    @Override
    public String toString() {
        return "traineeID: " + traineeID + " traineeCourse: " + traineeCourse;
    }
}
