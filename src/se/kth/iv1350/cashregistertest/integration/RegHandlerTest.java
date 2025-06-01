package se.kth.iv1350.cashregistertest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.integration.RegHandler;
import se.kth.iv1350.cashregister.integration.FailureToReachDataBaseException;
import se.kth.iv1350.cashregister.controller.NoItemFoundException;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class RegHandlerTest {
    private RegHandler regHandler;

    @BeforeEach
    public void setUp() {
        regHandler = new RegHandler("src/lib/svensk_matmeny.csv");
    }

    @Test
    public void testGetItemReturnsCorrectItem() {
        ItemDTO item = null;
        try {
            item = regHandler.getItem(1);
        } catch (FailureToReachDataBaseException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        } catch (NoItemFoundException e) {
            assertNull(item, "Throw Exception for nonexistent item ID");
        }
        assertNotNull(item, "Item should not be null");
        assertEquals(1, item.getItemID());
        assertEquals("Meatballs", item.getName());
    }

    @Test
    public void testGetItemReturnsNullForInvalidID() {
        ItemDTO item = null;
        try {
            item = regHandler.getItem(999);
        } catch (FailureToReachDataBaseException e) {
            assertNull(item, "Should throw Exception for failure to reach item database");
        } catch (NoItemFoundException e) {
            assertNull(item, "Throw Exception for nonexistent item ID");
        }
    }

    @Test
    public void testAccountSaleReturnsZeroOnSuccess() {
        Sale sale = new Sale();
        int result = regHandler.accountSale(sale);
        assertEquals(0, result, "Expected accounting result to be 0 (success)");
    }
}
