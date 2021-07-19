package idir.embag.Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
public class LoginController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private VBox boxRight;
    @FXML // This TextField using to type the username of the user
    private JFXTextField usernameField;
    @FXML // This PasswordField using to type the password of the user
    private JFXPasswordField passwordField;
    private JFXSnackbar toastErrorMsg;

    @Override
    public void initialize(URL url, ResourceBundle rb) { // this function like constracotor, called in the creation of the FXML

        boxRight.setOnKeyPressed(event -> { // Add Key Listener, if i click to the Enter Button Call btnLogin() method
            if (event.getCode().equals(ENTER)) {
                btnLogin();
            }
        });
        
        usernameField.setOnKeyReleased(event -> { // this event for check max length of the answer area
            if (usernameField.getText().length() > 25) {
                usernameField.setText(usernameField.getText().substring(0, 25));
                usernameField.positionCaret(usernameField.getText().length());
            }
        });
        passwordField.setOnKeyReleased(event -> { // this event for check max length of the answer area
            if (passwordField.getText().length() > 50) {
                passwordField.setText(passwordField.getText().substring(0, 50));
                passwordField.positionCaret(passwordField.getText().length());
            }
        });
        
        toastErrorMsg = new JFXSnackbar(boxRight);
    }

    @FXML
    private void btnLogin() { // This function check username & password to login to the System    
        final JFXSnackbar.SnackbarEvent snackbarEvent = 
        new JFXSnackbar.SnackbarEvent(new Label("Some string to display"), Duration.seconds(3.33), null);

        if (usernameField.getText().trim().isEmpty()) {
            toastErrorMsg.enqueue(snackbarEvent);
            return;
        }
        if (!usernameField.getText().trim().matches("[A-Za-z0-9_]{4,}")) {
            toastErrorMsg.enqueue(snackbarEvent);
            return;
        }
        

        if (passwordField.getText().trim().isEmpty()) {
            toastErrorMsg.enqueue(snackbarEvent);
            return;
        }
        if (passwordField.getText().trim().length() < 8) {
            toastErrorMsg.enqueue(snackbarEvent);
            return;
        }
        
   
    }

    @FXML
    private void btnExit() {
        Platform.exit();
    }

}
