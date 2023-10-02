/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

/**
 *
 * @author duane
 */
public class Person {
    
 
    private String name;
    private int ID;
    private String phoneNumber;
    private String address;
    private String postcode;

    // Constructor for the Person class
    public Person(String name, int ID, String phoneNumber, String address, String postcode) {
        this.name = name;
        this.ID = ID;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postcode = postcode;
    }

     public String getName() {
        return name;
    }
     
    public void setName(String name) {
        this.name = name;
    } 
    
     public int getID() {
        return ID;
    }
 

    

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

   
    @Override
    public String toString() {
        return ID + "," + name + "," + phoneNumber + "," + address + "," + postcode;
    }
}
    
