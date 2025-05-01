package se.kth.iv1350.cashregistertest.startup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import se.kth.iv1350.cashregister.startup.Main;

public class MainTest {

    @Test
    public void testMainRunsWithoutException() {
        assertDoesNotThrow(() -> Main.main(null), "Main should execute without throwing any exception.");
    }
}
