package com.sparta.simulation;

import com.sparta.simulation.controller.SimulatorController;
import com.sparta.simulation.view.SimulationCLIView;

public class App {
    public static void main(String[] args) {
        SimulationCLIView.displayWelcomeBanner();
        SimulatorController controller = new SimulatorController();
        controller.runSim();
    }
}
