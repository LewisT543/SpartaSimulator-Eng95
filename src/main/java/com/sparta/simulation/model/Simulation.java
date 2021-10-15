package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.UtilityMethods;
import com.sparta.simulation.view.SimulationCLIView;

import java.util.ArrayDeque;
import java.util.ArrayList;

// :TODO replace cheesy println statements for a suitable collection and view method.

/**
 * This class runs the simulation program and is responsible
 * for co-ordinating the states of the bench and the various ArrayDeques
 * Testing for this class can be found at #BenchShould.java
 * @version 3.0
 * @since 2021-10-12
 * @author Everyone
 */
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
    private int numberOfClients=0;
    private int numClientGeneratedPM=1;
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<String> tableHeaders = new ArrayList<>() {{
        add("Open centres");
        add("Closed centres");
        add("Full centres");
        add("Total trainees");
        add("Waiting list length");
    }};

    public enum Courses {DEVOPS, JAVA, DATA, CSHARP, BUSINESS}

    public void generateCentre(){
        int centreNum = UtilityMethods.generateRandomInt(1, 4, null);

        switch (centreNum) {
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
            if(i % 2 == 0) {generateClient();} // turn this on and comment out the other one if you want centres every 2 months rather than every month
            generateCentre();
            generateRandomStudents(i, 50, 101, null);
            addToBench(findTwelveMonthTrainees(i)); // turn this on to take trainees out of centres, needs to have the view updated to show how many trainees are on the bench
            distributeTraineesToCentres(null);

            checkClosures();
            for (Centre centre : trainingCentres)
                centre.setAgeInMonths(centre.getAgeInMonths() + 1);
            if (outputChoice.equals("m")) {
                System.out.println("Month: " + i);
                SimulationCLIView.displayAllResults(this, tableHeaders);
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


    public ArrayList<Client> getClientArrayList() {
        return clientArrayList;
    }

    public void setClientArrayList(ArrayList<Client> clientArrayList) {
        this.clientArrayList = clientArrayList;
    }

    public void addToClient(){
        for (Client c : clientArrayList){

            if (c.getTypeRequirement() == Courses.DEVOPS){
                Trainee t = Bench.removeTrainee(Simulation.Courses.DEVOPS);
                c.addTrainee(t);
            }
            else if (c.getTypeRequirement() == Courses.JAVA){
                Trainee t = Bench.removeTrainee(Simulation.Courses.JAVA);
                c.addTrainee(t);
            }
            else if (c.getTypeRequirement() == Courses.BUSINESS){
                Trainee t = Bench.removeTrainee(Simulation.Courses.BUSINESS);
                c.addTrainee(t);
            }
            else if (c.getTypeRequirement() == Courses.CSHARP){
                Trainee t = Bench.removeTrainee(Simulation.Courses.CSHARP);
                c.addTrainee(t);
            }
            else if (c.getTypeRequirement() == Courses.DATA){
                Trainee t = Bench.removeTrainee(Simulation.Courses.DATA);
                c.addTrainee(t);
            }

        }

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

    /**
     * Seed is used to initialise the random number generator during testing.
     * Takes the number of months and goes through the defined simulation behaviour for each month.
     *
     * @author Lewis T, Dan W
     * @param seed
     */
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


    /**
     * This gets the trainees that are a year old and adds them to an array list called to toBeBenched,
     * as well as removing them from the centres.
     * * * @author Pedro L
     * @param currentTick
     * @return
     */
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

    /**
     * Takes an array of clients and adds each to the bench
     * Iterates over the output of findTwelveMonthTrainees(int currentTick), sending them to the bench
     * through the addTrainee() static method defined in the Bench class.
     * @param toBeBenched
     */
    public void addToBench(ArrayList<Trainee> toBeBenched){
        for (Trainee trainee : toBeBenched) {
            Bench.addTrainee(trainee);
        }
    }
    /**
     * Generates the various training facilities based on the lifecycle of the simulation
     * @author Halil K
     */

    public void generateClient() {
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

    /**
     * generates a specified number of random Trainees and stores them in
     * the newTrainees ArrayDeque. param can be sed to set boundaries.
     * seed param is strictly for testing purposes only.

    /**
     * Generates a random amount of Trainees with the lowerBound being inclusive, and the upperBound being exclusive.
     * The method also sets their unique ID number and month they were created.

     * @param tickCreated
     * @param lowerBound
     * @param upperBound
     * @param seed
     */

    public void generateRandomStudents(int tickCreated, int lowerBound, int upperBound, Long seed) {
        int numberOfTrainees = UtilityMethods.generateRandomInt(lowerBound, upperBound, seed);
        for (int i = 0; i <= numberOfTrainees; i++){
            newTrainees.addLast(new Trainee(traineeID, tickCreated));
            traineeID++;
        }
    }

    /**
     * checks to see if a training centre cna be close and if
     * so moves the assigned trainees to the reallocatedTrainees Array
     * * @author Dan W
     */
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

    /**
     * moves a training centre marked for closure to the closed training centre array
     * @param i
     */
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

    public int getTotalTrainingCentres() {
        return totalTrainingCentres;
    }

    public void setNewTrainees(ArrayDeque<Trainee> newTrainees) {
        this.newTrainees = newTrainees;
    }

    public ArrayDeque<Trainee> getNewTrainees() {
        return newTrainees;
    }


}
