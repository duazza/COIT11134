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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
/**
 * FXML Controller class
 *
 * @author duane
 */
public class ReportingPurchaseHistoryController implements Initializable {


    @FXML
    private Button closeButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextArea largeTextField;
    @FXML
    private Label fromDateField;
    @FXML
    private DatePicker toDateField;
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
