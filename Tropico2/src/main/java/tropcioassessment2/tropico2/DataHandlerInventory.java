/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author duane
 */


import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;



public class DataHandlerInventory {
    private String fileName;
    private ArrayList<StockInventory> inventoryList;

public DataHandlerInventory(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.inventoryList = new ArrayList<StockInventory>();
        readDataFile();
    }

    private void readDataFile() {
        try {
            Scanner in = new Scanner(new FileReader(fileName));

            while (in.hasNextLine()) {
                String entry = in.nextLine();
                System.out.println("readling line: " + entry);
                String[] tokens = entry.split(",");

                String name = tokens[0].trim();
                int inventoryCode = Integer.parseInt(tokens[1].trim());
                double salePrice = Double.parseDouble(tokens[2].trim());
                double purchasePrice = Double.parseDouble(tokens[3].trim());

                StockInventory item = new StockInventory(name, inventoryCode, salePrice, purchasePrice);
                inventoryList.add(item);
            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error processing file: " + ex.getMessage());
        }
    }

public boolean saveData() {
    try {
        Formatter out = new Formatter(new FileWriter(fileName));

        for (StockInventory item : inventoryList) {
            out.format("%s,%d,%.2f,%.2f\n", 
                       item.getName(), item.getProductCode(), 
                       item.getSalePrice(), item.getPurchasePrice());
        }
        System.out.println("Saved " + inventoryList.size() + " inventory items to the file.");
        out.close();
        return true;  // Return true if save operation is successful
    } catch (IOException ex) {
        System.err.println("Error saving data: " + ex.getMessage());
        return false;  // Return false if there's an error during the save operation
    }
}


    public void addItem(StockInventory item) {
        inventoryList.add(item);
    }

    public StockInventory searchItemByCode(int code) {
        for (StockInventory item : inventoryList) {
            if (item.getProductCode() == code) {
                return item;
            }
        }
        return null;  // return null if no matching item is found
    }
    // This method can help you search an item by its name
public StockInventory searchItemByName(String name) {
    for (StockInventory item : inventoryList) {
        if (item.getName().equalsIgnoreCase(name)) {
            return item;
        }
    }
    return null;  // Return null if no item matches the provided name
}

    public void resetForm(TextField... fields) {
    for (TextField field : fields) {
        field.clear();
    }
}

public StockInventory searchInventory(String searchName, int searchID) {
    for (StockInventory inventory : inventoryList) {
        if (!searchName.isEmpty() && searchID != -1) { // both name and ID provided
            if (inventory.getName().equalsIgnoreCase(searchName) && inventory.getProductCode() == searchID) {
                return inventory;
            }
        } else if (!searchName.isEmpty()) { // only name provided
            if (inventory.getName().equalsIgnoreCase(searchName)) {
                return inventory;
            }
        } else if (searchID != -1) { // only ID provided
            if (inventory.getProductCode() == searchID) {
                return inventory;
            }
        }
    }
    return null; // return null if no match is found
}

public boolean updateInventoryDetails(String originalName, int originalID, 
                                     String editedName, double editedSalePrice, 
                                     double editedPurchasePrice, boolean editedAvailability) {
    // Assuming you have a list of inventory items called 'inventories'
    for (StockInventory inventory : inventoryList) {
        // Check if this is the item we want to update
        if (inventory.getName().equals(originalName) && inventory.getProductCode() == originalID) {
            // Update the details
            inventory.setName(editedName);
            inventory.setSalePrice(editedSalePrice);
            inventory.setPurchasePrice(editedPurchasePrice);
            inventory.setAvailable(editedAvailability); // Assuming you have a setter for availability
            return true; // Update was successful
        }
    }
    return false; // Inventory item not found or update failed
}


public void displayConfirmation(String message) {
    displayMessage(message, javafx.scene.control.Alert.AlertType.INFORMATION);
}


    public void displayMessage(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


