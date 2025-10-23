/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.util.ArrayList;

import database.Database;

public class UserDatabase extends Database<AdminUser> {

    public UserDatabase(String filename) {
        super(filename) ;
        this.records = new ArrayList<>();
    }

    @Override
    protected AdminUser createRecordFrom(String line) {
        if(line==null || line.trim().isEmpty()){
            return null;
        }
        String[] field = line.split(",");
        AdminUser adminUser= new AdminUser(
                field[0],
                field[1]
        );
        return adminUser;
    }

}
