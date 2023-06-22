package com.mycompany.project_database;

import java.io.IOException;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class SignUp {
    static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    static String username = "FINAL_PROJECT";
    static String passwordSql = "123456";
    static Connection conn ;
    
    @FXML
    public TextField id;
    
    @FXML
    public TextField name;
    
    @FXML
    public TextField password;
    
    @FXML
    public TextField mylocation;
    
    @FXML
    public TextField phone;
    
    @FXML
    public Label errorMassage;
    
    @FXML
    public Label createMassage;
    
    @FXML
    private void create() throws IOException {
        if (id.getText().length() == 9 && !name.getText().equals("") 
                && !password.getText().equals("") && phone.getText().length() == 10 && !mylocation.getText().equals("")){
            try {
                    conn = DriverManager.getConnection(url, username, passwordSql);
                    String sqll = "INSERT INTO CUSTOMER(ID,NAME,PASSWORD,PHONE_NUMBER,LOCATION) VALUES(?,?,?,?,?)";
                    Statement sts = conn.createStatement();
                    PreparedStatement psts = conn.prepareStatement(sqll);
                    psts.setString(1, id.getText());
                    psts.setString(2, name.getText());
                    psts.setString(3, password.getText());
                    psts.setString(4, phone.getText());
                    psts.setString(5, mylocation.getText());
                    psts.executeUpdate();
                    createMassage.setText("Account created.");

            } catch (Exception ex) {
                errorMassage.setText("This id exists.");
            }
        }else{
            errorMassage.setText("Wrong input.");
        }
    }
    @FXML
    private void back() throws IOException {
        App.setRoot("Login");
    }
}
