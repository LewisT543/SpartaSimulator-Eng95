package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BootCampShould {
    private BootCamp bootCamp;
    @BeforeEach
    public void setUp() {
        bootCamp = new BootCamp(1, 500);
    }

    // testing isCloseable()

    @Test
    public void isCloseable_TwoMonthGracePeriod_MonthOne_LessThan25Trainees_ReturnFalse() {
        for (int i = 0; i < 1; i ++) {
            // add some trainees
            bootCamp.addTrainees(new ArrayList<>(List.of(
                    new Trainee(1),
                    new Trainee(2))));
        }
        assertFalse(bootCamp.isCloseable());
    }

    @Test
    public void isCloseable_TwoMonthGracePeriod_MonthTwo_LessThan25Trainees_ReturnFalse() {
        for (int i = 0; i < 2; i ++) {
            // add some trainees
            bootCamp.addTrainees(new ArrayList<>(List.of(
                    new Trainee(1),
                    new Trainee(2))));
        }
        assertFalse(bootCamp.isCloseable());
    }

    @Test
    public void isCloseable_TwoMonthGracePeriod_MonthThree_LessThan25Trainees_ReturnFalse() {
        for (int i = 0; i < 3; i ++) {
            // add some trainees
            bootCamp.addTrainees(new ArrayList<>(List.of(
                    new Trainee(1),
                    new Trainee(2))));
        }
        assertTrue(bootCamp.isCloseable());
    }

    @Test
    public void isCloseable_TwoMonthGracePeriod_MonthOne_Exactly25Trainees_ReturnFalse() {
        for (int i = 0; i < 1; i ++) {
            // add some trainees
            bootCamp.addTrainees(new ArrayList<>(List.of(
                    new Trainee(1),
                    new Trainee(2))));
        }
        assertFalse(bootCamp.isCloseable());
    }

    @Test
    public void isCloseable_TwoMonthGracePeriod_MonthThree_Exactly25Trainees_ReturnFalse() {
        for (int i = 0; i < 3; i ++) {
            // add some trainees
            bootCamp.addTrainees(new ArrayList<>(List.of(
                    new Trainee(1),
                    new Trainee(2))));
        }
        assertFalse(bootCamp.isCloseable());
    }


    // 1. 2 month grace period, can't close no matter what
    // 1.1 if < 25, don't close
    // 1.2 if >= 25, don't close
    // 2. 3 months consecutively < 25 trainees, close
    // 2.1 1 month in, < 25, don't close
    // 2.2 1 month in, >=25, don't close
    // 2.3 3 months in < 25, close
}
