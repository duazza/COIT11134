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
public class CustomerAddController implements Initializable {

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
    //not needed
    }

    @FXML
    //save button
    private void handleSaveAction(ActionEvent event) {
        try {
            // code to retrieve the data from the text fields
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String postcode = postCodeField.getText().trim();
            String contactName = contactNameField.getText().trim();
            String paymentOptions = paymentOptionsField.getText().trim();

            //if statement for validation checks
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || postcode.isEmpty()
                    || contactName.isEmpty() || paymentOptions.isEmpty()) {
                Alerts.showErrorAlert("Error", "Please fill in all fields.");

                return;
            }
            //if statement for validation checks
            if (phone.length() != 10 || !phone.matches("\\d+")) {
                Alerts.showErrorAlert("Error", "Phone number must be exactly 10 digits.");

                return;
            }
            //if statement for validation checks
            if (postcode.length() != 4 || !postcode.matches("\\d+")) {
                Alerts.showErrorAlert("Error", "Postcode must be exactly 4 digits.");

                return;
            }

            // code to generate a new ID for the new customer
            int ID = App.getDataHandlerPeople().generateNewID();

            // Creating a new customer - PersonCustomer
            PersonCustomer newCustomer = new PersonCustomer(name, ID, phone, address, postcode, contactName, paymentOptions);

            // calling the dataHandler to add the person to the array
            App.getDataHandlerPeople().addPerson(newCustomer);

            //calling the saveData method
            App.getDataHandlerPeople().saveData();

            //this will reset the the form
            App.getDataHandlerPeople().resetForm(nameField, IDField, phoneField, addressField, postCodeField, contactNameField, paymentOptionsField);
            //code to display a confirmation
            Alerts.showInfoAlert("Confirmation", "Customer added successfully!\n\n Name: " + name + "\nID: " + ID);

        } catch (NumberFormatException e) {
            // this will handle errors when a user trys to parse a string as int
            System.err.println("Error parsing ID: " + e.getMessage());
        } catch (Exception e) {
            // Handle for other potential errors
            System.err.println("Error adding customer: " + e.getMessage());
        }

    }

    @FXML //close button
    private void handleCloseButton(ActionEvent event) {
        resetFields();
        App.changeScene(7);
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        resetFields();
        App.changeScene(7);
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
        resetFields();
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
