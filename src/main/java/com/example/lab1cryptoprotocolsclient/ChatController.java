package com.example.lab1cryptoprotocolsclient;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField messageField;
    @FXML
    private TextArea chatArea;
    @FXML
    private Button sendButton;

    private ChatClient chatClient;

    private String userName;

    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
    }

    public void setUserName(String userName) {
        this.userName = userName;
        if (chatClient == null) {
            chatClient = new ChatClient("localhost", 12345, userName, this::onMessageReceived);
            chatClient.connect();
        }
    }

    private void sendMessage() {
        String message = messageField.getText();

        if (message.isEmpty() || userName.isEmpty()) {
            chatArea.appendText("Пожалуйста, введите сообщение.\n");
            return;
        }

        chatClient.sendMessage(message);
        messageField.clear();
    }

    private void onMessageReceived(String message) {
        Platform.runLater(() -> chatArea.appendText(message + "\n"));
    }

    public void closeConnection() {
        if (chatClient != null) {
            chatClient.disconnect();
        }
    }
}