package se.kth.iv1350.cashregister.model;

import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import java.util.ArrayList;

public class ItemCart {
    
    public ArrayList<CartItemDTO> cart = new ArrayList<CartItemDTO>();
    
    /**
     * Searches for item inside the itemCart 
     * @param itemDTO is the item to search for
     * @return the index of were the item is stored, or -1 if item not found in list
     */
    private int searchItem(ItemDTO itemDTO){
        for(int i=0; i<cart.size(); i++){
            if(cart.get(i).itemDTO == itemDTO){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Adds a item inside of the itemcart
     * @param itemDTO is the item that is added
     */
    public void addItem(ItemDTO itemDTO){
        int itemIndex = searchItem(itemDTO);
        
        if(itemIndex != -1){
            cart.get(itemIndex).incrementItem();
        } else {
            cart.add(new CartItemDTO(itemDTO));
        }

    }

    /**
     * Removes one of the given item from the cart
     * @param itemDTO the item to remove
     * @return if item is removed or not
     */
    public String removeItem(ItemDTO itemDTO){
        int itemIndex = searchItem(itemDTO); 

        if(itemIndex != -1){
            if(cart.get(itemIndex).getAmount() == 1) {
                cart.remove(itemIndex);
                return "Removed " + cart.get(itemIndex).itemDTO.getName() + " from cart";                
            } else {
                cart.get(itemIndex).decrementItem();
                return "Removed one " + cart.get(itemIndex).itemDTO.getName() + " from cart";
            }
        }
        return "Item was not found";
    }

    public ArrayList<CartItemDTO> getCart() {
        return this.cart;
    }
}