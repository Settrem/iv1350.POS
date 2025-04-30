package se.kth.iv1350.cashregister.DTOs;


public final class ItemDTO {
    private int itemID;
    private String name;
    private String description;
    private double price;
    private double vat;

    /**
     * Gets the itemID
     * @return itemID as int
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Creates a itemDTO when given all these parameters
     * @param itemID
     * @param name
     * @param description
     * @param price
     * @param vat
     */
    public ItemDTO(int itemID, String name, String description, double price, double vat) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vat = vat;
    }

    @Override
    public String toString() {
        return itemID + " - " + description + " - " + price + " - " + vat;
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