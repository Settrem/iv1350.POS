package se.kth.iv1350.cashregister.model;

import java.util.ArrayList;

import se.kth.iv1350.cashregister.dto.ItemDTO;

/**
 * Represents a sale transaction in the cash register system.
 * 
 * This class is responsible for managing a sale by adding items,
 * calculating the total price and VAT, accepting payments, and printing a
 * receipt.
 */
public class Sale {

    public ItemCart itemCart;

    /**
     * Creates a new sale and initializes the item cart.
     */
    public Sale() {
        this.itemCart = new ItemCart();
    }

    /**
     * Adds an item to the current sale by adding it to the item cart.
     *
     * @param item The item to be added to the sale.
     */
    public void addItem(ItemDTO item) {
        itemCart.addItem(item);
    }

    /**
     * Calculates and returns the change amount based on the paid amount and the
     * total sale price.
     *
     * @param paidAmount The amount of money provided by the customer.
     * @return The difference between the paid amount and the total sale price.
     */
    public int getChange(int paidAmount) {
        return paidAmount - this.getTotal();
    }

    /**
     * Calculates the total price of all items in the sale.
     * 
     * The total is computed by summing the price (including VAT) of each item
     * multiplied
     * by the quantity of that item in the cart.
     *
     * @return The total price of the sale.
     */
    public int getTotal() {
        int totalPrice = 0;
        ArrayList<CartItem> cart = this.itemCart.getCart();
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);

            totalPrice += (int)item.getPriceWithVAT();
        }
        return totalPrice;
    }

    /**
     * Calculates the total VAT for the sale.
     * 
     * the total VAT is calculated by summing up the VAT amount for each item
     * multiplied by the quantity of that item.
     *
     * @return The total VAT for the sale.
     */
    public int getVat() {
        int totalVat = 0;
        ArrayList<CartItem> cart = this.itemCart.getCart();
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);

            totalVat += item.getPriceWithVAT() - item.getPriceBeforeVAT();
        }
        return totalVat;
    }

    /**
     * Generates and returns the receipt for the sale.
     *
     * This method creates a {@code Receipt} based on the current sale details and
     * the cash received,
     * then returns the receipt as a formatted string.
     *
     * @param cash The amount of cash provided by the customer.
     * @return A string representing the formatted receipt.
     */
    public String getReceipt(int cash) {
        Receipt receipt = new Receipt(this, cash);
        return receipt.printReceipt();
    }

}
