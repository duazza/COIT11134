/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 *
 * @author duane
 */
public class DataHandlerPeople {
     private String fileName;
    private ArrayList<Person> peopleList;

    public DataHandlerPeople(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.peopleList = new ArrayList<Person>();
        readDataFile();
    }

    private void readDataFile() {
        try {
            Scanner in = new Scanner(new FileReader(fileName));

            while (in.hasNextLine()) {
                String entry = in.nextLine();
                System.out.println("readling line: " + entry);
                String[] tokens = entry.split(",");

                String type = tokens[0].trim();
                int ID = Integer.parseInt(tokens[1].trim());
                String name = tokens[2].trim();
                String phoneNumber = tokens[3].trim();
                String address = tokens[4].trim();
                String postcode = tokens[5].trim();
                String contactName = tokens[6].trim();
                String paymentOptions = tokens[7].trim();
           

                if ("Vendor".equals(type)) {
                    PersonVendor vendor = new PersonVendor(name, ID, phoneNumber, address, postcode, contactName, paymentOptions);
                    peopleList.add(vendor);
                } else if ("Customer".equals(type)) {
                    PersonCustomer customer = new PersonCustomer(name, ID, phoneNumber, address, postcode, contactName, paymentOptions);
                    peopleList.add(customer);
                }
            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error processing file: " + ex.getMessage());
        }
    }

public boolean saveData() {
    try {
        Formatter out = new Formatter(fileName);

        for (Person person : peopleList) {
            out.format("%s\n", person.toString());
        }
        System.out.println("Saved " + peopleList.size() + " entries to the file.");
        out.close();
        return true;  // Return true if save operation is successful
    } catch (SecurityException | FileNotFoundException ex) {
        System.err.println("Error saving data: " + ex.getMessage());
        return false;  // Return false if there's an error during the save operation
    }
}

public void addCustomer(PersonCustomer customer) {
    peopleList.add(customer);
}

   public void addPerson(Person p) {
    peopleList.add(p);
}
   
public void resetForm(TextField... fields) {
    for (TextField field : fields) {
        field.clear();
    }
}

   
public PersonCustomer searchCustomer(String name, int ID) {
    for (Person person : peopleList) {
        if (person instanceof PersonCustomer ){
            PersonCustomer customer = (PersonCustomer) person;
            
            if("Customer".equalsIgnoreCase(customer.getType())){
            // If name is provided and matches, or if ID is provided and matches, return the customer
                if ((!name.isEmpty() && customer.getName().equalsIgnoreCase(name)) || 
                    (ID != -1 && customer.getID() == ID)) {
                    return customer;
            }
        }
    }
    }
    return null;  // return null if no matching customer is found
}
    
    
public boolean updateCustomerDetails(String originalName, int originalID, 
                                     String editedName, String editedPhone, 
                                     String editedAddress, String editedPostcode, 
                                     String editedContactName, String editedPaymentOptions) {
    for (Person person : peopleList) {
        if (person instanceof PersonCustomer) {
            PersonCustomer customer = (PersonCustomer) person;

            if (customer.getName().equalsIgnoreCase(originalName) && customer.getID() == originalID) {
                // Found the customer to update

                // Update the customer's attributes
                customer.setName(editedName);
                customer.setPhoneNumber(editedPhone);
                customer.setAddress(editedAddress);
                customer.setPostcode(editedPostcode);
                customer.setContactName(editedContactName);
                customer.setPaymentOptions(editedPaymentOptions);

                return true; // Successfully updated
            }
        }
    }
    return false; // Customer not found and thus not updated
}

public PersonVendor searchVendor(String name, int ID) {
    for (Person person : peopleList) {
        if (person instanceof PersonVendor ){
            PersonVendor vendor = (PersonVendor) person;
            
            if("Vendor".equalsIgnoreCase(vendor.getType())){
            // If name is provided and matches, or if ID is provided and matches, return the vendor
                if ((!name.isEmpty() && vendor.getName().equalsIgnoreCase(name)) || 
                    (ID != -1 && vendor.getID() == ID)) {
                    return vendor;
            }
        }
    }
    }
    return null;  // return null if no matching customer is found
}
    
    
public boolean updateVendorDetails(String originalName, int originalID, 
                                     String editedName, String editedPhone, 
                                     String editedAddress, String editedPostcode, 
                                     String editedContactName, String editedPaymentOptions) {
    for (Person person : peopleList) {
        if (person instanceof PersonVendor) {
            PersonVendor vendor = (PersonVendor) person;

            if (vendor.getName().equalsIgnoreCase(originalName) && vendor.getID() == originalID) {
                // Found the customer to update

                // Update the customer's attributes
                vendor.setName(editedName);
                vendor.setPhoneNumber(editedPhone);
                vendor.setAddress(editedAddress);
                vendor.setPostcode(editedPostcode);
                vendor.setContactName(editedContactName);
                vendor.setPaymentOptions(editedPaymentOptions);

                return true; // Successfully updated
            }
        }
    }
    return false; // Vendor not found and thus not updated
}

   public Person getCustomerByID(int customerID) {
        for (Person person : peopleList) {
            if (person instanceof PersonCustomer && person.getID() == customerID) {
                return person;
            }
        }
        return null;  // Return null if no matching customer is found
    }


public void displayConfirmation(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
public static void displayMessage(String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle("Message");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}
