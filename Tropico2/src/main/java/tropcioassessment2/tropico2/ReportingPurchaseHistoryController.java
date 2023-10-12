/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

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
    private DatePicker fromDateField;
    @FXML
    private DatePicker toDateField;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button searchButton;
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
          // Clear the text area
    largeTextField.setText("");

    // Reset the date fields
    fromDateField.setValue(null);
    toDateField.setValue(null);
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

   @FXML
private void handleSearchAction(ActionEvent event) {
    // Extract dates from date pickers
    LocalDate fromDate = fromDateField.getValue();
    LocalDate finalToDate;  // final variable needed for lambda func

    // If toDate is empty, use the current date
    if (toDateField.getValue() == null) {
        finalToDate = LocalDate.now();
    } else {
        finalToDate = toDateField.getValue();
    }

    // Check if the fromDate is empty and show an error if it is
    if (fromDate == null) {
        largeTextField.setText("Please select a 'From' date.");
        return;
    }

    // Access the necessary data handlers
    DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();  // Assuming you have a method to access DataHandlerTransaction
    List<Transaction> allTransactions = dataHandlerTransaction.getTransactionList();

    // Filter out the transactions based on the date range
    List<TransactionVendor> filteredTransactions = allTransactions.stream()
            .filter(t -> t instanceof TransactionVendor)
            .map(t -> (TransactionVendor) t)
            .filter(t -> {
                LocalDate transactionDate = LocalDate.parse(t.getDate());  // Convert String to LocalDate
                return !transactionDate.isBefore(fromDate) && !transactionDate.isAfter(finalToDate);
            })
            .collect(Collectors.toList());

  

    StringBuilder displayText = new StringBuilder();
    for (TransactionVendor transaction : filteredTransactions) {
        displayText.append(dataHandlerTransaction.displayTransactionVendorDetails(transaction)).append("\n\n");
    }

    if (displayText.length() == 0) {
        largeTextField.setText("No vendor transactions found for the selected date range.");
    } else {
        largeTextField.setText(displayText.toString());
    }
}


}
