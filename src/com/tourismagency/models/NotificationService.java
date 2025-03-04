package com.tourismagency.models;

public abstract class NotificationService {

    protected String serviceName;

    public NotificationService(String serviceName) {
        this.serviceName = serviceName;
    }

    public abstract void sendNotification(String message);

    @Override
    public String toString() {
        return "NotificationService{name='" + serviceName + "'}";
    }
}