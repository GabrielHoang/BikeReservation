package com.ksr.summerproject.server.exceptions;

public class BicycleNotFoundException extends Exception {

    public BicycleNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
