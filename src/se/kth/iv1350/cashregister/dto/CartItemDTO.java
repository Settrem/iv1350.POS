package se.kth.iv1350.cashregister.dto;

/**
 * The {@code CartItemDTO} is used to hold both a itemDTO and the
 * amount of said item
 * 
 * It is used in the {@code ItemCart} to keep track of how many
 * of one item is scanned inside one entry.
 */
public class CartItemDTO {
    private int amount;
    public ItemDTO itemDTO;
    private static final double ÖRE_TO_SEK = 0.01;

    /**
     * Creates a cartItem that stores a itemDTO and the amount of that item
     * @param itemDTO The itemDTO that is to be stored
     */
    public CartItemDTO(ItemDTO itemDTO) {
        amount = 1;
        this.itemDTO = itemDTO;
    }

    public String toString(){
        return (this.itemDTO.getName() + "  x" + this.amount + "  " + (this.amount * this.itemDTO.getPriceWithVAT() * ÖRE_TO_SEK) + "kr\n");
    }
    
    public int getPriceWithVAT(){
        return this.itemDTO.getPriceWithVAT() * this.amount;
    }

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