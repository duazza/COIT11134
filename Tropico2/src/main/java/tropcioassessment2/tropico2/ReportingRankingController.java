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
/**
 * FXML Controller class
 *
 * @author duane
 */
public class ReportingRankingController implements Initializable {


    @FXML
    private Button closeButton;
    @FXML
    private TextArea field1;
    @FXML
    private TextArea field2;
    @FXML
    private TextArea field3;
    @FXML
    private TextArea field4;
    @FXML
    private TextArea field5;
    @FXML
    private Button backButton;
    @FXML
    private TextArea field6;
    @FXML
    private TextArea field7;
    @FXML
    private TextArea field8;
    @FXML
    private TextArea field9;
    @FXML
    private TextArea field10;
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
