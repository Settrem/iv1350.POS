package se.kth.iv1350.cashregister.integration;

import se.kth.iv1350.cashregister.integration.*;

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
     * Initializes all registrys
     */
    public RegHandler() {

        this.customerRegistry = new CustomerRegistry();
        this.discountRegistry = new DiscountRegistry();
        this.itemRegistry = new ItemRegistry();
        this.customerRegistry = new CustomerRegistry();
        this.accountingRegistry = new AccountingRegistry();

    }

    

}
