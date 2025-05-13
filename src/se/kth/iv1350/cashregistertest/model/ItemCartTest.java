package se.kth.iv1350.cashregistertest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.dto.ItemDTO;
import se.kth.iv1350.cashregister.model.ItemCart;
import se.kth.iv1350.cashregister.model.CartItem;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ItemCart class.
 */
public class ItemCartTest {

    private ItemCart cart;
    private ItemDTO milk;
    private ItemDTO bread;

    @BeforeEach
    public void setUp() {
        cart = new ItemCart();
        milk = new ItemDTO(1, "Milk", "1 liter of milk", 21.0, 12.0);
        bread = new ItemDTO(2, "Bread", "Whole grain bread", 35.0, 12.0);
    }

    @Test
    public void testAddNewItem() {
        cart.addItem(milk);
        ArrayList<CartItem> items = cart.getCart();
        assertEquals(1, items.size(), "Cart should contain 1 item");
        assertEquals(milk.getItemID(), items.get(0).itemDTO.getItemID(), "Item in cart should be milk");
        assertEquals(1, items.get(0).getAmount(), "Amount should be 1");
    }

    @Test
    public void testAddSameItemTwice() {
        cart.addItem(milk);
        cart.addItem(milk);
        ArrayList<CartItem> items = cart.getCart();
        assertEquals(1, items.size(), "Cart should still contain 1 item");
        assertEquals(2, items.get(0).getAmount(), "Amount should be incremented to 2");
    }

    @Test
    public void testAddDifferentItems() {
        cart.addItem(milk);
        cart.addItem(bread);
        ArrayList<CartItem> items = cart.getCart();
        assertEquals(2, items.size(), "Cart should contain 2 different items");
    }

    @Test
    public void testRemoveOneOfMultipleSameItems() {
        cart.addItem(milk);
        cart.addItem(milk);
        String result = cart.removeItem(milk);
        assertEquals("Removed one Milk from cart", result);
        assertEquals(1, cart.getCart().get(0).getAmount(), "Amount should be reduced to 1");
    }

    @Test
    public void testRemoveLastItem() {
        cart.addItem(milk);
        String result = cart.removeItem(milk);
        assertEquals("Removed Milk from cart", result);
        assertEquals(0, cart.getCart().size(), "Cart should be empty after removing last item");
    }

    @Test
    public void testRemoveItemNotInCart() {
        String result = cart.removeItem(bread);
        assertEquals("Item was not found", result, "Should return not found message");
    }

    @Test
    public void testGetCartReturnsCorrectList() {
        cart.addItem(milk);
        cart.addItem(bread);
        ArrayList<CartItem> items = cart.getCart();
        assertNotNull(items, "getCart should not return null");
        assertEquals(2, items.size(), "getCart should return 2 items");
    }
}
