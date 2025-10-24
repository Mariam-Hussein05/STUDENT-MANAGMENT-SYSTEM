/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import Student.StudentDatabase;
import Student.StudentUser;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nour
 */
public class AdminRole {
   private final StudentDatabase s;

    public AdminRole() {
        s=new StudentDatabase("Students.txt");

    }
    public boolean addStudent(int id, String name, int age, String gender, String department, float gpa)
    {s.readFromFile();
    StudentUser student= new StudentUser(id,name,age,gender,department,gpa);
    s.insertRecordID(student);
    try{
    s.saveToFile(); return true;}
    catch(IOException e)
    {System.out.println(e.getMessage()); return false;}
    }
    
}