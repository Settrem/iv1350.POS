package se.kth.iv1350.cashregister.view;

import se.kth.iv1350.cashregister.controller.Controller;

/**
 * Represents the User Interface of the Cash register
 */
public class View {
   
    private Controller controller;

    /**
     * Constructor that connects the system to the user interface
     * 
     * @param controller is used to give the view konroller over the current system
     */
    public View(Controller controller) {
        
        this.controller = controller;

    }



}
