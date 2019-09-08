package com.ksr.summerproject.server.exceptions;

public class ClientHasNoReservationException extends Exception {
    public ClientHasNoReservationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
