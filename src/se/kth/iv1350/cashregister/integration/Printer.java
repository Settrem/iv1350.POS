package se.kth.iv1350.cashregister.integration;

/**
 * This class represents a printer used to print receipts.
 * 
 * In this simplified version, it outputs the receipt to the system console.
 */
public class Printer {
    
    /**
     * Prints the given receipt.
     * In this implementation, it prints the receipt text to the console.
     * 
     * @param receipt The receipt content to print.
     */
    public void printSale(String receipt){
        System.out.println(receipt);
    }

}
