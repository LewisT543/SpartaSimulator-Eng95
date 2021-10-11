module com.sparta.spartasimulatoreng95 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.sparta.spartasimulatoreng95 to javafx.fxml, javafx.graphics;
    exports com.sparta.spartasimulatoreng95;

}