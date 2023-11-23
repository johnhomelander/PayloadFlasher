package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;

public class DeletePayloadController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button deletePayloadButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<String> payloadChoiceBox;

    PayloadDB payloadDB = new PayloadDB();
    ArrayList<String> payloadNames = payloadDB.getPayloadNames();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payloadChoiceBox.getItems().addAll(payloadNames);
    }


    public void deletePayload(ActionEvent event) {
        String loginPass = passwordField.getText();
        String payloadName = payloadChoiceBox.getValue();

        payloadDB.deletePayload(event, payloadName, loginPass);
    }

    public void switchToMainScene(ActionEvent event) {
            PayloadDB.changeScene(event, "Main.fxml");
    }

}
