package se.kth.iv1350.cashregister.util;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints log messages to a file. The log file will be in the
 * src/lib/log and will be called Exceptionlog.txt.
 */
public class FileLogger implements Logger{
    private PrintWriter logStream;

    /**
     * Creates a new instance and also creates a new log file.
     * An existing log file will be deleted.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(
                new FileWriter("src/log/ExceptionLog.txt", true), true);
                logStream.println("Program Start");
        } catch (IOException ioe) {
            System.out.println("CAN NOT OPEN LOG.");
            ioe.printStackTrace();
        }
    }
 
    /**
     * Prints the specified string to the log file.
     *
     * @param message The string that will be printed to the
     *                log file.
     */
    @Override
    public void log(String message) {
        logStream.println(message);
    }
}
