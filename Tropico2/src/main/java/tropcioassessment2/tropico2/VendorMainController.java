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
public class VendorMainController implements Initializable {


    @FXML
    private Button addVendorButton;
    @FXML
    private Button editVendorButton;
    @FXML
    private Button purchaseOrderButton;
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
    private void handleAddAction(ActionEvent event) {
          App.changeScene(12);
    }

    @FXML
    private void handleEditAction(ActionEvent event) {
          App.changeScene(13);
    }

    @FXML
    private void handlePurhchaseAction(ActionEvent event) {
          App.changeScene(14);
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
          App.changeScene(1);
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
