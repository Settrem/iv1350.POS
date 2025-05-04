package se.kth.iv1350.cashregistertest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.integration.ItemRegistry;

import static org.junit.jupiter.api.Assertions.*;

public class ItemRegistryTest {

    private ItemRegistry itemRegistry;
    private final String filePath = "src/lib/svensk_matmeny.csv";

    @BeforeEach
    public void setUp() {
        itemRegistry = new ItemRegistry(filePath);
    }

    @Test
    public void testGetExistingItemById() {
        int itemID = 1;
        ItemDTO item = itemRegistry.getItemById(itemID);

        assertNotNull(item, "Item should not be null");
        assertEquals(itemID, item.getItemID());
        assertEquals("Köttbullar", item.getName());
        assertEquals("Traditionella svenska köttbullar med potatismos", item.getDescription());
        assertEquals(8900, item.getPriceBeforeVAT());
        assertEquals(12, item.getVAT());
    }

    @Test
    public void testGetNonExistentItemById() {
        int invalidItemID = 999;
        ItemDTO item = itemRegistry.getItemById(invalidItemID);

        assertNull(item, "Should return null for nonexistent item ID");
    }

    @Test
    public void testFileReadErrorDoesNotCrash() {
        ItemRegistry brokenRegistry = new ItemRegistry("invalid/path.csv");
        ItemDTO item = brokenRegistry.getItemById(1);

        assertNull(item, "Should return null if file cannot be read");
    }
}

