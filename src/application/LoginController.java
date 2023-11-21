package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    PayloadDB payloadDBLogin = new PayloadDB();

    public void login(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();

        payloadDBLogin.checkUser(username, password);
    }

}
