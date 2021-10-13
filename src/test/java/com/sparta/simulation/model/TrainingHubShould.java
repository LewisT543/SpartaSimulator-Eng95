package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrainingHubShould {

    @Test
    public void isClosableCheck_SmallYoung_ReturnFalse() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for(int i = 0; i < 24; i++) {
            trainees.add(new Trainee(i));
        }
        TrainingHub hub = new TrainingHub();
        hub.setCurrentTrainees(trainees);
        hub.setAgeInMonths(2);
        assertEquals(false, hub.isCloseable());
    }

    @Test
    public void isClosableCheck_SmallOld_ReturnTrue() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for(int i = 0; i < 24; i++) {
            trainees.add(new Trainee(i));
        }
        TrainingHub hub = new TrainingHub();
        hub.setCurrentTrainees(trainees);
        hub.setAgeInMonths(3);
        assertEquals(true, hub.isCloseable());
    }

    @Test
    public void isClosableCheck_BigYoung_ReturnFalse() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            trainees.add(new Trainee(i));
        }
        TrainingHub hub = new TrainingHub();
        hub.setCurrentTrainees(trainees);
        hub.setAgeInMonths(2);
        assertEquals(false, hub.isCloseable());
    }

    @Test
    public void isClosableCheck_BigOld_ReturnFalse() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            trainees.add(new Trainee(i));
        }
        TrainingHub hub = new TrainingHub();
        hub.setCurrentTrainees(trainees);
        hub.setAgeInMonths(3);
        assertEquals(false, hub.isCloseable());
    }
}