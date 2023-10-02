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
public class VendorPurchaseOrderController implements Initializable {


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
    private TextField paymentOptionField;
    @FXML
    private TextField searchNameField;
    @FXML
    private TextField searchIDField;
    @FXML
    private Button SearchButton;
    @FXML
    private Button submitButton;
    @FXML
    private TextField stockQTY1Field;
    @FXML
    private TextField stockName1Field;
    @FXML
    private TextField ID1Field;
    @FXML
    private TextField stockTotal1Field;
    @FXML
    private TextField stockQTY2Field;
    @FXML
    private TextField stockName2Field;
    @FXML
    private TextField ID2Field;
    @FXML
    private TextField stockTotal2Field;
    @FXML
    private TextField stockQTY3Field;
    @FXML
    private TextField stockName3Field;
    @FXML
    private TextField ID3Field;
    @FXML
    private TextField stockTotal3Field;
    @FXML
    private TextField stockQTY4Field;
    @FXML
    private TextField stockName4Field;
    @FXML
    private TextField ID4Field;
    @FXML
    private TextField stockTotal4Field;
    @FXML
    private TextField stockQTY5Field;
    @FXML
    private TextField stockName5Field;
    @FXML
    private TextField ID5Field;
    @FXML
    private TextField stockTotal5Field;
    @FXML
    private TextField stockQTY6Field;
    @FXML
    private TextField stockName6Field;
    @FXML
    private TextField ID6Field;
    @FXML
    private TextField stockTotal6Field;
    @FXML
    private TextField orderTotalField;
    @FXML
    private Button checkIDButton;
    @FXML
    private TextField stock$1Field;
    @FXML
    private TextField stock$2Field;
    @FXML
    private TextField stock$3Field;
    @FXML
    private TextField stock$4Field;
    @FXML
    private TextField stock$5Field;
    @FXML
    private TextField stock$6Field;
    @FXML
    private Button calculateButton;
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
    private void handleSubmitAction(ActionEvent event) {
          App.changeScene(11);
    }

    @FXML
    private void handleCheckIDAction(ActionEvent event) {
          
    }

    @FXML
    private void handleCalculateAction(ActionEvent event) {
          
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
          App.changeScene(11);
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
