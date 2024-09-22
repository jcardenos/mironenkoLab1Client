package com.example.lab1cryptoprotocolsclient;

@FunctionalInterface
public interface MessageHandler {
    void handleMessage(String message);
}
