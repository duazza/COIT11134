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

//subclass for adjustments - transactions
public class TransactionAdjustment extends Transaction {

    //define
    private int adjustedStockID;
    private String adjustedStockName;
    private double adjustedQuantity;
    private double purchasePrice;

    //constructor
    public TransactionAdjustment(int adjustedStockID, String adjustedStockName, double adjustedQuantity, double purchasePrice) {
        this.adjustedStockID = adjustedStockID;
        this.adjustedStockName = adjustedStockName;
        this.adjustedQuantity = adjustedQuantity;
        this.purchasePrice = purchasePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getAdjustedStockID() {
        return adjustedStockID;
    }

    public String getAdjustedStockName() {
        return adjustedStockName;
    }

    public double getAdjustedQuantity() {
        return adjustedQuantity;
    }

    @Override
    public String getTransactionType() {
        return "ADJUSTMENT";
    }

    @Override // gets the product ID for the prodct we are adjusting
    public String[] getProductIDs() {
        // Return a single product ID in an array for this adjustment
        return new String[]{String.valueOf(adjustedStockID)};
    }

    @Override //getter for the quantity
    public double[] getQuantities() {
        return new double[]{(double) adjustedQuantity};
    }

    @Override // this adds the transaction to the file
    public String toStringForFile() {
        StringJoiner sj = new StringJoiner(",");

        // Adding transaction type from superclass
        sj.add(getTransactionType());
        // Placeholder for customer ID 
        sj.add("0");

        // Add the adjusted stock details
        sj.add(String.valueOf(adjustedStockID));
        sj.add(adjustedStockName);

        // Adjusted purchase price 
        sj.add(String.valueOf(purchasePrice));

        sj.add(String.valueOf(adjustedQuantity));

        // Placeholders for remaining item details
        sj.add("N/A");  // sale price
        sj.add("N/A");  // discount
        sj.add("0.0");  // total price for item
        sj.add("0");    // item quantity

        // Add placeholders for other fields to ensure consistent format with transaction.txt
        for (int i = 0; i < 4; i++) {
            sj.add("N/A");
            sj.add("N/A");
            sj.add("0.0");
            sj.add("0");
        }

        // Placeholders for order total, invoice number, and date 
        sj.add("0.0");
        sj.add("0");
        sj.add(getDate());

        return sj.toString();
    }

    @Override //toString method
    public String toString() {
        return "TransactionAdjustment{"
                + "adjustedStockID=" + adjustedStockID
                + ", adjustedStockName='" + adjustedStockName + '\''
                + ", adjustedQuantity=" + adjustedQuantity
                + '}';
    }
}
