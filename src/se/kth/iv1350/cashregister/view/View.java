package se.kth.iv1350.cashregister.view;

import se.kth.iv1350.cashregister.controller.Controller;

/**
 * Represents the User Interface of the cash register
 */
public class View {
   
    private Controller controller;

    /**
     * Constructor that connects the system to the user interface
     * 
     * @param controller Is used to give the view access to the controller over the current system
     */
    public View(Controller controller) {
        
        this.controller = controller;

    }



}
