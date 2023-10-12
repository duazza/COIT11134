/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tropico
 */

//reporting controller - customer list
public class ReportingListCustomerController implements Initializable {

    @FXML
    private Button resetButton;
    @FXML
    private TextArea largeTextField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private Button searchButton;
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

    @Override  //brings up all customers upon entry
    public void initialize(URL url, ResourceBundle rb) {
        displayAllCustomers();
    }

    @FXML//reset button - resets all fields
    private void handleResetAction(ActionEvent event) {
        nameField.clear();
        IDField.clear();
        largeTextField.clear();
        displayAllCustomers();
    }

    @FXML //this filters down the list to a particular customer
    private void handleSearchAction(ActionEvent event) {
        // Get the entered name or ID
        String enteredName = nameField.getText().trim();
        String enteredIDStr = IDField.getText().trim();

        // Ensure at least one of the fields (name or ID is provided
        if (enteredName.isEmpty() && enteredIDStr.isEmpty()) {
            largeTextField.setText("Please provide either a name or an ID to search.");
            return;
        }

        // Access necessary data handlers
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();  // Assuming you have a method to access DataHandlerPeople

        PersonCustomer customer = null;

        // Search by ID if provided otherwise search by name
        if (!enteredIDStr.isEmpty()) {
            try {
                int enteredID = Integer.parseInt(enteredIDStr);
                customer = dataHandlerPeople.searchCustomer("", enteredID);
            } catch (NumberFormatException e) {
                largeTextField.setText("Please enter a valid customer ID.");
                return;
            }
        } else if (!enteredName.isEmpty()) {
            customer = dataHandlerPeople.searchCustomer(enteredName, -1);
        }

        // If found, display their details
        if (customer != null) {
            StringBuilder displayText = new StringBuilder();
            displayText.append("Customer ID: ").append(customer.getID()).append("\n");
            displayText.append("Name: ").append(customer.getName()).append("\n");
            displayText.append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
            displayText.append("Address: ").append(customer.getAddress()).append("\n");
            displayText.append("Postcode: ").append(customer.getPostcode()).append("\n");
            displayText.append("Contact Name: ").append(customer.getContactName()).append("\n");
            displayText.append("Payment Options: ").append(customer.getPaymentOptions()).append("\n");

            largeTextField.setText(displayText.toString());
        } else {
            largeTextField.setText("Customer not found with the provided name or ID.");
        }
    }

    @FXML //close button
    private void handleCloseAction(ActionEvent event) {
        nameField.clear();
        IDField.clear();
        largeTextField.clear();
        App.changeScene(17);
    }

    @FXML  //back button
    private void handleBackAction(ActionEvent event) {
        nameField.clear();
        IDField.clear();
        largeTextField.clear();
        App.changeScene(17);
    }

    @FXML  //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML  //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    //method to display all the customers upon entering
    private void displayAllCustomers() {
        // Access the necessary data handlers
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();  

        // call to the list of all customers
        List<PersonCustomer> allCustomers = dataHandlerPeople.getAllCustomers();  

        //stringBuilder - 
        StringBuilder displayText = new StringBuilder();

        //loop to get all the details and display
        for (PersonCustomer customer : allCustomers) {
            displayText.append("Customer ID: ").append(customer.getID()).append(" | ");
            displayText.append("Name: ").append(customer.getName()).append(" | ");
            displayText.append("Phone Number: ").append(customer.getPhoneNumber()).append(" | ");
            displayText.append("Address: ").append(customer.getAddress()).append(" | ");
            displayText.append("Postcode: ").append(customer.getPostcode()).append(" | ");
            displayText.append("Contact Name: ").append(customer.getContactName()).append(" | ");
            displayText.append("Payment Options: ").append(customer.getPaymentOptions()).append("\n");
            displayText.append("-------------------\n");  // Separator line between customers
        }

        largeTextField.setText(displayText.toString());
    }
}
