package se.kth.iv1350.cashregister.DTOs;

public class CartItemDTO {

    private int amount;
    public ItemDTO itemDTO;

    /**
     * Creates a cartItem that stores a itemDTO and the amount of that item
     * @param itemDTO The itemDTO that is to be stored
     */
    public CartItemDTO(ItemDTO itemDTO) {
        amount = 1;
        this.itemDTO = itemDTO;
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