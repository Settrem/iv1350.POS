package se.kth.iv1350.cashregister.controller;

import se.kth.iv1350.cashregister.integration.RegHandler;
import java.util.ArrayList;

import se.kth.iv1350.cashregister.model.ItemCart;
import se.kth.iv1350.cashregister.model.Sale;
import se.kth.iv1350.cashregister.dto.CartItemDTO;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.integration.Printer;

/**
 * The <code>Controller</code> class acts as the intermediary between the view
 * and the model
 * in application.
 * 
 * It coordinates operations such as starting a new sale, adding items,
 * ending the sale, and generating receipts.
 *
 * This class communicates with the {@code Sale}, {@code Printer}, and
 * {@code RegHandler}
 * components of the model, and exposes methods that the view can use to perform
 * user actions.
 */
public class Controller {
    private Sale currentSale;
    private RegHandler regHandler;
    private Printer printerMachine;
    private static final double ÖRE_TO_SEK = 0.01;
    

    /**
     * Initializes the entire model of the system that controls the entire sale
     * 
     * By creating {@code Printer} and {@code RegHandler} objects to be able to
     * reach the integration parts of the aplications
     */
    public Controller() {
        this.regHandler = new RegHandler();
        this.printerMachine = new Printer();
    }

    /**
     * Starts a new sale and initializes the {@code Sale} object.
     *
     * @return A confirmation message indicating the sale has started.
     */
    public String startSale() {
        this.currentSale = new Sale();
        return "----Started New Sale----";
    }

    /**
     * Displays the contents of the current shopping cart.
     * Shows item names, amounts, and total price including VAT.
     * If no sale has started, prompts the user to scan an item.
     */
    public String displayCart() {
        int totalPrice = 0;
        if (getSale() == null) {
            return("Scan item to start sale");
        }
        String cartString = "";
        ArrayList<CartItemDTO> itemCart = this.getCart();
        cartString += "Cart: \n -----------\n";
        for (int i = 0; i < itemCart.size(); i++) {
            CartItemDTO item = itemCart.get(i);

            totalPrice += item.getPriceWithVAT();
            cartString += item.toString();
        }
        cartString += "\n" + totalPrice * ÖRE_TO_SEK + "kr \n ----------";
        return cartString;
    }
    

    /**
     * Tries to add an item to the sale using the provided item ID.
     * Displays a message if the item is not found or confirms addition.
     *
     * @param itemID The identifier of the item to add.
     */
    public String addItem(int itemID) {
        ItemDTO newestItem = this.enterItem(itemID);
        if (newestItem == null) {
            return("Item not found!\n");
        } else {
            return(newestItem.getName() + " was added to cart\n");
        }
    }

    /**
     * Searches for an item using the given item ID via the registry handler.
     * 
     * If no sale has been started, a new sale is created.
     * If the item is found, it is added to the current sale.
     *
     * @param itemID The identification number of the item.
     * @return The corresponding {@code ItemDTO}, or {@code null} if not found.
     */
    public ItemDTO enterItem(int itemID) {

        ItemDTO itemDTO = this.regHandler.getItem(itemID);
        if (itemDTO == null) {
            return itemDTO;
        }

        if (this.currentSale == null) {
            this.startSale();
        }

        currentSale.addItem(itemDTO);
        return itemDTO;
    }

    /**
     * Gets the current sale information from the
     * Sale class and returns it.
     * 
     * @return the current sale
     */
    public Sale getSale() {
        return currentSale;
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
    public String endSale(int payedAmount) {

        if (payedAmount < currentSale.getTotal()){
            return("Customer did not provide enough cash, please try again.");
        }

        String receipt = printReceipt(payedAmount);
        if (regHandler.accountSale(this.getSale()) != 0) {
            return("Error occured while accounting sale!");
        }
        currentSale = null;
        this.printerMachine.printSale(receipt);
        return("Sale ended successfully!");
    }

    /**
     * Returns the item cart as a Array List
     * 
     * @return the item cart
     */
    public ArrayList<CartItemDTO> getCart() {
        return this.currentSale.itemCart.getCart();
    }

    /**
     * Calls for the sale objekt to get and return the
     * receipt of the current sale
     * 
     * @param cash is the payed amount
     * @return the receipt that represents
     *         the sale.
     */
    public String printReceipt(int cash) {
        return currentSale.getReceipt(cash);
    }

}
