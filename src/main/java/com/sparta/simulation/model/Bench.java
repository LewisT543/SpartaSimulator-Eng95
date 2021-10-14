package com.sparta.simulation.model;

import java.util.Queue;

public class Bench {
    private int totalSize;
    private static Queue<Trainee> devOpsTrainees;
    private static Queue<Trainee> javaTrainees;
    private static Queue<Trainee> dataTrainees;
    private static Queue<Trainee> cSharpTrainees;
    private static Queue<Trainee> businessTrainees;

    public int getTotalSize() {
        return totalSize;
    }

    public static Queue<Trainee> getDevOpsTrainees() {
        return devOpsTrainees;
    }

    public static Queue<Trainee> getJavaTrainees() {
        return javaTrainees;
    }

    public static Queue<Trainee> getDataTrainees() {
        return dataTrainees;
    }

    public static Queue<Trainee> getcSharpTrainees() {
        return cSharpTrainees;
    }

    public static Queue<Trainee> getBusinessTrainees() {
        return businessTrainees;
    }

    public static void addTrainee(Trainee trainee) {


    }

    public static Trainee removeTrainee(Simulation.Courses course) {
        return new Trainee(1);
    }
}
