package se.kth.iv1350.cashregister.model;
import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Receipt {
    private String receipt;

    public Receipt(Sale sale, double cash)
    {
        int width = 51;
        double total = 0;
        this.receipt = "";
        this.receipt = this.receipt.concat("-".repeat((width - 15)/2));
        this.receipt = this.receipt.concat("Begin receipt");
        this.receipt = this.receipt.concat("-".repeat((width - 15)/2));
        this.receipt = this.receipt.concat("\nTime of sale: ");
        this.receipt = this.receipt.concat(LocalDate.now() + " " + LocalTime.now().withSecond(0).withNano(0).toString() + "\n\n");
        ArrayList<CartItemDTO> cart = sale.itemCart.cart;
        for(int i = 0; i < cart.size(); i++) {
            CartItemDTO currentEntry = cart.get(i);
            String currentLine = currentEntry.itemDTO.getName();
            currentLine = currentLine.concat(" ".repeat(28 - currentLine.length()));
            currentLine = currentLine.concat(currentEntry.getAmount() + " x " + currentEntry.itemDTO.getPrice());
            currentLine = currentLine.concat(" ".repeat(40 - currentLine.length()));
            currentLine = currentLine.concat((currentEntry.getAmount() * currentEntry.itemDTO.getPrice()) + " SEK\n");
            total += currentEntry.getAmount() * currentEntry.itemDTO.getPrice();
            this.receipt = this.receipt.concat(currentLine);
        }
        this.receipt = this.receipt.concat("\nTotal:" + " ".repeat(34) + total + " SEK\n");
        this.receipt = this.receipt.concat("vat goes here\n\n");

        String cashString = "" + cash;
        String change = "" + (cash - total);
        this.receipt = this.receipt.concat("Cash:" + " ".repeat(40 - cashString.length()) + cashString + " SEK\n");
        this.receipt = this.receipt.concat("Change:" + " ".repeat(38 - change.length()) + change + " SEK\n");

        this.receipt = this.receipt.concat("-".repeat((width - 13)/2));
        this.receipt = this.receipt.concat("End receipt");
        this.receipt = this.receipt.concat("-".repeat((width - 13)/2));
    }

    public String printReceipt() {
        return this.receipt;
    }
}
