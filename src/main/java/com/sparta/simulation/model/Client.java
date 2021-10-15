package com.sparta.simulation.model;

import java.util.ArrayList;

public class Client {

    private int id;
    private Simulation.Courses typeRequirement;
    private int traineeAmountRequirement;

    private int existedFor;
    private ArrayList<Trainee> listOfTrainees = new ArrayList<>();
    private int intakeAmtThisMonth;
    private int intakeAmtThisYear;
    private boolean isHappy;
    private boolean activelyRecruiting;

    public Client(int id, Simulation.Courses typeRequirement, int traineeAmountRequirement) {
        this.id = id;
        this.typeRequirement = typeRequirement;
        this.traineeAmountRequirement = traineeAmountRequirement;
        existedFor = 0;
        intakeAmtThisMonth = 0;
        intakeAmtThisYear = 0; // every month, do intakeAmtThisYear += intakeAmtThisMonth. reset this to 0 after a year
        isHappy = true;
        activelyRecruiting = true;
    }


    public void addTrainee(Trainee t) {

        while (isHappy()||isActivelyRecruiting()){
            if (listOfTrainees.size()<traineeAmountRequirement){
                listOfTrainees.add(t);
            }
            else{
                Bench.addTrainee(t);
            }
            break;
        }
    }

    public void updateHappiness() {
        if(existedFor >= 12) { // only checks after a year. don't need this if using %
            if (intakeAmtThisYear >= traineeAmountRequirement) {
                isHappy = true;
            } else {
                isHappy = false;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Simulation.Courses getTypeRequirement() {
        return typeRequirement;
    }

    public void setTypeRequirement(Simulation.Courses typeRequirement) {
        this.typeRequirement = typeRequirement;
    }

    public int getTraineeAmountRequirement() {
        return traineeAmountRequirement;
    }

    public void setTraineeAmountRequirement(int traineeAmountRequirement) {
        this.traineeAmountRequirement = traineeAmountRequirement;
    }

    public int getExistedFor() {
        return existedFor;
    }

    public void setExistedFor(int existedFor) {
        this.existedFor = existedFor;
    }

    public ArrayList<Trainee> getListOfTrainees() {
        return listOfTrainees;
    }

    public void setListOfTrainees(ArrayList<Trainee> listOfTrainees) {
        this.listOfTrainees = listOfTrainees;
    }

    public int getIntakeAmtThisMonth() {
        return intakeAmtThisMonth;
    }

    public void setIntakeAmtThisMonth(int intakeAmtThisMonth) {
        this.intakeAmtThisMonth = intakeAmtThisMonth;
    }

    public int getIntakeAmtThisYear() {
        return intakeAmtThisYear;
    }

    public void setIntakeAmtThisYear(int intakeAmtThisYear) {
        this.intakeAmtThisYear = intakeAmtThisYear;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public boolean isActivelyRecruiting() {
        return activelyRecruiting;
    }

    public void setActivelyRecruiting(boolean activelyRecruiting) {
        this.activelyRecruiting = activelyRecruiting;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", typeRequirement=" + typeRequirement +
                ", amountRequirement=" + traineeAmountRequirement +
                ", existedFor=" + existedFor +
                ", listOfTrainees=" + listOfTrainees +
                ", intakeAmtThisMonth=" + intakeAmtThisMonth +
                ", isHappy=" + isHappy +
                ", activelyRecruiting=" + activelyRecruiting +
                '}';
    }
}
