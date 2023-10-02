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
public class VendorEditController implements Initializable {


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
        // TODO
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
        boolean success = App.getDataHandlerPeople().updateVendorDetails(
            nameField.getText().trim(), // Original name for reference
            Integer.parseInt(IDField.getText().trim()), // Original ID for reference
            editedName, editedPhone, editedAddress, editedPostcode, editedContactName, editedPaymentOptions
        );

        // Display a confirmation if the update was successful
        if (success) {
            DataHandlerPeople.displayMessage("Vendor details updated successfully!", Alert.AlertType.INFORMATION);
        } else {
            DataHandlerPeople.displayMessage("Error updating vendor details!", Alert.AlertType.WARNING);
        }
    } catch (NumberFormatException e) {
        // Handle error while parsing ID
        DataHandlerPeople.displayMessage("Error parsing ID: " + e.getMessage(), Alert.AlertType.ERROR);
    } catch (Exception e) {
        // Handle other potential errors
        DataHandlerPeople.displayMessage("Error updating vendor: " + e.getMessage(), Alert.AlertType.ERROR);
    }
      App.getDataHandlerPeople().resetForm(nameField, IDField, phoneField, addressField, postCodeField, contactNameField, paymentOptionField);
App.getDataHandlerPeople().resetForm(editNameField, editPhoneField, editAddressField, editPostCodeField, editContactNameField, editPaymentOptionsField);
App.getDataHandlerPeople().resetForm(searchNameField, searchIDField);
}

    @FXML
    private void handleCloseAction(ActionEvent event) {
          App.changeScene(11);
    }

      @FXML
   private void handleSearchEnterAction(ActionEvent event) {
    try {
        // Retrieve search criteria
        String searchName = searchNameField.getText().trim();
        String searchIDString = searchIDField.getText().trim();
        int searchID = (searchIDString.isEmpty()) ? -1 : Integer.parseInt(searchIDString);
        
        // Use DataHandlerPeople to search for the customer
        PersonVendor foundVendor = App.getDataHandlerPeople().searchVendor(searchName, searchID);

        // Display the customer details if found
        if (foundVendor != null) {
            nameField.setText(foundVendor.getName());
            IDField.setText(String.valueOf(foundVendor.getID()));
            phoneField.setText(foundVendor.getPhoneNumber());
            addressField.setText(foundVendor.getAddress());
            postCodeField.setText(foundVendor.getPostcode());
            contactNameField.setText(foundVendor.getContactName());
            paymentOptionField.setText(foundVendor.getPaymentOptions());
        } else {
            // Display a message that customer was not found
            // (You might have a method or mechanism to display messages to the user)
            DataHandlerPeople.displayMessage("Vendor not found!", Alert.AlertType.INFORMATION);
        }
    } catch (NumberFormatException e) {
        // Handle error while parsing ID
        DataHandlerPeople.displayMessage("Error parsing ID: " + e.getMessage(), Alert.AlertType.ERROR);
    } catch (Exception e) {
        // Handle other potential errors
        DataHandlerPeople.displayMessage("Error searching for vendor: " + e.getMessage(), Alert.AlertType.ERROR);
    }
}

    @FXML
    private void handleBackAction(ActionEvent event) {
          App.changeScene(11);
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
