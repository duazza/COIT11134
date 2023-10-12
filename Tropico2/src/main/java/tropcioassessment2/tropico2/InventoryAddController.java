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

//This is the controller to add a new inventory 
public class InventoryAddController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField PurchasePriceField;
    @FXML
    private TextField SalePriceField;
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
        // not needed
    }

    @FXML  //submit button 
    private void handleSubmitAction(ActionEvent event) {
        // Check for empty fields
        if (nameField.getText().trim().isEmpty()
                || SalePriceField.getText().trim().isEmpty()
                || PurchasePriceField.getText().trim().isEmpty()) {

            Alerts.showWarningAlert("Warning", "Please fill in all required fields.");

            return;
        }

        try { //validation for the name field
            String name = nameField.getText();
            if (!App.getDataHandlerInventory().isNameUnique(name)) {
                Alerts.showWarningAlert("Warning", "This name already exists or is too similar to an existing name.");

                return;
            }
            //generating a new ID
            int inventoryCode = App.getDataHandlerInventory().generateNewInventoryID();

            //getting the price from the fxml fields
            double salePrice = Double.parseDouble(SalePriceField.getText());
            double purchasePrice = Double.parseDouble(PurchasePriceField.getText());

            // Create a new StockInventory object
            StockInventory newItem = new StockInventory(name, inventoryCode, salePrice, purchasePrice);

            // Use the DataHandlerInventory to save this new inventory item
            App.getDataHandlerInventory().addItem(newItem);

            // Save the updated list to inventory.txt
            App.getDataHandlerInventory().saveData();

            // Reset the form and display a confirmation message
            App.getDataHandlerInventory().resetForm(nameField, IDField, SalePriceField, PurchasePriceField);
            Alerts.showInfoAlert("Success", "Inventory item added successfully!\n\nStock ID: " + inventoryCode + "\nItem: " + name);

        } catch (NumberFormatException e) {
            // Handle any error while converting numbers
            Alerts.showErrorAlert("Error", "Error parsing data: " + e.getMessage());

        } catch (Exception e) {
            // Handle other potential errors
            Alerts.showErrorAlert("Error", "Error adding inventory item: " + e.getMessage());

        }

        App.changeScene(2);
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        resetFields();
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

    @FXML
    public void resetFields() {
        // Reset TextFields
        nameField.setText("");
        IDField.setText("");
        SalePriceField.setText("");
        PurchasePriceField.setText("");
    }

}
