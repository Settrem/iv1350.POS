package se.kth.iv1350.cashregister.model;
import se.kth.iv1350.cashregister.model.ItemCart;

public class Sale {

    private ItemCart itemcart;
    
    /**
     * Creates a new item cart that will be used in the current sale
     */
    public Sale(){

        this.itemcart = new ItemCart();

    }

}
