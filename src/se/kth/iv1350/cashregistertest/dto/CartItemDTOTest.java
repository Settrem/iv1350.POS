package se.kth.iv1350.cashregistertest.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.dto.CartItemDTO;
import se.kth.iv1350.cashregister.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Unit tests for the CartItemDTO class.
 */
public class CartItemDTOTest {
    private CartItemDTO cartItem;
    private ItemDTO testItem;

    @BeforeEach
    public void setUp() {
        testItem = new ItemDTO(1, "Milk", "1 liter of milk", 21.0, 12.0);
        cartItem = new CartItemDTO(testItem);
    }

    @Test
    public void testInitialAmountIsOne() {
        assertEquals(1, cartItem.getAmount(), "Initial amount should be 1");
    }

    @Test
    public void testIncrementItem() {
        cartItem.incrementItem();
        assertEquals(2, cartItem.getAmount(), "Amount should be incremented to 2");
    }

    @Test
    public void testDecrementItem() {
        cartItem.incrementItem();
        cartItem.decrementItem();
        assertEquals(1, cartItem.getAmount(), "Amount should be decremented back to 1");
    }

    @Test
    public void testGetItemDTO() {
        assertSame(testItem, cartItem.itemDTO, "ItemDTO should be the same instance as provided");
    }
}

