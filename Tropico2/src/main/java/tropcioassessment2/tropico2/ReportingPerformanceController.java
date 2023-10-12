/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tropico
 */

//controller for the performance of a product 
public class ReportingPerformanceController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private Button searchButton;
    @FXML
    private TextArea largeTextField;
    @FXML
    private Button backButton;
    @FXML
    private DatePicker fromDateField;
    @FXML
    private DatePicker toDateField;
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
        // Clear the text fields
        nameField.setText("");
        IDField.setText("");

        // Reset the date pickers
        fromDateField.setValue(null);
        toDateField.setValue(null);

        // Clear the TextArea
        largeTextField.setText("");
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        String itemName = nameField.getText().trim();
        String itemIDStr = IDField.getText().trim();

        // Convert itemIDStr to int if it's not empty
        int itemID = itemIDStr.isEmpty() ? -1 : Integer.parseInt(itemIDStr);

        // Get the date range
        LocalDate fromDate = fromDateField.getValue();
        LocalDate toDate = toDateField.getValue() != null ? toDateField.getValue() : LocalDate.now();

        // Fetch transactions based on the item name or ID
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
        List<Transaction> allTransactions = dataHandlerTransaction.getTransactions();

        // Filter transactions based on the entered item name/ID and date range
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof TransactionCustomer) {
                TransactionCustomer transactionCustomer = (TransactionCustomer) transaction;
                for (int i = 0; i < transactionCustomer.getProductIDs().length; i++) {
                    boolean nameMatch = itemName.equalsIgnoreCase(transactionCustomer.getProductNames()[i]);
                    boolean idMatch = itemID == Integer.parseInt(transactionCustomer.getProductIDs()[i]);
                    LocalDate transactionDate = LocalDate.parse(transactionCustomer.getDate());
                    boolean dateInRange = !transactionDate.isBefore(fromDate) && !transactionDate.isAfter(toDate);
                    if ((nameMatch || idMatch) && dateInRange) {
                        filteredTransactions.add(transactionCustomer);
                        break;  // Break out of the inner loop once a match is found for this transaction
                    }
                }
            }
        }

        // Format and display the transactions
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : filteredTransactions) {
            if (transaction instanceof TransactionCustomer) {
                TransactionCustomer transactionCustomer = (TransactionCustomer) transaction;
                for (int i = 0; i < transactionCustomer.getProductIDs().length; i++) {
                    sb.append(transactionCustomer.getDate()).append(" | ")
                            .append(transactionCustomer.getProductNames()[i]).append(" | ")
                            .append(transactionCustomer.getSalePrices()[i]).append(" | ")
                            .append(transactionCustomer.getQuantities()[i]).append(" | ")
                            .append(transactionCustomer.getSalePrices()[i] * transactionCustomer.getQuantities()[i]).append("\n");
                }
            }
        }

        // Set the text in the TextArea
        largeTextField.setText(sb.toString());
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        App.changeScene(17);
    }

    @FXML//logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

}
