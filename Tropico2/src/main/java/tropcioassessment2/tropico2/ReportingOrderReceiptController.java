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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tropico
 */
// order receipt controller
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
    @Override //this displays all orders upon entry
    public void initialize(URL url, ResourceBundle rb) {
        displayAllCustomerOrders();
    }

    @FXML  //reset button
    private void handleResetAction(ActionEvent event) {
        invoiceField.clear();
        mainTextField.clear();
        displayAllCustomerOrders();
    }

    @FXML  //search button - it will filter the customer orders to display the correct one - invoice ID
    private void handleSearchAction(ActionEvent event) {
        // Get the invoice number entered by the user
        String invoiceNumberStr = invoiceField.getText();

        try {
            // Convert invoice string to an integer
            int invoiceNumber = Integer.parseInt(invoiceNumberStr);

            // Access the DataHandlerTransaction
            DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

            // Search 
            Transaction transaction = dataHandlerTransaction.searchTransactionByInvoiceNumber(invoiceNumber);

            if (transaction != null) {
                if (transaction instanceof TransactionCustomer) {
                    TransactionCustomer transactionCustomer = (TransactionCustomer) transaction;

                    // Access the DataHandlerPeople
                    DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

                    // Search for the customer using the customer ID from the transaction
                    PersonCustomer customer = dataHandlerPeople.searchCustomer("", transactionCustomer.getCustomerID());

                    // Format the date as dd-mm-yyyy
                    String formattedDate = LocalDate.parse(transaction.getDate()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    // display
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
                            double lineTotal = transactionCustomer.getSalePrices()[i] * transactionCustomer.getQuantities()[i];
                            invoiceText.append(transactionCustomer.getProductIDs()[i]).append(" | ")
                                    .append(transactionCustomer.getProductNames()[i]).append(" | ")
                                    .append(transactionCustomer.getSalePrices()[i]).append(" | ")
                                    .append(transactionCustomer.getQuantities()[i]).append(" | ")
                                    .append("Total: ").append(lineTotal).append("\n");
                        }
                    }

                    invoiceText.append("\nOrder Total: ").append(transactionCustomer.getOrderTotal());

                    // Set the text in the mainTextField
                    mainTextField.setText(invoiceText.toString());
                } else {
                    Alerts.showErrorAlert("Error", "The provided Invoice Number doesn't belong to a Customer Transaction.");

                }
            } else {
                // Transaction not found, show an error message
                Alerts.showErrorAlert("Error", "Transaction not found with the provided Invoice Number.");

            }
        } catch (NumberFormatException e) {
            // Handle the error when the invoice number is not a valid integer
            Alerts.showErrorAlert("Error", "Please enter a valid Invoice Number.");

        }
    }

    private void displayAllCustomerOrders() {
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
        StringBuilder allOrdersText = new StringBuilder();

        // Iterate through all transactions
        for (Transaction transaction : dataHandlerTransaction.getTransactions()) {
            if (transaction instanceof TransactionCustomer) {
                TransactionCustomer transactionCustomer = (TransactionCustomer) transaction;

                // Access the DataHandlerPeople
                DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

                // Search for the customer using the customer ID from the transaction
                PersonCustomer customer = dataHandlerPeople.searchCustomer("", transactionCustomer.getCustomerID());

                // Format the date as dd-mm-yyyy
                String formattedDate = LocalDate.parse(transaction.getDate()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                // Append to the StringBuilder
                allOrdersText.append("Date: ").append(formattedDate).append(" | ");
                allOrdersText.append("Invoice Number: ").append(transactionCustomer.getInvoiceNumber()).append(" | ");

                allOrdersText.append("Customer Name: ").append(customer.getName()).append(" | ");
                allOrdersText.append("Customer ID: ").append(customer.getID()).append(" | ");
                allOrdersText.append("Customer Type: ").append(customer.getType()).append(" | "); // Assuming you have a getType() method
                allOrdersText.append("Customer Address: ").append(customer.getAddress()).append(" | ");
                allOrdersText.append("Customer Payment Options: ").append(customer.getPaymentOptions()).append(" | ");

                // Add a separator for readability
                allOrdersText.append("--------------------------------------------------\n");
            }
        }

        // Set the text in the mainTextField
        mainTextField.setText(allOrdersText.toString());
    }

    @FXML  //close button
    private void handleCloseAction(ActionEvent event) {
        App.changeScene(17);
    }

    @FXML  //back button
    private void handleBackAction(ActionEvent event) {
        App.changeScene(17);
    }

    @FXML//logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML  //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

}
