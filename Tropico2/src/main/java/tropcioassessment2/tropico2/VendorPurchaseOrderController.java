/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
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
//controller for the purchase order
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
     *
     * @param url
     * @param rb
     */
    @Override //this is needed 
    public void initialize(URL url, ResourceBundle rb) {
        //not needed
    }

    @FXML  //submit button
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

        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
        // Access the necessary data handlers

        try {
            // Extract vendor details
            String vendorIDStr = IDField.getText(); // Declare vendorIDStr before parsing it
            int vendorID = Integer.parseInt(vendorIDStr); // Convert to int

            // Create lists to store the product details as a temp and then convert back to array because array can't be open ended
            List<String> productIDsList = new ArrayList<>();
            List<String> productNamesList = new ArrayList<>();
            List<Double> purchasePricesList = new ArrayList<>();
            List<Double> quantitiesList = new ArrayList<>();

            // Extract product details from the fields and add to the lists
            addProductDetailsToLists(productIDsList, productNamesList, purchasePricesList, quantitiesList,
                    ID1Field, stockName1Field, stock$1Field, stockQTY1Field);
            addProductDetailsToLists(productIDsList, productNamesList, purchasePricesList, quantitiesList,
                    ID2Field, stockName2Field, stock$2Field, stockQTY2Field);
            addProductDetailsToLists(productIDsList, productNamesList, purchasePricesList, quantitiesList,
                    ID3Field, stockName3Field, stock$3Field, stockQTY3Field);
            addProductDetailsToLists(productIDsList, productNamesList, purchasePricesList, quantitiesList,
                    ID4Field, stockName4Field, stock$4Field, stockQTY4Field);
            addProductDetailsToLists(productIDsList, productNamesList, purchasePricesList, quantitiesList,
                    ID5Field, stockName5Field, stock$5Field, stockQTY5Field);
            addProductDetailsToLists(productIDsList, productNamesList, purchasePricesList, quantitiesList,
                    ID6Field, stockName6Field, stock$6Field, stockQTY6Field);

            // add to arrays 
            String[] productIDs = productIDsList.toArray(new String[0]);
            String[] productNames = productNamesList.toArray(new String[0]);
            double[] purchasePrices = purchasePricesList.stream().mapToDouble(Double::doubleValue).toArray();
            double[] quantities = quantitiesList.stream().mapToDouble(Double::doubleValue).toArray();
            DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
            // Create a new TransactionVendor object
            TransactionVendor transaction = new TransactionVendor(vendorID, productIDs, productNames, purchasePrices, quantities);

            // Update the stock based on the vendor transaction
            dataHandlerTransaction.updateStockForVendorTransaction(transaction);
            // Add the transaction to the transaction list
            dataHandlerTransaction.addTransaction(transaction);

            // Save the updated transaction list to the file
            dataHandlerTransaction.writeDataFile();

            // Create the alert message 
            String alertMessage = "Order has been saved/submitted!\n";
            alertMessage += "Invoice ID: " + transaction.getInvoiceNumber() + "\n";
            alertMessage += "Invoice Type: " + (transaction.isVendor() ? "Vendor" : "Customer");

            // Show a confirmation message to the user
            Alerts.showInfoAlert("Success", alertMessage);

            // Navigate back to desired scenescene7
            App.changeScene(11);

        } catch (NumberFormatException e) {
            // Handle the error when the vendorID not valid 
            Alerts.showErrorAlert("Error", "Please enter a valid Vendor ID.");
        }
          resetFields();
    }

    @FXML //checks the ID of the product and retrieves the name and price
    private void handleCheckIDAction(ActionEvent event) {
        // Access the DataHandlerVendorInventory instance
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

        for (int i = 0; i < productIDs.length; i++) {
            if (productIDs[i].isEmpty()) {
                continue;  // Skip for empty product IDs
            }

            try {
                // Convert the product ID string to an integer
                int productCode = Integer.parseInt(productIDs[i]);

                // Searching for the product in the vendor inventory
                StockInventory product = dataHandlerInventory.searchItemByCode(productCode);

                if (product != null) {
                    // If the product is found, fill fields
                    switch (i) { //goes thru the fields 1 to 6 to display the name and price of each line
                        case 0:
                            stockName1Field.setText(product.getName());
                            stock$1Field.setText(String.valueOf(product.getPurchasePrice()));
                            break;
                        case 1:
                            stockName2Field.setText(product.getName());
                            stock$2Field.setText(String.valueOf(product.getPurchasePrice()));
                            break;
                        case 2:
                            stockName3Field.setText(product.getName());
                            stock$3Field.setText(String.valueOf(product.getPurchasePrice()));
                            break;
                        case 3:
                            stockName4Field.setText(product.getName());
                            stock$4Field.setText(String.valueOf(product.getPurchasePrice()));
                            break;
                        case 4:
                            stockName5Field.setText(product.getName());
                            stock$5Field.setText(String.valueOf(product.getPurchasePrice()));
                            break;
                        case 5:
                            stockName6Field.setText(product.getName());
                            stock$6Field.setText(String.valueOf(product.getPurchasePrice()));
                            break;
                        default:
                            break;
                    }
                } else {
                    // Handle error
                    Alerts.showErrorAlert("Error", "Product not found with the provided Vendor ID");

                }

            } catch (NumberFormatException e) {
                // Handle the error 
                Alerts.showErrorAlert("Error", "Please enter a valid product Vendor ID.");
            }
        }
    }

    @FXML//calculates the price of the order and the total order
    public void handleCalculateAction(ActionEvent event) {
        // parse purchase prices
        double[] purchasePrices = new double[6];
        TextField[] purchasePriceFields = {
            stock$1Field, stock$2Field, stock$3Field,
            stock$4Field, stock$5Field, stock$6Field
        };
        //loop for the purchase prices /add 0 if empty
        for (int i = 0; i < purchasePrices.length; i++) {
            String value = purchasePriceFields[i].getText();
            if (value != null && !value.isEmpty()) {
                try {
                    purchasePrices[i] = Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    // Handle error
                    purchasePrices[i] = 0.0;
                }
            } else {
                // Handle error /set a default value
                purchasePrices[i] = 0.0;
            }
        }

        // parse quantities for the 6 fields
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
                    // Handle error|set to 0
                    quantities[i] = 0;
                }
            } else {
                // set to 0
                quantities[i] = 0;
            }
        }

        // Calculate the product totals
        double[] productTotals = new double[purchasePrices.length];
        for (int i = 0; i < purchasePrices.length; i++) {
            productTotals[i] = purchasePrices[i] * quantities[i];
        }

        // Update the total fields for products+
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
        } //sets the order total
        orderTotalField.setText(String.valueOf(overallTotal));
    }

    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        resetFields();
        App.changeScene(11);
    }

    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        App.changeScene(0);
    }

    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    @FXML //search button to display the vendor details
    private void handleSearchAction(ActionEvent event) {
        // Access the DataHandlerPeople instance from the main app class
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

        String enteredName = searchNameField.getText();
        int enteredID = -1; // Default to -1, signifies no ID entered so name search will  work

        // If the ID field is not empty, parse it
        if (!searchIDField.getText().isEmpty()) {
            try {
                enteredID = Integer.parseInt(searchIDField.getText());
            } catch (NumberFormatException e) {
                // Handle invalid input for ID 
                Alerts.showErrorAlert("Error", "Please enter a valid numeric ID.");

                return;
            }
        }

        // searchVendor method to get the matching vendor
        PersonVendor vendor = dataHandlerPeople.searchVendor(enteredName, enteredID);

        if (vendor != null) {
            // If a matching vendor is found, populate the form fields
            nameField.setText(vendor.getName());
            IDField.setText(String.valueOf(vendor.getID()));
            phoneField.setText(vendor.getPhoneNumber());
            addressField.setText(vendor.getAddress());
            postCodeField.setText(vendor.getPostcode());
            contactNameField.setText(vendor.getContactName());
            paymentOptionField.setText(vendor.getPaymentOptions());
        } else {
            // Show a message no vendor was found
            Alerts.showErrorAlert("Information", "No vendor found with the provided name or ID.");

        }
    }

    //this gets the details and displays
    private void updateFieldsBasedOnProductID(TextField productIDField, TextField productNameField, TextField salePriceField) {
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
        String productID = productIDField.getText();
        try {
            int productIDInt = Integer.parseInt(productID);
            StockInventory product = dataHandlerInventory.searchItemByCode(productIDInt);
            if (product != null) {
                productNameField.setText(product.getName());
                salePriceField.setText(String.valueOf(product.getSalePrice()));
            } else {
                // Handle case where product is not found
                productNameField.setText("");
                salePriceField.setText("");
            }
        } catch (NumberFormatException e) {
            // Handle case where entered product ID is not a valid number
            productNameField.setText("");
            salePriceField.setText("");
        }
    }

    //method that is called to add to lists. 
    private void addProductDetailsToLists(List<String> productIDs, List<String> productNames, List<Double> purchasePrices, List<Double> quantities,
            TextField idField, TextField nameField, TextField priceField, TextField qtyField) {
        String productID = idField.getText().trim();
        String productName = nameField.getText().trim();
        String purchasePriceStr = priceField.getText().trim();
        String quantityStr = qtyField.getText().trim();

        if (!productID.isEmpty() && !purchasePriceStr.isEmpty() && !quantityStr.isEmpty()) {
            productIDs.add(productID);
            productNames.add(productName);
            purchasePrices.add(Double.parseDouble(purchasePriceStr));
            quantities.add(Double.parseDouble(quantityStr));
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
