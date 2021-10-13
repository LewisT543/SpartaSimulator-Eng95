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
