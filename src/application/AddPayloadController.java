package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddPayloadController {

    @FXML
    private Button backButton;

    @FXML
    private Button addPayloadButton;

    @FXML
    private TextField payloadNameField;

    @FXML
    private TextArea payloadValueArea;

    PayloadDB payloadDB = new PayloadDB();

    public void addPayload(ActionEvent event){
        String payloadName = payloadNameField.getText();
        String payloadValue = payloadValueArea.getText();

        payloadDB.insertPayload(event, payloadName, payloadValue);
    }

    public void switchToMainScene(ActionEvent event){
        if (payloadNameField.getText().isEmpty() && payloadValueArea.getText().isEmpty()){
            PayloadDB.changeScene(event, "Main.fxml");
        }
        else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Save changes made?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK){
                    if (payloadNameField.getText().isEmpty()){
                        PayloadDB.createErrorAlert("No payload name given");}
                    else if (payloadValueArea.getText().isEmpty()){
                        PayloadDB.createErrorAlert("Payload value is empty");}
                    else{
                        addPayload(event);
                        PayloadDB.changeScene(event, "Main.fxml");}
                }
                else{
                    PayloadDB.changeScene(event, "Main.fxml");
                }
            });
        }
    }

}
