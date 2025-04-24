package se.kth.iv1350.cashregister.controller;

import se.kth.iv1350.cashregister.integration.RegHandler;
import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import se.kth.iv1350.cashregister.integration.Printer;
import se.kth.iv1350.cashregister.model.Sale;



public class Controller {
    private Sale currentSale;
    private Printer printer;
    private RegHandler regHandler;

    /**
     * Initializes the entire model of the system that controls the entire sale
     */
    public Controller () {
        this.printer = new Printer();
        this.regHandler = new RegHandler();
    }

    /**
     * Creates a new sale
     * 
     * @return Tells if method was successful (1) or unsuccesful (0) 
     */
    public int startSale() {
        this.currentSale = new Sale();
        System.out.println("----Started New Sale----");
        return 1; 
    }

    /**
     * Search for a Item matching the specified criteria
     * @param itemID is the idetification used to search for a specific item
     * @return Will return a Item Data transfer objekt
     */
    public ItemDTO enterItem(int itemID){
        
        ItemDTO itemDTO = this.regHandler.getItem(itemID);

        return itemDTO;
    }


}
