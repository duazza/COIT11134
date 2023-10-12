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

//this is the main inventory controller
public class InventoryMainController implements Initializable {


    @FXML
    private Button addIButton;
    @FXML
    private Button editButton;
    @FXML
    private Button stocktakeButton;
    @FXML
    private Button currentButton;
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
    
    @FXML  //takes user to the 'add item'
    private void handleAddAction(ActionEvent event) {
        App.changeScene(3);
    }

    @FXML//takes user to the 'edit' screen
    private void handleEditAction(ActionEvent event) {
        App.changeScene(4);
    }

    @FXML  //opens thhe stock take controller
    private void handleStocktakeAction(ActionEvent event) {
        App.changeScene(5);
    }

    @FXML  // opens the current stock
    private void handleCurrenAction(ActionEvent event) {
        App.changeScene(6);
        
    }

    @FXML  //back button
    private void handleBackAction(ActionEvent event) {
        App.changeScene(1);
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
