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
import javafx.scene.control.ToggleButton;

/**
 * FXML Controller class
 *
 * @author Tropico
 */
//This is the controller to edit the inventory item
public class InventoryEditController implements Initializable {

    private StockInventory foundInventory;
    @FXML
    private TextField editNameField;
    @FXML
    private ToggleButton editYesButton;
    @FXML
    private ToggleButton editNoButton;
    @FXML
    private TextField IDField;
    @FXML
    private TextField SalePriceField;
    @FXML
    private TextField purchasePriceField;
    @FXML
    private TextField editSalePriceField;
    @FXML
    private TextField editPurchasePriceField;
    @FXML
    private Button backButton;
    @FXML
    private TextField SearchNameField;
    @FXML
    private TextField IDSearchField;
    @FXML
    private Button SearchEnterButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField nameField;
    @FXML
    private Button saveButon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //not needed
    }

    @FXML //this changes the toggle buttons
    private void handleEditYesAction(ActionEvent event) {
        if (foundInventory != null) {
            editYesButton.setSelected(true);
            editNoButton.setSelected(false);
            foundInventory.setAvailable(true);
        }
    }

    @FXML //this changeds the toggle button
    private void handleEditNoAction(ActionEvent event) {
        if (foundInventory != null) {
            editYesButton.setSelected(false);
            editNoButton.setSelected(true);
            foundInventory.setAvailable(false);
        }
    }

    @FXML  //back button
    private void handleBackButton(ActionEvent event) {
         resetFields();
        App.changeScene(2);
    }

    @FXML  //search button to find the exiting inventory item
    private void handleSearchEnterAction(ActionEvent event) {
        try {//grabbing the details from the field. 
            String searchName = SearchNameField.getText().trim();
            String searchIDString = IDSearchField.getText().trim();
            int searchID = (searchIDString.isEmpty()) ? -1 : Integer.parseInt(searchIDString);

            //calling the search method
            StockInventory foundInventory = App.getDataHandlerInventory().searchInventory(searchName, searchID);

            if (foundInventory != null) {
                nameField.setText(foundInventory.getName());
                IDField.setText(String.valueOf(foundInventory.getProductCode()));
                SalePriceField.setText(String.valueOf(foundInventory.getSalePrice()));
                purchasePriceField.setText(String.valueOf(foundInventory.getPurchasePrice()));

                // Set ToggleButtons based on availability
                editYesButton.setSelected(foundInventory.isAvailable());
                editNoButton.setSelected(!foundInventory.isAvailable());
            } else {
                // Display a message that inventory was not found
                Alerts.showInfoAlert("Information", "Inventory item not found!");
            }
        } catch (NumberFormatException e) {
            // Handle error while parsing ID
            Alerts.showErrorAlert("Error", "Error parsing ID: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential errors
            Alerts.showErrorAlert("Error", "Error searching for inventory item: ");
        }
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML  //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    @FXML //close button
    private void handleCloseAction(ActionEvent event) {
        resetFields();
        App.changeScene(2);
    }

    @FXML // save button
    private void handleSaveAction(ActionEvent event) {
        boolean success = false;
        try {
            // Retrieve edited details
            String editedName = editNameField.getText().trim().isEmpty() ? nameField.getText().trim() : editNameField.getText().trim();
            double editedSalePrice = editSalePriceField.getText().trim().isEmpty() ? Double.parseDouble(SalePriceField.getText().trim()) : Double.parseDouble(editSalePriceField.getText().trim());
            double editedPurchasePrice = editPurchasePriceField.getText().trim().isEmpty() ? Double.parseDouble(purchasePriceField.getText().trim()) : Double.parseDouble(editPurchasePriceField.getText().trim());
            boolean editedAvailability = editYesButton.isSelected();

            // Update the inventory's details
            success = App.getDataHandlerInventory().updateInventoryDetails(
                    nameField.getText().trim(), // Original name for reference
                    Integer.parseInt(IDField.getText().trim()), // Original ID for reference
                    editedName, editedSalePrice, editedPurchasePrice, editedAvailability
            );

        } catch (NumberFormatException e) {
            Alerts.showErrorAlert("Error", "Error parsing data: " + e.getMessage());
        } catch (Exception e) {
            Alerts.showErrorAlert("Error", "Error updating inventory: " + e.getMessage());
        }

        // Check if the update was successful and display appropriate alerts
        if (success) {
            Alerts.showInfoAlert("Information", "Inventory details updated successfully!");

            if (!App.getDataHandlerInventory().saveData()) {
                Alerts.showErrorAlert("Error", "Error saving updated inventory to disk!");
            }

            // Reset the form 
            App.getDataHandlerPeople().resetForm(IDField, nameField, SalePriceField, purchasePriceField);
            App.getDataHandlerPeople().resetForm(editNameField, editSalePriceField, editPurchasePriceField);
            App.getDataHandlerPeople().resetForm(SearchNameField, IDSearchField);

        } else {
            Alerts.showErrorAlert("Error", "Error updating inventory details!");
        }
    }
@FXML
public void resetFields() {
    // Reset TextFields
    editNameField.setText("");
    IDField.setText("");
    SalePriceField.setText("");
    purchasePriceField.setText("");
    editSalePriceField.setText("");
    editPurchasePriceField.setText("");
    SearchNameField.setText("");
    IDSearchField.setText("");
    nameField.setText("");
}}
