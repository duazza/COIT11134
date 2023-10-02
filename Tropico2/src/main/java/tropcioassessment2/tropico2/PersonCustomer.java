/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author duane
 */
class PersonCustomer extends Person {

    private String contactName;
    private String paymentOptions;
    private String type = "Customer"; // Hardcoded as Customer for this subclass
    
    public PersonCustomer(String name, int ID, String phoneNumber, String address, String postcode,
                          String contactName, String paymentOptions) {
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






