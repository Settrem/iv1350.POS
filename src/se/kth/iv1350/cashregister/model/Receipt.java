package se.kth.iv1350.cashregister.model;
import se.kth.iv1350.cashregister.DTOs.CartItemDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Receipt {
    String receipt;
    public Receipt(Sale sale, int cash)
    {
        int width = 49;
        int total = 0;
        this.receipt = "";
        this.receipt.concat("-".repeat((width - 15)/2));
        this.receipt.concat("Begin receipt");
        this.receipt.concat("-".repeat((width - 15)/2));
        this.receipt.concat("Time of sale: ");
        this.receipt.concat(LocalDateTime.now().toString() + "\n\n");
        ArrayList<CartItemDTO> cart = sale.itemCart.cart;
        for(int i = 0; i < cart.size(); i++) {
            CartItemDTO currentEntry = cart.get(i);
            String currentLine = currentEntry.itemDTO.getName();
            currentLine.concat(" ".repeat(30 - currentLine.length()));
            currentLine.concat(currentEntry.getAmount() + " x " + currentEntry.itemDTO.getPrice());
            currentLine.concat(" ".repeat(40 - currentLine.length()));
            currentLine.concat((currentEntry.getAmount() * currentEntry.itemDTO.getPrice()) + " SEK\n");
            total += currentEntry.getAmount() * currentEntry.itemDTO.getPrice();
            this.receipt.concat(currentLine);
        }
        this.receipt.concat("\n" + " ".repeat(40) + total + " SEK\n");
        this.receipt.concat("vat goes here\n");
        this.receipt.concat("-".repeat((width - 13)/2));
        this.receipt.concat("End receipt");
        this.receipt.concat("-".repeat((width - 13)/2));
    }

    public String printReceipt() {
        return this.receipt;
    }
}
