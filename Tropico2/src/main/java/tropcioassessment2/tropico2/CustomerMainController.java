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
public class CustomerMainController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button orderButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //not needed
    }

    @FXML //takes user to 'add a customer'
    private void handleAddAction(ActionEvent event) {
        App.changeScene(8);
    }

    @FXML //takes user to 'edit a customer'
    private void handleEditAction(ActionEvent event) {
        App.changeScene(9);
    }

    @FXML //takes the user to place a customer order
    private void handleOrderAction(ActionEvent event) {
        App.changeScene(10);
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML  //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    @FXML  //back button
    private void handleBackAction(ActionEvent event) {
        App.changeScene(1);
    }

}
