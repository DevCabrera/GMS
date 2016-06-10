/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MacBook
 */
public class DBStatements extends DBConnector {

    private Statement stmt;
    Member member;
    String sql;
    
    public DBStatements(){
        member = null;
    }
    
    public Member searchById(int id) {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "SELECT MemberID FROM Members WHERE MemberID = "
                    + Integer.toString(id) + ";";

        } catch (SQLException ex) {
            //TODO: Handle this exception;
        }// end try

        return member;
    }// end searchById

    public Member searchByName(String fName, String lName) {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "SELECT FirstName, LastName FROM Members WHERE FirstName = "
                    + fName + "AND LastName =" + lName + ";";

        } catch (SQLException ex) {
            //TODO: Handle this exception;
        }// end try

        return member;
    }// end searchByName
    
}// end class
