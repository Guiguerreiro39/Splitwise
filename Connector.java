/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Houdini
 */
public class Connector {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Master22Piece";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/dividas";
    
    public static Connection connectDB() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected");
        }catch(SQLException e){
            System.out.println(e);
        }   
        return conn;
    }
}
