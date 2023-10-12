// Import necessary libraries and set up the class.
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class This class handles reporting profit margins.
 *
 * @author duane
 */
public class ReportingProfitMarginController implements Initializable {

    @FXML
    private Button resetButton;
    @FXML
    private TextArea purchasedField;
    @FXML
    private TextArea soldField;
    @FXML
    private TextArea profitField;
    @FXML
    private TextArea totalField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code can be added here.
    }

    // This method handles the "Reset" button click.
    @FXML
    private void handleResetAction(ActionEvent event) {
        // Reset all input fields.
        resetFields();
    }

    @FXML // This method handles the "Search" button click.
    private void handleSearchAction(ActionEvent event) {
        // Access the necessary data handlers.
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();

        // Get the product name and ID entered by the user.
        String productName = nameField.getText().trim();
        String productIDStr = IDField.getText().trim();

        // Search for the stock item in the inventory.
        StockInventory stockItem = fetchStockItemFromInventory(productName, productIDStr, dataHandlerInventory);

        if (stockItem == null) {
            // Show an error message if the product is not found.
            Alerts.showErrorAlert("Error", "Product not found.");
            return;
        }

        // If the user searched by name, set the IDField. If they searched by ID, set the nameField.
        if (!productName.isEmpty()) {
            IDField.setText(String.valueOf(stockItem.getProductCode()));
        } else if (!productIDStr.isEmpty()) {
            nameField.setText(stockItem.getName());
        }

        // Calculate the total quantity sold for this product.
        double totalQuantitySold = calculateTotalQuantitySold(stockItem, dataHandlerTransaction);

        // Update the display fields with the calculated values.
        updateDisplayFields(stockItem, totalQuantitySold);
    }

    // This method fetches a stock item from the inventory based on name or ID.
    private StockInventory fetchStockItemFromInventory(String productName, String productIDStr, DataHandlerInventory dataHandlerInventory) {
        if (!productName.isEmpty()) {
            return dataHandlerInventory.searchItemByName(productName);
        } else if (!productIDStr.isEmpty()) {
            int productID = Integer.parseInt(productIDStr);
            return dataHandlerInventory.searchItemByCode(productID);
        }
        return null;
    }

    // This method calculates the total quantity sold for a stock item.
    private double calculateTotalQuantitySold(StockInventory stockItem, DataHandlerTransaction dataHandlerTransaction) {
        double totalQuantitySold = 0.0;

        for (Transaction transaction : dataHandlerTransaction.getTransactions()) {
            if (transaction instanceof TransactionCustomer) {
                TransactionCustomer customerTransaction = (TransactionCustomer) transaction;
                for (int i = 0; i < customerTransaction.getProductIDs().length; i++) {
                    if (Integer.parseInt(customerTransaction.getProductIDs()[i]) == stockItem.getProductCode()) {
                        totalQuantitySold += customerTransaction.getQuantities()[i];
                    }
                }
            }
        }
        return totalQuantitySold;
    }

    // This method updates the display fields with profit margin information.
    private void updateDisplayFields(StockInventory stockItem, double totalQuantitySold) {
        double purchasePrice = stockItem.getPurchasePrice();
        double salePrice = stockItem.getSalePrice();

        double purchased = totalQuantitySold * purchasePrice;
        double sold = totalQuantitySold * salePrice;
        double profit = salePrice - purchasePrice;
        double totalProfit = sold - purchased;

        purchasedField.setText("$" + String.format("%.2f", purchased));
        soldField.setText("$" + String.format("%.2f", sold));
        profitField.setText("$" + String.format("%.2f", profit));
        totalField.setText("$" + String.format("%.2f", totalProfit));
    }

    // This method handles the "Close" button click.
    @FXML
    private void handleCloseAction(ActionEvent event) {
        // Go back to the previous screen.
        App.changeScene(17);
    }

    // This method handles the "Back" button click.
    @FXML
    private void handleBackAction(ActionEvent event) {
        // Go back to the previous screen.
        App.changeScene(17);
    }

    // This method handles the "Logout" button click.
    @FXML
    private void handleLogoutAction(ActionEvent event) {
        // Go back to the login screen.
        App.changeScene(0);
    }

    // This method handles the "Exit" button click.
    @FXML
    private void handleExitAction(ActionEvent event) {
        // Exit the application.
        App.exit();
    }

    // This method resets all input fields.
    private void resetFields() {
        nameField.setText("");
        IDField.setText("");
        purchasedField.setText("");
        soldField.setText("");
        profitField.setText("");
        totalField.setText("");
    }
}
