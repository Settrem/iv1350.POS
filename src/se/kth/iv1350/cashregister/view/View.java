package se.kth.iv1350.cashregister.view;

import se.kth.iv1350.cashregister.controller.Controller;
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
        
        this.controller = controller;

    }

    public void test() {
        addItem(5); //item that does not exist, should print "Item not found!"
        displayCart(); //cart has not been created yet, should print "Scan item to start sale"
        addItem(123); //item that exists, should print "Milk was added to cart"
        displayCart(); //previous item was successfully scanned and cart has been created, should print relevant details.
        addItem(123); //should print identical to two rows above
        displayCart(); //There should now be two instances of milk
        addItem(456); //sould print "Coke Zero has been added"
        displayCart(); //Should display two instances of milk and one of coke zero

        
    }

    private void addItem(int itemID) {
        this.newestItem = this.controller.enterItem(0);
        if (this.newestItem == null) {
            System.out.println("Item not found!");
        }
        else {
            System.out.println(newestItem.getName() + " was added to cart");
        }
    }

    private void displayCart() {
        /*
        int totalPrice = 0;
        CartEntry[] itemCart = this.controller.getCart();
        if (itemCart == null) {
            System.out.println("Scan item to start sale");
            return;
        }
        else {
            System.out.println("Cart: \n -----------");
            for(int i = 0; i < itemCart.length; i++) {
                item = cartEntry.item;
                totalPrice += item.price * cartEntry.amount;
                System.out.println(item.name + "  x" + cartEntry.amount + "  " + (cartEntry.amount * item.price) "kr");
            }
            System.out.println("\n" + totalPrice + "kr")
        }
        */
        return;
    }

    /* remove comment markers when relevant methods are added
    private int endSale(int paymentAmount) { // change to PaymentAmount class when added, or remain int/double if omitted
        int totalPrice = controller.getTotal();
        int result = controller.acceptPayment(paymentAmount);
        if (result == -1) {
            System.out.println("Customer is broke! ha ha") //Too little cash in payment
        }
        else {
            accountingRegistry.addSale(controller.sale()); //Breaks gdpr maybe, move to controller later
            println("Payment successful! Change: " totalPrice - paymentAmount "kr")
        }
    }
    */
}

