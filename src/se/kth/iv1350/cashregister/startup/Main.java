package se.kth.iv1350.cashregister.startup;

import se.kth.iv1350.cashregister.controller.Controller;
import se.kth.iv1350.cashregister.view.View;

/**
 * Starts the entire system, the main method creates the POS
 */

public class Main {
    
    /**
     * Calls on the constructors for view and controller
     * 
     * @param args The application does not take any comand line parameters
     */
    public static void main(String[] args) {

    Controller controller = new Controller();
    View view = new View(controller);

    }


}
