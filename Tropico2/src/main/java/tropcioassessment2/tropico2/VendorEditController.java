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
//controller to edit the vendor
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
        // not needed
    }

    @FXML //save button
    private void handleSaveAction(ActionEvent event) {

        {//check for ID and Name 
            String searchName = searchNameField.getText().trim();
            String searchIDString = searchIDField.getText().trim();

            // Check if both fields are empty
            if (searchName.isEmpty() && searchIDString.isEmpty()) {
                Alerts.showErrorAlert("Error", "No Data to save!");
                return;
            }

            try {
                // Retrieve edited details
                String editedName = editNameField.getText().trim().isEmpty() ? nameField.getText().trim() : editNameField.getText().trim();
                String editedPhone = editPhoneField.getText().trim().isEmpty() ? phoneField.getText().trim() : editPhoneField.getText().trim();
                String editedAddress = editAddressField.getText().trim().isEmpty() ? addressField.getText().trim() : editAddressField.getText().trim();
                String editedPostcode = editPostCodeField.getText().trim().isEmpty() ? postCodeField.getText().trim() : editPostCodeField.getText().trim();
                String editedContactName = editContactNameField.getText().trim().isEmpty() ? contactNameField.getText().trim() : editContactNameField.getText().trim();
                String editedPaymentOptions = editPaymentOptionsField.getText().trim().isEmpty() ? paymentOptionField.getText().trim() : editPaymentOptionsField.getText().trim();

                if (editedName.isEmpty() || editedPhone.isEmpty() || editedAddress.isEmpty() || editedPostcode.isEmpty() || editedContactName.isEmpty() || editedPaymentOptions.isEmpty()) {
                    Alerts.showErrorAlert("Error", "Please fill in all fields.");
                    return;
                }

                if (editedPhone.length() != 10 || !editedPhone.matches("\\d+")) {
                    Alerts.showErrorAlert("Error", "Phone number must be exactly 10 digits.");
                    return;
                }

                if (editedPostcode.length() != 4 || !editedPostcode.matches("\\d+")) {
                    Alerts.showErrorAlert("Error", "Postcode must be exactly 4 digits.");
                    return;
                }

                // Update the customer's details
                boolean success = App.getDataHandlerPeople().updateVendorDetails(
                        nameField.getText().trim(), // Original name for reference
                        Integer.parseInt(IDField.getText().trim()), // Original ID for reference
                        editedName, editedPhone, editedAddress, editedPostcode, editedContactName, editedPaymentOptions
                );

                // Display a confirmation if the update was successful
                if (success) {
                    Alerts.showInfoAlert("Information", "Vendor details updated successfully!");
                } else {
                    Alerts.showWarningAlert("Warning", "Error updating vendor details!");
                }
            } catch (NumberFormatException e) {
                // Handle error while parsing ID
                Alerts.showErrorAlert("Error", "Error parsing ID: " + e.getMessage());
            } catch (Exception e) {
                // Handle other potential errors
                Alerts.showErrorAlert("Error", "Error updating vendor: " + e.getMessage());
            }
            App.getDataHandlerPeople().saveData(); //save data
            //reset the form
            App.getDataHandlerPeople().resetForm(nameField, IDField, phoneField, addressField, postCodeField, contactNameField, paymentOptionField);
            App.getDataHandlerPeople().resetForm(editNameField, editPhoneField, editAddressField, editPostCodeField, editContactNameField, editPaymentOptionsField);
            App.getDataHandlerPeople().resetForm(searchNameField, searchIDField);
            
            resetAllFields();
        }
    }

    @FXML//close button
    private void handleCloseAction(ActionEvent event) {
        resetAllFields();
        App.changeScene(11);
    }

    @FXML //search button 
    private void handleSearchEnterAction(ActionEvent event) {
        try {
            // Retrieve search for the name or the ID and display the current details = ready to edit 
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
                Alerts.showInfoAlert("Information", "Vendor not found!");
            }
        } catch (NumberFormatException e) {
            // Handle error while parsing ID
            Alerts.showErrorAlert("Error", "Error searching for vendor: " + e.getMessage());

        } catch (Exception e) {
            // Handle other potential errors
            Alerts.showErrorAlert("Error", "Error searching for vendor: " + e.getMessage());

        }
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        resetAllFields();
        App.changeScene(11);
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
        resetAllFields();
        App.exit();
    }

    //method to reset all fields
    private void resetAllFields() {
        nameField.setText("");
        IDField.setText("");
        phoneField.setText("");
        addressField.setText("");
        postCodeField.setText("");
        contactNameField.setText("");
        paymentOptionField.setText("");
        searchNameField.setText("");
        searchIDField.setText("");
        editNameField.setText("");
        editPhoneField.setText("");
        editAddressField.setText("");
        editPostCodeField.setText("");
        editContactNameField.setText("");
        editPaymentOptionsField.setText("");
    }
}
