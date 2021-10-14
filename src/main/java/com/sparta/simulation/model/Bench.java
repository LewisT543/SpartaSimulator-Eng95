package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.CourseException;

import java.util.ArrayDeque;
import java.util.Queue;

public class Bench {
    private static int totalSize;
    private static Queue<Trainee> devOpsTrainees = new ArrayDeque<>();
    private static Queue<Trainee> javaTrainees = new ArrayDeque<>();
    private static Queue<Trainee> dataTrainees = new ArrayDeque<>();
    private static Queue<Trainee> cSharpTrainees = new ArrayDeque<>();
    private static Queue<Trainee> businessTrainees = new ArrayDeque<>();

    public static int getTotalSize() {
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

    public static void addTrainee(Trainee trainee) throws CourseException {
        Simulation.Courses traineeCourse = trainee.getTraineeCourse();
        switch(traineeCourse) {
            case DEVOPS -> devOpsTrainees.offer(trainee);
            case JAVA -> javaTrainees.offer(trainee);
            case DATA -> dataTrainees.offer(trainee);
            case CSHARP -> cSharpTrainees.offer(trainee);
            case BUSINESS -> businessTrainees.offer(trainee);
        }
        totalSize++;
    }

    public static Trainee removeTrainee(Simulation.Courses course) {
        Trainee removedTrainee;
        switch (course) {
            case DEVOPS -> removedTrainee = devOpsTrainees.poll();
            case JAVA -> removedTrainee = javaTrainees.poll();
            case DATA -> removedTrainee = dataTrainees.poll();
            case CSHARP -> removedTrainee = cSharpTrainees.poll();
            case BUSINESS -> removedTrainee = businessTrainees.poll();
            default -> throw new CourseException("Course type does not exist");
        }
        totalSize = Math.max(totalSize - 1, 0);

        return removedTrainee;
    }

    public static void resetState() {
        totalSize = 0;

        javaTrainees.clear();
        devOpsTrainees.clear();
        dataTrainees.clear();
        cSharpTrainees.clear();
        businessTrainees.clear();
    }
}
