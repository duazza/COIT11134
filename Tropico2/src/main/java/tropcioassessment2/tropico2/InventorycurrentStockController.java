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

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author duane
 */
public class InventorycurrentStockController implements Initializable {


    @FXML
    private Button closeButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextArea largeTextField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private Button resetButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleCloseAction(ActionEvent event) {
          App.changeScene(2);
    }
    
    
    
    
 /*   @FXML
private void handleSearchAction(ActionEvent event) {
    // Get the entered name or ID
    String enteredName = nameField.getText().trim();
    String enteredIDStr = IDField.getText().trim();

    // Access the necessary data handlers
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

    // Load the latest transaction data
   dataHandlerTransaction.refreshData();

    StockInventory stockItem = null;

    // Search by ID if provided; otherwise, search by name
    if (!enteredIDStr.isEmpty()) {
        try {
            int enteredID = Integer.parseInt(enteredIDStr);
            stockItem = dataHandlerInventory.searchItemByCode(enteredID);
        } catch (NumberFormatException e) {
            largeTextField.setText("Please enter a valid stock item ID.");
            return;
        }
    } else if (!enteredName.isEmpty()) {
        stockItem = dataHandlerInventory.searchItemByName(enteredName);
    }

    // If stock item is found, calculate and display stock details
    if (stockItem != null) {
        double adjustedStockQuantity = stockItem.getStockLevel(); // Start with the initial stock level
        
        // Calculate the adjusted stock based on the transactions
        for (Transaction transaction : dataHandlerTransaction.getTransactionList()) {
            for (int i = 0; i < transaction.getProductIDs().length; i++) {
                if (transaction.getProductIDs()[i].equals(String.valueOf(stockItem.getProductCode()))) {
                    switch (transaction.getTransactionType().toUpperCase()) {
                        case "ADJUSTMENT":
                            adjustedStockQuantity -= transaction.getQuantities()[i];
                            break;
                        case "CUSTOMER":
                            adjustedStockQuantity -= transaction.getQuantities()[i];
                            break;
                        case "VENDOR":
                            adjustedStockQuantity += transaction.getQuantities()[i];
                            break;
                    }
                }
            }
        }

        // Display the stock details
        StringBuilder displayText = new StringBuilder();
        displayText.append("Inventory ID: ").append(stockItem.getProductCode()).append("\n");
        displayText.append("Name: ").append(stockItem.getName()).append("\n");
        displayText.append("Stock On Hand: ").append(adjustedStockQuantity).append("\n");

        largeTextField.setText(displayText.toString());
    } else {
        largeTextField.setText("Stock item not found with the provided name or ID.");
    }
}
    
    

    @FXML
    private void handleSearchAction(ActionEvent event) {
            // Get the entered name or ID
    String enteredName = nameField.getText().trim();
    String enteredIDStr = IDField.getText().trim();

    // Access the necessary data handlers
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

    StockInventory stockItem = null;

    // Search by ID if provided; otherwise, search by name
    if (!enteredIDStr.isEmpty()) {
        try {
            int enteredID = Integer.parseInt(enteredIDStr);
            stockItem = dataHandlerInventory.searchItemByCode(enteredID);
        } catch (NumberFormatException e) {
            largeTextField.setText("Please enter a valid stock item ID.");
            return;
        }
    } else if (!enteredName.isEmpty()) {
        stockItem = dataHandlerInventory.searchItemByName(enteredName);
    }

    // If stock item is found, calculate and display stock details
    if (stockItem != null) {
        
   double netStockAdjustment = dataHandlerTransaction.calculateNetStockAdjustment(stockItem);
double adjustedStockQuantity = (double) (stockItem.getStockLevel() + netStockAdjustment);


        // Display the stock details
        StringBuilder displayText = new StringBuilder();
        displayText.append("Inventory ID: ").append(stockItem.getProductCode()).append("\n");
        displayText.append("Name: ").append(stockItem.getName()).append("\n");
        displayText.append("Stock On Hand: ").append(adjustedStockQuantity).append("\n");

        largeTextField.setText(displayText.toString());
    } else {
        largeTextField.setText("Stock item not found with the provided name or ID.");
    }

        
    }*/

    
    
    @FXML
private void handleSearchAction(ActionEvent event) {
    // Get the entered name or ID
    String enteredName = nameField.getText().trim();
    String enteredIDStr = IDField.getText().trim();

    // Access the necessary data handlers
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();  // Ensure you have this handler available

    StockInventory stockItem = null;

    // Search by ID if provided; otherwise, search by name
    if (!enteredIDStr.isEmpty()) {
        try {
            int enteredID = Integer.parseInt(enteredIDStr);
            stockItem = dataHandlerInventory.searchItemByCode(enteredID);
        } catch (NumberFormatException e) {
            largeTextField.setText("Please enter a valid stock item ID.");
            return;
        }
    } else if (!enteredName.isEmpty()) {
        stockItem = dataHandlerInventory.searchItemByName(enteredName);
    }

    // If stock item is found, calculate and display stock details
    if (stockItem != null) {
        // Using the new method to calculate adjusted quantity
        double adjustedQuantity = dataHandlerTransaction.calculateAdjustedQuantityForItem(stockItem.getProductCode());

        double finalStockOnHand = stockItem.getStockLevel() + adjustedQuantity;

        // Display the stock details
        StringBuilder displayText = new StringBuilder();
        displayText.append("Inventory ID: ").append(stockItem.getProductCode()).append("\n");
        displayText.append("Name: ").append(stockItem.getName()).append("\n");
        displayText.append("Stock On Hand: ").append(finalStockOnHand).append("\n");

        largeTextField.setText(displayText.toString());
    } else {
        largeTextField.setText("Stock item not found with the provided name or ID.");
    }
}

    
    
    @FXML
    private void handleResetAction(ActionEvent event) {
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
          App.changeScene(2);
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
          App.changeScene(0);
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

}
