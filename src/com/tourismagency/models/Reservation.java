package com.tourismagency.models;

import com.tourismagency.interfaces.Cancellable;

public class Reservation implements Cancellable {
    private int id;
    private int clientId;
    private int packageId;

    public Reservation(int id, int clientId, int packageId) {
        this.id = id;
        this.clientId = clientId;
        this.packageId = packageId;
    }

    @Override
    public void cancelReservation(int reservationId) {
        System.out.println("Reservation with ID " + reservationId + " has been cancelled.");
    }
}
