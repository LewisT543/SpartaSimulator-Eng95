package com.sparta.simulation.model;

import java.util.ArrayList;
import java.util.Random;

// :TODO replace cheesy println statements for a suitable collection and view method.

public class Simulation {
    private int traineeWaitingListLength;
    private ArrayList<TraineeCentre> trainingCentres = new ArrayList<>();


    public enum Courses{DEVOPS,JAVA,DATA,CSHARP,BUSINESS} // is this allowed to be public?

    public String[] processMonths(int months) {
        for (int i = 1; i <= months; i++) {
            int generatedStudents = generateRandomStudents(50, 101, null);
            traineeWaitingListLength += generatedStudents;
            if (i % 2 == 0) {
                trainingCentres.add(new TraineeCentre(i / 2));
            }
            distributeTraineesToCentres(null);
        }
        int fullCentres = 0;
        int totalTrainees = 0;
        for (TraineeCentre centre : trainingCentres) {
            if (centre.getNumberOfTrainees() == 100)
                fullCentres += 1;
            totalTrainees += centre.getNumberOfTrainees();
        }
        String[] results = new String[4];
        results[0] = String.valueOf(trainingCentres.size());
        results[1] = String.valueOf(fullCentres);
        results[2] = String.valueOf(totalTrainees);
        results[3] = String.valueOf(traineeWaitingListLength);
        return results;
    }

    // distribute trainees
    public void distributeTraineesToCentres(Long seed) {
        for (TraineeCentre centre : trainingCentres) {
            int incomingStudents = generateRandomStudents(0, 51, seed);
            if (traineeWaitingListLength < incomingStudents){
                centre.traineeIntake(traineeWaitingListLength);
                traineeWaitingListLength = 0;
            } else {
                centre.traineeIntake(incomingStudents);
                traineeWaitingListLength -= incomingStudents;
            }
            traineeWaitingListLength += centre.getReturnToWaitingList();
            centre.setReturnToWaitingList(0);
            System.out.println(centre);
        }

        if (trainingCentres.size() == 0) System.out.println("Currently no training centres.");
        System.out.println();
    }

    public int generateRandomStudents(int origin, int bound, Long seed) {
        Random rn = (seed==null) ? new Random(): new Random(seed);
        return rn.nextInt(bound-origin) + origin;
    }

    public int getTraineeWaitingListLength() {
        return traineeWaitingListLength;
    }

    public void setTraineeWaitingListLength(int traineeWaitingListLength) {
        this.traineeWaitingListLength = traineeWaitingListLength;
    }

    public ArrayList<TraineeCentre> getTrainingCentres() {
        return trainingCentres;
    }

    public void setTrainingCentres(ArrayList<TraineeCentre> trainingCentres) {
        this.trainingCentres = trainingCentres;
    }
}
