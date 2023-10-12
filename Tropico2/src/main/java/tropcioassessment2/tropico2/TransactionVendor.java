/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.util.StringJoiner;

/**
 *
 * @author Tropico
 */

//class for the vendors - transactions
public class TransactionVendor extends Transaction {

    //define
    private int vendorID;
    private String vendorName;
    private String[] productIDs;
    private String[] productNames;
    private double[] purchasePrices;
    private double[] quantities;
    private double[] stockTotals;
    private double orderTotal;
    private boolean isVendor;

    //constructor
    public TransactionVendor(int vendorID, String[] productIDs, String[] productNames, double[] purchasePrices, double[] quantities) {
        super();
        this.type = "V";
        this.vendorID = vendorID;
        this.productIDs = productIDs;
        this.productNames = productNames;
        this.purchasePrices = purchasePrices;
        this.quantities = quantities;
        this.stockTotals = new double[productIDs.length];
       calculateStockTotals();
        calculateOrderTotal();
        this.isVendor = true; // Set isVendor to true for TransactionVendor
    }
    //calc the stock total from transactions
   private void calculateStockTotals() {
        int length = Math.min(productIDs.length, Math.min(purchasePrices.length, quantities.length));
        for (int i = 0; i < length; i++) {
            this.stockTotals[i] = this.purchasePrices[i] * this.quantities[i];
        }
    }

   //calc the order total 
    private void calculateOrderTotal() {
        for (double stockTotal : stockTotals) {
            this.orderTotal += stockTotal;
        }
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int customerID) {
        this.vendorID = vendorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String[] getProductNames() {
        return productNames;
    }

    public void setProductNames(String[] productNames) {
        this.productNames = productNames;
    }

    public double[] getPurchasePrices() {
        return purchasePrices;
    }

    public void setPurchasePrices(double[] purchasePrices) {
        this.purchasePrices = purchasePrices;
    }

    @Override //gets quantity 
    public double[] getQuantities() {
        return quantities;
    }

    //sets the quantity
    public void setQuantities(double[] quantities) {
        this.quantities = quantities;
    }

    //gets the stock count
    public double[] getStockTotals() {
        return stockTotals;
    }

    //sets the new stock count
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

    @Override //type vendor
    public String getTransactionType() {
        return "VENDOR";
    }

    @Override
    public String[] getProductIDs() {
        return productIDs;
    }

    @Override  //this saves the transaction with placedholders  when not all 6 order lines are used. 
    public String toStringForFile() {
        StringJoiner sj = new StringJoiner(",");
        // Adding transaction type from superclass
        sj.add(getTransactionType());

        sj.add(String.valueOf(vendorID));

        for (int i = 0; i < 6; i++) {
            sj.add(i < productIDs.length ? productIDs[i] : "N/A");  //product ID - this is placed when not all order lines are used
            sj.add(i < productNames.length ? productNames[i] : "N/A"); //product name 
            sj.add(i < purchasePrices.length ? String.valueOf(purchasePrices[i]) : "0.0"); //price
            sj.add(i < quantities.length ? String.valueOf(quantities[i]) : "0");//quantity
        }

        sj.add(String.valueOf(orderTotal));
        sj.add(String.valueOf(getInvoiceNumber()));
        sj.add(getDate());

        return sj.toString();
    }

    @Override
    public String toString() { //to string method
        return "TransactionVendor{"
                + "vendorID='" + vendorID + '\''
                + ", productIDs=" + java.util.Arrays.toString(productIDs)
                + ", productNames=" + java.util.Arrays.toString(productNames)
                + ", salePrices=" + java.util.Arrays.toString(purchasePrices)
                + ", quantities=" + java.util.Arrays.toString(quantities)
                + ", stockTotals=" + java.util.Arrays.toString(stockTotals)
                + ", orderTotal=" + orderTotal
                + ", invoiceNumber=" + getInvoiceNumber()
                + ", date='" + getDate() + '\''
                + ", type='" + getType() + '\''
                + '}';
    }
}
