package se.kth.iv1350.cashregister.integration;

import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    private List<ItemDTO> item = new ArrayList<>();

    ItemRegistry() {
        addItem();
    }

    /**
     * Creates a new list to contain information about an item
     * 
     * @param itemID Items barcode
     * @param name Name of item
     * @param description The description of the item
     * @param price Price of item
     * @param vat Vat of item
     */
    private void addItem() {
        item.add(new ItemDTO(123, "Milk", "refreshing!", 19.90, 6.0));
        item.add(new ItemDTO(456, "Coke Zero", "This stuff is addicting!", 14.90, 6.0));
        item.add(new ItemDTO(789, "Kexchoklad", "This should be illegal!", 12.90, 6.0));
    }

    /**
     * 
     * @param itemID Fetches item with scanned itemID
     * @return Returns null if item is not find
     */
    public ItemDTO getItem(int itemID) {
        for (ItemDTO item : item) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null; 
    }
    

}
