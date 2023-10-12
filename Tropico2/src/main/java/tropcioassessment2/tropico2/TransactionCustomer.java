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
// this is the class for customers - transactions
public class TransactionCustomer extends Transaction {

    //define
    private int customerID;
    private String customerName;
    private String[] productIDs;
    private String[] productNames;
    private double[] salePrices;
    private double[] quantities;
    private double[] stockTotals;
    private double orderTotal;
    private boolean isVendor;

    //constructor
    public TransactionCustomer(int customerID, String[] productIDs, String[] productNames, double[] salePrices, double[] quantities) {
        super();
        this.type = "C";
        this.customerID = customerID;
        this.productIDs = productIDs;
        this.productNames = productNames;
        this.salePrices = salePrices;
        this.quantities = quantities;
        this.stockTotals = new double[productIDs.length];
        calculateStockTotals(); //
        calculateOrderTotal();
    }

    //calc stock totals from transactions
    private void calculateStockTotals() {
        int length = Math.min(productIDs.length, Math.min(salePrices.length, quantities.length));
        for (int i = 0; i < length; i++) {
            this.stockTotals[i] = this.salePrices[i] * this.quantities[i];
        }
    }
    //calc the order total
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

    public int getCustomerName() {
        return customerID;
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

    @Override //gets the quantity
    public double[] getQuantities() {
        return quantities;
    }

    //sets the new quantity
    public void setQuantities(double[] quantities) {
        this.quantities = quantities;
    }

    //stock level
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

    @Override  //sets the type to customer
    public String getTransactionType() {
        return "CUSTOMER";
    }

    @Override
    public String[] getProductIDs() {
        return productIDs;
    }

    @Override //this adds to the file to fit the standard
    public String toStringForFile() {
        StringJoiner sj = new StringJoiner(",");
        // Adding transaction type from superclass
        sj.add(getTransactionType());

        sj.add(String.valueOf(customerID));

        for (int i = 0; i < 6; i++) { //adds place holders when there are not 6 ordered products
            sj.add(i < productIDs.length ? productIDs[i] : "N/A"); //product name
            sj.add(i < productNames.length ? productNames[i] : "N/A"); //product ID
            sj.add(i < salePrices.length ? String.valueOf(salePrices[i]) : "0.0"); //price
            sj.add(i < quantities.length ? String.valueOf(quantities[i]) : "0"); //quanity sold
        }

        sj.add(String.valueOf(orderTotal));
        sj.add(String.valueOf(getInvoiceNumber()));
        sj.add(getDate());

        return sj.toString();
    }

    @Override //toString method
    public String toString() {
        return "TransactionCustomer{"
                + "customerID='" + customerID + '\''
                + ", productIDs=" + java.util.Arrays.toString(productIDs)
                + ", productNames=" + java.util.Arrays.toString(productNames)
                + ", salePrices=" + java.util.Arrays.toString(salePrices)
                + ", quantities=" + java.util.Arrays.toString(quantities)
                + ", stockTotals=" + java.util.Arrays.toString(stockTotals)
                + ", orderTotal=" + orderTotal
                + ", invoiceNumber=" + getInvoiceNumber()
                + ", date='" + getDate() + '\''
                + ", type='" + getType() + '\''
                + '}';
    }
}
