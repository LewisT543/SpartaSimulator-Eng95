package com.sparta.simulation.model;

import java.util.Queue;

public class Bench {
    private int totalSize;
    private Queue<Trainee> devOpsTrainees;
    private Queue<Trainee> javaTrainees;
    private Queue<Trainee> dataTrainees;
    private Queue<Trainee> cSharpTrainees;
    private Queue<Trainee> businessTrainees;

    public int getTotalSize() {
        return totalSize;
    }

    public Queue<Trainee> getDevOpsTrainees() {
        return devOpsTrainees;
    }

    public Queue<Trainee> getJavaTrainees() {
        return javaTrainees;
    }

    public Queue<Trainee> getDataTrainees() {
        return dataTrainees;
    }

    public Queue<Trainee> getcSharpTrainees() {
        return cSharpTrainees;
    }

    public Queue<Trainee> getBusinessTrainees() {
        return businessTrainees;
    }

    public static void addTrainee(Trainee trainee) {}

    public static Trainee removeTrainee(Simulation.Courses course) {
        return new Trainee(1);
    }
}
