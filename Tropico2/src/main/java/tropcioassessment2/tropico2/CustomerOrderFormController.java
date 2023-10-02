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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author duane
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
     * @param url
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();
DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

    }    
@FXML
private void handleSubmitAction(ActionEvent event) {
    // Access the necessary data handlers
    DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

    try {
        // Extract customer details
        String customerIDStr = IDField.getText(); // Declare customerIDStr before parsing it
        int customerID = Integer.parseInt(customerIDStr); // Convert to int

        // Create lists to store the product details
        List<String> productIDsList = new ArrayList<>();
        List<String> productNamesList = new ArrayList<>();
        List<Double> salePricesList = new ArrayList<>();
        List<Double> quantitiesList = new ArrayList<>();

        // Extract product details from the fields and add them to the lists
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

        // Convert lists to arrays
        String[] productIDs = productIDsList.toArray(new String[0]);
        String[] productNames = productNamesList.toArray(new String[0]);
        double[] salePrices = salePricesList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] quantities = quantitiesList.stream().mapToDouble(Double::doubleValue).toArray();

        // Create a new TransactionCustomer object
        TransactionCustomer transaction = new TransactionCustomer(customerID, productIDs, productNames, salePrices, quantities);

        // Print the object to inspect its content
        System.out.println(transaction.toString());

        // Add the transaction to the transaction list
        dataHandlerTransaction.addTransaction(transaction);

        // Save the updated transaction list to the file
        dataHandlerTransaction.writeDataFile();

        // Create the alert message with the invoice details
        String alertMessage = "Order has been saved/submitted!\n";
        alertMessage += "Invoice ID: " + transaction.getInvoiceNumber() + "\n";
        alertMessage += "Invoice Type: " + (transaction.isVendor() ? "Vendor" : "Customer");

        // Show a confirmation message to the user
        dataHandlerTransaction.showAlert("Success", alertMessage);

        // Navigate back to scene7
        App.changeScene(7);

    } catch (NumberFormatException e) {
        // Handle the error when the customerID is not a valid integer
        dataHandlerTransaction.showAlert("Error", "Please enter a valid Customer ID.");
    }
}



private void addProductDetailsToLists(List<String> productIDs, List<String> productNames, List<Double> salePrices, List<Double> quantities,
                                      TextField idField, TextField nameField, TextField priceField, TextField qtyField) {
    String productID = idField.getText().trim();
    String productName = nameField.getText().trim();
    String salePriceStr = priceField.getText().trim();
    String quantityStr = qtyField.getText().trim();

    if (!productID.isEmpty() && !salePriceStr.isEmpty() && !quantityStr.isEmpty()) {
        productIDs.add(productID);
        productNames.add(productName);
        salePrices.add(Double.parseDouble(salePriceStr));
        quantities.add(Double.parseDouble(quantityStr));
    }
}
    


@FXML
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
        // Extract more fields like ID2Field.getText(), ID3Field.getText(), etc.
    };
    
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
    // If the product is found, populate the respective fields
    switch (i) {
        case 0:
            stockName1Field.setText(product.getName());
            stock$1Field.setText(String.valueOf(product.getSalePrice()));
            break;
        case 1:
            stockName2Field.setText(product.getName());
            stock$2Field.setText(String.valueOf(product.getSalePrice()));
            break;
        case 2:
            stockName3Field.setText(product.getName());
            stock$3Field.setText(String.valueOf(product.getSalePrice()));
            break;
        case 3:
            stockName4Field.setText(product.getName());
            stock$4Field.setText(String.valueOf(product.getSalePrice()));
            break;
        case 4:
            stockName5Field.setText(product.getName());
            stock$5Field.setText(String.valueOf(product.getSalePrice()));
            break;
        case 5:
            stockName6Field.setText(product.getName());
            stock$6Field.setText(String.valueOf(product.getSalePrice()));
            break;
        default:
            // This shouldn't happen with the current setup, but it's a good practice to include a default case
            break;
    }
} else {
    // Handle error - product not found in the inventory
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Product not found with the provided ID.");
    alert.showAndWait();
}

} catch (NumberFormatException e) {
    // Handle the error when the product ID is not a valid integer
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a valid product ID.");
    alert.showAndWait();
}
    }
}




@FXML
public void handleCalculateAction(ActionEvent event) {
    // Safely parse sale prices for 6 fields
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
                // Handle error, for example show an alert to the user or set to 0.0
                salePrices[i] = 0.0;
            }
        } else {
            // Handle error or set a default value
            salePrices[i] = 0.0;
        }
    }

    // Safely parse quantities for 6 fields
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
                // Handle error, for example show an alert to the user or set to 0
                quantities[i] = 0;
            }
        } else {
            // Handle error or set a default value
            quantities[i] = 0;
        }
    }

    // Calculate the product totals
    double[] productTotals = new double[salePrices.length];
    for (int i = 0; i < salePrices.length; i++) {
        productTotals[i] = salePrices[i] * quantities[i];
    }

    // Update the total fields for each product
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

 //  @FXML
//public void submitButtonAction() {
// App.changeScene(7);
//}

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
    // Access the DataHandlerPeople instance from the main app class
    DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

    String enteredName = searchNameField.getText();
    int enteredID = -1; // Default to -1, which signifies no ID entered

    // If the ID field is not empty, try to parse it
    if (!searchIDField.getText().isEmpty()) {
        try {
            enteredID = Integer.parseInt(searchIDField.getText());
        } catch (NumberFormatException e) {
            // Handle invalid input for ID (non-numeric)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid numeric ID.");
            alert.showAndWait();
            return;
        }
    }

    // Use the searchCustomer method to get the matching customer
    PersonCustomer customer = dataHandlerPeople.searchCustomer(enteredName, enteredID);

    if (customer != null) {
        // If a matching customer is found, populate the form fields
        nameField.setText(customer.getName());
        IDField.setText(String.valueOf(customer.getID()));
        phoneField.setText(customer.getPhoneNumber());
        addressField.setText(customer.getAddress());
        postCodeField.setText(customer.getPostcode());
        contactNameField.setText(customer.getContactName());
        paymentOptionField.setText(customer.getPaymentOptions());
    } else {
        // Show a message to the user indicating that no customer was found
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search Result");
        alert.setHeaderText(null);
        alert.setContentText("No customer found with the provided name or ID.");
        alert.showAndWait();
    }
}


@FXML
private void handleBackAction(ActionEvent event) {
App.changeScene(7);
}

private void updateFieldsBasedOnProductID(TextField productIDField, TextField productNameField, TextField salePriceField) {
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
}
