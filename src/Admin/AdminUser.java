/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import database.Representation;

public class AdminUser implements Representation{
    private String username;
    private String password;
    public AdminUser(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
    //------------------------------Username & Password Validation---------------------------------------//
    private void setUsername(String username) {
        if(username==null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Username cannot be empty");
        }
        this.username=username;
    }
    //--------------------------------Password Validation--------------------------------------------------//
    private void setPassword(String password) {
        if(password==null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Password cannot be empty");
        }
        this.password=password;
    }
    @Override
    public String lineRepresentation() {
        return String.format("%s,%s",username,password);
    }
    @Override
    public String getSearchKey() {
        return username;
    }

    public String getpassword() {
        return password;
    }    

}

