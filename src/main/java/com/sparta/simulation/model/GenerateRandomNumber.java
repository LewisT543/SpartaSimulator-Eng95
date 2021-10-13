package com.sparta.simulation.model;

import java.util.Random;

public class GenerateRandomNumber {
    public static int generateRandomIntNumber(int lowerBound, int upperBound, Long seed) {
        Random rn = (seed == null) ? new Random() : new Random(seed);
        return rn.nextInt(upperBound - lowerBound) + lowerBound;
    }
}