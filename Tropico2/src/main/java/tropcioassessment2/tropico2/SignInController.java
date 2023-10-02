/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tropcioassessment2.tropico2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author duane
 */
public class SignInController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;

    // Hardcoded credentials
    private final String CORRECT_USERNAME = "admin";
    private final String CORRECT_PASSWORD = "admin";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: any initialization code if necessary
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) {
        if (usernameField.getText().equals(CORRECT_USERNAME) && 
            passwordField.getText().equals(CORRECT_PASSWORD)) {
            // Switch to the main page
            App.changeScene(1);
        } else {
            showAlert("Login Failed", "Incorrect username or password.");
        }
          // Clear the password field after attempting to log in
    passwordField.clear();
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        App.exit();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
