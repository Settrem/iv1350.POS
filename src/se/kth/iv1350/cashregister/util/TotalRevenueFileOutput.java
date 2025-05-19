package se.kth.iv1350.cashregister.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.cashregister.model.RevenueObserver;

public class TotalRevenueFileOutput implements RevenueObserver {
    private PrintWriter logStream;
    private int totalOfSales = 0;
    private static final double ÖRE_TO_SEK = 0.01;

    /**
     * Creates a new instance and starts a fresh file.
     */
    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("src/log/TotalRevenueLog.txt", false), true);
            logStream.println("Program Start");
        } catch (IOException ioe) {
            System.out.println("CAN NOT OPEN LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Updates revenue and logs the new total.
     *
     * @param amount The amount of the current sale (in sek).
     */
    @Override
    public void updateRevenue(int amount){
        this.totalOfSales += amount;
        printTotalRevenue();
    }

    /**
     * Prints the current total revenue to the log file.
     */
    public void printTotalRevenue() {
        logStream.printf("Total Revenue: %.2f SEK\n", totalOfSales * ÖRE_TO_SEK);
    }
}
