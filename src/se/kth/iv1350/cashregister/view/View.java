package se.kth.iv1350.cashregister.view;

import se.kth.iv1350.cashregister.controller.Controller;

/**
 * This class represents a simple user interface for the cash register system.
 * It interacts with the {@code Controller} to simulate scanning items, displaying
 * the cart, and completing a sale through hardcoded test cases.
 */
public class View {
    private Controller controller;

    /**
     * Creates a new instance of the view and connects it to the system controller.
     *
     * @param controller The controller that manages the application's logic and data.
     */
    public View(Controller controller) {
        this.controller = new Controller();
    }
}

