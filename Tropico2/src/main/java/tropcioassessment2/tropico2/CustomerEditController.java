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
        //not needed
    }

    @FXML
    //save button
    private void handleSaveAction(ActionEvent event) {
        {
            //retrieving the data from the fields
            String searchName = searchNameField.getText().trim();
            String searchIDString = searchIDField.getText().trim();

            // Checks if both fields are empty
            if (searchName.isEmpty() && searchIDString.isEmpty()) {
                //alert if there is no data to save
                Alerts.showErrorAlert("Error", "No data to save!");

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

                // Validate the edited details
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
                boolean success = App.getDataHandlerPeople().updateCustomerDetails(
                        nameField.getText().trim(), // Original name for reference
                        Integer.parseInt(IDField.getText().trim()), // Original ID for reference
                        editedName, editedPhone, editedAddress, editedPostcode, editedContactName, editedPaymentOptions
                );

                //calls the displayMessage method/alerts
                if (success && !App.getDataHandlerPeople().saveData()) {
                    Alerts.showErrorAlert("Error Title", "Error saving updated customer to file!");
                } else if (success) {
                    //calls the displayMessage method/alerts
                    Alerts.showInfoAlert("Info Title", "Customer details updated successfully!");
                } else {
                    //calls the displayMessage method
                    Alerts.showErrorAlert("Error Title", "Error updating customer details!");

                }
            } catch (NumberFormatException e) {
                // Handle error while parsing ID
                Alerts.showErrorAlert("Error", "Error parsing ID: " + e.getMessage());
            } catch (Exception e) {
                // Handle other potential errors
                Alerts.showErrorAlert("Error", "Error updating customer: " + e.getMessage());
            }

            //saves form - calls the method 
            App.getDataHandlerPeople().saveData();
            // Reset the form fields 
            App.getDataHandlerPeople().resetForm(nameField, IDField, phoneField, addressField, postCodeField, contactNameField, paymentOptionField);
            App.getDataHandlerPeople().resetForm(editNameField, editPhoneField, editAddressField, editPostCodeField, editContactNameField, editPaymentOptionsField);
            App.getDataHandlerPeople().resetForm(searchNameField, searchIDField);
        }
    }

    @FXML //close button
    private void handleCloseAction(ActionEvent event) {
        resetAllFields();//reset
        App.changeScene(7);
    }

    @FXML
    //search button to search for the customers details
    private void handleSearchEnterAction(ActionEvent event) {
        try {
            // search criteria
            String searchName = searchNameField.getText().trim();
            String searchIDString = searchIDField.getText().trim();
            //if the name is used this allows the search to work if ID is empty
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
                Alerts.showErrorAlert("Error", "Customer not found!");

            }
        } catch (NumberFormatException e) {
            // Handle error while parsing ID
            Alerts.showErrorAlert("Error", "Error parsing ID: " + e.getMessage());

        } catch (Exception e) {
            // Handle other potential errors
            Alerts.showErrorAlert("Error", "Error updating customer: " + e.getMessage());

        }
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        resetAllFields();
        App.changeScene(7);
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
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
