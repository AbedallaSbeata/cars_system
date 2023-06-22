package com.mycompany.project_database;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.scene.control.Label;

public class LogIn {
    static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    static String username = "FINAL_PROJECT";
    static String passwordSql = "123456";
    static Connection conn ;
    
    @FXML
    public TextField id;
    
    @FXML
    public TextField password;
    
    @FXML
    public Label massage;
              
    @FXML
    private void signUp() throws IOException {
        App.setRoot("SignUp");
    }
    
    @FXML
    private void signIn() throws IOException {
        Customer.idCustomer = id.getText();
        if(id.getText().equals("2022") && password.getText().equals("2022")) { 
            App.setRoot("Admin");
        }else{
            boolean signIn = false;
            try{
                conn = DriverManager.getConnection(url, username, passwordSql);
                String sql = "SELECT * FROM CUSTOMER";
                Statement sts = conn.createStatement();
                ResultSet rs = sts.executeQuery(sql);
                while(rs.next()) {
                    if(rs.getString(1).equals(id.getText()) && rs.getString(3).equals(password.getText())) {
                        signIn = true;
                        App.setRoot("Customer");
                    }
                }
                if (signIn == false) {
                    massage.setText("ID or Password is Wrong.");
                }
            } catch(Exception ex) {
                massage.setText("ID or Password is Wrong.");
            }
        }
    }
}
