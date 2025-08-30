package com.java.chatui;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class chatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server.");

            // Thread to read messages from server
            Thread readThread = new Thread(() -> {
                try {
                    String msg;
                    while (true) {
                        msg = dis.readUTF();
                        System.out.println("Server: " + msg);
                        if (msg.equalsIgnoreCase("exit")) {
                            System.out.println("Server terminated the chat.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });

            readThread.start();

            // Main thread to send messages to server
            String msg;
            while (true) {
                msg = scanner.nextLine();
                dos.writeUTF(msg);
                dos.flush();
                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("You terminated the chat.");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
