package se.kth.iv1350.cashregister.integration;

import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.integration.*;
import se.kth.iv1350.cashregister.DTOs.*;
import se.kth.iv1350.cashregister.model.Sale;

/**
 * Handles all outside actors
 */
public class RegHandler {
    
    private AccountingRegistry accountingRegistry;
    private StorageRegistry storageRegistry;
    private ItemRegistry itemRegistry;
    private DiscountRegistry discountRegistry;
    private CustomerRegistry customerRegistry;

    /**
     * Initializes all registries
     */
    public RegHandler() {
        this.customerRegistry = new CustomerRegistry();
        this.discountRegistry = new DiscountRegistry();
        this.itemRegistry = new ItemRegistry("src/lib/svensk_matmeny.csv");
        this.customerRegistry = new CustomerRegistry();
        this.accountingRegistry = new AccountingRegistry();
    }

    /**
     * 
     */
    public ItemDTO getItem(int itemID){

        ItemDTO itemDTO = itemRegistry.getItemById(itemID);

        return itemDTO;
    }

    public int accountSale(Sale sale){
        if (accountingRegistry.accountSale(sale) != 0) {
            return -1;
        }
        return 0;
    }
    

}
