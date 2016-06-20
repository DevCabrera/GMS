/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DBStatements;
import model.Member;
import view.MainScreenUI;

/**
 *
 * @author MacBook
 */
public class MainController {
    
    private Member model;
    private MainScreenUI view;
    private DBStatements db;

    // Constructor used when adding a new member
    public MainController(Member model, MainScreenUI view) {
        this.model = model;
        this.view = view;
    }// end constructor

    // Constructor used when searching for a member
    public MainController(MainScreenUI view, String fName, String lName, String id) {
        
        model = new Member();
        this.view = view;
        
        if (!fName.equals("")) 
            model.setFirstName(fName);
        
        if (!lName.equals("")) 
            model.setLastName(lName);
        
        if (!id.equals("")) 
            model.setMemberID(Integer.parseInt(id));
        else 
            model.setMemberID(0);
        
    }// end constructor

    public void searchMember() {
        db = new DBStatements();

        // if id was entered, then search by id
        if (model.getMemberID() != 0) {
            db.searchById(model);
        } else {
            db.searchByName(model);
        }
        
    }// end searchMember

    public void addMember() {
        db = new DBStatements();
        boolean isModelAdded = db.addMember(model);
        
        if (isModelAdded = true) {
            System.out.println("Member added");
        } else {
            System.out.println("Member not added");
        }
    }

    // updates the UI
    public void updateMemberView() {
        
    }//end updateMemberView class

}// end MainController class
