/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tropico
 */

//this is the data handler for the transactions
public class DataHandlerTransaction {

    //define
    private String fileName;
    private List<Transaction> transactionList = new ArrayList<>();
    private ArrayList<TransactionAdjustment> adjustmentList;
    private List<Transaction> newTransactions = new ArrayList<>();
    private DataHandlerInventory dataHandlerInventory;

    //constructor
    public DataHandlerTransaction(String fileName) {
        this.fileName = fileName;
        this.transactionList = new ArrayList<>();
        this.adjustmentList = new ArrayList<TransactionAdjustment>();
        this.dataHandlerInventory = App.getDataHandlerInventory();

        readDataFile();
    }

    //method to return the transaction list
    public List<Transaction> getTransactions() {
        return transactionList;
    }

    //this method will read the txt file for the transactions
    private void readDataFile() {
         File file = new File(fileName);
    if (!file.exists()) {//adds the file if doesn't exist
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.err.println("Error creating new file: " + ex.getMessage());
            return;
        }
    }
    DataHandlerInventory dataHandlerInventory = App.getDataHandlerInventory();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                String transactionType = parts[0].trim().toUpperCase(); //determing the type

                switch (transactionType) {
                    case "ADJUSTMENT": {
                        // Extract adjustment details
                        int adjustedStockID = Integer.parseInt(parts[2].trim());
                        String adjustedStockName = parts[3].trim();
                        double adjustedQuantity = Double.parseDouble(parts[5].trim());

                        // Fetch the purchase price for the adjusted stock item
                        StockInventory stockItem = dataHandlerInventory.searchItemByCode(adjustedStockID);
                        if (stockItem == null) {

                            System.err.println("Stock item not found for ID: " + adjustedStockID);
                            continue;
                        }
                        double purchasePrice = stockItem.getPurchasePrice();

                        // Create a new TransactionAdjustment object
                        TransactionAdjustment adjustmentTransaction = new TransactionAdjustment(
                                adjustedStockID, adjustedStockName, adjustedQuantity, purchasePrice
                        );

                        // Add the adjustmentTransaction to list
                        adjustmentList.add(adjustmentTransaction);
                        break;
                    }

                    case "CUSTOMER": {  //when type = customer
                        int customerID = Integer.parseInt(parts[1]);

                        //forming arraylists to help with reports/and the info sections because we don't know the size.
                        List<String> productIDsList = new ArrayList<>();
                        List<String> productNamesList = new ArrayList<>();
                        List<Double> salePricesList = new ArrayList<>();
                        List<Double> purchasePricesList = new ArrayList<>();
                        List<Double> quantitiesList = new ArrayList<>();

                        int fieldsPerProduct = 4;
                        for (int i = 0; i < 6; i++) {
                            int baseIndex = 2 + i * fieldsPerProduct;
                            if (baseIndex + fieldsPerProduct - 1 < parts.length && !parts[baseIndex].equals("N/A")) {
                                productIDsList.add(parts[baseIndex]);
                                productNamesList.add(parts[baseIndex + 1]);
                                salePricesList.add(Double.parseDouble(parts[baseIndex + 2]));
                                quantitiesList.add(Double.parseDouble(parts[baseIndex + 3]));
                            }
                        }

                        double orderTotal = Double.parseDouble(parts[parts.length - 3]);
                        int invoiceNumber = Integer.parseInt(parts[parts.length - 2]);
                        String date = parts[parts.length - 1];

                        //add to the array
                        String[] productIDs = productIDsList.toArray(new String[0]);
                        String[] productNames = productNamesList.toArray(new String[0]);
                        double[] salePrices = salePricesList.stream().mapToDouble(Double::doubleValue).toArray();
                        double[] quantities = quantitiesList.stream().mapToDouble(Double::doubleValue).toArray();

                        // creating the TransactionCustomer object.
                        TransactionCustomer transaction = new TransactionCustomer(customerID, productIDs, productNames, salePrices, quantities);
                        transaction.setOrderTotal(orderTotal);
                        transaction.setInvoiceNumber(invoiceNumber);
                        transaction.setDate(date);

                        transactionList.add(transaction);

                        break;
                    }
                    case "VENDOR": {  //when type is vendor
                        int vendorID = Integer.parseInt(parts[1]);

                        //places the details from the txt file into lists for easy mgmt and for the reports
                        List<String> productIDsList = new ArrayList<>();
                        List<String> productNamesList = new ArrayList<>();
                        List<Double> salePricesList = new ArrayList<>();
                        List<Double> purchasePricesList = new ArrayList<>();
                        List<Double> quantitiesList = new ArrayList<>();

                        int fieldsPerProduct = 4;
                        for (int i = 0; i < 6; i++) {
                            int baseIndex = 2 + i * fieldsPerProduct;
                            if (baseIndex + fieldsPerProduct - 1 < parts.length && !parts[baseIndex].equals("N/A")) {
                                productIDsList.add(parts[baseIndex]);
                                productNamesList.add(parts[baseIndex + 1]);
                                purchasePricesList.add(Double.parseDouble(parts[baseIndex + 2]));
                                quantitiesList.add(Double.parseDouble(parts[baseIndex + 3]));
                            }
                        }

                        double orderTotal = Double.parseDouble(parts[parts.length - 3]);
                        int invoiceNumber = Integer.parseInt(parts[parts.length - 2]);
                        String date = parts[parts.length - 1];

                        //adds tothe atrray
                        String[] productIDs = productIDsList.toArray(new String[0]);
                        String[] productNames = productNamesList.toArray(new String[0]);
                        double[] purchasePrices = purchasePricesList.stream().mapToDouble(Double::doubleValue).toArray();
                        double[] quantities = quantitiesList.stream().mapToDouble(Double::doubleValue).toArray();

                        //add new transactions
                        TransactionVendor transaction = new TransactionVendor(vendorID, productIDs, productNames, purchasePrices, quantities);
                        transaction.setOrderTotal(orderTotal);
                        transaction.setInvoiceNumber(invoiceNumber);
                        transaction.setDate(date);

                        transactionList.add(transaction); //this adds the transaction to the list
                        break;
                    }

                    default:
                        System.err.println("Unknown transaction type: " + transactionType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataFile() {
        System.out.println("Starting to write to file: " + fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Transaction transaction : newTransactions) {
                bw.write(transaction.toStringForFile());
                bw.newLine();
            }
            newTransactions.clear(); // Clear the list after writing to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method is to add and save the transaction.
    public void addAndSaveTransaction(TransactionCustomer transaction) {
        this.transactionList.add(transaction);
        writeDataFile();
    }

    //this method simply adds the transactions
    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
        this.newTransactions.add(transaction);
        System.out.println("Added new transaction: " + transaction.toString());
    }

    //search the transactions by the invoice number
    public Transaction searchTransactionByInvoiceNumber(int invoiceNumber) {
        for (Transaction transaction : transactionList) {
            if (transaction.getInvoiceNumber() == invoiceNumber) {
                return transaction;
            }
        }
        return null;
    }

    //method to refresh the data to make sure data is uptodate
    public void refreshData() {
        readDataFile();
    }

    //gets the list for the reports
    public List<Transaction> getTransactionList() {
        return this.transactionList;
    }

    //grabs the transaction details for the reports
    public String displayTransactionDetails(TransactionCustomer transactionCustomer) {
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

        PersonCustomer customer = dataHandlerPeople.getCustomerByID(transactionCustomer.getCustomerID());

        StringBuilder sb = new StringBuilder();

        //setting it up for how I want it displayed
        sb.append("Customer ID: ").append(customer.getID()).append("\n");
        sb.append("Customer Name: ").append(customer.getName()).append("\n");

        for (int i = 0; i < transactionCustomer.getProductNames().length; i++) {
            sb.append("Product: ").append(transactionCustomer.getProductNames()[i])
                    .append(" | Price: ").append(transactionCustomer.getSalePrices()[i])
                    .append(" | Quantity: ").append(transactionCustomer.getQuantities()[i])
                    .append(" | Total for Product: ").append(transactionCustomer.getSalePrices()[i] * transactionCustomer.getQuantities()[i])
                    .append("\n");
        }

        sb.append("\nOrder Total: ").append(transactionCustomer.getOrderTotal()).append("");
        sb.append(" | Invoice Number: ").append(transactionCustomer.getInvoiceNumber()).append("");
        sb.append(" | Date: ").append(transactionCustomer.getDate());
        sb.append("\n....................................................................................................................................................\n");

        return sb.toString();
    }

    //grabbing the vendor details for the reports
    public String displayTransactionVendorDetails(TransactionVendor transactionVendor) {
        DataHandlerPeople dataHandlerPeople = App.getDataHandlerPeople();

        PersonVendor vendor = dataHandlerPeople.getVendorByID(transactionVendor.getVendorID());
        StringBuilder sb = new StringBuilder();
        sb.append("Vendor ID: ").append(vendor.getID()).append("\n");
        sb.append("Vendor Name: ").append(vendor.getName()).append("\n\n");

        for (int i = 0; i < transactionVendor.getProductNames().length; i++) {
            sb.append("Product: ").append(transactionVendor.getProductNames()[i])
                    .append(" | Price: ").append(transactionVendor.getPurchasePrices()[i])
                    .append(" | Quantity: ").append(transactionVendor.getQuantities()[i])
                    .append(" | Total for Product: $").append(transactionVendor.getPurchasePrices()[i] * transactionVendor.getQuantities()[i])
                    .append("\n");
        }
        sb.append("\nOrder Total: $").append(transactionVendor.getOrderTotal()).append("\n");
        sb.append("Invoice Number: ").append(transactionVendor.getInvoiceNumber()).append("\n");
        sb.append("Date: ").append(transactionVendor.getDate());
        sb.append("\n....................................................................................................................................................\n");

        return sb.toString();
    }

    // New method to update StockInventory based on the customer transaction
    public void updateStockInventory(TransactionCustomer transaction) {
        String[] productIDs = transaction.getProductIDs();
        double[] quantities = transaction.getQuantities();

        //loop to update the stock from a customer transaction
        for (int i = 0; i < productIDs.length; i++) {
            StockInventory item = dataHandlerInventory.getStockByProductID(productIDs[i]);
            if (item != null) {
                item.deductStock(quantities[i]);
                dataHandlerInventory.updateStockInFile(item);  // Assuming this method updates the inventory.txt file
            }
        }
    }

    //new method to update the vendor transactions
    public void updateStockForVendorTransaction(TransactionVendor transaction) {
        String[] productIDs = transaction.getProductIDs();
        double[] quantities = transaction.getQuantities();

        //loop to update the stock levels for the vendor transactions
        for (int i = 0; i < productIDs.length; i++) {
            StockInventory item = dataHandlerInventory.getStockByProductID(productIDs[i]);
            if (item != null) {
                item.addStock(quantities[i]);
                dataHandlerInventory.updateStockInFile(item);  // Assuming this method updates the inventory.txt file
            }
        }
    }

    //grabs the customer transactions for the reports
    public List<TransactionCustomer> getCustomerTransactions() {
        List<TransactionCustomer> customerTransactions = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transaction instanceof TransactionCustomer) {
                customerTransactions.add((TransactionCustomer) transaction);
            }
        }

        return customerTransactions;
    }

}
