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
            sql = "SELECT MemberID FROM Members_T WHERE MemberID = "
                    + Integer.toString(id) + ";";

        } catch (SQLException ex) {
            //TODO: Handle this exception;
        }// end try

        closeDBConnection();
        return member;
    }// end searchById

    public Member searchByName(String fName, String lName) {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "SELECT FirstName, LastName FROM Members_T WHERE FirstName = "
                    + fName + "AND LastName =" + lName + ";";

        } catch (SQLException ex) {
            //TODO: Handle this exception;
        }// end try

        closeDBConnection();
        return member;
    }// end searchByName
    
    public boolean addMember(Member m) {
        
        sql = "";
        startDBConnection();
        
        
        try {
            stmt = conn.createStatement();
            sql = "INSERT INTO Members_T (MemberID, FirstName, LastName, DOB, "
                    + "Street, State, Zip, HomeNum, CellNum, MembershipDate, "
                    + "MembershipPlan, MembershipCost) VALUES (" + m.getMemberID()
                    + "'" + m.getFirstName() + "', '" + m.getLastName() + "', '" 
                    + m.getDob() + "', '" + m.getStreet() + "', '" + m.getState()
                     + "', " + m.getZipCode() + ", '" + m.getHomeNum()
                     + "', '" + m.getCellNum() + "', '" + m.getMembershipStartDate()
                     + "', '" + m.getMembershipPlan() + "', " + m.getMembershipCost()
                     + ");";

        } catch (SQLException ex) {
            //TODO: Handle this exception;
            return false;
        }// end try

        closeDBConnection();
        return true;
    }
}// end class
