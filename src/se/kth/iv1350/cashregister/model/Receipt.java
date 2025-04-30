package se.kth.iv1350.cashregister.model;
//import se.kth.iv1350.cashregister.model.cartEntry;
import java.time.LocalDateTime;

public class Receipt {
    String receipt;
    public Receipt(Sale sale, int cash)
    {
        int width = 49;
        int total = 0;
        this.receipt = "";
        this.receipt.concat(repeat((width - 15)/2));
        this.receipt.concat("Begin receipt");
        this.receipt.concat("-".repeat((width - 15)/2));
        this.receipt.concat("Time of sale: ");
        this.receipt.concat(LocalDateTime.now().toString() + "\n\n");
        cartEntry[] cart = sale.itemcart.getList();
        for(int i = 0; i < cart.length; i++) {
            cartEntry currentEntry = cart[i];
            String currentLine = currentEntry.item.name;
            currentLine.concat(" ".repeat(30 - currentLine.length()));
            currentLine.concat(currentEntry.amount + " x " + currentEntry.item.price);
            currentLine.concat(" ".repeat(40 - currentLine.length()));
            currentLine.concat((currentEntry.amount * currentEntry.item.price) + " SEK\n");
            total += currentEntry.amount * currentEntry.item.price;
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
