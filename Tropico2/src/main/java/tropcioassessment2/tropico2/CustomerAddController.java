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
        
        // TODO
    }    
    
    @FXML
    private void handleSaveAction(ActionEvent event) {
            try {
        // Retrieve values from text fields
        String name = nameField.getText();
        int ID = Integer.parseInt(IDField.getText());
        String phone = phoneField.getText();
        String address = addressField.getText();
        String postcode = postCodeField.getText();
        String contactName = contactNameField.getText();
        String paymentOptions = paymentOptionsField.getText();

        // Create a new PersonCustomer object
        PersonCustomer newCustomer = new PersonCustomer(name, ID, phone, address, postcode, contactName, paymentOptions);

        // Use the DataHandlerPeople to save this new customer
   App.getDataHandlerPeople().addPerson(newCustomer);
   
//save teh udpated list to people.txt
App.getDataHandlerPeople().saveData();

      App.getDataHandlerPeople().resetForm(nameField, IDField, phoneField, addressField, postCodeField, contactNameField, paymentOptionsField);
        App.getDataHandlerPeople().displayConfirmation("Customer added successfully!");

    } catch (NumberFormatException e) {
        // Handle any error while converting ID to integer
        // For instance, display an error message to the user
        System.err.println("Error parsing ID: " + e.getMessage());
    } catch (Exception e) {
        // Handle other potential errors
        System.err.println("Error adding customer: " + e.getMessage());
    }

    }

    @FXML
    private void handleCloseButton(ActionEvent event) {
        App.changeScene(7);
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
