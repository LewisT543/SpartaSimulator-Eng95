package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.UtilityMethods;

import java.util.ArrayList;

public class Trainee{
    private final int traineeID;
    private final Simulation.Courses traineeCourse;
    private ArrayList<Simulation.Courses> courses = new ArrayList<>();

    @Deprecated(forRemoval = true)
    public Trainee(int traineeID) {
        this.traineeCourse = setTraineeCourse();
        this.traineeID = traineeID;
        this.tickCreated = -1;
    }

    // this second constructor makes it easier to create trainees of certain streams and therefore easier to test
    public Trainee(int traineeID, Simulation.Courses traineeCourse) {
        this.traineeID = traineeID;
        this.traineeCourse = traineeCourse;
    }

    int randomInt = UtilityMethods.generateRandomInt(0,5, null);

    public Simulation.Courses setTraineeCourse(){
        courses.add(Simulation.Courses.DATA);
        courses.add(Simulation.Courses.BUSINESS);
        courses.add(Simulation.Courses.DEVOPS);
        courses.add(Simulation.Courses.CSHARP);
        courses.add(Simulation.Courses.JAVA);
        return courses.get(randomInt);
    }

    public int getTraineeID() {
        return traineeID;
    }

    public Simulation.Courses getTraineeCourse() {
        return traineeCourse;
    }

    @Override
    public String toString() {
        return "traineeID: " + traineeID + " traineeCourse: " + traineeCourse;
    }
}
