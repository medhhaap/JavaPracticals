package com.java.emailsender.ui;

import javax.swing.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailsSender extends JFrame {
    JTextField sender, receiver, subject;
    JTextArea message;
    JButton send;

    EmailsSender() {
        setTitle("Email Sender");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Sender:");
        l1.setBounds(20, 20, 80, 20);
        add(l1);

        JLabel l2 = new JLabel("Receiver:");
        l2.setBounds(20, 50, 80, 20);
        add(l2);

        JLabel l3 = new JLabel("Subject:");
        l3.setBounds(20, 80, 80, 20);
        add(l3);

        JLabel l4 = new JLabel("Message:");
        l4.setBounds(20, 110, 80, 20);
        add(l4);

        sender = new JTextField();
        sender.setBounds(100, 20, 250, 20);
        add(sender);

        receiver = new JTextField();
        receiver.setBounds(100, 50, 250, 20);
        add(receiver);

        subject = new JTextField();
        subject.setBounds(100, 80, 250, 20);
        add(subject);

        message = new JTextArea();
        message.setBounds(100, 110, 250, 100);
        add(message);

        send = new JButton("Send");
        send.setBounds(150, 220, 100, 30);
        add(send);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");


                    Session session = Session.getInstance(props, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("sujita.ghlan34@gmail.com", "sulm wpxh wmys rcko");
                        }
                    });

                    Message msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress(sender.getText()));
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver.getText()));
                    msg.setSubject(subject.getText());
                    msg.setText(message.getText());

                    Transport.send(msg);

                    JOptionPane.showMessageDialog(null, "Email Sent!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new EmailsSender();
    }
}
