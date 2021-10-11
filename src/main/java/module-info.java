module com.example.spartasimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sparta.simulation to javafx.fxml;
    exports com.sparta.simulation;
    exports com.sparta.simulation.controller;
    opens com.sparta.simulation.controller to javafx.fxml;
}