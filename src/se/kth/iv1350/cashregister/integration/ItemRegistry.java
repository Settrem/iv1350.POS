package se.kth.iv1350.cashregister.integration;

import se.kth.iv1350.cashregister.DTOs.ItemDTO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    
    //This is the filepath to the CSV file that will be used as the ItemRegistry
    String filepath = "src/lib/svensk_matmeny.csv";
    
    /**
     * Constructor that sets the source were the itemregistry receives item info from
     * @param filepath is the file path och the item file
     */
    public ItemRegistry(String filepath) {
        this.filepath = filepath;
    }


    /**
     * Creates and returns a ItemDTO with a String Line
     * @param line
     * @return
     */
    private ItemDTO parseCSVLine(String line) {
        String[] parts = line.split(",", 5);
        
        int itemID = Integer.parseInt(parts[0]);
        String name = parts[1];
        String description = parts[2];
        double price = Double.parseDouble(parts[3]);
        double vat = Integer.parseInt(parts[4]);

        return new ItemDTO(itemID, name, description, price, vat);
    }

    /**
     * Returns item by reading csv file
     * @param itemID Is used to recognize what Item we want returned
     * @return ItemDTO if successfull 
     */
    public ItemDTO getItemById(int itemID) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            br.readLine(); //Skips the header of the CSC file

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
