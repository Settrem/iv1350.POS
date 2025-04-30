package se.kth.iv1350.cashregister.DTOs;

public class CartItemDTO {

    private int amount;
    public ItemDTO itemDTO;

    public CartItemDTO(ItemDTO itemDTO) {
        amount = 1;
        this.itemDTO = itemDTO;
    }

    public void incrementItem(){
        amount++;
    }

    public void decrementItem() {
        amount--;
    }

    public int returnAmount(){
        return amount;
    }

}
