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
 * @author Garry Cabrera
 * Class that handles database connections and queries
 */
public class DBStatements {

    // Driver name and database url
    final private String DRIVER = "com.mysql.jdbc.Driver";
    final private String DB_URL = "jdbc:mysql://localhost:3306/gms";

    // Database credentials
    final String USER = Util.DB_USER;
    final String PASS = Util.DB_PASS;

    // Connection and query variables
    Connection conn;
    Statement stmt;
    String sql;

    // constructor
    public DBStatements() {
        stmt = null;
        conn = null;
    }

    // starts a connection to the database
    private void startDBConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {

        }
    }// end startDBConnection

    // closes the connection to the database
    private void closeDBConnection() {
        if (conn != null) {
            try {
                this.conn.close();
            } catch (SQLException ex) {
            }
        }// end if
    }// end closeDBConnection

    // search for a member by using user id
    public Member searchById(Member m) throws SQLException {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM Members_T WHERE MemberID = "
                    + Integer.toString(m.getMemberID()) + ";";
            ResultSet rs = stmt.executeQuery(sql);

            // searches database
            while (rs.next()) {

                m.setFirstName(rs.getString("FirstName"));
                m.setLastName(rs.getString("LastName"));
                m.setDob(rs.getString("DOB"));
                m.setStreet(rs.getString("Street"));
                m.setCity(rs.getString("City"));
                m.setState(rs.getString("State"));
                m.setZipCode(rs.getString("Zip"));
                m.setHomeNum(rs.getString("HomeNum"));
                m.setCellNum(rs.getString("CellNum"));
                m.setMembershipStartDate(rs.getString("MembershipDate"));
                m.setMembershipPlan(rs.getString("MembershipPlan"));
                m.setMembershipCost(rs.getDouble("MembershipCost"));

            }// end while

            // If no m is found return null
            if (!rs.first()) {
                m = null;
            }

            // close all connections
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

        return m;
    }// end searchById

    // search for a member by first and last name
    public Member searchByName(Member m) {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM Members_T WHERE FirstName = '"
                    + m.getFirstName() + "' AND LastName = '"
                    + m.getLastName() + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // searches database
            while (rs.next()) {
                m.setMemberID(rs.getInt("MemberID"));
                m.setDob(rs.getString("DOB"));
                m.setStreet(rs.getString("Street"));
                m.setCity(rs.getString("City"));
                m.setState(rs.getString("State"));
                m.setZipCode(rs.getString("Zip"));
                m.setHomeNum(rs.getString("HomeNum"));
                m.setCellNum(rs.getString("CellNum"));
                m.setMembershipStartDate(rs.getString("MembershipDate"));
                m.setMembershipPlan(rs.getString("MembershipPlan"));
                m.setMembershipCost(rs.getDouble("MembershipCost"));
            }// end while      

            // If no m is found return null
            if (!rs.next()) {
                m = null;
            }

            // close all connections
            rs.close();
            stmt.close();
            closeDBConnection();

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

        return m;
    }// end searchByName

    // adds a member to the database
    public boolean addMemberToDB(Member m) {
        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "INSERT INTO Members_T (MemberID, FirstName, LastName, DOB, "
                    + "Street, City, State, Zip, HomeNum, CellNum, MembershipDate, "
                    + "MembershipPlan, MembershipCost) VALUES (" + m.getMemberID()
                    + ", '" + m.getFirstName() + "', '" + m.getLastName() + "', '"
                    + m.getDob() + "', '" + m.getStreet() + "', '" + m.getCity()
                    + "', '" + m.getState() + "', '" + m.getZipCode() + "', '"
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

    }// end addMemberToDB

    // updates a members details 
    public boolean updateMemberInDB(Member m) {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            sql = "UPDATE gms.Members_T SET " 
                    + "FirstName = '" + m.getFirstName() + "', LastName = '"
                    +  m.getLastName() + "', DOB = '"+ m.getDob() 
                    + "', Street = '" + m.getStreet() + "', City = '" + m.getCity()
                    + "', State = '" + m.getState() + "', Zip = '"
                    + m.getZipCode() + "', HomeNum = '" + m.getHomeNum() 
                    + "', CellNum = '" + m.getCellNum() + "', MembershipDate = '"
                    + m.getMembershipStartDate() + "', MembershipPlan = '" 
                    + m.getMembershipPlan() + "', MembershipCost = "
                    + m.getMembershipCost() 
                    + " WHERE MemberID = " + m.getMemberID() + ";";

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

    }// end updateMemberInDB

}// end class
