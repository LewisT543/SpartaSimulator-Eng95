package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.UtilityMethods;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.ArrayDeque;
import java.util.ArrayList;

// :TODO replace cheesy println statements for a suitable collection and view method.

public class Simulation {
    private int traineeWaitingListLength;
    private ArrayList<Centre> trainingCentres = new ArrayList<>();
    private ArrayDeque<Trainee> reallocatedTrainees = new ArrayDeque<>();
    private ArrayDeque<Trainee> newTrainees = new ArrayDeque<>();
    private ArrayList<Centre> closedCentres = new ArrayList<>();
    private Bench theBench = new Bench();
    private int totalTrainingCentres =0;
    private int traineeID = 0;
    private int bootcampCount =0;


    public enum Courses{DEVOPS,JAVA,DATA,CSHARP,BUSINESS} 

    public void generateCentre(){
        int centreNum = UtilityMethods.generateRandomInt(1, 4, null);

        switch (centreNum){
            case 1:
                TrainingHub TH = new TrainingHub(totalTrainingCentres);
                trainingCentres.add(TH);
                totalTrainingCentres++;
                break;
            case 2:
                if(bootcampCount<2) {
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

    public String[] processMonths(int months, String outputChoice) {
        for (int i = 1; i <= months; i++) {
            //if(i % 2 == 0) {generateCentre();} // turn this on and comment out the other one if you want centres every 2 months rather than every month
            generateCentre();
            generateRandomStudents(i, 50, 101, null);
            //addToBench(findTwelveMonthTrainees(i)); // turn this on to take trainees out of centres, needs to have the view updated to show how many trainees are on the bench
            distributeTraineesToCentres(null);

            checkClosures();
            for (Centre centre : trainingCentres)
                centre.setAgeInMonths(centre.getAgeInMonths() + 1);
            if (outputChoice.equals("m")) {
                System.out.println("Month: " + i);
                SimulationCLIView.displayCentreGranular(getOpenCentres(), "open");
                SimulationCLIView.displayCentreGranular(getClosedCentres(), "closed");
                SimulationCLIView.displayCentreGranular(getFullCentres(), "full");
                SimulationCLIView.displayTraineeGranular(getAllTrainees(), "current");
                SimulationCLIView.displayTraineeGranular(getTraineesInWaiting(), "waiting");
            }
        }
        int fullCentres = 0;
        int totalTrainees = 0;
        for (Centre centre : trainingCentres) {
            if (centre.getCurrentTrainees().size() == centre.getCAPACITY())
                fullCentres += 1;
            totalTrainees += centre.getCurrentTrainees().size();
        }
        String[] results = new String[5];
        results[0] = String.valueOf(trainingCentres.size());
        results[1] = String.valueOf(closedCentres.size());
        results[2] = String.valueOf(fullCentres);
        results[3] = String.valueOf(totalTrainees);
        results[4] = String.valueOf(newTrainees.size() + reallocatedTrainees.size());
        return results;
    }

    public ArrayList<Centre> getOpenCentres() {
        return trainingCentres;
    }

    public ArrayList<Centre> getFullCentres() {
        ArrayList<Centre> fullCentres = new ArrayList<>();
        for (Centre centre : trainingCentres) {
            if (centre.getCurrentTrainees().size() == centre.getCAPACITY()) {
                fullCentres.add(centre);
            }
        }
        return fullCentres;
    }

    public ArrayList<Trainee> getAllTrainees() {
        ArrayList<Trainee> totalTrainees = new ArrayList<>();
        for (Centre centre : trainingCentres) {
            totalTrainees.addAll(centre.getCurrentTrainees());
        }
        return totalTrainees;
    }

    public ArrayList<Trainee> getTraineesInWaiting() {
        ArrayList<Trainee> reallocated = new ArrayList<>(reallocatedTrainees);
        ArrayList<Trainee> totalWaiting = new ArrayList<>();
        for (Trainee trainee : reallocated) {
            totalWaiting.add(trainee);
        }
        return totalWaiting;
    }

    public void popTrainee(int choice) {
        if (choice == 1)
            reallocatedTrainees.pop();
        if (choice == 2)
            newTrainees.pop();
    }


    public void distributeTraineesToCentres(Long seed) {
        // Setup the centres order
        ArrayList<Centre> temp = new ArrayList<>();
        for (Centre centre : trainingCentres) {
            if (centre instanceof TechCentre)
                temp.add(0,centre);
            else
                temp.add(temp.size(),centre);
        }
        trainingCentres.clear();
        trainingCentres.addAll(temp);

        // Set monthly intakes
        for (Centre centre : trainingCentres) {
            centre.setThisMonthIntake(UtilityMethods.generateRandomInt(0, 51, seed));
        }
        // Break out of this by checking if trainee has been added to any centre, if it has been continue, else break.
        int unplacedTraineeCounter = 0;
        while ((reallocatedTrainees.size() + newTrainees.size()) > 0) {
            int startWaiting = reallocatedTrainees.size() + newTrainees.size();
            if(startWaiting == 0) return;
            Trainee selectedTrainee = null;

            int choice = 0;

            if (reallocatedTrainees.size() > 0) {
                selectedTrainee = reallocatedTrainees.getFirst();
                choice = 1;
            } else {
                selectedTrainee = newTrainees.getFirst();
                choice = 2;
            }

            for (Centre centre : trainingCentres) {
                if (centre.getThisMonthIntake() == 0)
                    continue;
                if (centre instanceof TechCentre) {
                    if (((TechCentre) centre).getCentreCourseType().equals(selectedTrainee.getTraineeCourse())) {
                        if (centre.addTrainee(selectedTrainee)) {
                            popTrainee(choice);
                            centre.setThisMonthIntake(centre.getThisMonthIntake() - 1);
                            break;
                        } else {
                            if (centre.equals(trainingCentres.get(trainingCentres.size()-1)))
                                break;
                            else
                                continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (centre.addTrainee(selectedTrainee)) {
                        popTrainee(choice);
                        centre.setThisMonthIntake(centre.getThisMonthIntake() - 1);
                        break;
                    } else {
                        if(centre.equals(trainingCentres.get(trainingCentres.size()-1)))
                            break;
                        else
                            continue;
                    }
                }
            }
            int endWaitingTotal = reallocatedTrainees.size() + newTrainees.size();
            //-------------------------------------------------------------------------------------------------- If something is broken look here first
            if(endWaitingTotal == startWaiting) {
                unplacedTraineeCounter++;
                if (reallocatedTrainees.size() > 0)
                    reallocatedTrainees.push(reallocatedTrainees.pop());
                else
                    newTrainees.push(newTrainees.pop());
            }

            if (unplacedTraineeCounter >= endWaitingTotal) return;
            int fullCentres = 0;
            for (Centre centre : trainingCentres) {
                if (centre.getThisMonthIntake() == 0) {
                    fullCentres++;
                }
            }
            if (fullCentres == trainingCentres.size())
                return;
        }
    }




    //this gets the trainees that are a year old and adds them to an array list called to bebenched, as well as removing them from the centres
    public ArrayList<Trainee> findTwelveMonthTrainees(int currentTick) {
        ArrayList<Trainee> toBeBenched = new ArrayList<>(); // temp array to store all 12 month trainees
        for (Centre centre : trainingCentres) {
            for (Trainee trainee : centre.getCurrentTrainees()) {
                if ((currentTick - trainee.getTickCreated()) >= 12) {
                    toBeBenched.add(trainee);
                }
            }
        }
        for (Centre centre : trainingCentres) { // Removes trainee from centres
            for (Trainee trainee: toBeBenched){
                if (centre.getCurrentTrainees().contains(trainee)){
                    centre.getCurrentTrainees().remove(trainee);
                }
            }
        }
        return toBeBenched;
    }

    public void addToBench(ArrayList<Trainee> toBeBenched){
        for (Trainee trainee : toBeBenched) {
            Bench.addTrainee(trainee);
        }
    }



    public void generateRandomStudents(int tickCreated, int lowerBound, int upperBound, Long seed) {
        int numberOfTrainees = UtilityMethods.generateRandomInt(lowerBound, upperBound, seed);
        for (int i = 0; i <= numberOfTrainees; i++){
            newTrainees.addLast(new Trainee(traineeID, tickCreated));
            traineeID++;
        }
    }

    public void checkClosures(){
        for(int i=trainingCentres.size()-1; i>=0; i--){
            if(trainingCentres.get(i).isCloseable()) {
                reallocateTrainees(i);
                closeCentre(i);
            }
        }
    }

    public void reallocateTrainees(int i){
        reallocatedTrainees.addAll(trainingCentres.get(i).getCurrentTrainees());
        trainingCentres.get(i).setCurrentTrainees(new ArrayList<>());
    }

    public void closeCentre(int i){
        closedCentres.add(trainingCentres.get(i));
        trainingCentres.remove(i);
    }

    public Bench getTheBench() {
        return theBench;
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

    public void setReallocatedTrainees(ArrayDeque<Trainee> reallocatedTrainees) {
        this.reallocatedTrainees = reallocatedTrainees;
    }

    public void setNewTrainees(ArrayDeque<Trainee> newTrainees) {
        this.newTrainees = newTrainees;
    }

    public ArrayDeque<Trainee> getNewTrainees() {
        return newTrainees;
    }


}
