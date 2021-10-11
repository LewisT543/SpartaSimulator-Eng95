package com.sparta.spartasimulatoreng95;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class definitely_no {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}