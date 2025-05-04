package se.kth.iv1350.cashregister.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import se.kth.iv1350.cashregister.dto.CartItemDTO;

/**
 * Represents a printed receipt for a completed sale.
 * 
 * The receipt includes the time of sale, a list of purchased items with prices
 * and VAT,
 * as well as payment details such as total cost, cash received, and change
 * returned.
 */
public class Receipt {
    private String receipt;

    /**
     * Creates a receipt based on the completed sale and the amount of cash
     * received.
     *
     * @param sale The completed {@code Sale} that includes item and payment
     *             information.
     * @param cash The amount of cash received from the customer (in öre, as an
     *             integer).
     */
    public Receipt(Sale sale, int cash) {
        int width = 81;
        int total = sale.getTotal();
        int vat = sale.getVat();
        this.receipt = "";
        this.receipt += ("-".repeat((width - 15) / 2));
        this.receipt += ("Begin receipt");
        this.receipt += ("-".repeat((width - 15) / 2));
        this.receipt += ("\nTime of sale: ");
        this.receipt += (LocalDate.now() + " " + LocalTime.now().withSecond(0).withNano(0).toString() + "\n\n");
        ArrayList<CartItemDTO> cart = sale.itemCart.cart;
        for (int i = 0; i < cart.size(); i++) {
            CartItemDTO currentEntry = cart.get(i);
            String currentLine = currentEntry.itemDTO.getName();
            String priceString = "" + currentEntry.getAmount() * currentEntry.itemDTO.getPriceWithVAT() / 100.0;
            currentLine += (" ".repeat(40 - currentLine.length()));
            currentLine += (currentEntry.getAmount() + " x " + currentEntry.itemDTO.getPriceWithVAT() / 100.0);
            currentLine += (" ".repeat(width - currentLine.length() - priceString.length() - 6));
            currentLine += (priceString + " SEK\n");
            // total += currentEntry.getAmount() * currentEntry.itemDTO.getPriceWithVAT();
            this.receipt += (currentLine);
        }
        String totalString = "" + total / 100.0;
        String vatString = "" + vat / 100.0;
        this.receipt += ("\nTotal:" + " ".repeat(width - totalString.length() - 12) + totalString + " SEK\n");
        this.receipt += ("Vat:" + " ".repeat(width - totalString.length() - 9) + vatString + " SEK\n");

        double cashPaid = cash / 100.0;
        double changeAmount = (cash - total) / 100.0;

        String cashString = String.format("%.2f", cashPaid);
        String changeString = String.format("%.2f", changeAmount);

        this.receipt = this.receipt
                .concat("Cash:" + " ".repeat(width - cashString.length() - 11) + cashString + " SEK\n");
        this.receipt = this.receipt
                .concat("Change:" + " ".repeat(width - changeString.length() - 13) + changeString + " SEK\n");
        this.receipt +=("-".repeat((width - 13) / 2));
        this.receipt +=("End receipt");
        this.receipt +=("-".repeat((width - 13) / 2));
    }

    /**
     * Returns the full receipt as a printable text string.
     *
     * @return A {@code String} containing the formatted receipt.
     */
    public String printReceipt() {
        return this.receipt;
    }
}
