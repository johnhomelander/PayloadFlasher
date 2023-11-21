package dbconnect;

import java.sql.*;

public class Demo {
    public static void main(String[] args){
        // Class.forName("org.mariadb.jdbc.Driver");
        // System.out.println("done");
        Demo obj = new Demo();
        obj.insertData("table1",2,"bobo");
        obj.insertData("table1",4,"nimon");
        obj.deleteData("table1", 2);
        obj.selectAll();
        
    }
    public void insertData(String table, int id, String name){
        try{
            String query = "insert into "+table+" values("+id+",'"+name+"');";
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/db1","root","toor");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            // System.out.println(rs);
            // rs.next();
            // System.out.println(rs.getString(1));
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void selectAll(){
        try{
            String query = "select * from table1;";

            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/db1","root","toor");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                String out = "";
                for(int i=1;i<=2;i++){
                    out += rs.getString(i) + " ";
                }
                System.out.println(out);
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteData(String table,int id){
        try{
            String query = "delete from "+table+" where id="+id;
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db1","root","toor");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            con.close();
       }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
