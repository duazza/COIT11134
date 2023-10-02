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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
/**
 * FXML Controller class
 *
 * @author duane
 */
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
        // TODO
        
    }    
@FXML
private void handleEditYesAction(ActionEvent event) {
    if (foundInventory != null) {
        editYesButton.setSelected(true);
        editNoButton.setSelected(false);
        foundInventory.setAvailable(true);  // Assuming you have a setAvailable method in StockInventory class
    }
}

@FXML
private void handleEditNoAction(ActionEvent event) {
    if (foundInventory != null) {
        editYesButton.setSelected(false);
        editNoButton.setSelected(true);
        foundInventory.setAvailable(false);  // Assuming you have a setAvailable method in StockInventory class
    }
}

    @FXML
    private void handleBackButton(ActionEvent event) {
        App.changeScene(2);
    }

    @FXML
private void handleSearchEnterAction(ActionEvent event) {
    try {
        String searchName = SearchNameField.getText().trim();
        String searchIDString = IDSearchField.getText().trim();
        int searchID = (searchIDString.isEmpty()) ? -1 : Integer.parseInt(searchIDString);

        StockInventory foundInventory = App.getDataHandlerInventory().searchInventory(searchName, searchID);

        if (foundInventory != null) {
            nameField.setText(foundInventory.getName());
            IDField.setText(String.valueOf(foundInventory.getProductCode()));
            SalePriceField.setText(String.valueOf(foundInventory.getSalePrice()));
            purchasePriceField.setText(String.valueOf(foundInventory.getPurchasePrice()));

            // Set the ToggleButtons based on availability
            editYesButton.setSelected(foundInventory.isAvailable());
            editNoButton.setSelected(!foundInventory.isAvailable());
        } else {
            // Display a message that inventory was not found
            App.getDataHandlerInventory().displayMessage("Inventory item not found!", Alert.AlertType.INFORMATION);
        }
     } catch (NumberFormatException e) {
        // Handle error while parsing ID
        App.getDataHandlerInventory().displayMessage("Error parsing ID: " + e.getMessage(), Alert.AlertType.ERROR);
    } catch (Exception e) {
        // Handle other potential errors
        App.getDataHandlerInventory().displayMessage("Error searching for inventory item: " + e.getMessage(), Alert.AlertType.ERROR);
    }

    
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
    private void handleCloseAction(ActionEvent event) {
            App.changeScene(2);
    }

    @FXML
private void handleSaveAction(ActionEvent event) {
boolean success = false;     
    try {
        // Retrieve edited details
        String editedName = editNameField.getText().trim().isEmpty() ? nameField.getText().trim() : editNameField.getText().trim();
        double editedSalePrice = editSalePriceField.getText().trim().isEmpty() ? Double.parseDouble(SalePriceField.getText().trim()) : Double.parseDouble(editSalePriceField.getText().trim());
        double editedPurchasePrice = editPurchasePriceField.getText().trim().isEmpty() ? Double.parseDouble(purchasePriceField.getText().trim()) : Double.parseDouble(editPurchasePriceField.getText().trim());
        boolean editedAvailability = editYesButton.isSelected(); // Assuming only one of the toggle buttons can be active at a time

        // Update the inventory's details
        success = App.getDataHandlerInventory().updateInventoryDetails(
            nameField.getText().trim(), // Original name for reference
            Integer.parseInt(IDField.getText().trim()), // Original ID for reference
            editedName, editedSalePrice, editedPurchasePrice, editedAvailability
        );

        // Display a confirmation if the update was successful
        if (success) {
            App.getDataHandlerInventory().displayMessage("Inventory details updated successfully!", Alert.AlertType.INFORMATION);
        } else {
            App.getDataHandlerInventory().displayMessage("Error updating inventory details!", Alert.AlertType.WARNING);
        }
} catch (NumberFormatException e) {
    App.getDataHandlerInventory().displayMessage("Error parsing data: " + e.getMessage(), Alert.AlertType.ERROR);
} catch (Exception e) {
    App.getDataHandlerInventory().displayMessage("Error updating inventory: " + e.getMessage(), Alert.AlertType.ERROR);
}

App.getDataHandlerInventory().resetForm(nameField, IDField, SalePriceField, purchasePriceField);

if (success) {
    App.getDataHandlerInventory().displayMessage("Inventory details updated successfully!", Alert.AlertType.INFORMATION);

    if (!App.getDataHandlerInventory().saveData()) {  // Assuming the saveData method is correct
        App.getDataHandlerInventory().displayMessage("Error saving updated inventory to disk!", Alert.AlertType.ERROR);
    }
} else {
    App.getDataHandlerInventory().displayMessage("Error updating inventory details!", Alert.AlertType.WARNING);
}
App.getDataHandlerPeople().resetForm(IDField, nameField, SalePriceField, purchasePriceField);
App.getDataHandlerPeople().resetForm(editNameField, editSalePriceField, editPurchasePriceField);
App.getDataHandlerPeople().resetForm(SearchNameField, IDSearchField);
}



}
