package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.UtilityMethods;

import java.util.ArrayList;
import java.util.Objects;

public class Trainee{
    private final int traineeID;

    private int tickCreated; // This really should be final

    private final Simulation.Courses traineeCourse;
    private ArrayList<Simulation.Courses> courses = new ArrayList<>();
    private int randomInt = UtilityMethods.generateRandomInt(0,5, null);

    @Deprecated(forRemoval = true)
    public Trainee(int traineeID) {
        this.traineeCourse = setTraineeCourse();
        this.traineeID = traineeID;
        this.tickCreated = -1;
    }

    public Trainee(int traineeID, int tickCreated) {
        this.traineeCourse = setTraineeCourse();
        this.traineeID = traineeID;
        this.tickCreated = tickCreated;
    }

    // this second constructor makes it easier to create trainees of certain streams and therefore easier to test
    public Trainee(int traineeID, Simulation.Courses traineeCourse) {
        this.traineeID = traineeID;
        this.traineeCourse = traineeCourse;
    }

    public Trainee(int traineeID,int tickCreated ,Simulation.Courses traineeCourse) {
        this.traineeID = traineeID;
        this.tickCreated = tickCreated;
        this.traineeCourse = traineeCourse;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return traineeID == trainee.traineeID && tickCreated == trainee.tickCreated && randomInt == trainee.randomInt && traineeCourse == trainee.traineeCourse && Objects.equals(courses, trainee.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traineeID, tickCreated, traineeCourse, courses, randomInt);
    }

    public int getTickCreated() {
        return tickCreated;
    }
}
