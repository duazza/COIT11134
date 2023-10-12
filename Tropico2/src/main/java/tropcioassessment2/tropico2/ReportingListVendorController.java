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

//this controller lists all the vendors
public class ReportingListVendorController implements Initializable {

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
    @Override  //upon entry all vendors will be displayed
    public void initialize(URL url, ResourceBundle rb) {
        displayAllVendors();
    }

    @FXML //reset button
    private void handleResetAction(ActionEvent event) {
        nameField.clear();
        IDField.clear();
        largeTextField.clear();
        displayAllVendors();
    }

    @FXML //this filters the list
    private void handleSearchAction(ActionEvent event) {
        // Get the entered name or ID
        String enteredName = nameField.getText().trim();
        String enteredIDStr = IDField.getText().trim();

        //  at least one of the fields is needed
        if (enteredName.isEmpty() && enteredIDStr.isEmpty()) {
            largeTextField.setText("Please provide either a name or an ID to search.");
            return;
        }

        // Access data handlers
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();  

        PersonVendor vendor = null;

        // Search by ID if provided otherwise search by name
        if (!enteredIDStr.isEmpty()) {
            try {
                int enteredID = Integer.parseInt(enteredIDStr);
                vendor = dataHandlerPeople.searchVendor("", enteredID);
            } catch (NumberFormatException e) {
                largeTextField.setText("Please enter a valid vendor ID.");
                return;
            }
        } else if (!enteredName.isEmpty()) {
            vendor = dataHandlerPeople.searchVendor(enteredName, -1);
        }

        // If vendor is found display details
        if (vendor != null) { //this creates a new string with all the details,
            StringBuilder displayText = new StringBuilder();
            displayText.append("Vendor ID: ").append(vendor.getID()).append("\n");
            displayText.append("Name: ").append(vendor.getName()).append("\n");
            displayText.append("Phone Number: ").append(vendor.getPhoneNumber()).append("\n");
            displayText.append("Address: ").append(vendor.getAddress()).append("\n");
            displayText.append("Postcode: ").append(vendor.getPostcode()).append("\n");
            displayText.append("Contact Name: ").append(vendor.getContactName()).append("\n");
            displayText.append("Payment Options: ").append(vendor.getPaymentOptions()).append("\n");

            largeTextField.setText(displayText.toString());
        } else {
            largeTextField.setText("Vendor not found with the provided name or ID.");
        }
    }

    @FXML //close button/ reset fields
    private void handleCloseAction(ActionEvent event) {
        nameField.clear();
        IDField.clear();
        largeTextField.clear();
        App.changeScene(17);
    }

    @FXML //back button and reset fields
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

    //method to display all the vendors for the reports
    private void displayAllVendors() {
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();
        List<PersonVendor> allVendors = dataHandlerPeople.getAllVendors();

        //this creates a new string with all the information. 
        StringBuilder vendorList = new StringBuilder();
        for (PersonVendor vendor : allVendors) {
            vendorList.append("Vendor ID: ").append(vendor.getID()).append(" | ");
            vendorList.append("Name: ").append(vendor.getName()).append(" | ");
            vendorList.append("Phone Number: ").append(vendor.getPhoneNumber()).append(" | ");
            vendorList.append("Address: ").append(vendor.getAddress()).append(" | ");
            vendorList.append("Postcode: ").append(vendor.getPostcode()).append(" | ");
            vendorList.append("Contact Name: ").append(vendor.getContactName()).append(" | ");
            vendorList.append("Payment Options: ").append(vendor.getPaymentOptions()).append("\n");
            vendorList.append("-------------------\n");  // Separator line between customers
        }

        largeTextField.setText(vendorList.toString());
    }
}
