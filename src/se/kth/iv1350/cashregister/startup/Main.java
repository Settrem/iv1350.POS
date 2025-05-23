package se.kth.iv1350.cashregister.startup;

import se.kth.iv1350.cashregister.controller.Controller;
import se.kth.iv1350.cashregister.integration.RegHandler;
import se.kth.iv1350.cashregister.view.View;
import se.kth.iv1350.cashregister.util.FileLogger;
import se.kth.iv1350.cashregister.util.Logger;
import se.kth.iv1350.cashregister.util.TotalRevenueFileOutput;

/**
 * Starts the entire system, the main method creates the POS
 */
public class Main {
    /**
     * Calls on the constructors for view and controller
     * 
     * @param args The application does not take any command line parameters
     */
    public static void main(String[] args) {
        Logger logger = new FileLogger();
        String itemRegistryFilePath = "src/lib/svensk_matmeny.csv";
        RegHandler regHandler = new RegHandler(itemRegistryFilePath);
        Controller controller = new Controller(logger, regHandler);
        controller.addRevenueObserver(new TotalRevenueFileOutput());
        View view = new View(controller);
        view.userInterface();
    }
}
