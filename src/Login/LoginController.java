package Login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController{
    @FXML
     TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    PayloadDB payloadDBLogin = new PayloadDB();

    public void login(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();

        // System.out.println(username+password);
        payloadDBLogin.checkUser(event,username, password);
    }


}
