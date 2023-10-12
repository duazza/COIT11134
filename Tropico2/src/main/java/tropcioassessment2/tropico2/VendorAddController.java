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
//controller for the 'add vendor'
public class VendorAddController implements Initializable {

    @FXML
    private Button saveButton;
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
    private TextField paymentOptionsField;
    @FXML
    private Button closeButton;
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

    @FXML//save button 
    private void handleSaveAction(ActionEvent event) {
        try {
            // Validation checks
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String postcode = postCodeField.getText().trim();
            String contactName = contactNameField.getText().trim();
            String paymentOptions = paymentOptionsField.getText().trim();

            //validation check
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || postcode.isEmpty()
                    || contactName.isEmpty() || paymentOptions.isEmpty()) {
                Alerts.showErrorAlert("Error", "Please fill in all fields.");
                return;
            }
            //validation check

            if (phone.length() != 10 || !phone.matches("\\d+")) {
                Alerts.showErrorAlert("Error", "Phone number must be exactly 10 digits.");
                return;
            }

            //validation check
            if (postcode.length() != 4 || !postcode.matches("\\d+")) {
                Alerts.showErrorAlert("Error", "Postcode must be exactly 4 digits.");
                return;
            }

            // Generate a new ID for the new customer
            int ID = App.getDataHandlerPeople().generateNewID();

            // Create a new PersonVENDOR object
            PersonVendor newVendor = new PersonVendor(name, ID, phone, address, postcode, contactName, paymentOptions);

            // Use the DataHandlerPeople to save this new customer
            App.getDataHandlerPeople().addPerson(newVendor);

            //save teh udpated list to people.txt
            App.getDataHandlerPeople().saveData();

            //reset the form
            App.getDataHandlerPeople().resetForm(nameField, IDField, phoneField, addressField, postCodeField, contactNameField, paymentOptionsField);
            Alerts.showInfoAlert("Confirmation", "Vendor added successfully!\n\n Name: " + name + "\nID: " + ID);

        } catch (NumberFormatException e) {
            System.err.println("Error parsing ID: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential errors
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    @FXML//close button
    private void handleCloseButton(ActionEvent event) {
        resetFields();
        App.changeScene(11);
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        resetFields();
        App.changeScene(11);
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    //method to reset the fields of the controller
    private void resetFields() {
        nameField.setText("");
        IDField.setText("");
        phoneField.setText("");
        addressField.setText("");
        postCodeField.setText("");
        contactNameField.setText("");
        paymentOptionsField.setText("");
    }
}
