package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.CourseException;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * This class holds Trainees who have been training for a year in the various streams
 *
 @author Karina T & Edmund A
 @version 1.0
 @since 2021-10-14
 */

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

    /**
     * This method takes trainees and stores the trainees in the corresponding stream array
     * @param trainee
     */
    public static void addTrainee(Trainee trainee) {
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

    /**
     * Removes a trainee from the given stream.
     * When a stream is given that is not of the type expected, a CourseException is thrown
     * @throws CourseException
     * @param course
     * @return Trainee
     */
    public static Trainee removeTrainee(Simulation.Courses course) throws  CourseException{
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

    /**
     * Used to reset the state of all Bench instances. Exists for testing purposes only
     * Consult authors before implementing in production
     */
    public static void resetState() {
        totalSize = 0;

        javaTrainees.clear();
        devOpsTrainees.clear();
        dataTrainees.clear();
        cSharpTrainees.clear();
        businessTrainees.clear();
    }
}
