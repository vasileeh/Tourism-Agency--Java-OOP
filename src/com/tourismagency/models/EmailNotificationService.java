package com.tourismagency.models;

public class EmailNotificationService extends NotificationService {

    public EmailNotificationService(String serviceName) {
        super(serviceName);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[" + serviceName + "] Sending email notification: " + message);
    }

    public void sendEmail(String recipient, String message) {
        System.out.println("Trimitere email catre " + recipient + ": " + message);
    }
}