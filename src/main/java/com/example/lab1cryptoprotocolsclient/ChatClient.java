package com.example.lab1cryptoprotocolsclient;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {

    private final String host;
    private final int port;
    private final String userName;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final MessageHandler messageHandler;
    private boolean joined = false; // Флаг для отслеживания присоединения

    private static final String ENCODING = StandardCharsets.UTF_8.name();

    public ChatClient(String host, int port, String userName, MessageHandler messageHandler) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.messageHandler = messageHandler;
    }

    public void connect() {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING), true);

            // Отправляем сообщение о присоединении только один раз
            if (!joined) {
                out.println(userName + " присоединился к чату.");
                joined = true;
            }

            // Запуск потока для получения сообщений от сервера
            new Thread(this::receiveMessages).start();
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                // Отправляем сообщение о выходе клиента только один раз
                if (joined) {
                    out.println(userName + " покинул чат.");
                }
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }

    /*public boolean isConnected() {
        return socket != null && !socket.isClosed();
    }*/

    public void sendMessage(String message) {
        if (out != null) {
            out.println(userName + ": " + message);
        }
    }

    private void receiveMessages() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                messageHandler.handleMessage(message);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при получении сообщений: " + e.getMessage());
        }
    }
}