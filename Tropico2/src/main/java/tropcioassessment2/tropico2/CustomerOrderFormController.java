/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tropico
 */
public class CustomerOrderFormController implements Initializable {

    private DataHandlerInventory dataHandlerInventory;
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleSubmitAction(ActionEvent event) {
        // 1. Retrieve the value of the orderTotalField
        String orderTotalStr = orderTotalField.getText().trim();
        double orderTotal = 0.0;

        // 2. Convert this value to a double
        try {
            orderTotal = Double.parseDouble(orderTotalStr);
        } catch (NumberFormatException e) {
            Alerts.showErrorAlert("Error", "Invalid order total value. Please check your order details.");
            return;
        }

        // 3. Check if the value is 0.0
        if (orderTotal == 0.0) {
            Alerts.showErrorAlert("Error", "Order total is zero. Please add items to your order before submitting.");
            return;
        }

        // Access the necessary data handlers
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

        try {
            // retrieve customer details
            String customerIDStr = IDField.getText();
            int customerID = Integer.parseInt(customerIDStr); // Convert to int

            // Create lists to store the product details - temporary for the order form/stock amounts because we don't know the length of the arrays
            List<String> productIDsList = new ArrayList<>();
            List<String> productNamesList = new ArrayList<>();
            List<Double> salePricesList = new ArrayList<>();
            List<Double> quantitiesList = new ArrayList<>();

            // retrieves product details from the fields and add them to the lists
            addProductDetailsToLists(productIDsList, productNamesList, salePricesList, quantitiesList,
                    ID1Field, stockName1Field, stock$1Field, stockQTY1Field);
            addProductDetailsToLists(productIDsList, productNamesList, salePricesList, quantitiesList,
                    ID2Field, stockName2Field, stock$2Field, stockQTY2Field);
            addProductDetailsToLists(productIDsList, productNamesList, salePricesList, quantitiesList,
                    ID3Field, stockName3Field, stock$3Field, stockQTY3Field);
            addProductDetailsToLists(productIDsList, productNamesList, salePricesList, quantitiesList,
                    ID4Field, stockName4Field, stock$4Field, stockQTY4Field);
            addProductDetailsToLists(productIDsList, productNamesList, salePricesList, quantitiesList,
                    ID5Field, stockName5Field, stock$5Field, stockQTY5Field);
            addProductDetailsToLists(productIDsList, productNamesList, salePricesList, quantitiesList,
                    ID6Field, stockName6Field, stock$6Field, stockQTY6Field);

            // Convert lists to arrays to stick with current code
            String[] productIDs = productIDsList.toArray(new String[0]);
            String[] productNames = productNamesList.toArray(new String[0]);
            double[] salePrices = salePricesList.stream().mapToDouble(Double::doubleValue).toArray();
            double[] quantities = quantitiesList.stream().mapToDouble(Double::doubleValue).toArray();

            DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();

            // Create a new TransactionCustomer object
            TransactionCustomer transaction = new TransactionCustomer(customerID, productIDs, productNames, salePrices, quantities);

            // Print the object to inspect its content
            System.out.println(transaction.toString());
            //update the stock levels
            dataHandlerTransaction.updateStockInventory(transaction);

            // Add the transaction to the transaction list
            dataHandlerTransaction.addTransaction(transaction);

            // Save the updated transaction list to the file
            dataHandlerTransaction.writeDataFile();

            // Create the alert message with the invoice details
            String alertMessage = "Order has been saved/submitted!\n";
            alertMessage += "Invoice ID: " + transaction.getInvoiceNumber() + "\n";
            alertMessage += "Invoice Type: " + (transaction.isVendor() ? "Vendor" : "Customer");

            // Show a confirmation message to the user
            Alerts.showInfoAlert("Success", alertMessage);

            // Navigate back to scene7
            App.changeScene(7);

        } catch (NumberFormatException e) {
            // Handle the error when the customerID is not a valid integer
            Alerts.showErrorAlert("Error", "Please enter a valid Customer ID.");

        }
    }

    //method to add details to the lists
    private void addProductDetailsToLists(List<String> productIDs, List<String> productNames, List<Double> salePrices, List<Double> quantities,
            TextField idField, TextField nameField, TextField priceField, TextField qtyField) {
        String productID = idField.getText().trim();
        String productName = nameField.getText().trim();
        String salePriceStr = priceField.getText().trim();
        String quantityStr = qtyField.getText().trim();

        //if statemebt if sections are empty
        if (!productID.isEmpty() && !salePriceStr.isEmpty() && !quantityStr.isEmpty()) {
            productIDs.add(productID);
            productNames.add(productName);
            salePrices.add(Double.parseDouble(salePriceStr));
            quantities.add(Double.parseDouble(quantityStr));
        }
        resetFields();
    }

    @FXML  //checked the ID of the products and returns the name and price. 
    private void handleCheckIDAction(ActionEvent event) {
        // Access the DataHandlerInventory instance from the main app class
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();

        // Extracting product IDs from the form
        String[] productIDs = {
            ID1Field.getText(),
            ID2Field.getText(),
            ID3Field.getText(),
            ID4Field.getText(),
            ID5Field.getText(),
            ID6Field.getText()
        };

        //loop to the check the IDs and retrieve data
        for (int i = 0; i < productIDs.length; i++) {
            if (productIDs[i].isEmpty()) {
                continue;  // Skip processing for empty product IDs
            }

            try {
                // Convert the product ID string to an integer
                int productCode = Integer.parseInt(productIDs[i]);

                // Searching for the product in the inventory
                StockInventory product = dataHandlerInventory.searchItemByCode(productCode);

                if (product != null) {
                    // If the product is found, populate the fields that had ID
                    switch (i) {  //switch fills in the details of the product names/sale price. 
                        case 0:
                            stockName1Field.setText(product.getName());
                            stock$1Field.setText(String.valueOf(product.getSalePrice()));
                            stockQTY1Field.setUserData(product.getStockLevel());
                            break;
                        case 1:
                            stockName2Field.setText(product.getName());
                            stock$2Field.setText(String.valueOf(product.getSalePrice()));
                            stockQTY2Field.setUserData(product.getStockLevel());
                            break;
                        case 2:
                            stockName3Field.setText(product.getName());
                            stock$3Field.setText(String.valueOf(product.getSalePrice()));
                            stockQTY3Field.setUserData(product.getStockLevel());
                            break;
                        case 3:
                            stockName4Field.setText(product.getName());
                            stock$4Field.setText(String.valueOf(product.getSalePrice()));
                            stockQTY4Field.setUserData(product.getStockLevel());
                            break;
                        case 4:
                            stockName5Field.setText(product.getName());
                            stock$5Field.setText(String.valueOf(product.getSalePrice()));
                            stockQTY5Field.setUserData(product.getStockLevel());
                            break;
                        case 5:
                            stockName6Field.setText(product.getName());
                            stock$6Field.setText(String.valueOf(product.getSalePrice()));
                            stockQTY6Field.setUserData(product.getStockLevel());
                            break;
                        default:
                            break;
                    }
                } else {
                    // Handle error - product not found in the inventory
                    Alerts.showErrorAlert("Error Title", "Product not found with the provided ID.");

                }

            } catch (NumberFormatException e) {
                // Handle the error when the product ID is not a valid integer
                Alerts.showErrorAlert("Error Title", "Please enter a valid product ID.");

            }
        }
    }

    @FXML
    public void handleCalculateAction(ActionEvent event) {
        // Gets the sale prices from the six fields
        double[] salePrices = new double[6];
        TextField[] salePriceFields = {
            stock$1Field, stock$2Field, stock$3Field,
            stock$4Field, stock$5Field, stock$6Field
        };

        for (int i = 0; i < salePrices.length; i++) {
            String value = salePriceFields[i].getText();
            if (value != null && !value.isEmpty()) {
                try {
                    salePrices[i] = Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    salePrices[i] = 0.0;
                }
            } else {
                salePrices[i] = 0.0;
            }
        }

        int[] quantities = new int[6];
        TextField[] quantityFields = {
            stockQTY1Field, stockQTY2Field, stockQTY3Field,
            stockQTY4Field, stockQTY5Field, stockQTY6Field
        };

        for (int i = 0; i < quantities.length; i++) {
            String value = quantityFields[i].getText();
            if (value != null && !value.isEmpty()) {
                try {
                    quantities[i] = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    quantities[i] = 0;
                }
            } else {
                quantities[i] = 0;
            }
        }

        // Validate stock before calculating totals
        for (int i = 0; i < quantities.length; i++) {
            // Skip validation if quantity is 0 for the product
            if (quantities[i] == 0) {
                continue;
            }

            Object userData = quantityFields[i].getUserData();
            if (userData == null) {
                Alerts.showErrorAlert("Stock Error", "Stock data not loaded for product in row " + (i + 1));
                return; // Exit the method without calculating the total
            }

            double availableStock = (double) userData;
            if (quantities[i] > availableStock) {
                Alerts.showErrorAlert("Stock Error", "Sorry, we don't have enough stock for the product in row " + (i + 1) + ". Available stock: " + availableStock);
                return; // Exit the method without calculating the total
            }
        }

        double[] productTotals = new double[salePrices.length];
        for (int i = 0; i < salePrices.length; i++) {
            productTotals[i] = salePrices[i] * quantities[i];
        }

        TextField[] totalFields = {
            stockTotal1Field, stockTotal2Field, stockTotal3Field,
            stockTotal4Field, stockTotal5Field, stockTotal6Field
        };

        for (int i = 0; i < totalFields.length; i++) {
            totalFields[i].setText(String.valueOf(productTotals[i]));
        }

        double overallTotal = 0.0;
        for (double total : productTotals) {
            overallTotal += total;
        }
        orderTotalField.setText(String.valueOf(overallTotal));
    }

    @FXML  //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML  //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    @FXML  //this searches the nameID of the customer 
    private void handleSearchAction(ActionEvent event) {
        // Get access to the list of people
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

        // Get the name typed into the search field
        String enteredName = searchNameField.getText();
        int enteredID = -1;  // Start with a "no ID" value

        // If there's something in the ID field, read it
        if (!searchIDField.getText().isEmpty()) {
            try {
                enteredID = Integer.parseInt(searchIDField.getText());
            } catch (NumberFormatException e) {
                // Tell the user if they didn't type a number for the ID
                Alerts.showErrorAlert("Error Title", "The ID should be a number.");

                return;
            }
        }

        // Look for a customer that matches the name / ID
        PersonCustomer customer = dataHandlerPeople.searchCustomer(enteredName, enteredID);

        // If for when we find a customer
        if (customer != null) {
            // Fill in the form with the customer's details
            nameField.setText(customer.getName());
            IDField.setText(String.valueOf(customer.getID()));
            phoneField.setText(customer.getPhoneNumber());
            addressField.setText(customer.getAddress());
            postCodeField.setText(customer.getPostcode());
            contactNameField.setText(customer.getContactName());
            paymentOptionField.setText(customer.getPaymentOptions());
        } else {
            // alert if no cutomer can be found
            Alerts.showInfoAlert("Oh No...", "Sorry, we couldn't find a customer with that name or ID.");

        }
    }

    @FXML  //back button
    private void handleBackAction(ActionEvent event) {
        // Go back to the previous screen
         resetFields();
        App.changeScene(7);
    }

    //this method is needed to display the data in the fields.
    private void updateFieldsBasedOnProductID(TextField productIDField, TextField productNameField, TextField salePriceField) {
        // Get the typed product ID
        String productID = productIDField.getText();
        try {
            int productIDInt = Integer.parseInt(productID);
            // Look for a product with that ID
            StockInventory product = dataHandlerInventory.searchItemByCode(productIDInt);
            if (product != null) {
                // If found the product, display its details
                productNameField.setText(product.getName());
                salePriceField.setText(String.valueOf(product.getSalePrice()));
            } else {
                // If didn't find the product, clear the product details
                productNameField.setText("");
                salePriceField.setText("");
            }
        } catch (NumberFormatException e) {
            // catch when the  and clear the product details
            productNameField.setText("");
            salePriceField.setText("");
        }
    }

    @FXML//method to reset all fields
    public void resetFields() {
        // Reset TextFields
        nameField.setText("");
        IDField.setText("");
        phoneField.setText("");
        addressField.setText("");
        postCodeField.setText("");
        contactNameField.setText("");
        paymentOptionField.setText("");
        searchNameField.setText("");
        searchIDField.setText("");

        stockQTY1Field.setText("");
        stockName1Field.setText("");
        ID1Field.setText("");
        stockTotal1Field.setText("");

        stockQTY2Field.setText("");
        stockName2Field.setText("");
        ID2Field.setText("");
        stockTotal2Field.setText("");

        stockQTY3Field.setText("");
        stockName3Field.setText("");
        ID3Field.setText("");
        stockTotal3Field.setText("");

        stockQTY4Field.setText("");
        stockName4Field.setText("");
        ID4Field.setText("");
        stockTotal4Field.setText("");

        stockQTY5Field.setText("");
        stockName5Field.setText("");
        ID5Field.setText("");
        stockTotal5Field.setText("");

        stockQTY6Field.setText("");
        stockName6Field.setText("");
        ID6Field.setText("");
        stockTotal6Field.setText("");

        orderTotalField.setText("");

        stock$1Field.setText("");
        stock$2Field.setText("");
        stock$3Field.setText("");
        stock$4Field.setText("");
        stock$5Field.setText("");
        stock$6Field.setText("");
    }

}
