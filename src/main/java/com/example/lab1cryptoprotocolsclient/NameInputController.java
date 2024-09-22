package com.example.lab1cryptoprotocolsclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameInputController {

    @FXML
    private TextField nameField;
    @FXML
    private Button okButton;

    private Stage dialogStage;

    private String userName;

    @FXML
    private void initialize() {
        okButton.setOnAction(event -> handleOkButtonAction());
    }

    @FXML
    private void handleOkAction() {
        userName = nameField.getText();
        if (userName == null || userName.trim().isEmpty()) {
            nameField.setStyle("-fx-border-color: red;");
            return;
        }
        dialogStage.close();
    }

    private void handleOkButtonAction() {
        userName = nameField.getText();
        if (userName != null && !userName.trim().isEmpty()) {
            ((Stage) okButton.getScene().getWindow()).close();
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getUserName() {
        return userName;
    }
}
