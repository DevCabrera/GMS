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
    
    public MainController (Member model, MainScreenUI view) {
        this.model = model;
        this.view = view;
    }
    
    
}
