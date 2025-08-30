package com.java.chatui;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class OneOneChat {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started. Waiting for client...");

            try (Socket socket = serverSocket.accept();
                 DataInputStream dis = new DataInputStream(socket.getInputStream());
                 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("Client connected.");

                // Thread to read messages from client
                Thread readThread = new Thread(() -> {
                    try {
                        String msg;
                        while (true) {
                            msg = dis.readUTF();
                            System.out.println("Client: " + msg);
                            if (msg.equalsIgnoreCase("exit")) {
                                System.out.println("Client disconnected.");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Connection closed.");
                    }
                });

                readThread.start();

                // Main thread to send messages to client
                String msg;
                while (true) {
                    msg = scanner.nextLine();
                    dos.writeUTF(msg);
                    dos.flush();
                    if (msg.equalsIgnoreCase("exit")) {
                        System.out.println("Server terminated the chat.");
                        break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

