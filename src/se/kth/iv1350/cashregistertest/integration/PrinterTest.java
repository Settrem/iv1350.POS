package se.kth.iv1350.cashregistertest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashregister.integration.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PrinterTest {

    @Test
    public void testPrintSalePrintsReceipt() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String receipt = "Test Receipt: Item A - 100 SEK";
        new Printer().printSale(receipt);

        assertTrue(outContent.toString().contains("Item A - 100 SEK"));
    }
}