package se.kth.iv1350.cashregister.util;

/**
 * Prints log messages, while the destination of th elog is chosen by the class
 * using the interface.
 */
public interface Logger {
    /**
     * Logs the specified string.
     * 
     * @param message The string that will be logged
     */
    public void log(String message); 
}
