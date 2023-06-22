package com.mycompany.project_database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Customer {
    
    static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    static String username = "FINAL_PROJECT";
    static String passwordSql = "123456";
    static Connection conn ;
    
    @FXML
    public TextField id;
        
    @FXML
    public Label errorMassage;
    
    @FXML
    public Label buyMassage;
    
    @FXML
    private TextArea table;
    
    private String tableOutput;
    
    static String idCustomer;

    @FXML
    private void buy() throws IOException {
        boolean isExists = false;
        try{
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sql = "SELECT * FROM CARS";
            Statement sts = conn.createStatement();
            ResultSet rs = sts.executeQuery(sql);
            while(rs.next()) {
                if(rs.getString(1).equals(id.getText())) {
                   isExists = true;
                }
            }
            if(isExists) {
                conn = DriverManager.getConnection(url, username, passwordSql);
                String sqll = "UPDATE CARS SET ID_CUSTOMER = " + idCustomer + " WHERE ID = "+ id.getText();
                Statement stss = conn.createStatement();
                stss.executeUpdate(sqll);
                buyMassage.setText("Done..");
            } else {
                errorMassage.setText("This car not exists..");
            }
        } catch(Exception ex) {
            System.out.println(ex);
            errorMassage.setText("Error.. ");
        }
    }
    
    @FXML
    private void carShow() throws IOException {
        table.clear();
        table.setEditable(false);
        tableOutput = "";
        tableOutput += String.format("%-10s | %-15s | %-10s | %-10s\n","ID_CAR","MODEL","COLOR","PRICE");
        try {
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sqll = "SELECT * FROM CARS";
            Statement sts = conn.createStatement();
            ResultSet rs = sts.executeQuery(sqll);
            while(rs.next()) {
                if(rs.getString(2) == (null))
                    tableOutput += String.format("%-18s| %-18s| %-15s | %s $\n",rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(5));
            }
            table.setText(tableOutput);
        } catch(Exception ex) {
            table.setText("Error...");
        }
    }
    
    @FXML
    private void myCars() throws IOException {
        table.clear();
        table.setEditable(false);
        tableOutput = "";
        tableOutput += String.format("%-10s | %-15s | %-15s | %-10s | %-10s\n","ID_CAR","ID_CUSTOMER","MODEL","COLOR","PRICE");
        try {
            conn = DriverManager.getConnection(url, username, passwordSql);
            String sqll = "SELECT * FROM CARS";
            Statement sts = conn.createStatement();
            ResultSet rs = sts.executeQuery(sqll);
            while(rs.next()) {
                if(rs.getString(2) != null && rs.getString(2).equals(idCustomer))
                    tableOutput += String.format("%-18s| %-25s| %-18s| %-16s | %s $\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
            table.setText(tableOutput);
        } catch(Exception ex) {
            table.setText("Error...");
        }  
    }
    
    @FXML
    private void SignOut() throws IOException {
        App.setRoot("LogIn");
    }
}