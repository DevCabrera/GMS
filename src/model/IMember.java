/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;

/**
 *
 * @author Garry Cabrera
 * Interface for a member model
 */
public interface IMember {
    
    public void addMember();
    public void searchMember()throws SQLException;
    public void updateMember(Member m);
}
