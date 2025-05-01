package se.kth.iv1350.cashregistertest.dto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import se.kth.iv1350.cashregister.dto.ItemDTO;

/**
 * Unit tests for the ItemDTO class.
 */
public class ItemDTOTest {
    private ItemDTO item;

    @BeforeEach
    public void prepareTest(){
        item = new ItemDTO(1, "Milk", "1 liter of milk", 21.0, 12.0);
    }

    @Test
    public void testGetItemID() {
        assertEquals(1, item.getItemID(), "Item ID should be 1");
    }

    @Test
    public void testGetName() {
        assertEquals("Milk", item.getName(), "Item name should be 'Milk'");
    }

    @Test
    public void testGetDescription() {
        assertEquals("1 liter of milk", item.getDescription(), "Description should be '1 liter of milk'");
    }

    @Test
    public void testGetPriceBeforeVAT() {
        assertEquals(21.0, item.getPriceBeforeVAT(), "Price before VAT should be 21");
    }

    @Test
    public void testGetVAT() {
        assertEquals(12.0, item.getVAT(), "VAT should be 12.0%");
    }

    @Test
    public void testGetPriceWithVAT() {
        int expectedPriceWithVAT = (int)(21.0 * 1.12);
        assertEquals(expectedPriceWithVAT, item.getPriceWithVAT(), "Price with VAT should be" + expectedPriceWithVAT);
    }

    @Test
    public void testGetVATByPrice() {
        double expectedVatPrice = item.getPriceWithVAT() - item.getPriceBeforeVAT();
        assertEquals(expectedVatPrice, item.getVATByPrice(), "VAT by price should be approx. 1.0");
    }

    @Test
    public void testToString() {
        String result = item.toString();
        assertTrue(result.contains("1"), "String should contain item ID");
        assertTrue(result.contains("Milk"), "String should contain name");
        assertTrue(result.contains("1 liter of milk"), "String should contain description");
        assertTrue(result.contains("21.0"), "String should contain price");
        assertTrue(result.contains("12.0"), "String should contain VAT");
    }
}
