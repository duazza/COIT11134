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

//This is the main controller for the reporting menu
public class ReportingMainController implements Initializable {

    @FXML
    private Button salesRankingButton;
    @FXML
    private Button profitMarginButton;
    @FXML
    private Button priceListButton;
    @FXML
    private Button orderHistoryButton;
    @FXML
    private Button customerListButton;
    @FXML
    private Button vendorListButton;
    @FXML
    private Button purchaseHistoryAction;
    @FXML
    private Button viewOrderReceiptButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // not needed
    }

    @FXML //sales ranking controller
    private void handleSalesRankingAction(ActionEvent event) {
        App.changeScene(23);
    }

    @FXML //profit margin controller
    private void handleProfitMarginAction(ActionEvent event) {
        App.changeScene(21);
    }

    @FXML  //price list controller
    private void handlePriceListAction(ActionEvent event) {
        App.changeScene(20);
    }

    @FXML  //order history controller
    private void handleOrderHistoryAction(ActionEvent event) {
        App.changeScene(19);
    }

    @FXML  //customer list controller
    private void handleCutomerListAction(ActionEvent event) {
        App.changeScene(15);
    }

    @FXML //vendor list controller
    private void handleVedorListAction(ActionEvent event) {
        App.changeScene(16);
    }

    @FXML  //purchase history controller
    private void handlePurchaseHistoryAction(ActionEvent event) {
        App.changeScene(22);
    }

    @FXML  //order receipt controller
    private void handleViewReceiptAction(ActionEvent event) {
        App.changeScene(18);
    }

    @FXML //close button
    private void handleCloseAction(ActionEvent event) {
        App.changeScene(1);
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        App.changeScene(1);
    }

}
