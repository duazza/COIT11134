// Import necessary libraries and set up the class.
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
  * @author Tropico
 */

//controller for the rankings
public class ReportingRankingController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private TextArea field1;
    @FXML
    private TextArea field2;
    @FXML
    private TextArea field3;
    @FXML
    private TextArea field4;
    @FXML
    private TextArea field5;
    @FXML
    private Button backButton;
    @FXML
    private TextArea field6;
    @FXML
    private TextArea field7;
    @FXML
    private TextArea field8;
    @FXML
    private TextArea field9;
    @FXML
    private TextArea field10;
    @FXML
    private Button logoutButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button refreshButton;

    /**
     * Initializes the controller class.
     */
    
    @Override  // displays upon entry 
    public void initialize(URL url, ResourceBundle rb) {
        // Call a method to display product rankings.
        displayProductRankings();
    }
        
    // This method retrieves and displays product rankings.
    private void displayProductRankings() {
        DataHandlerTransaction dataHandlerTransaction = App.getDataHandlerTransaction();

        //Returns only customer transactions
        List<TransactionCustomer> transactions = dataHandlerTransaction.getCustomerTransactions();

        //add a new map - makes things easier 
        Map<String, Double> productSalesMap = new HashMap<>();

        // Calculate total sales for each product
        for (TransactionCustomer transaction : transactions) {
            String[] productIDs = transaction.getProductIDs();
            double[] quantities = transaction.getQuantities();
            double[] salePrices = transaction.getSalePrices();

            for (int i = 0; i < productIDs.length; i++) {
                String productID = productIDs[i];
                double salesTotal = quantities[i] * salePrices[i];
                productSalesMap.put(productID, productSalesMap.getOrDefault(productID, 0.0) + salesTotal);
            }
        }

        // Sort products by total sales in descending order
        List<Map.Entry<String, Double>> sortedEntries = productSalesMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toList());

        // Display the top 10 products
        displayRankings(sortedEntries);
    }

    // This method displays product rankings in the TextArea fields.
    private void displayRankings(List<Map.Entry<String, Double>> sortedEntries) {
        TextArea[] fields = {field1, field2, field3, field4, field5, field6, field7, field8, field9, field10};
        for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
            Map.Entry<String, Double> entry = sortedEntries.get(i);
            fields[i].setText("Product ID: " + entry.getKey() + " | Total Sales: $" + entry.getValue());
        }
    }

    
    @FXML  //close button
    private void handleCloseAction(ActionEvent event) {
        // Go back to the previous screen.
        App.changeScene(17);
    }

   
    @FXML //back button
    private void handleBackAction(ActionEvent event) {
        // Go back to the previous screen.
        App.changeScene(17);
    }

    
    @FXML //logout button
    private void handleLogoutAction(ActionEvent event) {
        // Go back to the login screen.
        App.changeScene(0);
    }

    
    @FXML //exit button
    private void handleExitAction(ActionEvent event) {
        // Exit the application.
        App.exit();
    }

    // This method updates the product rankings.
    public void updateRankings() {
        // Call the method to display product rankings again.
        displayProductRankings();
    }

    @FXML //refreshes the ranking when new orders are placed
    private void handleRefreshAction(ActionEvent event) {
        // Call the method to update and display product rankings.
        updateRankings();
    }
}
