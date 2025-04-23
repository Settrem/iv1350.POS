package se.kth.iv1350.cashregister.controller;

import se.kth.iv1350.cashregister.integration.RegHandler;
import se.kth.iv1350.cashregister.integration.Printer;
import se.kth.iv1350.cashregister.model.Sale;


public class Controller {
    
    private Sale currentSale;
    private Printer printer;
    private RegHandler regHandler;

    /**
     * Initializes the entire model of the system that the Controlls the entire sale
     */
    public Controller () {
        
        this.printer = new Printer();
        this.regHandler = new RegHandler();

    }

    /**
     * Creates a new sale
     * 
     * @return tells if method was successfull (1) or unsuccesfull (0) 
     */
    public int startSale() {

        this.currentSale = new Sale();
        return 1; 
        
    }


}
