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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author duane
 */
public class ReportingPriceListController implements Initializable {


    @FXML
    private Button closeButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextArea largeTextField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private Button resetButton;
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
    private void handleCloseAction(ActionEvent event) {
          App.changeScene(17);
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
          
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
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
