package se.kth.iv1350.cashregister.controller;

import se.kth.iv1350.cashregister.integration.RegHandler;
import java.util.ArrayList;

import se.kth.iv1350.cashregister.model.CartItem;
import se.kth.iv1350.cashregister.model.RevenueObserver;
import se.kth.iv1350.cashregister.model.Sale;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.integration.FailureToReachDataBaseException;
import se.kth.iv1350.cashregister.integration.Printer;
import se.kth.iv1350.cashregister.util.*;

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
    private final Logger logger;
    private static final double ÖRE_TO_SEK = 0.01;

    private ArrayList<RevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Initializes the entire model of the system that controls the entire sale
     * 
     * By creating {@code Printer} and {@code RegHandler} objects to be able to
     * reach the integration parts of the aplications
     */
    public Controller(Logger logger, RegHandler regHandler) {
        this.logger = logger;
        this.regHandler = regHandler;
        this.printerMachine = new Printer();
    }

    /**
     * Starts a new sale and initializes the {@code Sale} object.
     *
     * @return A confirmation message indicating the sale has started.
     */
    public void startSale() {
        this.currentSale = new Sale();
    }

    /**
     * Searches for an item using the given item ID via the registry handler.
     * 
     * If no sale has been started, a new sale is created.
     * If the item is found, it is added to the current sale.
     *
     * @param itemID The identification number of the item.
     * @return The corresponding {@code ItemDTO}.
     * @throws NoItemFoundException    if itemID isn't found
     * @throws NetworkFailureException if enterItem fails to connect to database
     */
    public ItemDTO enterItem(int itemID) throws NoItemFoundException, NetworkFailureException {
    try {
        ItemDTO itemDTO = this.regHandler.getItem(itemID);

        if (this.currentSale == null) {
            this.startSale();
        }

        currentSale.addItem(itemDTO);
        return itemDTO;

    } catch (NoItemFoundException e) {
        logger.log("Exception: " + e.getMessage());
        throw e;

    } catch (FailureToReachDataBaseException e) {
        logger.log("Exception: " + e.getMessage());
        throw new NetworkFailureException(e.getMessage());
    }
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
     * Otherwise, it records the sale, prints the receipt, and ends the current
     * sale.
     *
     * @param paidAmount The amount paid by the customer.
     * @return A message indicating the result of the operation.
     * @throws InsufficientPaymentException If payment fails.
     */
    public void endSale(int paidAmount) throws InsufficientPaymentException {
        if (!enoughMoney(paidAmount)) {
            InsufficientPaymentException e = new InsufficientPaymentException(
                    "Insufficient payment: paid " + paidAmount * ÖRE_TO_SEK +
                            ", total is: " + currentSale.getTotal() * ÖRE_TO_SEK);
            logger.log("Exception: " + e.getMessage());
            throw e;
        }

        String receipt = printReceipt(paidAmount);
        this.printerMachine.printSale(receipt);
        this.notifyObservers();

        currentSale = null;
    }

    /**
     * Checks if enough money was entered at end of sale
     * 
     * @return whether or not the amount was enough
     */
    public boolean enoughMoney(int paidAmount) {
        return (paidAmount >= currentSale.getTotal());
    }

    /**
     * enters the current sale into the accounting registry.
     * 
     * @return the result of the operation, 0 if concluded
     */
    public boolean accountSale() {
        return regHandler.accountSale(this.getSale()) == 0;
    }

    /**
     * Returns the item cart as a Array List
     * 
     * @return the item cart
     */
    public ArrayList<CartItem> getCart() {
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

    /**
     * 
     * Notifies all registered revenue observers about the revenue
     * generated from the current sale by passing the total sale amount.
     */
    private void notifyObservers() {
        revenueObservers.forEach(RevenueObserver -> RevenueObserver.updateRevenue(currentSale.getTotal()));
    }

    /**
     * 
     * Adds a revenue observer to the list of observers that should be notified
     * whenever a sale is completed.*
     * 
     * @param obs The revenue observer to be added.
     **/
    public void addRevenueObserver(RevenueObserver obs) {
        revenueObservers.add(obs);
    }

    public String getAccountedSale(int index) {
        return regHandler.getAccountedSale(index).getReceipt(1000);
    }
}
