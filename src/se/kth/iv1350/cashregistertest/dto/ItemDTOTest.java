package se.kth.iv1350.cashregistertest.dto;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.cashregister.dto.ItemDTO;


/**
 * Unit tests for the ItemDTO class.
 */
public class ItemDTOTest {

    private ItemDTO item;

    @BeforeEach
    public void prepareTest(){
        this.item = new ItemDTO(1, "Milk", "1 liter of milk", 21.0, 12.0);
    }

    @Test
    public void testGetItemID() {
        Assert.assertEquals(1, this.item.getItemID());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Milk", this.item.getName());
    }

    @Test
    public void testGetDescription() {
        Assert.assertEquals("1 liter of milk", this.item.getDescription());
    }

    @Test
    public void testGetPriceBeforeVAT() {
        Assert.assertEquals(21.0, this.item.getPriceBeforeVAT());
    }

    @Test
    public void testGetVAT() {
        Assert.assertEquals(12.0, this.item.getVAT());
    }

    @Test
    public void testGetPriceWithVAT() {
        int expectedPriceWithVAT = (int)(21.0 * 1.12);
        Assert.assertEquals(expectedPriceWithVAT, this.item.getPriceWithVAT());
    }

    @Test
    public void testGetVATByPrice() {
        double expectedVatPrice = this.item.getPriceWithVAT() - this.item.getPriceBeforeVAT();
        Assert.assertEquals(expectedVatPrice, this.item.getVATByPrice());
    }

    @Test
    public void testToString() {
        String result = this.item.toString();
        Assert.assertTrue("String should contain item ID", result.contains("1"));
        Assert.assertTrue("String should contain name", result.contains("Milk"));
        Assert.assertTrue("String should contain description", result.contains("1 liter of milk"));
        Assert.assertTrue("String should contain price", result.contains("10.0"));
        Assert.assertTrue("String should contain VAT", result.contains("12.0"));
    }
}
