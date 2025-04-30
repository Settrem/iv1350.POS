package se.kth.iv1350.cashregister.model;
import java.util.ArrayList;

import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.model.ItemCart;

public class Sale {

    public ItemCart itemCart;
    private int totalPrice;
    
    /**
     * Creates a new item cart that will be used in the current sale
     */
    public Sale(){
        this.itemCart = new ItemCart();
    }

    /**
     * Adds item in itemcart
     * @param item the item that is added
     */
    public void addItem(ItemDTO item) {
        itemCart.addItem(item);
    }

    /**
     * Cashier accepts payment and enters amount paid,
     * Controller calculates change
     * @param payedAmount The payed amount to the cashier
     * @param totalPrice The totalprice for the items
     */
    public double acceptPayment(double payedAmount) {
        if (payedAmount >= this.totalPrice) {
            return getChange(payedAmount); 
        } else {
            System.out.println("Payment Failed");
            return -1;
        }
    }

    private double getChange(double payedAmount){
        return payedAmount - this.totalPrice;
    }

    /**
     * Takes the items registered in cart and calculates running total
     * @param itemcart Imports all items scanned 
     */
    private void getPrice(ArrayList<CartItemDTO> cart) {

    for (CartItemDTO itemCart : cart) {
        double price = itemCart.itemDTO.getPrice();
        double vat = itemCart.itemDTO.getVAT();
        int amount = itemCart.getAmount();
        
        this.totalPrice += amount * price * (1 + vat / 100.0);
    }
}

}
