package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SignUpController {

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private Button loginInsteadButton;

    @FXML
    private ImageView logoImageView;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameField;

    PayloadDB payloadDB = new PayloadDB();

    public void signUp(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String confirmPassword = confirmPasswordField.getText();

        payloadDB.signUpUser(event, username, email, password, confirmPassword);

    }

    public void loginInstead(ActionEvent event){
        PayloadDB.changeScene(event, "Login.fxml");;
    }

}
