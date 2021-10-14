package com.sparta.simulation.model;

import java.util.ArrayList;

public class Client {

    private int id;
    private Simulation.Courses typeRequirement;
    private int amountRequirement;

    private int existedFor;
    private ArrayList<Trainee> listOfTrainees;
    private int intakeAmtThisMonth;
    private boolean isHappy;
    private boolean activelyRecruiting;

    public Client(int id, Simulation.Courses typeRequirement, int amountRequirement) {
        this.id = id;
        this.typeRequirement = typeRequirement;
        this.amountRequirement = amountRequirement;
        existedFor = 0;
        intakeAmtThisMonth = 0;
        isHappy = true;
        activelyRecruiting = true;
    }

    public Client() {

    }

    public void addTrainee(Trainee t) {
        while (isHappy()||isActivelyRecruiting()){
            if (listOfTrainees.size()<amountRequirement){
                listOfTrainees.add(t);
            }

        }
    }

    // TODO: check whether client is happy or not and update
    public void updateHappiness() {
        // do something
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

    public int getAmountRequirement() {
        return amountRequirement;
    }

    public void setAmountRequirement(int amountRequirement) {
        this.amountRequirement = amountRequirement;
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
                ", amountRequirement=" + amountRequirement +
                ", existedFor=" + existedFor +
                ", listOfTrainees=" + listOfTrainees +
                ", intakeAmtThisMonth=" + intakeAmtThisMonth +
                ", isHappy=" + isHappy +
                ", activelyRecruiting=" + activelyRecruiting +
                '}';
    }
}
