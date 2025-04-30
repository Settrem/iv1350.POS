package se.kth.iv1350.cashregister.model;
import java.util.ArrayList;

import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.model.ItemCart;

public class Sale {

    private ItemCart itemcart;
    
    /**
     * Creates a new item cart that will be used in the current sale
     */
    public Sale(){

        this.itemcart = new ItemCart();

    }

    public void addItem(ItemDTO item) {
        
    }
    /**
     * Cashier accepts payment and enters amount paid,
     * Controller calculates change
     * @param payedAmount The payed amount to the cashier
     * @param totalPrice The totalprice for the items
     */
    public double acceptPayment(double payedAmount, double totalPrice) {
        if (payedAmount >= totalPrice) {
            return payedAmount - totalPrice; 
        } else {
            System.out.println("Payment Failed");
            return -1; 
        }
    }
    /**
     * Takes the items registered in cart and calculates running total
     * @param itemcart Imports all items scanned 
     */
    private double getPrice(ArrayList<CartItemDTO> cart) {
    double totalPrice = 0.0;

    for (CartItemDTO itemCart : cart) {
        double price = itemCart.ItemDTO().getPrice();
        double vat = itemCart.ItemDTO().getVAT();
        int amount = itemCart.getAmount();

        totalPrice += amount * price * (1 + vat / 100.0);
    }

    return totalPrice;
}

}
