/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;

/**
 *
 * @author duane
 */
public class DataHandlerTransaction {

    private String fileName;
    private List<Transaction> transactionList = new ArrayList<>();
    private ArrayList<TransactionAdjustment> adjustmentList;

    public DataHandlerTransaction(String fileName) {
        this.fileName = fileName;
        this.transactionList = new ArrayList<>();
        this.adjustmentList = new ArrayList<TransactionAdjustment>();

        readDataFile();
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

   private void readDataFile() {
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            String transactionType = parts[0].trim().toUpperCase();

            switch (transactionType) {
                case "ADJUSTMENT":
                    // Extract adjustment details
                    int adjustedStockID = Integer.parseInt(parts[2].trim());
                    String adjustedStockName = parts[3].trim();
                    double adjustedQuantity = Double.parseDouble(parts[5].trim());

                    // Fetch the purchase price for the adjusted stock item
                    StockInventory stockItem = dataHandlerInventory.searchItemByCode(adjustedStockID);
                    if (stockItem == null) {
                        // Handle the case where the stock item isn't found
                        System.err.println("Stock item not found for ID: " + adjustedStockID);
                        continue;  // Skip this line and move to the next one
                    }
                    double purchasePrice = stockItem.getPurchasePrice();

                    // Create a new TransactionAdjustment object
                    TransactionAdjustment adjustmentTransaction = new TransactionAdjustment(
                            adjustedStockID, adjustedStockName, adjustedQuantity, purchasePrice
                    );

                    // Add the adjustmentTransaction to your list of adjustments
                    adjustmentList.add(adjustmentTransaction);
                    break;

                case "CUSTOMER":
                    // This is a customer transaction
                    int customerID = Integer.parseInt(parts[1]);
                    String[] productIDs = parts[2].split(";");
                    String[] productNames = parts[3].split(";");
                    double[] salePrices = Arrays.stream(parts[4].split(";")).mapToDouble(Double::parseDouble).toArray();
                    double[] quantities = Arrays.stream(parts[5].split(";")).mapToDouble(Double::parseDouble).toArray();
                    double orderTotal = Double.parseDouble(parts[parts.length - 3]);
                    int invoiceNumber = Integer.parseInt(parts[parts.length - 2]);
                    String date = parts[parts.length - 1];

                    TransactionCustomer transaction = new TransactionCustomer(customerID, productIDs, productNames, salePrices, quantities);
                    transaction.setOrderTotal(orderTotal);
                    transaction.setInvoiceNumber(invoiceNumber);
                    transaction.setDate(date);

                    transactionList.add(transaction);
                    break;

                default:
                    System.err.println("Unknown transaction type: " + transactionType);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   
   //this is for the currentstock #
   public double calculateAdjustedQuantityForItem(int targetProductId) {
    List<String[]> tempDataList = new ArrayList<>();

    // 1. Read the file and populate the tempDataList
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.split(",");
                tempDataList.add(parts);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    double cumulativeQuantity = 0.0;

    // 2. Iterate over each line in the tempDataList
    for (String[] parts : tempDataList) {
        int adjustedStockID = Integer.parseInt(parts[2].trim());

        // Check if the product ID matches
        if (adjustedStockID == targetProductId) {
            double adjustedQuantity = Double.parseDouble(parts[5].trim());

            switch (parts[0].trim().toUpperCase()) {
                case "ADJUSTMENT":
                case "CUSTOMER":
                    cumulativeQuantity -= adjustedQuantity;
                    break;
                case "VENDOR":
                    cumulativeQuantity += adjustedQuantity;
                    break;
            }
        }
    }

    return cumulativeQuantity;
}

    
    
   /* private void readDataFile() {
        DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts[0].equals("0")) { // This is an adjustment
                    // Extract adjustment details
                    int adjustedStockID = Integer.parseInt(parts[1].trim());
                    String adjustedStockName = parts[2].trim();
                    double adjustedQuantity = Double.parseDouble(parts[4].trim());

                    // Fetch the purchase price for the adjusted stock item
                    StockInventory stockItem = dataHandlerInventory.searchItemByCode(adjustedStockID);
                    if (stockItem == null) {
                        // Handle the case where the stock item isn't found
                        System.err.println("Stock item not found for ID: " + adjustedStockID);
                        continue;  // Skip this line and move to the next one
                    }
                    double purchasePrice = stockItem.getPurchasePrice();

                    // Create a new TransactionAdjustment object
                    TransactionAdjustment adjustmentTransaction = new TransactionAdjustment(
                            adjustedStockID, adjustedStockName, adjustedQuantity, purchasePrice
                    );

                    // Add the adjustmentTransaction to your list of adjustments
                    adjustmentList.add(adjustmentTransaction);

                } else { // This is a customer transaction
                    int customerID = Integer.parseInt(parts[0]);
                    String[] productIDs = parts[1].split(";");
                    String[] productNames = parts[2].split(";");
                    double[] salePrices = Arrays.stream(parts[3].split(";")).mapToDouble(Double::parseDouble).toArray();
                    double[] quantities = Arrays.stream(parts[4].split(";")).mapToDouble(Double::parseDouble).toArray();
                    double orderTotal = Double.parseDouble(parts[parts.length - 3]);
                    int invoiceNumber = Integer.parseInt(parts[parts.length - 2]);
                    String date = parts[parts.length - 1];

                    TransactionCustomer transaction = new TransactionCustomer(customerID, productIDs, productNames, salePrices, quantities);
                    transaction.setOrderTotal(orderTotal);
                    transaction.setInvoiceNumber(invoiceNumber);
                    transaction.setDate(date);

                    transactionList.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     private void readDataFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                int customerID = Integer.parseInt(parts[0]);

                String[] productIDs = parts[1].split(";");
                String[] productNames = parts[2].split(";");
                double[] salePrices = Arrays.stream(parts[3].split(";")).mapToDouble(Double::parseDouble).toArray();
                int[] quantities = Arrays.stream(parts[4].split(";")).mapToInt(Integer::parseInt).toArray();

                double orderTotal = Double.parseDouble(parts[parts.length - 3]);
                int invoiceNumber = Integer.parseInt(parts[parts.length - 2]);  // Updated to parse as int
                String date = parts[parts.length - 1];

                TransactionCustomer transaction = new TransactionCustomer(customerID, productIDs, productNames, salePrices, quantities);
                transaction.setOrderTotal(orderTotal);
                transaction.setInvoiceNumber(invoiceNumber);  // Updated to set as int
                transaction.setDate(date);

                transactionList.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public void writeDataFile() {
        System.out.println("Starting to write to file: " + fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Transaction transaction : transactionList) {
                bw.write(transaction.toStringForFile());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAndSaveTransaction(TransactionCustomer transaction) {
        this.transactionList.add(transaction);
        writeDataFile();
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
        System.out.println("Added new transaction: " + transaction.toString());
    }

    public Transaction searchTransactionByInvoiceNumber(int invoiceNumber) {
        for (Transaction transaction : transactionList) {
            if (transaction.getInvoiceNumber() == invoiceNumber) { // Use == for int comparison
                return transaction;
            }
        }
        return null;  // return null if no matching transaction is found
    }

    public int calculateNetStockAdjustment(StockInventory stockItem) {
        int netAdjustment = 0;
        System.out.println("Calculating adjustment for stock item: " + stockItem.getName() + " with ID: " + stockItem.getProductCode());


        for (Transaction transaction : transactionList) {
            for (int i = 0; i < transaction.getProductIDs().length; i++) {
                if (transaction.getProductIDs()[i].equals(String.valueOf(stockItem.getProductCode()))) {
                    System.out.println("Processing transaction of type: " + transaction.getTransactionType() + " for product ID: " + transaction.getProductIDs()[i]);

                    switch (transaction.getTransactionType().toUpperCase()) { // Ensure uppercase comparison
                        case "VENDOR":
                            netAdjustment += transaction.getQuantities()[i];
                            System.out.println("Vendor adjustment of: " + transaction.getQuantities()[i] + ". Current net adjustment: " + netAdjustment);
                            break;
                        case "CUSTOMER":
                            netAdjustment -= transaction.getQuantities()[i];
                            System.out.println("Customer adjustment of: " + (-transaction.getQuantities()[i]) + ". Current net adjustment: " + netAdjustment);
                            break;
                        case "ADJUSTMENT":
                            netAdjustment += transaction.getQuantities()[i];
                            System.out.println("Stock adjustment of: " + transaction.getQuantities()[i] + ". Current net adjustment: " + netAdjustment);
                            break;

                        // You can add more cases for other transaction types if needed
                    }
                }
            }
        }
        return netAdjustment;
    }
    
    public void refreshData() {
    readDataFile();
}
    
    public List<Transaction> getTransactionList() {
    return this.transactionList;
}



    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
