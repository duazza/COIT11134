/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author Tropcio
 */

//this is the subclass for the vendor
class PersonVendor extends Person {
    
    //define
    private String contactName;
    private String paymentOptions;
    private String type = "Vendor"; // Hardcoded as Vendor for this subclass
    
    //constructor
    public PersonVendor(String name, int ID, String phoneNumber, String address, String postcode, String contactName, String paymentOptions) {
        super(name, ID, phoneNumber, address, postcode);
        this.contactName = contactName;
        this.paymentOptions = paymentOptions;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(String paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public String getType() { //adds the type of person
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
      @Override //adds to the superclass tostring
    public String toString() {
        return type + "," + super.toString() + "," + contactName + "," + paymentOptions;
    }
}