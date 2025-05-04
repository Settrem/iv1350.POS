package se.kth.iv1350.cashregistertest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.integration.RegHandler;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class RegHandlerTest {
    private RegHandler regHandler;

    @BeforeEach
    public void setUp() {
        regHandler = new RegHandler();
    }

    @Test
    public void testGetItemReturnsCorrectItem() {
        ItemDTO item = regHandler.getItem(1);
        assertNotNull(item, "Item should not be null");
        assertEquals(1, item.getItemID());
        assertEquals("Köttbullar", item.getName());
    }

    @Test
    public void testGetItemReturnsNullForInvalidID() {
        ItemDTO item = regHandler.getItem(999);
        assertNull(item, "Should return null for invalid item ID");
    }

    @Test
    public void testAccountSaleReturnsZeroOnSuccess() {
        Sale sale = new Sale();
        int result = regHandler.accountSale(sale);
        assertEquals(0, result, "Expected accounting result to be 0 (success)");
    }
}
