package se.kth.iv1350.cashregister.controller;

import java.io.IOException;


/**
 * Thrown when the system fails find a item,
 * such as when the item entered is not valid.
 */
public class NoItemFoundException extends Exception {

    /**
     * Creates a new instance with a specified message.
     *
     * @param message The detail message describing what went wrong.
     */
    public NoItemFoundException(String message) {
        super(message);
    }

}
