package se.kth.iv1350.cashregister.integration;

import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.integration.*;
import se.kth.iv1350.cashregister.DTOs.*;

/**
 * Handles all outside actors
 */
public class RegHandler {
    
    private AccountingRegistry accountingRegistry;
    private StorageRegistry storageRegistry;
    private ItemRegistry itemRegistry;
    private DiscountRegistry discountRegistry;
    private CustomerRegistry customerRegistry;
    private ItemRegistryAdarTest itemRegistryAdarTest;

    /**
     * Initializes all registries
     */
    public RegHandler() {
        this.customerRegistry = new CustomerRegistry();
        this.discountRegistry = new DiscountRegistry();
        this.itemRegistry = new ItemRegistry();
        this.customerRegistry = new CustomerRegistry();
        this.accountingRegistry = new AccountingRegistry();
        this.itemRegistryAdarTest = new ItemRegistryAdarTest("src/lib/svensk_matmeny.csv");
    }

    /**
     * 
     */
    public ItemDTO getItem(int itemID){

        ItemDTO itemDTO = itemRegistry.getItem(itemID);

        return itemDTO;
    }
    

}
