package se.kth.iv1350.cashregister.integration;

import java.io.FileNotFoundException;

/**
 * Thrown when the system fails to reach the database,
 * such as when the database server is down.
 */
public class FailureToReachDataBaseException extends FileNotFoundException {

    /**
     * Creates a new instance with a specified message.
     *
     * @param message The detail message.
     */
    public FailureToReachDataBaseException(String message) {
        super(message);
    }
}

