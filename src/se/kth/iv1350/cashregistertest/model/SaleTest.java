package se.kth.iv1350.cashregistertest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.cashregister.model.Sale;
import se.kth.iv1350.cashregister.dto.ItemDTO;

public class SaleTest {

    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        ItemDTO milk = new ItemDTO(1, "Milk", "1 liter of milk", 2100, 12.0); 
        sale.addItem(milk);
    }

    @Test
    void testAcceptPaymentSuccessfulPayment() {
        int payedAmount = 25 * 100;
        int expectedChange = payedAmount - sale.getTotal();
        int actualChange = sale.acceptPayment(payedAmount);
        assertEquals(expectedChange, actualChange, "Change should be correct when payment is successful");
    }

    @Test
    void testAcceptPaymentInsufficientPayment() {
        int payedAmount = 5 * 100;
        int result = sale.acceptPayment(payedAmount);
        assertEquals(-1, result, "Should return -1 when payment is unsuccessful");
    }

    @Test
    void testGetTotal() {
        int expectedTotal = (int) (2100 * 1.12);
        assertEquals(expectedTotal, sale.getTotal(), "Total should include price with VAT");
    }

    
    @Test
    void testGetVat() {
        int expectedVat = (int) (2100 * 0.12); 
        assertEquals(expectedVat, sale.getVat(), "VAT should be 12% of item price");
    }
    
}
