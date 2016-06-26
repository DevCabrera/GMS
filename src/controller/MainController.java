/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import model.DBStatements;
import model.IMember;
import model.Member;
import view.MainScreenUI;

/**
 *
 * @author Garry Cabrera Controller class that handles the interaction between
 * the MainScreenUI view and the DBStatements/model.
 */
public class MainController implements IMember {

    private Member model;
    private MainScreenUI view;
    private DBStatements db;
    private boolean success = false;

    // Constructor used when adding a new member
    public MainController(MainScreenUI view, Member model) {
        this.view = view;
        this.model = model;
    }// end constructor

    // Constructor used when searching for a member
    public MainController(MainScreenUI view, String fName, String lName, String id) {

        model = new Member();
        this.view = view;

        if (!fName.equals("")) {
            model.setFirstName(fName);
        }

        if (!lName.equals("")) {
            model.setLastName(lName);
        }

        if (!id.equals("")) {
            model.setMemberID(Integer.parseInt(id));
        } else {
            model.setMemberID(0);
        }

    }// end constructor

    /**
     * Calls a search method from the model, and returns to the view if member
     * exists, else an error dialog will appear.
     */
    public void searchMember() throws SQLException {
        db = new DBStatements();

        // if id was entered, then search by id
        if (model.getMemberID() != 0) {
            model = db.searchById(model);
        } else {
            model = db.searchByName(model);
        }

        // if a member wasn't found then call view's message dialog
        if (model == null) {
            view.showMessage("Member Not Found");
        } else {
            view.updateView(model);
        }
    }// end searchMember

    /**
     * Passes the member model to the database handler class, and adds a member
     * information
     */
    @Override
    public void addMember() {
        db = new DBStatements();
        success = db.addMemberToDB(model);

        if (success = true) {
            view.showMessage("Member Added to Database");
        } else {
            view.showMessage("Unable to Add Member");
        }
    }// end addMember

    /**
     * Passes the member model to the database handler class, and updates a
     * member information
     */
    @Override
    public void updateMember(Member m) {
        db = new DBStatements();
        success = db.updateMemberInDB(m);

        if (success = true) {
            view.showMessage("Member Details Updated");
        } else {
            view.showMessage("Could Not Update Member Details");
        }
    }// end updateMember

}// end MainController class
