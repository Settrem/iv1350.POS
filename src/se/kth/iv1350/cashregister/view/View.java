package se.kth.iv1350.cashregister.view;

import java.util.Scanner;
import se.kth.iv1350.cashregister.controller.Controller;

/**
 * This class represents a simple user interface for the cash register system.
 * It interacts with the {@code Controller} to simulate scanning items, displaying
 * the cart, and completing a sale through hardcoded test cases.
 */
public class View {
    private Controller controller;
    private Scanner myScanner = new Scanner(System.in);
    private boolean endSale = false;
    private boolean running = true;
    private static final int SEK_TO_ÖRE = 100;

    /**
     * Creates a new instance of the view and connects it to the system controller.
     *
     * @param controller The controller that manages the application's logic and data.
     */
    public View(Controller controller) {
        this.controller = new Controller();
    }

     /**
     * Starts the user interface loop for handling sales. Prompts the user to input
     * item IDs or the keyword "endsale" to complete the sale. Displays cart contents
     * after each item is scanned.
     */
    public void userInterface() {
        while (running) {
            System.out.println("Enter Item-ID (1-20)");
            System.out.println("Type 'endsale' to end current sale");

            String input = myScanner.next();

            if (input.equalsIgnoreCase("endsale")) {
                endSale();
                
            } else {
                scanItem(input);
                System.out.println(controller.displayCart());    
            }
            
        }
        
    }

    /**
     * Starts a new sale through the controller and prints a message confirming the sale
     * has started.
     *
     * @return Always returns 1 to indicate success.
     */
    public int startSale() {
        System.out.println(this.controller.startSale());
        return 1;
    }

    /**
     * Attempts to parse and add an item to the current sale based on user input.
     * Prints an error message if the input is not a valid number.
     *
     * @param input The user-provided input, expected to be a numeric item ID.
     * @return The value of endSale, which indicates whether a sale-ending condition has occurred.
     */
    public boolean scanItem(String input) {
        try {
            int itemID = Integer.parseInt(input);
            System.out.println(controller.addItem(itemID));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter a number or 'endsale'.");
        }
        return endSale;
    }

    /**
     * Ends the current sale by prompting the user to enter a payment amount.
     * Validates the input and forwards the payment to the controller to finalize
     * the transaction. Repeats until a valid numeric input is entered.
     */
    public void endSale() {
        boolean complete = false;
        while (complete == false){
            System.out.println("Enter payed amount");
            String input = myScanner.next();
            try {
                int payedAmount = Integer.parseInt(input) * SEK_TO_ÖRE;
                System.out.println(controller.endSale(payedAmount));
                complete = true;
            } catch (NumberFormatException e) {
                System.out.println("Please input a number.");
            }
            
        }
    }
}
