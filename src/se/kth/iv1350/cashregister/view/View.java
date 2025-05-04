package se.kth.iv1350.cashregister.view;

import java.util.Scanner;

import se.kth.iv1350.cashregister.controller.Controller;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.model.Sale;

/**
 * This class represents a simple user interface for the cash register system.
 * It interacts with the {@code Controller} to simulate scanning items, displaying
 * the cart, and completing a sale through hardcoded test cases.
 */
public class View {
    private Controller controller;
    private Scanner myScanner = new Scanner(System.in);
    private boolean endSale = false;
    private ItemDTO newestItem = null;
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
     * Loops current sale until "endsale" is read as input
     */
    public void userInterface() {
        while (running) {
            System.out.println("Enter Item-ID (1-10)");
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
     * This function takes payment and confirms if it covers the total price.
     * @return payedAmount 
     */
    private int receivePayment(int payedAmount) {
        System.out.println(controller.endSale(payedAmount));
    
        return payedAmount;
    }

    /**
     * Creates a new sale.
     * 
     * @return Tells if method was successful (1) or unsuccesful (0)
     */
    public int startSale() {
        System.out.println(this.controller.startSale());
        return 1;
    }

    /**
     * Scans user input for itemID to add to cart.
     * Scans user input for 'endsale' to end current sale.
     * @return endSale has been called and the sale is moved to the endSale process
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
