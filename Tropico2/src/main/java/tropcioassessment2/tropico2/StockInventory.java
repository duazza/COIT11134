/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author duane
 */
public class StockInventory {
        private String name;
    private int inventoryCode;  // This might be set externally or when the object is created.
    private double salePrice;
    private double purchasePrice;
    private double stockLevel;  // Initialized to 0, but can be modified by Transaction operations.
    private boolean available;

    public StockInventory(String name, int inventoryCode, double salePrice, double purchasePrice) {
        this.name = name;
        this.inventoryCode = inventoryCode;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.stockLevel = 0;  // Default stock level is 0 when an item is created.
        
    }



    // Getters
    public String getName() {
        return name;
    }

    public int getProductCode() {
        return inventoryCode;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getStockLevel() {
        return stockLevel;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setProductCode(int inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setStockLevel(double stockLevel) {
        this.stockLevel = stockLevel;
    }

    // Additional methods to modify stock level
    public void addStock(double quantity) {
        this.stockLevel += quantity;
    }

    public void deductStock(double quantity) {
        this.stockLevel -= quantity;
    }
    

  
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Optional: Override toString() for easier debugging and data saving
    @Override
    public String toString() {
        return String.format("%s,%d,%.2f,%.2f", name, inventoryCode, salePrice, purchasePrice);
    }

    
}
