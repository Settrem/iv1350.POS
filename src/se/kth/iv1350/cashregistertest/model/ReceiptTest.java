package se.kth.iv1350.cashregistertest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.model.Receipt;
import se.kth.iv1350.cashregister.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Receipt class.
 */
public class ReceiptTest {
    private Sale sale;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        ItemDTO milk = new ItemDTO(1, "Milk", "1 liter of milk", 2100, 12.0); // 21.00 SEK
        ItemDTO bread = new ItemDTO(2, "Bread", "Whole wheat bread", 3000, 6.0); // 30.00 SEK
        sale.addItem(milk);
        sale.addItem(milk); // Add milk twice
        sale.addItem(bread);
    }

    @Test
    public void testReceiptContainsHeaderAndFooter() {
        Receipt receipt = new Receipt(sale, 10000);
        String result = receipt.printReceipt();
        assertTrue(result.contains("Begin receipt"), "Receipt should contain 'Begin receipt'");
        assertTrue(result.contains("End receipt"), "Receipt should contain 'End receipt'");
    }

    @Test
    public void testReceiptIncludesItemsAndTotals() {
        Receipt receipt = new Receipt(sale, 10000);
        String result = receipt.printReceipt();
        assertTrue(result.contains("Milk"), "Receipt should contain item 'Milk'");
        assertTrue(result.contains("Bread"), "Receipt should contain item 'Bread'");
        assertTrue(result.contains("Total:"), "Receipt should contain 'Total'");
        assertTrue(result.contains("Vat:"), "Receipt should contain 'Vat'");
        assertTrue(result.contains("Cash:"), "Receipt should contain 'Cash'");
        assertTrue(result.contains("Change:"), "Receipt should contain 'Change'");
    }

    @Test
    public void testReceiptCalculatesChangeCorrectly() {
        int cash = 10000; // 100.00 SEK
        Receipt receipt = new Receipt(sale, cash);
        String result = receipt.printReceipt();
    
        int expectedTotal = sale.getTotal();
        int expectedChange = cash - expectedTotal;
        String expectedChangeStr = String.valueOf(expectedChange / 100.0);
    
        assertTrue(result.contains(expectedChangeStr), "Receipt should contain correct change: " + expectedChangeStr);
    }

    @Test
    public void testReceiptFormattingHasTimeOfSale() {
        Receipt receipt = new Receipt(sale, 10000);
        String result = receipt.printReceipt();
        assertTrue(result.contains("Time of sale:"), "Receipt should include the timestamp");
    }
}
