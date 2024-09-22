package com.example.lab1cryptoprotocolsclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UIManager {
    private static String userName;

    // Показать окно с вводом имени пользователя перед запуском чата
    public static void showInputUserName() throws IOException {
        FXMLLoader nameInputLoader = new FXMLLoader(UIManager.class.getResource("NameInputView.fxml"));
        Stage nameInputStage = new Stage();
        nameInputStage.setTitle("Введите имя пользователя");
        nameInputStage.initModality(Modality.APPLICATION_MODAL);
        nameInputStage.setScene(new Scene(nameInputLoader.load()));

        NameInputController nameInputController = nameInputLoader.getController();
        nameInputStage.showAndWait();  // Дождитесь, пока пользователь введет имя и закроет окно

        userName = nameInputController.getUserName();

        if (userName == null || userName.trim().isEmpty()) {
            System.out.println("Имя пользователя не введено. Приложение закрывается.");
            System.exit(0);
        }
    }

    public static Scene getChatScene(Stage primaryStage) throws IOException {
        FXMLLoader chatLoader = new FXMLLoader(UIManager.class.getResource("ChatView.fxml"));

        Scene chatScene = new Scene(chatLoader.load());
        ChatController chatController = chatLoader.getController();
        chatController.setUserName(userName);

        primaryStage.setTitle("Чат (" + userName + ")");
        primaryStage.show();
        return chatScene;
    }
}
