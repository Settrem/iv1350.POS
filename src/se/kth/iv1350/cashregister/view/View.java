package se.kth.iv1350.cashregister.view;

import java.util.ArrayList;
import java.util.Scanner;
import se.kth.iv1350.cashregister.controller.Controller;
import se.kth.iv1350.cashregister.model.CartItem;
import se.kth.iv1350.cashregister.dto.ItemDTO;

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
    private TotalRevenueView revenueView = new TotalRevenueView();
    private static final int SEK_TO_ÖRE = 100;
    private static final double ÖRE_TO_SEK = 0.01;


    /**
     * Creates a new instance of the view and connects it to the system controller.
     *
     * @param controller The controller that manages the application's logic and data.
     */
    public View(Controller controller) {
        this.controller = controller;
        this.controller.addRevenueObserver(revenueView);
    }

     /**
     * Starts the user interface loop for handling sales. Prompts the user to input
     * item IDs or the keyword "endsale" to complete the sale. Displays cart contents
     * after each item is scanned.
     */
    public void userInterface() {
        while (running) {
            this.revenueView.printRevenue();
            System.out.println("Enter Item-ID (1-20)");
            if (controller.getSale() != null) {
                System.out.println("Type 'endsale' to end current sale");
            };
            
            String input = myScanner.next();

            if (input.equalsIgnoreCase("endsale") && controller.getSale() != null) {
                System.out.println(this.endSale(enterPayment()));
                
            } else {
                scanItem(input);      
            }
            System.out.println(this.displayCart());
            
        }
        
    }

        /**
     * Displays the contents of the current shopping cart.
     * Shows item names, amounts, and total price including VAT.
     * If no sale has started, prompts the user to scan an item.
     */
    private String displayCart() {
        int totalPrice = 0;
        if (controller.getSale() == null) {
            return("Scan item to start sale");
        }
        String cartString = "";
        ArrayList<CartItem> itemCart = controller.getCart();
        cartString += "Cart: \n -----------\n";
        for (int i = 0; i < itemCart.size(); i++) {
            CartItem item = itemCart.get(i);

            totalPrice += item.getPriceWithVAT();
            cartString += item.toString();
        }
        cartString += "\n" + Math.round(totalPrice * ÖRE_TO_SEK * 100.0) / 100.0 + "kr \n ----------";
        return cartString;
    }

    /**
     * Attempts to parse and add an item to the current sale based on user input.
     * Prints an error message if the input is not a valid number.
     *
     * @param input The user-provided input, expected to be a numeric item ID.
     * @return The value of endSale, which indicates whether a sale-ending condition has occurred.
     */
    private boolean scanItem(String input) {
        try {
            int itemID = Integer.parseInt(input);
            System.out.println(this.addItem(itemID));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter a number.");
        }
        return endSale;
    }

    /**
     * Tries to add an item to the sale using the provided item ID.
     * Displays a message if the item is not found or confirms addition.
     *
     * @param itemID The identifier of the item to add.
     */
    private String addItem(int itemID) {
        ItemDTO newestItem = controller.enterItem(itemID);
        if (newestItem == null) {
            return("Item not found!\n");
        } else {
            return(newestItem.getName() + " was added to cart\n");
        }
    }

    /**
     * Finalizes the current sale by processing payment and generating a receipt.
     * 
     * If the paid amount is insufficient, it returns an error message.
     * Otherwise, it records the sale, prints the receipt, and ends the current sale.
     *
     * @param paidAmount The amount paid by the customer (in öre).
     * @return A message indicating the result of the operation.
     */
    private String endSale(int paidAmount) {

        if (controller.enoughMoney(paidAmount)){
            return("Customer did not provide enough cash, please try again.");
        }

        if (!controller.accountSale()) {
            return("Error occured while accounting sale!");
        }

        controller.printReceipt(paidAmount);
        controller.endSale(paidAmount);
        return("Sale ended successfully!");
    }

    /**
     * Ends the current sale by prompting the user to enter a payment amount.
     * Validates the input and forwards the payment to the controller to finalize
     * the transaction. Repeats until a valid numeric input is entered.
     */
    private int enterPayment() {
        int paidAmount = -1;
        boolean complete = false;
        while (complete == false){
            System.out.println("Enter paid amount");
            String input = myScanner.next();
            try {
                paidAmount = Integer.parseInt(input) * SEK_TO_ÖRE;
                complete = true;
                
            } catch (NumberFormatException e) {
                System.out.println("Please input a number.");
            }
        }
    return(paidAmount);
    }
}


