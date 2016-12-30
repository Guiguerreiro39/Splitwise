/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Houdini
 */
public class Connector {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Master22Piece";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/dividas?useSSL=false";
    
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
    
    public static void close(Connection c) {
        try {
            if(c!=null && !c.isClosed()) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
