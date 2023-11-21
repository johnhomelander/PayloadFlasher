package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.util.*;

public class Controller implements Initializable {
    @FXML
    private Button loadButton;
    @FXML
    private Button flashButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button addButton;
    @FXML
    private TextArea payloadOutputArea;
    @FXML
    private ChoiceBox<String> payloadChoiceBox;

    PayloadDB payloadDB = new PayloadDB();

    private ArrayList<String> payloadTypes = payloadDB.getPayloadNames();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       payloadChoiceBox.getItems().addAll(payloadTypes);
    }

    public void loadPayload(ActionEvent event){
        String output = payloadDB.getPayloadValue(payloadChoiceBox.getValue());
        payloadOutputArea.setText(output);
    }
    public void flashPayload(){}

    public void clearPayloadOutputArea(ActionEvent event){
        payloadOutputArea.clear();
    }

}
