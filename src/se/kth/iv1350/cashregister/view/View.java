package se.kth.iv1350.cashregister.view;

import se.kth.iv1350.cashregister.controller.Controller;
import java.util.ArrayList;
import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;

/**
 * This class represents a simple user interface for the cash register system.
 * It interacts with the {@code Controller} to simulate scanning items, displaying
 * the cart, and completing a sale through hardcoded test cases.
 */
public class View {
    private ItemDTO newestItem = null;
    private Controller controller;

    /**
     * Creates a new instance of the view and connects it to the system controller.
     *
     * @param controller The controller that manages the application's logic and data.
     */
    public View(Controller controller) {
        this.controller = new Controller();
    }

    /**
     * Runs a sequence of hardcoded method calls to simulate a typical sale
     * and demonstrate system functionality. Includes scanning items and ending
     * the sale.
     */
    public void test() {
        displayCart(); //cart has not been created yet, should print "Scan item to start sale"
        addItem(16); //item that does not exist, should print "Item not found!"
        displayCart(); //cart has not been created yet, should print "Scan item to start sale"
        addItem(1); //item that exists, should print "Köttbullar was added to cart"
        displayCart(); //previous item was successfully scanned and cart has been created, should print relevant details.
        addItem(1); //should print identical to two rows above
        displayCart(); //There should now be two instances of köttbullar
        addItem(4); //sould print "Gravad lax has been added"
        displayCart(); //Should display two instances of köttbullar and one of gravad lax
        endSale(50000);
    }

    /**
     * Tries to add an item to the sale using the provided item ID.
     * Displays a message if the item is not found or confirms addition.
     *
     * @param itemID The identifier of the item to add.
     */
    private void addItem(int itemID) {
        this.newestItem = this.controller.enterItem(itemID);
        if (this.newestItem == null) {
            System.out.println("Item not found!");
        }
        else {
            System.out.println(newestItem.getName() + " was added to cart");
        }
    }

    /**
     * Displays the contents of the current shopping cart.
     * Shows item names, amounts, and total price including VAT.
     * If no sale has started, prompts the user to scan an item.
     */
    private void displayCart() {
        int totalPrice = 0;
        if (controller.getSale() == null) {
            System.out.println("Scan item to start sale");
            return;
        }
        else {
            ArrayList<CartItemDTO> itemCart = this.controller.getCart();
            System.out.println("Cart: \n -----------");
            for(int i = 0; i < itemCart.size(); i++) {
                CartItemDTO item = itemCart.get(i);
                String name = item.itemDTO.getName();
                int amount = item.getAmount();
                double price = item.itemDTO.getPriceWithVAT();

                totalPrice += item.itemDTO.getPriceWithVAT() * item.getAmount();
                System.out.println(name + "  x" + amount + "  " + (amount * price / 100.0) + "kr");
            }
            System.out.println("\n" + totalPrice/100.0 + "kr");
        }
    }

     /**
     * Ends the current sale and returns a receipt.
     *
     * @param paymentAmount The amount of cash paid by the customer (in öre).
     * @return A string representation of the printed receipt.
     */
    private void endSale(int paymentAmount) { // change to PaymentAmount class when added, or remain int/double if omitted
        controller.endSale(paymentAmount);
        System.out.println("Sale has ended");
    }
}

