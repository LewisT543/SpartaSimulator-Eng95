package com.sparta.simulation.model;

import java.util.ArrayList;
import java.util.Random;

public class Trainee{

    private final int traineeID;
    private final Simulation.Courses traineeCourse;
    private ArrayList<Simulation.Courses> courses = new ArrayList<>();


    public Trainee(int traineeID) {
        this.traineeCourse = setTraineeCourse(null);
        this.traineeID = traineeID;
    }

    public int generateRandomInt(Long seed){
        Random rand = (seed==null) ? new Random(): new Random(seed);
        return rand.nextInt(5);
    }

    public Simulation.Courses setTraineeCourse(Long seed){
        courses.add(Simulation.Courses.DATA);
        courses.add(Simulation.Courses.BUSINESS);
        courses.add(Simulation.Courses.DEVOPS);
        courses.add(Simulation.Courses.CSHARP);
        courses.add(Simulation.Courses.JAVA);
        return courses.get(generateRandomInt(seed));
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
