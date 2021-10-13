package com.sparta.simulation.model;

import com.sparta.simulation.model.utils.UtilityMethods;

import java.util.ArrayList;
// Currently reworking
public class TechCentre extends Centre {
    private final int CAPACITY = 200;
    private int id;
    private int ageInMonths;
    private ArrayList<Trainee> currentTrainees = new ArrayList<>();
    private ArrayList<Trainee> returnToWaitingList = new ArrayList<>();
    private ArrayList<Simulation.Courses> courses = new ArrayList<>() {{
        add(Simulation.Courses.DATA);
        add(Simulation.Courses.BUSINESS);
        add(Simulation.Courses.DEVOPS);
        add(Simulation.Courses.CSHARP);
        add(Simulation.Courses.JAVA);
    }};
    private Simulation.Courses centreCourseType;

    public TechCentre(int id) {
        super(id);
        centreCourseType = courses.get(UtilityMethods.generateRandomInt(0, 4, null));
    }

    // Concrete method
    @Override
    void addTrainees(ArrayList<Trainee> incomingTrainees) {
        if (incomingTrainees.size() == 0) { return; }
        int capacityDiff = CAPACITY - (getCurrentTrainees().size());
        if (capacityDiff >= incomingTrainees.size()) {
            for (Trainee incomingTrainee : incomingTrainees) {
                if (incomingTrainee.getTraineeCourse().name().equals(getCentreCourseType())) {
                    getCurrentTrainees().add(incomingTrainee);
                } else {
                    getReturnToWaitingList().add(incomingTrainee);
                }
            }
        } else {
            ArrayList<Trainee> joining = (ArrayList<Trainee>) incomingTrainees.subList(0, capacityDiff);
            ArrayList<Trainee> remainder = (ArrayList<Trainee>) incomingTrainees.subList(capacityDiff, incomingTrainees.size());
            getCurrentTrainees().addAll(joining);
            getReturnToWaitingList().addAll(remainder);
        }



    ArrayList<Trainee> closeCentre() {
        return getCurrentTrainees();

    }

    @Override
    boolean isCloseable() {
        return (getCurrentTrainees().size() < 25 && getAgeInMonths() > 2);
    }

    public Simulation.Courses getCentreCourseType() {
        return centreCourseType;
    }

    public void setCentreCourseType(Simulation.Courses centreCourseType) {
        this.centreCourseType = centreCourseType;
    }
}
