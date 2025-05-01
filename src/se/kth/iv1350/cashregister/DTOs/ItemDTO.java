package se.kth.iv1350.cashregister.DTOs;

/**
 * This class is a Data Transfer Object (DTO) that holds information about one item.
 * 
 * It is used to send item data between different parts of the program,
 * like from the database to the controller and view.
 * 
 * The class stores the item's ID, name, description, price, and VAT.
 * The data cannot be changed after the object is created.
 */
public final class ItemDTO {
    private int itemID;
    private String name;
    private String description;
    private double price;
    private double vat;

    /**
     * Is used to get the itemID
     * @return itemID as int
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Creates a new ItemDTO with the given item information.
     *
     * @param itemID The unique ID of the item.
     * @param name The name of the item.
     * @param description A short description of the item.
     * @param price The price of the item (without VAT).
     * @param vat The VAT (Value Added Tax) rate for the item, in percent.
     */
    public ItemDTO(int itemID, String name, String description, double price, double vat) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vat = vat;
    }

    /**
     * Used to turn an itemDTO into a String following this strucutre
     * "ItemID, Name, Description, Price and VAT"
     */
    @Override
    public String toString() {
        return itemID + " - " + name + " - " + description + " - " + price + " - " + vat;
    }

    /**
     * Gets the name of the item.
     *
     * @return The item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Desciption of the item
     * 
     * @return The item's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the Price before VAT of the item
     * 
     * @return The item's price before VAT
     */
    public double getPriceBeforeVAT(){
        return this.price;
    }

    /**
     * Gets the price with VAT of the item
     * 
     * @return The item's price with VAT
     */
    public int getPriceWithVAT() {
        return (int)(price * (1 + vat/100));
    }

    /**
     * Gets the VAT of the item
     * 
     * @return The item's VAT
     */
    public double getVAT() {
        return vat;
    }

    /**
     * Gets the VAT of the item as a price
     *  
     * @return The item's VAT in price
     */
    public double getVATByPrice(){
        return this.getPriceWithVAT() - this.getPriceBeforeVAT();
    }

}