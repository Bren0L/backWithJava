package com.webstore.services;

public class PaymentNotFoundException extends Throwable {
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
