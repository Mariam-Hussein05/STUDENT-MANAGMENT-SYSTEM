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
        this.username=username;
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

    public String getPassword() {
        return password;
    }    

}


