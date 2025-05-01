package se.kth.iv1350.cashregister.integration;

import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.model.Sale;

/**
 * The {@code RegHandler} class handles communication with external systems or registries,
 * such as item storage, accounting, discounts, and customer data.
 *
 * It acts as a central point for accessing these subsystems and provides methods
 * to fetch item data and record completed sales.
 */
public class RegHandler {
    
    private AccountingRegistry accountingRegistry;
    //private StorageRegistry storageRegistry;
    private ItemRegistry itemRegistry;
    //private DiscountRegistry discountRegistry;
    //private CustomerRegistry customerRegistry;

    /**
    * Creates a new {@code RegHandler} and initializes all external registries,
    * such as customer, discount, item, storage, and accounting registries.
    * <p>
    * These registries are used to manage external data like item info, discounts,
    * customer details, and to record sales.
    */
    public RegHandler() {
        //this.customerRegistry = new CustomerRegistry();
        //this.discountRegistry = new DiscountRegistry();
        this.itemRegistry = new ItemRegistry("src/lib/svensk_matmeny.csv");
        //this.customerRegistry = new CustomerRegistry();
        this.accountingRegistry = new AccountingRegistry();
    }

   /**
     * Finds and returns item information using the given item ID.
     * <p>
     * This method asks the {@code ItemRegistry} to search for the item in the data source.
     *
     * @param itemID The ID of the item to look for.
     * @return The {@code ItemDTO} if the item is found, or {@code null} if not found.
     */
    public ItemDTO getItem(int itemID){

        ItemDTO itemDTO = itemRegistry.getItemById(itemID);

        return itemDTO;
    }

    /**
     * Sends the completed sale to the accounting system to be recorded.
     *
     * @param sale The {@code Sale} object that contains all sale information.
     * @return {@code 0} if the sale was recorded successfully, or {@code -1} if an error occurred.
     */
    public int accountSale(Sale sale){
        if (accountingRegistry.accountSale(sale) != 0) {
            return -1;
        }
        return 0;
    }
    

}
