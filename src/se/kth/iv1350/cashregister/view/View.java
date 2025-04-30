package se.kth.iv1350.cashregister.view;

import se.kth.iv1350.cashregister.controller.Controller;

import java.util.ArrayList;

import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
//import se.kth.iv1350.cashregister.model.CartEntry;

/**
 * Represents the User Interface of the cash register
 * Add vat stuff
 */
public class View {
    private ItemDTO newestItem = null;

    private Controller controller;

    /**
     * Constructor that connects the system to the user interface
     * 
     * @param controller Is used to give the view access to the controller over the current system
     */
    public View(Controller controller) {
        
        this.controller = new Controller();

    }

    public void test() {
        addItem(16); //item that does not exist, should print "Item not found!"
        displayCart(); //cart has not been created yet, should print "Scan item to start sale"
        addItem(1); //item that exists, should print "Köttbullar was added to cart"
        displayCart(); //previous item was successfully scanned and cart has been created, should print relevant details.
        addItem(1); //should print identical to two rows above
        displayCart(); //There should now be two instances of köttbullar
        addItem(4); //sould print "Gravad lax has been added"
        displayCart(); //Should display two instances of köttbullar and one of gravad lax
        System.out.println(endSale(500));
        
    }

    private void addItem(int itemID) {
        this.newestItem = this.controller.enterItem(itemID);
        if (this.newestItem == null) {
            System.out.println("Item not found!");
        }
        else {
            System.out.println(newestItem.getName() + " was added to cart");
        }
    }

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
                double price = item.itemDTO.getPrice();
                totalPrice += item.itemDTO.getPrice() * item.getAmount();
                System.out.println(name + "  x" + amount + "  " + (amount * price) + "kr");
            }
            System.out.println("\n" + totalPrice + "kr");
        }
        
        return;
    }

    private String endSale(double paymentAmount) { // change to PaymentAmount class when added, or remain int/double if omitted
        return controller.endSale(paymentAmount);
    }
}

