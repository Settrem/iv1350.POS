package se.kth.iv1350.cashregister.controller;

import java.io.IOException;


/**
 * Thrown when the system fails find a item,
 * such as when the database server is down.
 */
public class NetworkFailureException extends RuntimeException {

    /**
     * Creates a new instance with a specified message.
     *
     * @param message The detail message describing what went wrong.
     */
    public NetworkFailureException(String message) {
        super(message);
    }

}
