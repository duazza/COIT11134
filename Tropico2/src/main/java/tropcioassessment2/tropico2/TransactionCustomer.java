/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.util.StringJoiner;

/**
 *
 * @author duane
 */

public class TransactionCustomer extends Transaction {
    private int customerID;
    private String[] productIDs;
    private String[] productNames;
    private double[] salePrices;
    private double[] quantities;
    private double[] stockTotals;
    private double orderTotal;
    private boolean isVendor;

    public TransactionCustomer(int customerID, String[] productIDs, String[] productNames, double[] salePrices, double[] quantities) {
        super();
        this.type = "C";
        this.customerID = customerID;
        this.productIDs = productIDs;
        this.productNames = productNames;
        this.salePrices = salePrices;
        this.quantities = quantities;
        this.stockTotals = new double[productIDs.length];
        
        calculateStockTotals();
        calculateOrderTotal();
    }

    private void calculateStockTotals() {
        int length = Math.min(productIDs.length, Math.min(salePrices.length, quantities.length));
        for (int i = 0; i < length; i++) {
            this.stockTotals[i] = this.salePrices[i] * this.quantities[i];
        }
    }

    private void calculateOrderTotal() {
        for (double stockTotal : stockTotals) {
            this.orderTotal += stockTotal;
        }
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String[] getProductNames() {
        return productNames;
    }

    public void setProductNames(String[] productNames) {
        this.productNames = productNames;
    }

    public double[] getSalePrices() {
        return salePrices;
    }

    public void setSalePrices(double[] salePrices) {
        this.salePrices = salePrices;
    }

 @Override
public double[] getQuantities() {
    return quantities;
}

    public void setQuantities(double[] quantities) {
        this.quantities = quantities;
    }

    public double[] getStockTotals() {
        return stockTotals;
    }

    public void setStockTotals(double[] stockTotals) {
        this.stockTotals = stockTotals;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public boolean isVendor() {
        return isVendor;
    }

    public void setVendor(boolean isVendor) {
        this.isVendor = isVendor;
    }

    // ... [Other getters and setters here] ...

    @Override
    public String getTransactionType() {
        return "CUSTOMER";
    }

  

    @Override
    public String[] getProductIDs() {
        return productIDs;
    }

    @Override
    public String toStringForFile() {
        StringJoiner sj = new StringJoiner(",");
         // Adding transaction type from superclass
    sj.add(getTransactionType()); 

        sj.add(String.valueOf(customerID));

        for (int i = 0; i < 6; i++) {
            sj.add(i < productIDs.length ? productIDs[i] : "N/A");
            sj.add(i < productNames.length ? productNames[i] : "N/A");
            sj.add(i < salePrices.length ? String.valueOf(salePrices[i]) : "0.0");
            sj.add(i < quantities.length ? String.valueOf(quantities[i]) : "0");
        }

        sj.add(String.valueOf(orderTotal));
        sj.add(String.valueOf(getInvoiceNumber()));
        sj.add(getDate());

        return sj.toString();
    }

    @Override
    public String toString() {
        return "TransactionCustomer{" +
               "customerID='" + customerID + '\'' +
               ", productIDs=" + java.util.Arrays.toString(productIDs) +
               ", productNames=" + java.util.Arrays.toString(productNames) +
               ", salePrices=" + java.util.Arrays.toString(salePrices) +
               ", quantities=" + java.util.Arrays.toString(quantities) +
               ", stockTotals=" + java.util.Arrays.toString(stockTotals) +
               ", orderTotal=" + orderTotal +
               ", invoiceNumber=" + getInvoiceNumber() +
               ", date='" + getDate() + '\'' +
               ", type='" + getType() + '\'' +
               '}';
    }
}
