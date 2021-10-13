package com.sparta.simulation.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

// :TODO replace cheesy println statements for a suitable collection and view method.

public class Simulation {
    private int traineeWaitingListLength;
    private ArrayList<Centre> trainingCentres = new ArrayList<>();
    private ArrayDeque<Trainee> reallocatedTrainees = new ArrayDeque<>();
    private ArrayDeque<Trainee> newTrainees = new ArrayDeque<>();
    private int traineeID = 0;
    private int bootcampCount =0;
    private int trainingHubCount = 0;
    private int closedBootcampCount =0;
    private int closedTrainingHubCount =0;
    private int closedTechCentreCount =0;

    public enum Courses{DEVOPS,JAVA,DATA,CSHARP,BUSINESS} // is this allowed to be public?

    public void generateCentre(){
        Random rand = new Random();
        TraineeCentre newCentre;
        int centreNum = rand.nextInt(1-4);

        switch (centreNum){
            case 1:
                TrainingHub TH = new TrainingHub();
                trainingCentres.add(TH);
                break;
            case 2:
                if(bootcampCount<=2) {
                    bootcampCount+=1;
                    BootCamp BC = new BootCamp(closedBootcampCount + bootcampCount);
                    trainingCentres.add(BC);
                } else{
                    generateCentre();
                }
                break;
            case 3:
                TechCentre TC = new TechCentre();
                trainingCentres.add(TC);
                break;
        }

    }//



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
    // getClosedCentres()
    // getFullCentres()
    // getTraineesInTraining()
    // getTraineesInWaiting()

    // distribute trainees
    public void distributeTraineesToCentres(Long seed) {
        for(Centre centre: trainingCentres) {
            int trainingIntake = GenerateRandomNumber.generateRandomIntNumber(0, 51, null);

            while (reallocatedTrainees.size() > 0  && trainingIntake > 0) {
                if (centre instanceof TechCentre && centre.getCAPACITY() > centre.getCurrentTrainees().size()) {

                    if (((TechCentre) centre).getCentreCourseType().equals(String.valueOf(reallocatedTrainees.getFirst().getTraineeCourse()))) {
                        centre.addTrainee(reallocatedTrainees.getFirst());
                        reallocatedTrainees.pop();
                        trainingIntake--;
                    }
                }

                else if (centre.getCAPACITY() > centre.getCurrentTrainees().size()) {
                    centre.addTrainee(reallocatedTrainees.getFirst());
                    reallocatedTrainees.pop();
                    trainingIntake--;
                }
            }

            while (newTrainees.size() > 0  && trainingIntake > 0) {
                if (centre instanceof TechCentre && centre.getCAPACITY() > centre.getCurrentTrainees().size()) {

                    if (((TechCentre) centre).getCentreCourseType().equals(String.valueOf(newTrainees.getFirst().getTraineeCourse()))) {
                        centre.addTrainee(newTrainees.getFirst());
                        newTrainees.pop();
                        trainingIntake--;
                    }
                }

                else if (centre.getCAPACITY() > centre.getCurrentTrainees().size()) {
                    centre.addTrainee(newTrainees.getFirst());
                    newTrainees.pop();
                    trainingIntake--;
                }
            }
        }
    }



    public void generateRandomStudents(int lowerBound, int upperBound, Long seed) {
        int numberOfTrainees = GenerateRandomNumber.generateRandomIntNumber(lowerBound, upperBound, seed);

        for (int i = 0; i <= numberOfTrainees; i++){
            newTrainees.addLast(new Trainee(traineeID));
            traineeID++;
        }
    }

    public void checkClosures(){

    }

    public void closeCentre(){

    }


    public int getTraineeWaitingListLength() {
        return traineeWaitingListLength;
    }

    public void setTraineeWaitingListLength(int traineeWaitingListLength) {
        this.traineeWaitingListLength = newTrainees.size() + reallocatedTrainees.size();
    }

    public ArrayList<Centre> getTrainingCentres() {
        return trainingCentres;
    }

    public void setTrainingCentres(ArrayList<Centre> trainingCentres) {
        this.trainingCentres = trainingCentres;
    }
}
