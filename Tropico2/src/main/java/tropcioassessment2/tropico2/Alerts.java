/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tropcioassessment2.tropico2;

import javafx.scene.control.Alert;  // Importing the Alert class from JavaFX library

/**
 * Alerts class provides utility methods to show various types of alert messages.
 * This class centralizes the alerts.
 * 
 * @author Tropico
 */
public class Alerts {

    /**
     * Displays an alert of the specified type with the given title and message.
     *
     * @param alertType The type of the alert (e.g., INFORMATION, ERROR, WARNING).
     * @param title     The title of the alert.
     * @param message   The message to be displayed in the alert.
     */
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);  // Create a new alert of the given type
        alert.setTitle(title);               // Set the title of the alert
        alert.setHeaderText(null);           // Clearing the header text for a cleaner look
        alert.setContentText(message);       // Set the main message of the alert
        alert.showAndWait();                 // Display the alert and wait for user interaction
    }

    /**
     * Displays an informational alert with the given title and message.
     *
     * @param title   The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    public static void showInfoAlert(String title, String message) {
        showAlert(Alert.AlertType.INFORMATION, title, message);  //showAlert method with INFO type
    }

    /**
     * Displays an error alert with the given title and message.
     *
     * @param title   The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    public static void showErrorAlert(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);  //showAlert method with ERROR type
    }

    /**
     * Displays a warning alert with the given title and message.
     *
     * @param title   The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    public static void showWarningAlert(String title, String message) {
        showAlert(Alert.AlertType.WARNING, title, message);  //showAlert method with WARNING type
    }
}
