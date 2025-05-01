package se.kth.iv1350.cashregister.model;
import java.util.ArrayList;

import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.model.ItemCart;

public class Sale {

    public ItemCart itemCart;
    
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
        if (payedAmount >= this.getTotal()) {
            return getChange(payedAmount); 
        } else {
            System.out.println("Payment Failed");
            return -1;
        }
    }

    private double getChange(double payedAmount){
        return payedAmount - this.getTotal();
    }

    /**
     * Takes the items registered in cart and calculates running total
     * @param itemcart Imports all items scanned 
     */
    public int getTotal() {
        int totalPrice = 0;
        ArrayList<CartItemDTO> cart = this.itemCart.getCart();
        for (int i = 0; i < cart.size(); i++) {
            ItemDTO item = cart.get(i).itemDTO;
            double price = item.getPrice();
            double vat = item.getVAT();
            int amount = cart.get(i).getAmount();
            
            totalPrice += amount * price;
        }
        return totalPrice;
    }

    public int getVat() {
        int totalVat = 0;
        ArrayList<CartItemDTO> cart = this.itemCart.getCart();
        for (int i = 0; i < cart.size(); i++) {
            ItemDTO item = cart.get(i).itemDTO;
            double price = item.getPrice();
            double vat = item.getVAT();
            int amount = cart.get(i).getAmount();
            
            totalVat += amount * price * (vat / 100.0);
        }
        return totalVat;
    }
    
    public String getReceipt(int cash) {
        Receipt receipt = new Receipt(this, cash);
        return receipt.printReceipt();
    }

}
