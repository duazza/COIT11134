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
/**
 * FXML Controller class
 *
 * @author Tropico
 */
public class MainMainController implements Initializable {


    @FXML
    private Button inventoryButton;
    @FXML
    private Button salesButton;
    @FXML
    private Button purchaseButton;
    @FXML
    private Button reportingButton;
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
    
    @FXML//this opens the inventory - main 
    private void handleInventoryAction(ActionEvent event) {
          App.changeScene(2);
    }

    @FXML // opens up the customer main
    private void handleSalesAction(ActionEvent event) {
          App.changeScene(7);
    }

    @FXML  //opens up the purchase main
    private void handlePurchaseButton(ActionEvent event) {
          App.changeScene(11);
    }

    @FXML  // opens the reporting 
    private void handleReportingAction(ActionEvent event) {
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

}
