/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author duane
 */
class PersonVendor extends Person {
    
    private String contactName;
    private String paymentOptions;
    private String type = "Vendor"; // Hardcoded as Vendor for this subclass
    
    public PersonVendor(String name, int ID, String phoneNumber, String address, String postcode,
                        String contactName, String paymentOptions) {
        super(name, ID, phoneNumber, address, postcode);
        this.contactName = contactName;
        this.paymentOptions = paymentOptions;
    }

    // Additional methods and getters/setters specific to vendors can be added here

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
      @Override
    public String toString() {
        return type + "," + super.toString() + "," + contactName + "," + paymentOptions;
    }
}