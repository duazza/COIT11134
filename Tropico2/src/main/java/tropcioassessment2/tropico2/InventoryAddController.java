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
 * @author duane
 */
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
        // TODO
    }    
    
    @FXML
    private void handleSubmitAction(ActionEvent event) {
        try {
            // Retrieve values from text fields
            String name = nameField.getText();
            int inventoryCode = Integer.parseInt(IDField.getText());
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
App.getDataHandlerInventory().displayConfirmation("Inventory item added successfully!");


        } catch (NumberFormatException e) {
            // Handle any error while converting numbers
            System.err.println("Error parsing data: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential errors
            System.err.println("Error adding inventory item: " + e.getMessage());
        }
    
        App.changeScene(2);
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
