/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

import javafx.scene.control.TextField;

/**
 *
 * @author duane
 */

//this is a helper class that helps groups the fields related to each product.
public class ProductFieldGroup {
    public TextField productCodeField;
    public TextField qtyField;
    public TextField priceField;  // ... and any other fields you might need

    public ProductFieldGroup(TextField productCodeField, TextField qtyField, TextField priceField) {
        this.productCodeField = productCodeField;
        this.qtyField = qtyField;
        this.priceField = priceField;
    }

    public TextField getProductCodeField() {
        return productCodeField;
    }

    public void setProductCodeField(TextField productCodeField) {
        this.productCodeField = productCodeField;
    }

    public TextField getQtyField() {
        return qtyField;
    }

    public void setQtyField(TextField qtyField) {
        this.qtyField = qtyField;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public void setPriceField(TextField priceField) {
        this.priceField = priceField;
    }
    
    
}