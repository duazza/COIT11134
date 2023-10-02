/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
/**
 * FXML Controller class
 *
 * @author duane
 */
public class ReportingOrderReceiptController implements Initializable {


    @FXML
    private Button resetButton;
    @FXML
    private TextField invoiceField;
    @FXML
    private Button searchButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextArea mainTextField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();
DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
    }    
    
    @FXML
    private void handleResetAction(ActionEvent event) {
                invoiceField.clear();
        mainTextField.clear();
    }

    
@FXML
private void handleSearchAction(ActionEvent event) {
    // Get the invoice number entered by the user
    String invoiceNumberStr = invoiceField.getText();

    try {
        // Convert the invoice number string to an integer
        int invoiceNumber = Integer.parseInt(invoiceNumberStr);

        // Access the DataHandlerTransaction instance from the main app class
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

        // Search for the transaction by invoice number
        Transaction transaction = dataHandlerTransaction.searchTransactionByInvoiceNumber(invoiceNumber);

        if (transaction != null) {
            if (transaction instanceof TransactionCustomer) {
                TransactionCustomer transactionCustomer = (TransactionCustomer) transaction;

                // Access the DataHandlerPeople instance from the main app class
                DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

                // Search for the customer using the customer ID from the transaction
                PersonCustomer customer = dataHandlerPeople.searchCustomer("", transactionCustomer.getCustomerID());

                // Format the date as dd-mm-yyyy
                String formattedDate = LocalDate.parse(transaction.getDate()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                // Prepare the text to display
                StringBuilder invoiceText = new StringBuilder();
                invoiceText.append("Invoice Number: ").append(transactionCustomer.getInvoiceNumber()).append("\n");
                invoiceText.append("Date: ").append(formattedDate).append("\n");
                invoiceText.append("Customer Name: ").append(customer.getName()).append("\n");
                invoiceText.append("Customer ID: ").append(customer.getID()).append("\n");
                invoiceText.append("Customer Type: ").append(customer.getType()).append("\n"); // Assuming you have a getType() method
                invoiceText.append("Customer Address: ").append(customer.getAddress()).append("\n");
                invoiceText.append("Customer Payment Options: ").append(customer.getPaymentOptions()).append("\n");

                // Iterate through the order lines and add them to the text, skipping placeholders
                for (int i = 0; i < transactionCustomer.getProductIDs().length; i++) {
                    if (!"N/A".equals(transactionCustomer.getProductNames()[i])) {
                        invoiceText.append("Product ID: ").append(transactionCustomer.getProductIDs()[i]).append("\n");
                        invoiceText.append("Product Name: ").append(transactionCustomer.getProductNames()[i]).append("\n");
                        invoiceText.append("Sale Price: ").append(transactionCustomer.getSalePrices()[i]).append("\n");
                        invoiceText.append("Quantity: ").append(transactionCustomer.getQuantities()[i]).append("\n");
                        invoiceText.append("Stock Total: ").append(transactionCustomer.getStockTotals()[i]).append("\n");
                    }
                }

                // Set the text in the mainTextField
                mainTextField.setText(invoiceText.toString());
            } else {
                // This section can be expanded for other transaction types (e.g., TransactionVendor, TransactionAdjustment)
                // For now, just showing a message that it's not a customer transaction
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("The provided Invoice Number doesn't belong to a Customer Transaction.");
                alert.showAndWait();
            }
        } else {
            // Transaction not found, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Transaction not found with the provided Invoice Number.");
            alert.showAndWait();
        }
    } catch (NumberFormatException e) {
        // Handle the error when the invoice number is not a valid integer
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid Invoice Number.");
        alert.showAndWait();
    }
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
