package se.kth.iv1350.cashregistertest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.controller.Controller;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.model.Sale;
import se.kth.iv1350.cashregister.controller.NoItemFoundException;
import se.kth.iv1350.cashregister.util.*;
import se.kth.iv1350.cashregister.util.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Controller class.
 */
public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        Logger logger = new ConsoleLogger();
        controller = new Controller(logger);
        controller.startSale();
    }

    @Test
    public void testStartSaleCreatesNewSale() {
        Sale sale = controller.getSale();
        assertNotNull(sale, "Sale should be initialized after startSale()");
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

