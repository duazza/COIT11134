/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author Tropico
 */
public abstract class Transaction {

    protected static int invoiceCounter = 0;
    protected int invoiceNumber;  // Changed type to int
    protected String date;
    protected String type;

    public abstract String[] getProductIDs();

    public abstract double[] getQuantities();

    public abstract String getTransactionType();  // Returns 'VENDOR', 'CUSTOMER', ADJUSTMENT

    public Transaction() {
        this.date = java.time.LocalDate.now().toString();
        generateInvoiceNumber(); //this generates the invoice ID
    }

    protected void generateInvoiceNumber() {
        invoiceCounter++;
        this.invoiceNumber = invoiceCounter;
    }

    public static int getInvoiceCounter() {
        return invoiceCounter;
    }

    public static void setInvoiceCounter(int invoiceCounter) {
        Transaction.invoiceCounter = invoiceCounter;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   //adstract method when adding to file
    public abstract String toStringForFile();

    @Override //tostring method 
    public String toString() {
        return "Transaction{"
                + "invoiceNumber=" + invoiceNumber
                + // Updated string concatenation
                ", date='" + date + '\''
                + ", type='" + type + '\''
                + '}';
    }
}
