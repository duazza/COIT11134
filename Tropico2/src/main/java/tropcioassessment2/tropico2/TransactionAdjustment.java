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

  public class TransactionAdjustment extends Transaction {

    private int adjustedStockID;
    private String adjustedStockName;
    private double adjustedQuantity;
    private double purchasePrice;

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

     @Override
    public String[] getProductIDs() {
        // Return a single product ID in an array for this adjustment
        return new String[]{String.valueOf(adjustedStockID)};
    }

@Override
public double[] getQuantities() {
    return new double[]{(double) adjustedQuantity};
}

@Override
public String toStringForFile() {
    StringJoiner sj = new StringJoiner(",");

     // Adding transaction type from superclass
    sj.add(getTransactionType()); 
    // Placeholder for customer ID (same position as in the first class)
    sj.add("0");

    // Add the adjusted stock details
    sj.add(String.valueOf(adjustedStockID));
    sj.add(adjustedStockName);

    // Adjusted purchase price (assuming you've added a field for this in TransactionAdjustment)
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

    // Placeholders for order total, invoice number, and date (same positions as in the first class)
    sj.add("0.0");
    sj.add("0");
    sj.add(getDate());

    return sj.toString();
}


/*@Override
public String toStringForFile() {
    StringJoiner sj = new StringJoiner(",");

    // Placeholder for customer ID (same position as in the first class)
    sj.add("0");

    // Add the adjusted stock details
    sj.add(String.valueOf(adjustedStockID));
    sj.add(adjustedStockName);
    sj.add("0.0");  // No sale price for adjustments
    sj.add(String.valueOf(adjustedQuantity));

    // Add placeholders for other fields to ensure consistent format with transaction.txt
    for (int i = 0; i < 5; i++) {
        sj.add("N/A");
        sj.add("N/A");
        sj.add("0.0");
        sj.add("0");
    }

    // Placeholders for order total, invoice number, and date (same positions as in the first class)
    sj.add("0.0");
    sj.add("0");
    sj.add(getDate());

    return sj.toString();
}
    /*@Override
    public String toStringForFile() {
        StringJoiner sj = new StringJoiner(",");

      
        // Add the adjusted stock details
        sj.add(String.valueOf(adjustedStockID));
        sj.add(adjustedStockName);
        sj.add("0.0");  // No sale price for adjustments
        sj.add(String.valueOf(adjustedQuantity));

        // Add placeholders for other fields to ensure consistent format with transaction.txt
        for (int i = 0; i < 5; i++) {
            sj.add("N/A");
            sj.add("N/A");
            sj.add("0.0");
            sj.add("0");
        }

        sj.add("N/A");  // No total order value for adjustments
        sj.add("0.0");
        sj.add(getDate());

        return sj.toString();
    }*/

    @Override
    public String toString() {
        return "TransactionAdjustment{" +
               "adjustedStockID=" + adjustedStockID +
               ", adjustedStockName='" + adjustedStockName + '\'' +
               ", adjustedQuantity=" + adjustedQuantity +
               '}';
    }
}