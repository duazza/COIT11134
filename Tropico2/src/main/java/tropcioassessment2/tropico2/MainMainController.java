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
 * @author duane
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
        // TODO
    }    
    
    @FXML
    private void handleInventoryAction(ActionEvent event) {
          App.changeScene(2);
    }

    @FXML
    private void handleSalesAction(ActionEvent event) {
          App.changeScene(7);
    }

    @FXML
    private void handlePurchaseButton(ActionEvent event) {
          App.changeScene(11);
    }

    @FXML
    private void handleReportingAction(ActionEvent event) {
          App.changeScene(17);
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
