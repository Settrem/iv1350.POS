package se.kth.iv1350.cashregister.model;

import java.util.ArrayList;

import se.kth.iv1350.cashregister.dto.CartItemDTO;
import se.kth.iv1350.cashregister.dto.ItemDTO;


/**
 * Represents a shopping cart that holds items during a sale.
 *
 * This class keeps track of all added items and their quantities.
 * Items are stored as {@code CartItemDTO} objects, which include both itemDTO and their amount.
 */
public class ItemCart {
    
    public ArrayList<CartItemDTO> cart = new ArrayList<CartItemDTO>();
    
    /**
     * Searches for item inside the of the itemCart,
     * by comparing items with eachother until found or 
     * until the entire cart has been searched.
     * 
     * @param itemDTO is the item to search for
     * @return the index of were the item is stored, or -1 if item not found in list
     */
    private int searchItem(ItemDTO itemDTO){
        for(int i=0; i<cart.size(); i++){
            if(cart.get(i).itemDTO.getItemID() == itemDTO.getItemID()){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Adds an item to the cart. If the item already exists in the cart,
     * it increases the quantity by one. Otherwise, it adds the item as a new entry.
     *
     * @param itemDTO The item to be added to the cart.
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
     * Removes one unit of the specified item from the cart.
     * 
     * If only one unit exists, the item is removed completely.
     * If the item is not found in the cart, nothing is removed.
     *
     * @param itemDTO The item to remove from the cart.
     * @return A message indicating if the item was removed, partially removed,
     *         or not found in the cart.
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

    /**
     * Returns the current list of items in the cart.
     *
     * @return A list of {@code CartItemDTO} representing all items and their quantities in the cart.
     */
    public ArrayList<CartItemDTO> getCart() {
        return this.cart;
    }
}