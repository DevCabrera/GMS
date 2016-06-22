/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Utility.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MacBook
 */
public class DBStatements {

    // Driver name and database url
    final private String DRIVER = "com.mysql.jdbc.Driver";
    final private String DB_URL = "jdbc:mysql://localhost:3306/gms?zeroDateTimeBehavior=convertToNull";

    // Database credentials
    final String USER = Util.DB_USER;
    final String PASS = Util.DB_PASS;

    // Connection variables
    Connection conn;
    Statement stmt;
    String sql;

    public DBStatements() {
        stmt = null;
        conn = null;
    }

    private void startDBConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {

        }
    }// end startDBConnection

    private void closeDBConnection() {
        if (conn != null) {
            try {
                this.conn.close();
            } catch (SQLException ex) {
            }
        }// end if
    }// end closeDBConnection

    public Member searchById(Member member) throws SQLException {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "SELECT MemberID FROM Members_T WHERE MemberID = "
                    + Integer.toString(member.getMemberID()) + ";";
            ResultSet rs = stmt.executeQuery(sql);

            // searches database
            while (rs.next()) {
                member.setFirstName(rs.getString("FirstName"));
                member.setLastName(rs.getString("LastName"));
                member.setDob(rs.getString("DOB"));
                member.setStreet(rs.getString("Street"));
                member.setState(rs.getString("State"));
                member.setZipCode(rs.getInt("Zip"));
                member.setHomeNum(rs.getString("HomeNum"));
                member.setCellNum(rs.getString("CellNum"));
                member.setMembershipStartDate(rs.getString("MembershipDate"));
                member.setMembershipPlan(rs.getString("MembershipPlan"));
                member.setMembershipCost(rs.getDouble("MembershipCost"));

            }// end while
            
            /* HANDLES RESULT SET IF IT'S EMPTY
            if(!rs.next())
            */
            
            rs.close();
            stmt.close();
            closeDBConnection();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }// end try
        
        return member;
    }// end searchById

    public Member searchByName(Member member) {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "SELECT FirstName, LastName FROM Members_T WHERE FirstName = '"
                    + member.getFirstName() + "' AND LastName = '"
                    + member.getLastName() + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // searches database
            while(rs.next()) {
                member.setMemberID(rs.getInt("MemberID"));
                member.setDob(rs.getString("DOB"));
                member.setStreet(rs.getString("Street"));
                member.setState(rs.getString("State"));
                member.setZipCode(rs.getInt("Zip"));
                member.setHomeNum(rs.getString("HomeNum"));
                member.setCellNum(rs.getString("CellNum"));
                member.setMembershipStartDate(rs.getString("MembershipDate"));
                member.setMembershipPlan(rs.getString("MembershipPlan"));
                member.setMembershipCost(rs.getDouble("MembershipCost"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se2) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
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
                    + ", '" + m.getFirstName() + "', '" + m.getLastName() + "', '"
                    + m.getDob() + "', '" + m.getStreet() + "', '" + m.getCity() 
                    + "', '" + m.getState() + "', " + m.getZipCode() + ", '" 
                    + m.getHomeNum() + "', '" + m.getCellNum() + "', '" 
                    + m.getMembershipStartDate() + "', '" + m.getMembershipPlan() 
                    + "', " + m.getMembershipCost() + ");";
            
            stmt.executeUpdate(sql);
            closeDBConnection();
            return true;
            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se2) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            return false;
        }// end try

    }// end addMember
    
}// end class
