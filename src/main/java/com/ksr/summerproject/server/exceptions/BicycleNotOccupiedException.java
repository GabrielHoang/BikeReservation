package com.ksr.summerproject.server.exceptions;

public class BicycleNotOccupiedException extends Exception {
    public BicycleNotOccupiedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
