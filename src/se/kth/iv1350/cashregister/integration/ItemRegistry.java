package se.kth.iv1350.cashregister.integration;

import java.io.BufferedReader;
import java.io.FileReader;

import se.kth.iv1350.cashregister.dto.ItemDTO;


/**
 * The {@code ItemRegistry} class handles access to item data stored in a CSV file.
 * 
 * It reads item information from a file and provides methods to find and return items
 * based on their ID. This class is part of the integration layer and acts as a
 * simulated item database for the system.
 */
public class ItemRegistry {
    //This is the filepath to the CSV file that will be used as the ItemRegistry
    String filepath;
    
    /**
     * Constructor that sets the source were the itemregistry receives item info from
     * @param filepath is the file path och the item file
     */
    public ItemRegistry(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Creates a {@code ItemDTO} by disecting a String that hold the items information 
     * @param line contains the item information in one string
     * 
     * @return the itemDTO created from the the parameter String
     */
    private ItemDTO parseCSVLine(String line) {
        String[] parts = line.split(",", 5);
        
        int itemID = Integer.parseInt(parts[0]);
        String name = parts[1];
        String description = parts[2];
        int price = Integer.parseInt(parts[3]);
        int vat = Integer.parseInt(parts[4]);

        return new ItemDTO(itemID, name, description, price, vat);
    }

    /**
     * Searches for an item in the CSV file using the given item ID.
     * 
     * If a matching item is found, it returns an {@code ItemDTO} with the item's details.
     * If no match is found or an error occurs, it returns {@code null}.
     *
     * @param itemID The ID of the item to look for.
     * @return The {@code ItemDTO} if found, or {@code null} if not found or an error happens.
     */
    public ItemDTO getItemById(int itemID) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            br.readLine(); 

            while ((line = br.readLine()) != null) {
                ItemDTO item = parseCSVLine(line);
                if (item.getItemID() == itemID) {
                    return item;
                }           
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
}
