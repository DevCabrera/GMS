/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MacBook
 */
public class DBStatements extends DBConnector {

    private Statement stmt;
    String sql;

    public DBStatements() {
    }

    public Member searchById(Member member) {

        sql = "";
        startDBConnection();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            sql = "SELECT MemberID FROM Members_T WHERE MemberID = "
                    + Integer.toString(member.getMemberID()) + ";";

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
            ResultSet rs = stmt.executeQuery(sql);
            sql = "SELECT FirstName, LastName FROM Members_T WHERE FirstName = "
                    + member.getFirstName() + "AND LastName ="
                    + member.getLastName() + ";";

            // searches database
            while (rs.next()) {
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
                    + "'" + m.getFirstName() + "', '" + m.getLastName() + "', '"
                    + m.getDob() + "', '" + m.getStreet() + "', '" + m.getState()
                    + "', " + m.getZipCode() + ", '" + m.getHomeNum()
                    + "', '" + m.getCellNum() + "', '" + m.getMembershipStartDate()
                    + "', '" + m.getMembershipPlan() + "', " + m.getMembershipCost()
                    + ");";

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
        return true;
    }
}// end class
