package com.ksr.summerproject.server.exceptions;

public class ClientAlreadyExistsException extends Exception {

    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
