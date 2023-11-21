package application;

import java.sql.*;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PayloadDB {
    private String url = "jdbc:mariadb://localhost/db1";
    private String dbUser = "root";
    private String dbPass = "toor";
    
    // public static void main(String[] args) {
        
    // PayloadDB payloadDB = new PayloadDB();
    
    // ArrayList<String> payloadTypes = payloadDB.getPayloadNames();
    // System.out.println(payloadTypes);
    // System.out.println(payloadDB.getPayloadValue("hoaxshell"));

    // }

    public ArrayList<String> getPayloadNames(){
        ArrayList<String> output = new ArrayList<String>();
        try{
            String query = "select payload_name from payload_table;";
            Connection con = DriverManager.getConnection(url,dbUser,dbPass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                output.add(rs.getString(1));
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }

    public String getPayloadValue(String payloadName){
        String output = "";
        try{
            String query = "select payload_value from payload_table where payload_name='"+payloadName+"'";
            Connection con = DriverManager.getConnection(url,dbUser,dbPass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            output = rs.getString(1);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }

    public void loginUser(ActionEvent event,String loginUser, String loginPass){
        // int flag = 0;
        if (loginUser.isEmpty()){
            createErrorAlert("Username not entered");
            // System.out.println("Enter username");
        }
        else if (loginPass.isEmpty()){
            createErrorAlert("Password not entered");
            // System.out.println("Enter password");
        }
        else{
        try{
            String query = "select password from payload_user where username='"+loginUser+"'";
            Connection con = DriverManager.getConnection(url,dbUser,dbPass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.isBeforeFirst()){
                    // flag=1;
                while (rs.next()){
                    String retrievedPassword = rs.getString("password");
                    // flag = 2;
                    if (retrievedPassword.equals(loginPass)){
                        // System.out.println("Logged In!");
                        changeScene(event, "Main.fxml");
                        
                    }
                    else{
                        createErrorAlert("Incorrect Password");
                        // System.out.println("Incorrect Password");
                    }
                }
            }
            else{
                createErrorAlert("Incorrect Credentials");
                // System.out.println("Incorrect Credentials");
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        // System.out.println(flag);
    }
    }

    public static void changeScene(ActionEvent event, String fxmlFile){
        try{
        Parent root = FXMLLoader.load(App.class.getResource(fxmlFile));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void createErrorAlert(String errorMessage){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(errorMessage); 
        alert.show();
    }

    public void signUpUser(ActionEvent event, String signUpUser, String signUpEmail, String signUpPass,String signUpConfirmPass){
        
        if (signUpUser.isEmpty()|| signUpEmail.isEmpty() || signUpPass.isEmpty() || signUpConfirmPass.isEmpty()){
            createErrorAlert("Incomplete Details");
            // System.out.println("Incomplete Details");
        }
        else{
        try{
            String checkQuery = "select * from payload_user where username='"+signUpUser+"'";
            String addUserQuery = "insert into payload_user values('"+signUpUser+"','"+signUpPass+"','"+signUpEmail+"')";
            Connection con = DriverManager.getConnection(url, dbUser, dbPass);
            Statement st = con.createStatement();
            ResultSet checkResult = st.executeQuery(checkQuery);

            if (!checkResult.isBeforeFirst()){
                if (signUpPass.equals(signUpConfirmPass)){
                    st.executeQuery(addUserQuery);
                }
                else{
                    createErrorAlert("Input passwords do not match");
                    // System.out.println("Input passwords do not match");
                }
                // System.out.println("User created");
                changeScene(event, "Main.fxml");
            }
            else{
                createErrorAlert("User exists already");
                // System.out.println("User exists");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }
    }


}
