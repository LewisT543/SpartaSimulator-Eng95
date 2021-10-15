package com.sparta.simulation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientShould {

    private Client client;
    @BeforeEach
    public void setUp() {
        client = new Client(1, Simulation.Courses.DATA, 20);
    }

    @Test
    void updateHappiness_GivenExistedFor0MonthsAnd0TraineesRecruited_ReturnTrue() {
        client.setExistedFor(0);
        client.setIntakeAmtThisYear(0);
        client.updateHappiness();
        assertTrue(client.isHappy());
    }

    @Test
    void updateHappiness_GivenExistedFor11MonthsAnd0TraineesRecruited_ReturnTrue() {
        client.setExistedFor(11);
        client.setIntakeAmtThisYear(0);
        client.updateHappiness();
        assertTrue(client.isHappy());
    }

    @Test
    void updateHappiness_GivenExistedFor12MonthsAnd0TraineesRecruited_ReturnFalse() {
        client.setExistedFor(12);
        client.setIntakeAmtThisYear(0);
        client.updateHappiness();
        assertFalse(client.isHappy());
    }

    @Test
    void updateHappiness_GivenExistedFor13MonthsAnd0TraineesRecruited_ReturnFalse() {
        client.setExistedFor(13);
        client.setIntakeAmtThisYear(0);
        client.updateHappiness();
        assertFalse(client.isHappy());
    }

    @Test
    void updateHappiness_GivenExistedFor13MonthsAnd19TraineesRecruited_ReturnFalse() {
        client.setExistedFor(13);
        client.setIntakeAmtThisYear(19);
        client.updateHappiness();
        assertFalse(client.isHappy());
    }

    @Test
    void updateHappiness_GivenExistedFor13MonthsAnd20TraineesRecruited_ReturnTrue() {
        client.setExistedFor(13);
        client.setIntakeAmtThisYear(20);
        client.updateHappiness();
        assertTrue(client.isHappy());
    }

    @Test
    void updateHappiness_GivenExistedFor13MonthsAnd21TraineesRecruited_ReturnTrue() {
        client.setExistedFor(13);
        client.setIntakeAmtThisYear(21);
        client.updateHappiness();
        assertTrue(client.isHappy());
    }

}