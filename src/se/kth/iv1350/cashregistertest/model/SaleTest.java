package se.kth.iv1350.cashregistertest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.cashregister.model.Sale;
import se.kth.iv1350.cashregister.dto.ItemDTO;

class SaleTest {

    private Sale sale;
    private ItemDTO testItem;

    @BeforeEach
    void setUp() {
        sale = new Sale();

        // Sample item: name, id, price (incl. VAT), price (excl. VAT), VAT
        testItem = new ItemDTO(12,"Milk 1L","Full-fat cow's milk",1200,12); // 12kr incl. VAT
    }

    @Test
    void testAddItem() {
        sale.addItem(testItem);
        assertEquals(1, sale.itemCart.getCart().size(), "ItemCart should contain one item.");
    }

    @Test
    void testGetTotalSingleItem() {
        sale.addItem(testItem);
        int expectedTotal = (int)(1200*1.12); // 12kr in öre
        assertEquals(expectedTotal, sale.getTotal(), "Total should match price including VAT.");
    }

    @Test
    void testGetTotalMultipleSameItems() {
        sale.addItem(testItem);
        sale.addItem(testItem);
        int expectedTotal = (int)(2400 * 1.12); // 2 x 1200 öre
        assertEquals(expectedTotal, sale.getTotal(), "Total should include quantity.");
    }

    @Test
    void testGetVat() {
        sale.addItem(testItem); // VAT = 200
        sale.addItem(testItem); // VAT = 200 again
        int expectedVat = 288;
        assertEquals(expectedVat, sale.getVat(), "VAT should be calculated based on quantity.");
    }

    @Test
    void testGetChange() {
        sale.addItem(testItem); // 13.44kr
        int cash = 2000; // 20kr
        int expectedChange = 656; // 8kr
        assertEquals(expectedChange, sale.getChange(cash), "Change should be cash - total.");
    }

    @Test
    void testGetReceiptReturnsString() {
        sale.addItem(testItem);
        String receipt = sale.getReceipt(2000);
        assertNotNull(receipt, "Receipt should not be null.");
        assertTrue(receipt.contains("Milk"), "Receipt should contain item name.");
    }
}
