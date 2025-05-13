package se.kth.iv1350.cashregister.model;

import se.kth.iv1350.cashregister.dto.ItemDTO;

/**
 * The {@code CartItemDTO} is used to hold both a itemDTO and the
 * amount of said item
 * 
 * It is used in the {@code ItemCart} to keep track of how many
 * of one item is scanned inside one entry.
 */
public class CartItem {
    private int amount;
    public ItemDTO itemDTO;
    private static final double ÖRE_TO_SEK = 0.01;

    /**
     * Creates a cartItem that stores a itemDTO and the amount of that item
     * @param itemDTO The itemDTO that is to be stored
     */
    public CartItem(ItemDTO itemDTO) {
        amount = 1;
        this.itemDTO = itemDTO;
    }

    /**
     * Returns a formatted string representation of the cart item, including name, quantity, and total price with VAT.
     *
     * @return A formatted string describing the cart item.
     */
    public String toString(){
        return (this.itemDTO.getName() + "  x" + this.amount + "  " + (this.amount * this.itemDTO.getPriceWithVAT() * ÖRE_TO_SEK) + "kr\n");
    }
    
    /**
     * Calculates and returns the total price of this item including VAT, based on quantity.
     *
     * @return Total price with VAT for the quantity of this item.
     */
    public int getPriceWithVAT(){
        return this.itemDTO.getPriceWithVAT() * this.amount;
    }

     /**
     * Calculates and returns the total price before VAT for the quantity of this item.
     *
     * @return Total price before VAT.
     */
    public int getPriceBeforeVAT(){
        return (int)this.itemDTO.getPriceBeforeVAT() * this.amount;
    }

    /**
     * increments the amount of item
     */
    public void incrementItem(){
        amount++;
    }

    /**
     * decrements the amount of item
     */
    public void decrementItem() {
        amount--;
    }

    /**
     * Returns the amount of items in the CartItem
     * @return the amount
     */
    public int getAmount(){
        return amount;
    }

}