package com.webstore.services;

public class ClientAlreadyExistsException extends Throwable {
    public ClientAlreadyExistsException(){
        super("Email, CPF or name already exists");
    }
}
