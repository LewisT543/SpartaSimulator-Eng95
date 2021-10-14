package com.sparta.simulation.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.stream.Collectors;

// :TODO replace cheesy println statements for a suitable collection and view method.

public class Simulation {
    private int traineeWaitingListLength;
    private ArrayList<Centre> trainingCentres = new ArrayList<>();
    private ArrayDeque<Trainee> reallocatedTrainees = new ArrayDeque<>();
    private ArrayDeque<Trainee> newTrainees = new ArrayDeque<>();
    private ArrayList<Centre> closedCentres = new ArrayList<>();
    private int totalTrainingCentres =0;
    private int traineeID = 0;
    private int bootcampCount =0;
    private int trainingHubCount = 0;
    private int closedBootcampCount =0;
    private int closedTrainingHubCount =0;
    private int closedTechCentreCount =0;

    public enum Courses{DEVOPS,JAVA,DATA,CSHARP,BUSINESS} // is this allowed to be public?

    public void generateCentre(){
        Random rand = new Random();
        int centreNum = GenerateRandomNumber.generateRandomIntNumber(1, 4, null);

        switch (centreNum){
            case 1:
                TrainingHub TH = new TrainingHub(totalTrainingCentres);
                trainingCentres.add(TH);
                totalTrainingCentres++;
                break;
            case 2:
                if(bootcampCount<=2) {
                    bootcampCount+=1;
                    BootCamp BC = new BootCamp(totalTrainingCentres);
                    trainingCentres.add(BC);
                    totalTrainingCentres++;
                } else {
                    generateCentre();
                }
                break;
            case 3:
                TechCentre TC = new TechCentre(totalTrainingCentres);
                trainingCentres.add(TC);
                totalTrainingCentres++;
                break;
        }
    }

    public String[] processMonths(int months) {
        for (int i = 1; i <= months; i++) {
            generateCentre();
//            trainingCentres.add(new TraineeCentre(i));
            generateRandomStudents(50, 101, null);
            distributeTraineesToCentres(null);
//            CheckClosures()
        }
        ////
        int fullCentres = 0;
        int totalTrainees = 0;
        for (Centre centre : trainingCentres) {
            if (centre.getCurrentTrainees().size() == centre.getCAPACITY())
                fullCentres += 1;
            totalTrainees += centre.getCurrentTrainees().size();
        }
        String[] results = new String[5];
        results[0] = String.valueOf(trainingCentres.size());
        results[1] = String.valueOf(closedBootcampCount + closedTechCentreCount + closedTrainingHubCount);
        results[2] = String.valueOf(fullCentres);
        results[3] = String.valueOf(totalTrainees);
        results[4] = String.valueOf(traineeWaitingListLength);
        return results;
    }

    // getOpenCentres()
    public ArrayList<Centre> getOpenCentres() {
        return trainingCentres;
    }
    // getClosedCentres()
    // Already created

    // getFullCentres()
    public ArrayList<Centre> getFullCentres() {
        ArrayList<Centre> fullCentres = new ArrayList<>();
        for (Centre centre : trainingCentres) {
            if (centre.getCurrentTrainees().size() == centre.getCAPACITY()) {
                fullCentres.add(centre);
            }
        }
        return fullCentres;
    }
    // getTraineesInTraining()
    public ArrayList<Trainee> getAllTrainees() {
        ArrayList<Trainee> totalTrainees = new ArrayList<>();
        for (Centre centre : trainingCentres) {
            totalTrainees.addAll(centre.getCurrentTrainees());
        }
        return totalTrainees;
    }
    // getTraineesInWaiting()
    public ArrayList<Trainee> getTraineesInWaiting() {
        return new ArrayList<>(newTrainees);
    }

    // distribute trainees
    public void distributeTraineesToCentres(Long seed) {
        for(Centre centre: trainingCentres) {
            // It would go here
            int trainingIntake = GenerateRandomNumber.generateRandomIntNumber(0, 51, null);
            while (centre.getCAPACITY() > centre.getCurrentTrainees().size() && reallocatedTrainees.size() > 0
                    && trainingIntake > 0) {
                if (centre instanceof TechCentre) {
                    if (((TechCentre) centre).getCentreCourseType().equals(String.valueOf(reallocatedTrainees.getFirst().getTraineeCourse()))) {
                        centre.addTrainee(reallocatedTrainees.getFirst());
                        reallocatedTrainees.pop();
                        trainingIntake--;
                    }
                }

                else {
                    centre.addTrainee(reallocatedTrainees.getFirst());
                    reallocatedTrainees.pop();
                    trainingIntake--;
                }
            }

            while (centre.getCAPACITY() > centre.getCurrentTrainees().size() && newTrainees.size() > 0
                    && trainingIntake > 0) {
                if (centre instanceof TechCentre) {

                    if (((TechCentre) centre).getCentreCourseType().equals(String.valueOf(newTrainees.getFirst().getTraineeCourse()))) {
                        centre.addTrainee(newTrainees.getFirst());
                        newTrainees.pop();
                        trainingIntake--;
                    }
                }

                else {
                    centre.addTrainee(newTrainees.getFirst());
                    newTrainees.pop();
                    trainingIntake--;
                }
            }
            centre.setAgeInMonths(centre.getAgeInMonths() + 1);
        }
    }



    public void generateRandomStudents(int lowerBound, int upperBound, Long seed) {
        int numberOfTrainees = GenerateRandomNumber.generateRandomIntNumber(lowerBound, upperBound, seed);

        for (int i = 0; i <= numberOfTrainees; i++){
            int courseNum = GenerateRandomNumber.generateRandomIntNumber(0, 4, null);
            newTrainees.addLast(new Trainee(traineeID));
            traineeID++;
        }
    }

    public void checkClosures(){
        for(int i=trainingCentres.size()-1; i>=0; i--){
            if(trainingCentres.get(i).isCloseable());{
                reallocateTrainees(i);
                closeCentre(i);
            }
        }
    }

    public void reallocateTrainees(int i){
        reallocatedTrainees.addAll(trainingCentres.get(i).getCurrentTrainees());
    }

    public void closeCentre(int i){
        closedCentres.add(trainingCentres.remove(i));
    }


    public int getTraineeWaitingListLength() {
        return traineeWaitingListLength;
    }

    public void setTraineeWaitingListLength(int traineeWaitingListLength) {
        this.traineeWaitingListLength = newTrainees.size() + reallocatedTrainees.size();
    }

    public ArrayDeque<Trainee> getReallocatedTrainees(){return reallocatedTrainees;}

    public ArrayList<Centre> getTrainingCentres() {return trainingCentres;}

    public ArrayList<Centre> getClosedCentres() {return closedCentres;}

    public void setTrainingCentres(ArrayList<Centre> trainingCentres) {
        this.trainingCentres = trainingCentres;
    }
}
