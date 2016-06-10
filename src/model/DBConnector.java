/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Utility.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author MacBook
 */
public class DBConnector {
    
    // Driver name and database url
    final private String DRIVER = "com.mysql.jdbc.Driver";
    final private String DB_URL = "jdbc:mysql://localhost:3306/gms";
    
    // Database credentials
    final String USER = Util.DB_USER;
    final String PASS = Util.DB_PASS;
    
    protected Connection conn;

    protected void startDBConnection(){
        conn = null;
        
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (Exception e ){
            
        }
    }// end startDBConnection
    
    protected void closeDBConnection(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// end if
    }// end closeDBConnection
    
}// end class
