package com.sparta.simulation;

import com.sparta.simulation.view.SimulationCLIView;

public class App {
    public static void main(String[] args) {
        SimulationCLIView view = new SimulationCLIView();
        view.displayWelcomeBanner();
        int input = view.getIntegerInput(0,100,":)");

    }
}
