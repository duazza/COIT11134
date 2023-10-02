/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.ArrayList;
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
public class CustomerEditController implements Initializable {


    @FXML
    private Button saveButton;
    @FXML
    private TextField editNameField;
    @FXML
    private TextField editPhoneField;
    @FXML
    private TextField editAddressField;
    @FXML
    private TextField editPostCodeField;
    @FXML
    private TextField editContactNameField;
    @FXML
    private TextField editPaymentOptionsField;
    @FXML
    private Button closeButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postCodeField;
    @FXML
    private TextField contactNameField;
    @FXML
    private TextField paymentOptionField;
    @FXML
    private TextField searchNameField;
    @FXML
    private TextField searchIDField;
    @FXML
    private Button searchEnterButton;
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
 
    }    
    
   
@FXML
private void handleSaveAction(ActionEvent event) {
    try {
        // Retrieve edited details
        String editedName = editNameField.getText().trim().isEmpty() ? nameField.getText().trim() : editNameField.getText().trim();
        String editedPhone = editPhoneField.getText().trim().isEmpty() ? phoneField.getText().trim() : editPhoneField.getText().trim();
        String editedAddress = editAddressField.getText().trim().isEmpty() ? addressField.getText().trim() : editAddressField.getText().trim();
        String editedPostcode = editPostCodeField.getText().trim().isEmpty() ? postCodeField.getText().trim() : editPostCodeField.getText().trim();
        String editedContactName = editContactNameField.getText().trim().isEmpty() ? contactNameField.getText().trim() : editContactNameField.getText().trim();
        String editedPaymentOptions = editPaymentOptionsField.getText().trim().isEmpty() ? paymentOptionField.getText().trim() : editPaymentOptionsField.getText().trim();

        // Update the customer's details
        boolean success = App.getDataHandlerPeople().updateCustomerDetails(
            nameField.getText().trim(), // Original name for reference
            Integer.parseInt(IDField.getText().trim()), // Original ID for reference
            editedName, editedPhone, editedAddress, editedPostcode, editedContactName, editedPaymentOptions
        );

        // Save the entire updated list to the disk if the update was successful
        if (success && !App.getDataHandlerPeople().saveData()) {
            DataHandlerPeople.displayMessage("Error saving updated customer to disk!", Alert.AlertType.ERROR);
        } else if (success) {
            DataHandlerPeople.displayMessage("Customer details updated successfully!", Alert.AlertType.INFORMATION);
        } else {
            DataHandlerPeople.displayMessage("Error updating customer details!", Alert.AlertType.WARNING);
        }

    } catch (NumberFormatException e) {
        // Handle error while parsing ID
        DataHandlerPeople.displayMessage("Error parsing ID: " + e.getMessage(), Alert.AlertType.ERROR);
    } catch (Exception e) {
        // Handle other potential errors
        DataHandlerPeople.displayMessage("Error updating customer: " + e.getMessage(), Alert.AlertType.ERROR);
    }

    // Reset the form fields after processing
    App.getDataHandlerPeople().resetForm(nameField, IDField, phoneField, addressField, postCodeField, contactNameField, paymentOptionField);
App.getDataHandlerPeople().resetForm(editNameField, editPhoneField, editAddressField, editPostCodeField, editContactNameField, editPaymentOptionsField);
App.getDataHandlerPeople().resetForm(searchNameField, searchIDField);
}

    @FXML
    private void handleCloseAction(ActionEvent event) {
        App.changeScene(7);
    }

    @FXML
private void handleSearchEnterAction(ActionEvent event) {
    try {
        // Retrieve search criteria
        String searchName = searchNameField.getText().trim();
        String searchIDString = searchIDField.getText().trim();
        int searchID = (searchIDString.isEmpty()) ? -1 : Integer.parseInt(searchIDString);
        
        // Use DataHandlerPeople to search for the customer
        PersonCustomer foundCustomer = App.getDataHandlerPeople().searchCustomer(searchName, searchID);

        // Display the customer details if found
        if (foundCustomer != null) {
            nameField.setText(foundCustomer.getName());
            IDField.setText(String.valueOf(foundCustomer.getID()));
            phoneField.setText(foundCustomer.getPhoneNumber());
            addressField.setText(foundCustomer.getAddress());
            postCodeField.setText(foundCustomer.getPostcode());
            contactNameField.setText(foundCustomer.getContactName());
            paymentOptionField.setText(foundCustomer.getPaymentOptions());
        } else {
            // Display a message that customer was not found
            // (You might have a method or mechanism to display messages to the user)
            DataHandlerPeople.displayMessage("Customer not found!", Alert.AlertType.INFORMATION);
        }
    } catch (NumberFormatException e) {
        // Handle error while parsing ID
        DataHandlerPeople.displayMessage("Error parsing ID: " + e.getMessage(), Alert.AlertType.ERROR);
    } catch (Exception e) {
        // Handle other potential errors
        DataHandlerPeople.displayMessage("Error searching for customer: " + e.getMessage(), Alert.AlertType.ERROR);
    }
}


    @FXML
    private void handleBackAction(ActionEvent event) {
        App.changeScene(7);
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
