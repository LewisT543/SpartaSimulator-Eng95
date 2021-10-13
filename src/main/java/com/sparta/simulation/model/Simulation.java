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
        int centreNum = rand.nextInt(0-4);

        switch (centreNum){
            case 1:
                TrainingHub TH=new TrainingHub();
                trainingCentres.add(TH);
                break;
            case 2:
                if(bootcampCount<=2) {
                    BootCamp BC = new BootCamp();
                    trainingCentres.add(BC);
                }
                break;
            case 3:
                TechCentre TC=new TechCentre();
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
        String[] results = new String[4];
        results[0] = String.valueOf(trainingCentres.size());
        results[1] = String.valueOf(fullCentres);
        results[2] = String.valueOf(totalTrainees);
        results[3] = String.valueOf(traineeWaitingListLength);
        return results;
    }

    // distribute trainees
    public void distributeTraineesToCentres(Long seed) {

        int trainingHubIntake = GenerateRandomNumber.generateRandomIntNumber(0, 100, seed);
        int bootcampIntake = GenerateRandomNumber.generateRandomIntNumber(0, 100, seed);
        int techCentreIntake = GenerateRandomNumber.generateRandomIntNumber(0, 100, seed);

        if (reallocatedTrainees.size() > 0){

            while (reallocatedTrainees.size() > 0){
//                allocate to centres
                for(Centre centre: trainingCentres){
                    int thTaken = 0;
                    if(trainingHubIntake < thTaken){

                    }
                }

            }
        }
        else {
            while (newTrainees.size() > 0){

            }
        }
    }

    public void generateRandomStudents(int lowerBound, int upperBound, Long seed) {
        int numberOfTrainees = GenerateRandomNumber.generateRandomIntNumber(lowerBound, upperBound, seed);

        for (int i = 0; i <= numberOfTrainees; i++){
            int courseNum = GenerateRandomNumber.generateRandomIntNumber(0, 4, null);
            newTrainees.addLast(new Trainee(traineeID));
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
