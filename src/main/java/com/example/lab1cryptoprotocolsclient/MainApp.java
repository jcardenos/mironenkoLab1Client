package com.example.lab1cryptoprotocolsclient;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Показать окно с вводом имени пользователя
        UIManager.showInputUserName();
        primaryStage.setScene(UIManager.getChatScene(primaryStage));
    }

    public static void main(String[] args) {
        launch(args);
    }
}