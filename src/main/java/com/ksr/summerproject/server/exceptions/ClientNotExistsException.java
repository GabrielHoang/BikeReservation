package com.ksr.summerproject.server.exceptions;

public class ClientNotExistsException extends Exception {
    public ClientNotExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
