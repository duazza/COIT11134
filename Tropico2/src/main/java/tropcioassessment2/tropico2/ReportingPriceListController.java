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
 * FXML Controller class
 * This class handles the price list reporting.
 * @author duane
 */
public class ReportingPriceListController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextArea largeTextField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private Button resetButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     * This method is called when the controller is initialized.
     * It displays all items by default.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayAllItems();
    }

    // This method handles the "Close" button click.
    @FXML
    private void handleCloseAction(ActionEvent event) {
        // Go back to the previous screen.
        App.changeScene(17);
    }

    // This method handles the "Search" button click.
    @FXML
    private void handleSearchAction(ActionEvent event) {
        // Access the inventory data handler
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
        String name = nameField.getText().trim();
        String idStr = IDField.getText().trim();
        StockInventory foundItem = null;

        if (!name.isEmpty()) {
            // Search by name
            foundItem = dataHandlerInventory.searchItemByName(name);
        } else if (!idStr.isEmpty()) {
            try {
                // Try to parse the ID as an integer
                int id = Integer.parseInt(idStr);
                // Search by ID
                foundItem = dataHandlerInventory.searchItemByCode(id);
            } catch (NumberFormatException e) {
                // Handle the case where the ID format is invalid
                largeTextField.setText("Invalid ID format. Please enter a numeric ID.");
                return;
            }
        }

        if (foundItem != null) {
            // Display item details
            String itemDetails = String.format("%s | %d | Sale Price: %.2f | Purchase Price: %.2f",
                    foundItem.getName(), foundItem.getProductCode(), foundItem.getSalePrice(), foundItem.getPurchasePrice());
            largeTextField.setText(itemDetails);
        } else {
            // Item not found
            largeTextField.setText("Item not found.");
        }
    }

    // This method handles the "Reset" button click.
    @FXML
    private void handleResetAction(ActionEvent event) {
        // Clear the text fields and display all items
        nameField.clear();
        IDField.clear();
        displayAllItems();
    }

    // This method displays all items in the largeTextField.
    private void displayAllItems() {
        // Access the inventory data handler
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
        StringBuilder displayText = new StringBuilder();
        // Iterate through all items in the inventory and format their details
        for (StockInventory item : dataHandlerInventory.getInventoryList()) {
            displayText.append(String.format("%s | %d | Sale Price: %.2f | Purchase Price: %.2f\n",
                    item.getName(), item.getProductCode(), item.getSalePrice(), item.getPurchasePrice()));
        }
        // Set the text in the largeTextField
        largeTextField.setText(displayText.toString());
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
}
