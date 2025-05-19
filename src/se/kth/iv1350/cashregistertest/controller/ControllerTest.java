package se.kth.iv1350.cashregistertest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.controller.Controller;
import se.kth.iv1350.cashregister.controller.InsufficientPaymentException;
import se.kth.iv1350.cashregister.controller.NetworkFailureException;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.integration.RegHandler;
import se.kth.iv1350.cashregister.model.Sale;
import se.kth.iv1350.cashregister.controller.NoItemFoundException;
import se.kth.iv1350.cashregister.util.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Controller class.
 */
public class ControllerTest {
    private Controller controller;
    private RegHandler regHandler = new RegHandler("src/lib/svensk_matmeny.csv");
    Logger logger = new ConsoleLogger();

    @BeforeEach
    public void setUp() {
        controller = new Controller(logger, regHandler);
        controller.startSale();
    }

    @Test
    public void testStartSaleCreatesNewSale() {
        Sale sale = controller.getSale();
        assertNotNull(sale, "Sale should be initialized after startSale()");
    }


    @Test
    public void testEnterItemThrowsNoItemFoundException() {
        int invalidItemID = 21; // This ID is not found according to your controller logic

        NoItemFoundException thrown = assertThrows(
            NoItemFoundException.class,
            () -> controller.enterItem(invalidItemID),
            "Expected enterItem() to throw NoItemFoundException for invalid ID"
        );

        assertTrue(thrown.getMessage().contains("Item with ID " + invalidItemID + " was not found!"));
    }

    @Test
    public void testEnterItemThrowsNetworkFailureException() {
        this.regHandler = new RegHandler("sdsdsds");
        this.controller = new Controller(logger, regHandler);
        int randomIremID = 999;

        NetworkFailureException thrown = assertThrows(
            NetworkFailureException.class,
            () -> controller.enterItem(randomIremID),
            "Expected enterItem() to throw NetworkFailureException for DB failure"
        );

        assertNotNull(thrown.getMessage(), "Exception message should not be null");
    }

    @Test
    public void testEndSaleThrowsInsufficientPaymentException() {
        controller.enterItem(1); // Add item to set a non-zero total
        int insufficientAmount = 0;

        InsufficientPaymentException thrown = assertThrows(
            InsufficientPaymentException.class,
            () -> controller.endSale(insufficientAmount),
            "Expected endSale() to throw InsufficientPaymentException for low payment"
        );

        assertTrue(thrown.getMessage().contains("Insufficient payment"));
    }

    @Test
    public void testEnterValidItem() {
        ItemDTO item = null;
        try {
        item = controller.enterItem(1);
        } catch (NoItemFoundException e) {
        assertNotNull(item, "ItemDTO should not be null for valid ID");
        assertEquals(1, item.getItemID(), "Item ID should be 1");
        }
    }

    @Test
    public void testEnterInvalidItem() {
        ItemDTO item = null;
        try {
        item = controller.enterItem(21);
        } catch (NoItemFoundException e) {
            assertNull(item, "ItemDTO should be null for invalid ID");
        }
    }

    @Test
    public void testGetCartAfterAddingItems() {
        controller.enterItem(1);
        controller.enterItem(2);
        ArrayList<?> cart = controller.getCart();
        assertEquals(2, cart.size(), "Cart should contain 2 different items");
    }

    @Test
    public void testEndSaleClearsCurrentSale() {
        controller.enterItem(1);
        controller.endSale(100000);
        assertNull(controller.getSale(), "Sale should be null after ending it");
    }

    @Test
    public void testPrintReceiptReturnsString() {
        controller.enterItem(1);
        String receipt = controller.printReceipt(100);
        assertNotNull(receipt, "Receipt should not be null");
        assertTrue(receipt.contains("Total"), "Receipt should contain 'Total'");
    }
}

