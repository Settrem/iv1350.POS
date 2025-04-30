package se.kth.iv1350.cashregister.model;

import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import java.util.ArrayList;

public class ItemCart {
    
    ArrayList<CartItemDTO> cart = new ArrayList<CartItemDTO>();
    
    private int searchItem(ItemDTO itemDTO){
        for(int i=0; i<cart.size(); i++){
            if(cart.get(i).itemDTO == itemDTO){
                return i;
            }
        }
        return -1;
    }
    
    public void addItem(ItemDTO itemDTO){

        int itemIndex = searchItem(itemDTO);
        
        if(itemIndex != -1){
            cart.get(itemIndex).incrementItem();
        } else {



        }


    }


}
