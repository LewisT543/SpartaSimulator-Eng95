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
    private ArrayList<Client> clientArrayList= new ArrayList<>();
    private int totalTrainingCentres =0;
    private int traineeID = 0;
    private int bootcampCount =0;
    private int trainingHubCount = 0;
    private int closedBootcampCount =0;
    private int closedTrainingHubCount =0;
    private int closedTechCentreCount =0;
    private int numClientGeneratedPM= 1;
    private int numberOfClients=0;

    public enum Courses{DEVOPS,JAVA,DATA,CSHARP,BUSINESS} // is this allowed to be public?

    public void clientGenerate() {
        for (int i = 0; i < numClientGeneratedPM; i++) {
            int clientGenAmount = UtilityMethods.generateRandomInt(15, 51, null);
            int clientGenType = UtilityMethods.generateRandomInt(1, 6, null);
            switch (clientGenType) {
                case 1:
                    Client DO = new Client(numberOfClients, Courses.DEVOPS, clientGenAmount);
                    clientArrayList.add(DO);
                    numberOfClients++;
                case 2:
                    Client JA = new Client(numberOfClients, Courses.JAVA, clientGenAmount);
                    clientArrayList.add(JA);
                    numberOfClients++;
                case 3:
                    Client DA = new Client(numberOfClients, Courses.DATA, clientGenAmount);
                    clientArrayList.add(DA);
                    numberOfClients++;
                case 4:
                    Client CS = new Client(numberOfClients, Courses.CSHARP, clientGenAmount);
                    clientArrayList.add(CS);
                    numberOfClients++;
                case 5:
                    Client BU = new Client(numberOfClients, Courses.BUSINESS, clientGenAmount);
                    clientArrayList.add(BU);
                    numberOfClients++;
            }
        }
    }
    public void generateCentre(){
        int centreNum = UtilityMethods.generateRandomInt(1, 4, null);

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
    public void addToCentre(Client c){
        if (c.getTypeRequirement() == Courses.DEVOPS){
            c.addTrainee(Bench.getDevOpsTrainees().remove());
        }
        else if (c.getTypeRequirement() == Courses.JAVA){
            c.addTrainee(Bench.getJavaTrainees().remove());
        }
        else if (c.getTypeRequirement() == Courses.BUSINESS){
            c.addTrainee(Bench.getBusinessTrainees().remove());
        }
        else if (c.getTypeRequirement() == Courses.CSHARP){
            c.addTrainee(Bench.getcSharpTrainees().remove());
        }
        else if (c.getTypeRequirement() == Courses.DATA){
            c.addTrainee(Bench.getDataTrainees().remove());
        }

    }

    public String[] processMonths(int months, String outputChoice) {
        for (int i = 1; i <= months; i++) {
            generateCentre();
            generateRandomStudents(50, 101, null);
            distributeTraineesToCentres(null);
            checkClosures();

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
        results[1] = String.valueOf(closedBootcampCount + closedTechCentreCount + closedTrainingHubCount);
        results[2] = String.valueOf(fullCentres);
        results[3] = String.valueOf(totalTrainees);
        results[4] = String.valueOf(traineeWaitingListLength);
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

    // Currently not working
    public void distributeTraineesToCentres(Long seed) {
        for(Centre centre: trainingCentres) {
            int trainingIntake = UtilityMethods.generateRandomInt(0, 51, null);
            while (centre.getCAPACITY() > centre.getCurrentTrainees().size() && reallocatedTrainees.size() > 0
                    && trainingIntake > 0) {
                if (centre instanceof TechCentre) {
                    if (((TechCentre) centre).getCentreCourseType().equals(reallocatedTrainees.getFirst().getTraineeCourse())) {
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
                    if (((TechCentre) centre).getCentreCourseType().equals(newTrainees.getFirst().getTraineeCourse())) {
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
        int numberOfTrainees = UtilityMethods.generateRandomInt(lowerBound, upperBound, seed);

        for (int i = 0; i <= numberOfTrainees; i++){
            newTrainees.addLast(new Trainee(traineeID));
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
