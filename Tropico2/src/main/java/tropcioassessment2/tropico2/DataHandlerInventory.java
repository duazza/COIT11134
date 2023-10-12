/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author Tropico
 */
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javafx.scene.control.TextField;

//this will manage the inventory data
public class DataHandlerInventory {
    //defining 
    private String fileName;
    private ArrayList<StockInventory> inventoryList;

    //constructor
    public DataHandlerInventory(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.inventoryList = new ArrayList<StockInventory>();
        readDataFile();
    }

    //method to the read the inventory file.
    private void readDataFile() {
        try {
            Scanner in = new Scanner(new FileReader(fileName));

            while (in.hasNextLine()) {
                String entry = in.nextLine();
                System.out.println("reading line: " + entry);
                String[] tokens = entry.split(",");
                //this is trimming to the txt file 
                String name = tokens[0].trim();
                int inventoryCode = Integer.parseInt(tokens[1].trim());
                double salePrice = Double.parseDouble(tokens[2].trim());
                double purchasePrice = Double.parseDouble(tokens[3].trim());
                double stockQuantity = Double.parseDouble(tokens[4].trim());

                //adds a new stock object
                StockInventory item = new StockInventory(name, inventoryCode, salePrice, purchasePrice);
                item.addStock(stockQuantity);
                inventoryList.add(item);
            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error processing file: " + ex.getMessage());
        }
    }

    //boolean method for saving the data for the inventory item
    public boolean saveData() {
        try {
            Formatter out = new Formatter(new FileWriter(fileName));

            for (StockInventory item : inventoryList) {
                out.format("%s,%d,%.2f,%.2f,%.2f\n",
                        item.getName(), item.getProductCode(),
                        item.getSalePrice(), item.getPurchasePrice(), item.getStockLevel());
            }
            System.out.println("Saved " + inventoryList.size() + " inventory items to the file.");
            out.close();
            return true;
        } catch (IOException ex) {
            System.err.println("Error saving data: " + ex.getMessage());
            return false;  // Return false if error during the save operation
        }
    }

    //method to add new item in the array
    public void addItem(StockInventory item) {
        inventoryList.add(item);
    }

    //method to search by code
    public StockInventory searchItemByCode(int code) {
        for (StockInventory item : inventoryList) {
            if (item.getProductCode() == code) {
                return item;
            }
        }
        return null;  //null if not found
    }

    //method to search by name
    public StockInventory searchItemByName(String name) {
        for (StockInventory item : inventoryList) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;//null if not found
    }

    public void resetForm(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    //newer method to serch for name/id in inventory
    public StockInventory searchInventory(String searchName, int searchID) {
        for (StockInventory inventory : inventoryList) {
            if (!searchName.isEmpty() && searchID != -1) {
                if (inventory.getName().equalsIgnoreCase(searchName) && inventory.getProductCode() == searchID) {
                    return inventory;
                }
            } else if (!searchName.isEmpty()) { //search name
                if (inventory.getName().equalsIgnoreCase(searchName)) {
                    return inventory;
                }
            } else if (searchID != -1) { //search ID
                if (inventory.getProductCode() == searchID) {
                    return inventory;
                }
            }
        }
        return null;
    }

    //method needed to update/edit the iventory item
    public boolean updateInventoryDetails(String originalName, int originalID,
            String editedName, double editedSalePrice,
            double editedPurchasePrice, boolean editedAvailability) {
        for (StockInventory inventory : inventoryList) {
            if (inventory.getName().equals(originalName) && inventory.getProductCode() == originalID) {
                // Update the details
                inventory.setName(editedName);
                inventory.setSalePrice(editedSalePrice);
                inventory.setPurchasePrice(editedPurchasePrice);
                inventory.setAvailable(editedAvailability); 
                return true;
            }
        }
        return false;
    }

    //method to generate the ID
    public int generateNewInventoryID() {
        int highestID = 0;

        for (StockInventory item : inventoryList) {
            if (item.getProductCode() > highestID) {
                highestID = item.getProductCode();
            }
        }

        return highestID + 1;
    }

    //method to get the inventory list for the reports
    public ArrayList<StockInventory> getInventoryList() {
        return inventoryList;
    }

    // Fetch a StockInventory object by its ID
    public StockInventory getStockByProductID(String productID) {
        for (StockInventory item : inventoryList) {
            if (String.valueOf(item.getProductCode()).equals(productID)) {
                return item;
            }
        }
        return null;
    }

    // Update a specific StockInventory item and save the updated list to the file
    public void updateStockInFile(StockInventory updatedItem) {
        // Find the item in the list and update it
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getProductCode() == updatedItem.getProductCode()) {
                inventoryList.set(i, updatedItem);
                break;
            }
        }
        // Save the updated list back to the file
        saveData();
    }

    //method to check if the name of the stock item is unique. 
    public boolean isNameUnique(String newName) {
        for (StockInventory item : inventoryList) {
            String existingName = item.getName().toLowerCase();

            // Normalize the new name to lowercase for case-insensitive comparison
            newName = newName.toLowerCase();

            if (existingName.equals(newName)
                    || existingName.equals(newName + "s")
                    || existingName.equals(newName + "'s")
                    || newName.equals(existingName + "s")
                    || newName.equals(existingName + "'s")) {
                return false;
            }
        }
        return true;
    }

}
