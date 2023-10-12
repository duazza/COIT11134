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
 * @author Tropico
 */
//this is the current stock controller
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
        //not needed
    }

    @FXML //close button
    private void handleCloseAction(ActionEvent event) {
        App.changeScene(2);
    }

    @FXML //searches the inventory file for the stock item
    private void handleSearchAction(ActionEvent event) {
        // Get the entered name or ID
        String enteredName = nameField.getText().trim();
        String enteredIDStr = IDField.getText().trim();

        // Access the necessary data handlers
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();

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

        // If stock item is found, display stock details
        if (stockItem != null) {
            double finalStockOnHand = stockItem.getStockLevel();

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

    @FXML  //button to reset controller
    private void handleResetAction(ActionEvent event) {
        // Clear the text fields
        nameField.clear();
        IDField.clear();
        largeTextField.clear();
    }

    @FXML  //back button
    private void handleBackAction(ActionEvent event) {
        nameField.clear(); // Clear the text fields 
        IDField.clear();
        largeTextField.clear();
        App.changeScene(2);
    }

    @FXML  //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML  //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

}
