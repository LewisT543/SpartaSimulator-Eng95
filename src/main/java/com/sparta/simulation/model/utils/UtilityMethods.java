package com.sparta.simulation.model.utils;

import java.util.Random;

public class UtilityMethods {
        public static int generateRandomInt(int lowerBound, int upperBound, Long seed) {
            Random rn = (seed==null) ? new Random(): new Random(seed);
            return rn.nextInt(upperBound-lowerBound) + lowerBound;
        }
}
