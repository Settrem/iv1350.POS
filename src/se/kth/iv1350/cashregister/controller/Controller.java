package se.kth.iv1350.cashregister.controller;

import se.kth.iv1350.cashregister.integration.RegHandler;

import java.util.ArrayList;

import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.model.Sale;
import se.kth.iv1350.cashregister.integration.Printer;

/**
 * The <code>Controller</code> class acts as the intermediary between the view and the model
 * in application.
 * 
 * It coordinates operations such as starting a new sale, adding items,
 * ending the sale, and generating receipts.
 *
 * This class communicates with the {@code Sale}, {@code Printer}, and {@code RegHandler}
 * components of the model, and exposes methods that the view can use to perform user actions.
 */
public class Controller {
    private Sale currentSale;
    private RegHandler regHandler;
    private Printer printerMachine;

    /**
     * Initializes the entire model of the system that controls the entire sale
     * 
     * By creating {@code Printer} and {@code RegHandler} objects to be able to reach
     * the integration parts of the aplications. 
     */
    public Controller () {
        this.regHandler = new RegHandler();
        this.printerMachine = new Printer();
    }

    /**
     * Creates a new sale
     * 
     * @return Tells if method was successful (1) or unsuccesful (0) 
     */
    public int startSale() {
        this.currentSale = new Sale();
        System.out.println("----Started New Sale----");
        return 1; 
    }

    /**
     * Search for a Item matching the specified criteria
     * @param itemID is the idetification used to search for a specific item
     * @return Will return a Item as a Item Data transfer objekt
     */
    public ItemDTO enterItem(int itemID){

        ItemDTO itemDTO = this.regHandler.getItem(itemID);
        if (itemDTO == null) {
            return itemDTO; //returns null to view if failed
        }

        if (this.currentSale == null) {
            this.startSale();
        }
        
        currentSale.addItem(itemDTO);
        return itemDTO;
    }

    /**
     * Gets the current sale information from the
     * Sale class and returns the it.
     * @return the current sale
     */
    public Sale getSale() {
        return currentSale;
    }

    /**
     * Runs when ending the sale from view, and tells the
     * Sale to end the currentsale while giving it the payed amount
     * and returns the receipt.
     * 
     * @param cash is payed amount
     * @return returns the receipt of the sale
     */
    public void endSale(int cash) {
        String receipt = printReceipt(cash);
        if (regHandler.accountSale(this.getSale()) != 0) {//Breaks gdpr maybe
            System.out.println("Error occured while accounting sale!");
        } 
        currentSale = null;
        this.printerMachine.printSale(receipt);
    }

    /**
     * Returns the item cart as a Array List
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
