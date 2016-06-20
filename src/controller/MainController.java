/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Member;
import view.MainScreenUI;

/**
 *
 * @author MacBook
 */
public class MainController {

    private Member model;
    private MainScreenUI view;
    String firstName;
    String lastName;

    // Constructor used when adding a new member
    public MainController(Member model, MainScreenUI view) {
        this.model = model;
        this.view = view;
    }// end constructor

    // Constructor used when searching for a member
    public MainController(MainScreenUI view, String fName, String lName, int id) {
        this.view = view;
        this.firstName = fName;
        this.lastName = lName;
        model = null;
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setMemberID(id);
    }// end constructor

    public void searchMember() {

        // if id was entered, then search by id
        if (model.getMemberID() != 0) {

        } else {
            // Search by first and last name
        }

    }// end searchMember

    // updates the UI
    public void updateMemberView() {

    }//end updateMemberView class

}// end MainController class
