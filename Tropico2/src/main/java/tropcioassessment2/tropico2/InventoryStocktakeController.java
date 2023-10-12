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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tropico
 */

// this the stocktake controller
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //not needed
    }

    @FXML//close button
    private void handleCloseAction(ActionEvent event) {
        resetForm();
        App.changeScene(2);
    }

    @FXML //save button - just resets the fields
    private void handlesSaveAction(ActionEvent event) {
        resetForm();
        App.changeScene(2);

    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        App.changeScene(2);
    }

    @FXML  // this is the save button when the user adjusts the stock
    private void handleNextAction(ActionEvent event) {
        int searchID;
        try {
            searchID = Integer.parseInt(searchIDField.getText().trim());
        } catch (NumberFormatException e) {
            Alerts.showErrorAlert("Error", "Please enter a valid stock item ID.");
            return;
        }

        //the amount to deduct from the inventory
        double deductionAmount;
        try {
            deductionAmount = Double.parseDouble(QTYField.getText().trim());
        } catch (NumberFormatException e) {
            Alerts.showErrorAlert("Error", "Please enter a valid quantity to deduct.");
            return;
        }

        //calling the datahandler and search the item by code
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
        StockInventory stockItem = dataHandlerInventory.searchItemByCode(searchID);

        if (stockItem != null) {//
            // Ensure stock level doesn't go negative
            if (stockItem.getStockLevel() < deductionAmount) {
                Alerts.showErrorAlert("Error", "Cannot deduct more than the current stock level.");
                return;
            }

            // Calculate the new stock level after deduction
            double newQty = stockItem.getStockLevel() - deductionAmount;

            // Create a new transaction for this stock adjustment
            double purchasePrice = stockItem.getPurchasePrice();
            TransactionAdjustment stockAdjustment = new TransactionAdjustment(
                    searchID,
                    stockItem.getName(),
                    -deductionAmount, // Negative because it's a deduction
                    purchasePrice
            );

            // Update the stock level in the StockInventory object
            stockItem.setStockLevel(newQty);

            // Save this adjustment as a transaction in transaction.txt
            DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
            dataHandlerTransaction.addTransaction(stockAdjustment);

            // Save updated stock level to inventory.txt
            dataHandlerInventory.saveData();

            resetForm();
        } else {
            Alerts.showErrorAlert("Error", "Item not found!");
        }
    }

    //method to reset the controller
    private void resetForm() {
        searchIDField.clear();
        searchNameField.clear();
        searchQTYField.clear();
        QTYField.clear();
    }

    @FXML //the logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //the exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    @FXML //this searches the inventory file for the stock item and data
    private void handleSearchAction(ActionEvent event) {
        // Get the entered ID
        String enteredIDStr = searchIDField.getText().trim();

        // Access the necessary data handlers
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();

        StockInventory stockItem = null;

        // Search by ID
        if (!enteredIDStr.isEmpty()) {
            try {
                int enteredID = Integer.parseInt(enteredIDStr);
                stockItem = dataHandlerInventory.searchItemByCode(enteredID);
            } catch (NumberFormatException e) {
                // Handle invalid ID
                searchNameField.setText("Please enter a valid stock item ID.");
                return;
            }
        }

        // If stock item is found, display stock details
        if (stockItem != null) {
            // Display the stock details
            searchNameField.setText(stockItem.getName());
            searchQTYField.setText(String.valueOf(stockItem.getStockLevel()));
        } else {
            // Show that the item is not found
            searchNameField.setText("Stock item not found with the provided ID.");
            searchQTYField.clear();
        }
    }
}
