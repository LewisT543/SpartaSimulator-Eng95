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
        bootCamp = new BootCamp(1);
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
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.setCurrentTrainees(trainees);

        for (int i = 0; i < 1; i ++) {
            // add some trainees
            bootCamp.addTrainees(new ArrayList<>());
        }
        assertFalse(bootCamp.isCloseable());
    }

    @Test
    public void isCloseable_TwoMonthGracePeriod_MonthThree_Exactly25Trainees_ReturnFalse() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.setCurrentTrainees(trainees);

        for (int i = 0; i < 3; i ++) {
            // add some trainees
            bootCamp.addTrainees(new ArrayList<>());
        }
        assertFalse(bootCamp.isCloseable());
    }

    @Test
    public void isCloseable_TwoMonthsFewer25Trainees_ThirdMonthMoreThan25Trainees_ReturnFalse() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.setCurrentTrainees(trainees);

        // simulate 2 months
        for (int i = 0; i < 2; i++) {
            bootCamp.addTrainees(new ArrayList<>());
        }
        for (int i = 0; i < 50; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.setCurrentTrainees(trainees);

        // simulate 1 month
        bootCamp.addTrainees(new ArrayList<>());

        assertFalse(bootCamp.isCloseable());

    }

    // testing addTrainees
    // test both currentTrainees and returnToWaitingList
    // 1. 0> and <500 trainees
    // 2. exactly 500 trainees
    // 3. more than 500 trainees
    // 4. 0 trainees

    @Test
    public void addTrainees_WhenTraineesAreGreaterThanZero_LessThan500_ShouldBeInCurrentTrainees() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.addTrainees(trainees);
        assertEquals(10, bootCamp.getCurrentTrainees().size());
    }

    @Test
    public void addTrainees_WhenTraineesAreGreaterThanZero_LessThan500_ReturnToWaitingListSizeIsZero() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.addTrainees(trainees);
        assertEquals(0, bootCamp.getReturnToWaitingList().size());
    }

    @Test
    public void addTrainees_WhenTraineesIs500_ShouldBeInCurrentTrainees() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int x = 0; x < 500; x++) {
            trainees.add(new Trainee(x));
        }
        bootCamp.addTrainees(trainees);
        assertEquals(500, bootCamp.getCurrentTrainees().size());
    }

    @Test
    public void addTrainees_WhenTraineesIs500_ReturnToWaitingListSizeIsZero() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.addTrainees(trainees);
        assertEquals(0, bootCamp.getReturnToWaitingList().size());
    }

    @Test
    public void addTrainees_WhenTraineesAreMoreThan500_500ShouldBeInCurrentTrainees() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 510; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.addTrainees(trainees);
        assertEquals(500, bootCamp.getCurrentTrainees().size());
    }

    @Test
    public void addTrainees_WhenTraineesAreMoreThan500_ExcessShouldBeInReturnToWaitingList() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < 510; i++) {
            trainees.add(new Trainee(i));
        }
        bootCamp.addTrainees(trainees);
        assertEquals(10, bootCamp.getReturnToWaitingList().size());
    }

    @Test
    public void addTrainees_WhenTraineesIsZero_ZeroInCurrentTrainees() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        bootCamp.addTrainees(trainees);
        assertEquals(0, bootCamp.getCurrentTrainees().size());
    }

    @Test
    public void addTrainees_WhenTraineesIsZero_ZeroInWaitingList() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        bootCamp.addTrainees(trainees);
        assertEquals(0, bootCamp.getReturnToWaitingList().size());
    }

    @Test
    public void addTrainees_WhenTraineesIsNull_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> bootCamp.addTrainees(null));
    }

    // 1. 2 month grace period, can't close no matter what
    // 1.1 if < 25, don't close
    // 1.2 if >= 25, don't close
    // 2. 3 months consecutively < 25 trainees, close
    // 2.1 1 month in, < 25, don't close
    // 2.2 1 month in, >=25, don't close
    // 2.3 3 months in < 25, close
}
