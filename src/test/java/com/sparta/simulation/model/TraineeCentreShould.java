package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TraineeCentreShould {
    private static TraineeCentre traineeCentre;


    @BeforeEach
    public void setUp() {
        traineeCentre = new TraineeCentre(123);
    }

    // testing capacityCheck()
    @Test
    public void capacityCheck_NotMaxCapacity_ReturnTrue() {
        traineeCentre.setNumberOfTrainees(50);
        assertTrue(traineeCentre.capacityCheck());
    }

    @Test
    public void capacityCheck_AtMaxCapacity_ReturnFalse() {
        traineeCentre.setNumberOfTrainees(100);
        assertFalse(traineeCentre.capacityCheck());
    }

    @Test
    public void capacityCheck_ExceedMaxCapacity_ReturnFalse() {
        traineeCentre.setNumberOfTrainees(101);
        assertFalse(traineeCentre.capacityCheck());
    }

    // testing traineeIntake()
    @Test
    public void traineeIntake_IntakeDoesNotExceedCapacity_CurrentCapacityEqualsIntake(){
        traineeCentre.traineeIntake(50);
        assertEquals(50, traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void traineeIntake_IntakeDoesNotExceedCapacity_ReturnToWaitingListEqualsZero() {
        traineeCentre.traineeIntake(50);
        assertEquals(0, traineeCentre.getReturnToWaitingList());
    }

    @Test
    public void traineeIntake_IntakeEqualsCapacity_CurrentCapacityEqualsMaxCapacity() {
        traineeCentre.traineeIntake(100);
        assertEquals(100,traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void traineeIntake_IntakeEqualsCapacity_ReturnToWaitingListEqualsZero() {
        traineeCentre.traineeIntake(100);
        assertEquals(0, traineeCentre.getReturnToWaitingList());
    }

    @Test
    public void traineeIntake_IntakeExceedsCapacity_CurrentCapacityEqualsMaxCapacity() {
        traineeCentre.traineeIntake(105);
        assertEquals(100, traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void traineeIntake_IntakeExceedsCapacity_ReturnToWaitingListEqualsExcess(){
        traineeCentre.traineeIntake(105);
        assertEquals(5,traineeCentre.getReturnToWaitingList());
    }

    @Test
    public void traineeIntake_CurrentsCapacityAlreadySet_IntakeFillsTheCapacity_ReturnMaxCapacity(){
        traineeCentre.setNumberOfTrainees(70);
        traineeCentre.traineeIntake(50);
        assertEquals(100,traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void traineeIntake_CurrentCapacityAlreadySet_IntakeExceedsMaxCapacity_ReturnToWaitingListEqualsExcess() {
        traineeCentre.setNumberOfTrainees(70);
        traineeCentre.traineeIntake(50);
        assertEquals(20, traineeCentre.getReturnToWaitingList());
    }

    @Test
    public void traineeIntake_ZeroTraineeIntake_CurrentCapacityDoesNotChange(){
        traineeCentre.setNumberOfTrainees(50);
        traineeCentre.traineeIntake(0);
        assertEquals(50,traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void traineeIntake_ZeroTraineeIntake_ReturnToWaitingListDoesNotChange(){
        traineeCentre.setReturnToWaitingList(50);
        traineeCentre.traineeIntake(-10);
        assertEquals(50, traineeCentre.getReturnToWaitingList());
    }

    @Test
    public void traineeIntake_NegativeTraineeIntake_CurrentCapacityDoesNotChange() {
        traineeCentre.setNumberOfTrainees(50);
        traineeCentre.traineeIntake(-10);
        assertEquals(50, traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void traineeIntake_NegativeTraineeIntake_ReturnToWaitingListDoesNotChange() {
        traineeCentre.setReturnToWaitingList(50);
        traineeCentre.traineeIntake(-10);
        assertEquals(50, traineeCentre.getReturnToWaitingList());
    }

    // testing setCurrentCapacity()
    @Test
    public void setCurrentCapacity_PositiveInputLessThanMax_GetCurrentCapacityShouldBeTheSame(){
        traineeCentre.setNumberOfTrainees(70);
        assertEquals(70,traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void setCurrentCapacity_PositiveInputExactlyMax_GetCurrentCapacityIsMaxCapacity() {
        traineeCentre.setNumberOfTrainees(100);
        assertEquals(100, traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void setCurrentCapacity_InputIsZero_GetCurrentCapacityIsZero() {
        traineeCentre.setNumberOfTrainees(0);
        assertEquals(0, traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void setCurrentCapacity_NegativeInput_GetCurrentCapacityIsZero(){
        traineeCentre.setNumberOfTrainees(-10);
        assertEquals(0,traineeCentre.getNumberOfTrainees());
    }

    @Test
    public void setCurrentCapacity_ExceedsMaxCapacity_GetCurrentCapacityIsMaxCapacity(){
        traineeCentre.setNumberOfTrainees(105);
        assertEquals(100,traineeCentre.getNumberOfTrainees());
    }

}
