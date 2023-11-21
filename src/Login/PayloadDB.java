package Login;

import java.sql.*;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PayloadDB {
    private String url = "jdbc:mariadb://localhost/db1";
    private String username = "root";
    private String password = "toor";
    
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
            Connection con = DriverManager.getConnection(url,username,password);
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
            Connection con = DriverManager.getConnection(url,username,password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            output = rs.getString(1);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }
    public void checkUser(ActionEvent event,String user, String pass){
        try{
            String query = "select password from payload_user where username='"+user+"'";
            Connection con = DriverManager.getConnection(url,username,password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (!rs.isBeforeFirst()){
                System.out.println("Empty output");
            }
            else{
                while (rs.next()){
                    String retrievedPassword = rs.getString("password");
                    if (retrievedPassword.equals(pass)){
                        System.out.println("Logged In!");
                        changeScene(event, "Main.fxml");
                    }
                    else{
                        System.out.println("Incorrect Password");
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }

    public static void changeScene(ActionEvent event, String fxmlFile){
        try{
        Parent root = FXMLLoader.load(LoginController.class.getResource(fxmlFile));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        }
        catch(Exception e){
            e.printStackTrace();
        }
    

    }
}
