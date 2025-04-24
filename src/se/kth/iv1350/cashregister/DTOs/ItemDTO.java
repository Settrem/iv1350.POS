package se.kth.iv1350.cashregister.DTOs;

/**
 * Contains information about an item
 */

public final class ItemDTO {
    private int itemID;
    private String name;
    private String description;
    private double price;
    private double vat;

    public int getItemID() {
        return itemID;
    }
/**
 * Constructor for the ItemDTO class
 */
    public ItemDTO(int itemID, String name, String description, double price, double vat) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vat = vat;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public double getVAT() {
        return vat;
    }

}
