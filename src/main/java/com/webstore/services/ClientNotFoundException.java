package com.webstore.services;

public class ClientNotFoundException extends Throwable {

    public ClientNotFoundException(String message) {
        super(message);
    }
}
