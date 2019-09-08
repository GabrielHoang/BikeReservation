package com.ksr.summerproject.server.exceptions;

public class ClientAlreadyHasReservation extends Exception {
    public ClientAlreadyHasReservation(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
