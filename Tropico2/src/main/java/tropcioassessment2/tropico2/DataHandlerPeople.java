/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.TextField;

/**
 *
 * @author Tropico
 */

//this will manage the data for people
public class DataHandlerPeople {

    //define
    private String fileName;
    private ArrayList<Person> peopleList;

    //constructor
    public DataHandlerPeople(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.peopleList = new ArrayList<Person>();
        readDataFile();
    }

    //method to read the text file and create the objects and arrays
    private void readDataFile() {
        try {
            Scanner in = new Scanner(new FileReader(fileName));

            while (in.hasNextLine()) {
                String entry = in.nextLine();
                System.out.println("readling line: " + entry);
                String[] tokens = entry.split(",");

                //this is reading the txt file and cutting up into strings
                String type = tokens[0].trim();
                int ID = Integer.parseInt(tokens[1].trim());
                String name = tokens[2].trim();
                String phoneNumber = tokens[3].trim();
                String address = tokens[4].trim();
                String postcode = tokens[5].trim();
                String contactName = tokens[6].trim();
                String paymentOptions = tokens[7].trim();

                if ("Vendor".equals(type)) { //creating a vendor from the txt file
                    PersonVendor vendor = new PersonVendor(name, ID, phoneNumber, address, postcode, contactName, paymentOptions);
                    peopleList.add(vendor);
                } else if ("Customer".equals(type)) {  //creating a cusotmer from the txt file 
                    PersonCustomer customer = new PersonCustomer(name, ID, phoneNumber, address, postcode, contactName, paymentOptions);
                    peopleList.add(customer);
                }
            }
            in.close();
        } catch (FileNotFoundException ex) { //catch errors
            System.err.println("File not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error processing file: " + ex.getMessage());
        }
    }

    //this method will be saving the data
    public boolean saveData() {
        try {
            Formatter out = new Formatter(fileName);

            for (Person person : peopleList) {
                out.format("%s\n", person.toString());
            }
            System.out.println("Saved " + peopleList.size() + " entries to the file.");
            out.close();
            return true;
        } catch (SecurityException | FileNotFoundException ex) { //catch errors
            System.err.println("Error saving data: " + ex.getMessage());
            return false;
        }
    }

    //method to get all the customers for the reports
    public List<PersonCustomer> getAllCustomers() {
        List<PersonCustomer> customers = new ArrayList<>(); //creating a list for the customer details

        for (Person person : peopleList) {
            if (person instanceof PersonCustomer) {
                customers.add((PersonCustomer) person);
            }
        }

        return customers;
    }

    //method to get all the vendors for the reports
    public List<PersonVendor> getAllVendors() {
        List<PersonVendor> vendors = new ArrayList<>();  //creating a list for the vendors

        for (Person person : peopleList) {
            if (person instanceof PersonVendor) {
                vendors.add((PersonVendor) person);
            }
        }

        return vendors;
    }

    //method that adds a new customer to the array
    public void addCustomer(PersonCustomer customer) {
        peopleList.add(customer);
    }

    //method that adds a superclass person
    public void addPerson(Person p) {
        peopleList.add(p);
    }

    //method to reset the fields in the people controllers
    public void resetForm(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    //method for searching for a customer
    public PersonCustomer searchCustomer(String name, int ID) {
        for (Person person : peopleList) {
            if (person instanceof PersonCustomer) {
                PersonCustomer customer = (PersonCustomer) person;

                if ("Customer".equalsIgnoreCase(customer.getType())) { 

                    if ((!name.isEmpty() && customer.getName().equalsIgnoreCase(name))
                            || (ID != -1 && customer.getID() == ID)) {
                        return customer;
                    }
                }
            }
        }
        return null;  // return null if no matching customer is found
    }

    //method for updating the customer details 
    public boolean updateCustomerDetails(String originalName, int originalID,
            String editedName, String editedPhone,
            String editedAddress, String editedPostcode,
            String editedContactName, String editedPaymentOptions) {
        for (Person person : peopleList) {
            if (person instanceof PersonCustomer) {
                PersonCustomer customer = (PersonCustomer) person;

                if (customer.getName().equalsIgnoreCase(originalName) && customer.getID() == originalID) {
                    // Found the customer to update

                    // Update the customer's details
                    customer.setName(editedName);
                    customer.setPhoneNumber(editedPhone);
                    customer.setAddress(editedAddress);
                    customer.setPostcode(editedPostcode);
                    customer.setContactName(editedContactName);
                    customer.setPaymentOptions(editedPaymentOptions);

                    return true;
                }
            }
        }
        return false; // Customer not found and not updated
    }

//method to search for a vendor
    public PersonVendor searchVendor(String name, int ID) {
        for (Person person : peopleList) {
            if (person instanceof PersonVendor) {
                PersonVendor vendor = (PersonVendor) person;

                if ("Vendor".equalsIgnoreCase(vendor.getType())) {
                    // if match one or the other, will return the vendor
                    if ((!name.isEmpty() && vendor.getName().equalsIgnoreCase(name))
                            || (ID != -1 && vendor.getID() == ID)) {
                        return vendor;
                    }
                }
            }
        }
        return null;
    }

    //method for updating the vendor details
    public boolean updateVendorDetails(String originalName, int originalID,
            String editedName, String editedPhone,
            String editedAddress, String editedPostcode,
            String editedContactName, String editedPaymentOptions) {
        for (Person person : peopleList) {
            if (person instanceof PersonVendor) {
                PersonVendor vendor = (PersonVendor) person;

                if (vendor.getName().equalsIgnoreCase(originalName) && vendor.getID() == originalID) {
                    // Found the customer to update

                    // fields to update the customer's details
                    vendor.setName(editedName);
                    vendor.setPhoneNumber(editedPhone);
                    vendor.setAddress(editedAddress);
                    vendor.setPostcode(editedPostcode);
                    vendor.setContactName(editedContactName);
                    vendor.setPaymentOptions(editedPaymentOptions);

                    return true;
                }
            }
        }
        return false; // Vendor not found and thus not updated
    }

//method to get the customer using  ID
    public PersonCustomer getCustomerByID(int customerID) {
        for (Person person : peopleList) {
            if (person instanceof PersonCustomer && person.getID() == customerID) {
                return (PersonCustomer) person;
            }
        }
        return null;
    }

    //method to get the vendor using the ID
    public PersonVendor getVendorByID(int vendorID) {
        for (Person person : peopleList) {
            if (person instanceof PersonVendor && person.getID() == vendorID) {
                return (PersonVendor) person;
            }
        }
        return null;
    }

    //method to get a new ID thats unique
    public int generateNewID() {
        int maxID = 0;
        for (Person person : peopleList) {
            if (person.getID() > maxID) {
                maxID = person.getID();
            }
        }
        return maxID + 1;
    }

}
