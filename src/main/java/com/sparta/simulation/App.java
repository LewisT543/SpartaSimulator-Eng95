package com.sparta.simulation;

import com.sparta.simulation.view.SimulationCLIView;

public class App {
    public static void main(String[] args) {
        SimulationCLIView.displayWelcomeBanner();
        int input = SimulationCLIView.getIntegerInput(0,100,":)");

    }
}
