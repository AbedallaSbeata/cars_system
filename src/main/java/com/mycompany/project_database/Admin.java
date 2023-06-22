package com.mycompany.project_database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Admin {
    
    static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    static String username = "FINAL_PROJECT";
    static String passwordSql = "123456";
    static Connection conn ;
    
    @FXML
    TextField carId;
    @FXML
    TextField carModel;
    @FXML
    TextField carPrice;
    @FXML
    TextField carColor;
    @FXML
    TextArea table;
    @FXML
    public Label Massage;
    @FXML
    public Label createMassage;
    String tableOutput;
 
   
    @FXML
    private void insert() throws IOException {
        try {
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sqll = "INSERT INTO CARS(ID,MODEL,COLOR,PRICE) VALUES(?,?,?,?)";
            Statement sts = conn.createStatement();
            PreparedStatement psts = conn.prepareStatement(sqll);
            psts.setString(1, carId.getText());
            psts.setString(2, carModel.getText());
            psts.setString(3, carColor.getText());
            psts.setString(4, carPrice.getText());
            psts.executeUpdate();
            createMassage.setText("The new car has been added.");
            } catch(Exception ex) {
                Massage.setText("This id exists.. ");
            }
    }
    
    @FXML
    private void update() throws IOException {
        try {
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sqll = "UPDATE CARS SET MODEL = ?, COLOR = ?, PRICE = ? WHERE ID = ? ";
            Statement sts = conn.createStatement();
            PreparedStatement psts = conn.prepareStatement(sqll);
            psts.setString(1, carModel.getText());
            psts.setString(2, carColor.getText());
            psts.setString(3, carPrice.getText());
            psts.setString(4, carId.getText());
            psts.executeUpdate();
            createMassage.setText("Car data has been updated.");
            } catch(Exception ex) {
                Massage.setText("Wrong input.. ");
            }
    }
    
    @FXML
    private void delete() throws IOException {
        try {
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sqll = "DELETE FROM cars WHERE id = ? and id_customer <> null";
            Statement sts = conn.createStatement();
            PreparedStatement psts = conn.prepareStatement(sqll);
            psts.setString(1, carId.getText());
            psts.executeUpdate();
            createMassage.setText("The car has been deleted.");
            } catch(Exception ex) {
                Massage.setText("Car not exists..");
            }
    }
    
    @FXML
    private void carShow() throws IOException {
        table.clear();
        table.setEditable(false);
        tableOutput = "";
        tableOutput += String.format("%-10s|%-25s|%-15s|%-15s|%s\n","ID_CAR","ID_CUSTOMER","MODEL","COLOR","PRICE");
        try {
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sqll = "SELECT * FROM CARS";
            Statement sts = conn.createStatement();
            ResultSet rs = sts.executeQuery(sqll);
            while(rs.next()) {
                tableOutput += String.format("%-18s|%-30s|%-20s|%-18s|%s $\n",rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
            table.setText(tableOutput);
        } catch(Exception ex) {
            table.setText("Error...");
        }
    }
    
    @FXML
    private void customerShow() throws IOException {
        table.clear();
        table.setEditable(false);
        tableOutput = "";
        tableOutput += String.format("%-15s|%-30s |%-15s|%s\n","ID_CUSTOMER","NAME","PHONE_NUMBER","LOCATION");
        try {
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sqll = "SELECT * FROM CUSTOMER";
            Statement sts = conn.createStatement();
            ResultSet rs = sts.executeQuery(sqll);
            while(rs.next()) {
               tableOutput += String.format("%-23s|%-32s|%-25s|%s\n",rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5));
            }
            table.setText(tableOutput);
        } catch(Exception ex) {
            table.setText("Error...");
        }
    }
    @FXML
    private void sginOut() throws IOException {
        App.setRoot("LogIn");
    }
}