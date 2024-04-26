/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcapp;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Lenovo
 */
public class Connector {
    String DBurl = "jdbc:mysql://localhost/student_db";
    String DBusername = "root";
    String DBpassword = "";
    
    String data[][] = new String[500][4];
    Connection conn;
    Statement statement;

    public Connector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl, DBusername, DBpassword);
            System.out.println("Connection Success");
        }catch(Exception ex){
            System.out.println("Connection Failed " + ex.getMessage());
        }
    }
    
    void insertData(String lecture_number, String lecture_name, String lecturer_number, String classes){
        try{
            String query = "INSERT INTO `schedule`(`lecture_number`,`lecture_name`,`lecturer_number`,`class`) VALUES ('" + lecture_number + "','" + lecture_name + "','" + lecturer_number + "','" + classes + "')";
                    
            statement = conn.createStatement();
            statement.executeUpdate(query);
                    
            System.out.println("Input Success");
            JOptionPane.showMessageDialog(null, "Input Success");
        }catch (Exception ex){
            System.out.println("Input Failed");
        }
    }
    
    String[][] readData(){
        try{
           int totalData = 0; 
           String query = "SELECT * FROM schedule";
           statement = conn.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           while(resultSet.next()){ //konversi tabel ke string
               data[totalData][0] = resultSet.getString("lecture_number");
               data[totalData][1] = resultSet.getString("lecture_name");
               data[totalData][2] = resultSet.getString("lecturer_number");
               data[totalData][3] = resultSet.getString("class");
               totalData++;
           }
           statement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }finally{
            return data;
        }
    }
    
    void deleteData(String lecture_name){
        try{
            String query = "DELETE FROM `schedule` WHERE lecture_name='" + lecture_name + "'";
            // delete based on primary key
            statement = conn.createStatement();
            statement.executeUpdate(query);
                   
            JOptionPane.showMessageDialog(null, "Delete Success");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
    }
    
    void updateData(String lecture_number, String lecture_name, String lecturer_number, String classes){
        try{
            String query = "UPDATE `schedule` SET lecture_number = '" + lecture_number + "',"
                    + " lecturer_number = '" + lecturer_number + "',"
                    + " class = '" + classes + "' WHERE lecture_name = '" + lecture_name + "'";
       
            statement = conn.createStatement();
            statement.executeUpdate(query);
                    
            System.out.println("Update Success");
            JOptionPane.showMessageDialog(null, "Update Success");
        }catch (Exception ex){
            System.out.println("Update Failed : " + ex.getMessage());
        }
    }
}
