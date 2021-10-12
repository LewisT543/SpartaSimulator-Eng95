module com.sparta.spartasimulatoreng95 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.sparta.spartasimulatoreng95 to javafx.fxml, javafx.graphics;
    exports com.sparta.spartasimulatoreng95;
    exports com.sparta.simulation.controller;
    opens com.sparta.simulation.controller to javafx.fxml, javafx.graphics;

}