package se.kth.iv1350.cashregister.controller;

import java.io.IOException;


/**
 * Thrown when not enough cash is provided to end current sale.
 */
public class InsufficientPaymentException extends Exception {

    /**
     * Creates a new instance with a specified message.
     *
     * @param message The detail message describing what went wrong.
     */
    public InsufficientPaymentException(String message) {
        super(message);
    }

}