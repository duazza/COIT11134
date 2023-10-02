/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author duane
 */
public class InventoryStocktakeController implements Initializable {


    @FXML
    private Button closeButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField searchIDField;
    @FXML
    private Button backButton;
    @FXML
    private TextField QTYField;
    @FXML
    private Button nextItemButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchNameField;
    @FXML
    private TextField searchQTYField;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleCloseAction(ActionEvent event) {
        App.changeScene(2);
    }

    @FXML
    private void handlesSaveAction(ActionEvent event) {
        App.changeScene(2);
        
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
        App.changeScene(2);
    }


 /*   @FXML
private void handleNextAction(ActionEvent event) {
    int searchID;
    try {
        searchID = Integer.parseInt(searchIDField.getText().trim());
    } catch (NumberFormatException e) {
        // Handle invalid ID
        return;
    }
    
    double newQty;
    try {
        newQty = Double.parseDouble(QTYField.getText().trim());
    } catch (NumberFormatException e) {
        // Handle invalid QTY
        return;
    }
    
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    StockInventory stockItem = dataHandlerInventory.searchItemByCode(searchID);
    
    if (stockItem != null) {
        // Create a new transaction for this stock adjustment
        double purchasePrice = stockItem.getPurchasePrice(); // Assuming getPurchasePrice() is a method in StockInventory
        TransactionAdjustment stockAdjustment = new TransactionAdjustment(
                searchID, 
                stockItem.getName(), 
                newQty - stockItem.getStockLevel(),
                purchasePrice
        );
        System.out.println("Created new stock adjustment: " + stockAdjustment.toString());
        
        // Save this adjustment as a transaction in transaction.txt
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
        dataHandlerTransaction.addTransaction(stockAdjustment);
        
        // Adjust the stock level in the inventory
        stockItem.setStockLevel(stockItem.getStockLevel() + newQty); // Adjusting the stock level
        dataHandlerTransaction.writeDataFile();

        resetForm();
    } else {
        // Item not found. Show an alert to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Item not found!");
        alert.showAndWait();
    }
    }

    
private void resetForm() {
    searchIDField.clear();
    searchNameField.clear();
    searchQTYField.clear();
    dateField.clear();
    QTYField.clear();
}   
        
   */
    
@FXML
private void handleNextAction(ActionEvent event) {
    int searchID;
    try {
        searchID = Integer.parseInt(searchIDField.getText().trim());
    } catch (NumberFormatException e) {
        // Handle invalid ID
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid stock item ID.");
        alert.showAndWait();
        return;
    }
    
    double newQty;
    try {
        newQty = Double.parseDouble(QTYField.getText().trim());
    } catch (NumberFormatException e) {
        // Handle invalid QTY
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid quantity.");
        alert.showAndWait();
        return;
    }
    
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    StockInventory stockItem = dataHandlerInventory.searchItemByCode(searchID);
    
    if (stockItem != null) {
        // Create a new transaction for this stock adjustment
        double purchasePrice = stockItem.getPurchasePrice(); 
        TransactionAdjustment stockAdjustment = new TransactionAdjustment(
                searchID, 
                stockItem.getName(), 
                newQty, // Directly use the new quantity entered
                purchasePrice
        );

        // Save this adjustment as a transaction in transaction.txt
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
        dataHandlerTransaction.addTransaction(stockAdjustment);
        
        dataHandlerTransaction.writeDataFile();

        resetForm();
    } else {
        // Item not found. Show an alert to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Item not found!");
        alert.showAndWait();
    }
}

private void resetForm() {
    searchIDField.clear();
    searchNameField.clear();
    searchQTYField.clear();
    QTYField.clear();
}  


    @FXML
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

   @FXML
private void handleSearchAction(ActionEvent event) {
    // Get the entered ID
    String enteredIDStr = searchIDField.getText().trim();

    // Access the necessary data handlers
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();  // Ensure you have this handler available

    StockInventory stockItem = null;

    // Search by ID
    if (!enteredIDStr.isEmpty()) {
        try {
            int enteredID = Integer.parseInt(enteredIDStr);
            stockItem = dataHandlerInventory.searchItemByCode(enteredID);
        } catch (NumberFormatException e) {
            // Handle invalid ID, perhaps with an alert or a message in searchNameField.
            searchNameField.setText("Please enter a valid stock item ID.");
            return;
        }
    }

    // If stock item is found, calculate and display stock details
    if (stockItem != null) {
        // Using the new method to calculate adjusted quantity
        double adjustedQuantity = dataHandlerTransaction.calculateAdjustedQuantityForItem(stockItem.getProductCode());
        double finalStockOnHand = stockItem.getStockLevel() + adjustedQuantity;

        // Display the stock details
        searchNameField.setText(stockItem.getName());
        searchQTYField.setText(String.valueOf(finalStockOnHand));
    } else {
        // Show that the item is not found, perhaps with an alert or a message in searchNameField.
        searchNameField.setText("Stock item not found with the provided ID.");
        searchQTYField.clear();
    }
}
    
    /*@FXML
    private void handleSearchAction(ActionEvent event) {
           int searchID;
    try {
        searchID = Integer.parseInt(searchIDField.getText().trim());
    } catch (NumberFormatException e) {
        // Handle invalid ID
        // Show an alert or error message
        return;
    }
    
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    StockInventory stockItem = dataHandlerInventory.searchItemByCode(searchID);
    
    if (stockItem != null) {
        searchNameField.setText(stockItem.getName());
        searchQTYField.setText(String.valueOf(stockItem.getStockLevel()));
    } else {
        // Item not found. Show an alert or error message
    }
    }

// Assuming you have already adjusted the stock level of a product and want to record the adjustment
public void recordStockAdjustment(StockInventory adjustedProduct, int adjustedQuantity, double p) {
    // Create an adjustment transaction
       double purchasePrice = adjustedProduct.getPurchasePrice();

    // Create an adjustment transaction
    TransactionAdjustment adjustmentTransaction = new TransactionAdjustment(
        adjustedProduct.getProductCode(), 
        adjustedProduct.getName(), 
        adjustedQuantity,
        purchasePrice  // Using the purchase price from the StockInventory object
    );


    // Access the necessary data handlers
    DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

    // Add the adjustment transaction to the list
    dataHandlerTransaction.addTransaction(adjustmentTransaction);

    // Save the updated transaction list to the file
    dataHandlerTransaction.writeDataFile();
}
*/
}   
    