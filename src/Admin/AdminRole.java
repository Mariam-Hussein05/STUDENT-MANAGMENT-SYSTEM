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
        s = new StudentDatabase("Students.txt");
    }

    public boolean addStudent(int id, String name, int age, String gender, String department, float gpa) {
        s.readFromFile();
        StudentUser student = new StudentUser(id, name, age, gender, department, gpa);
        s.insertRecordID(student);
        try {
            s.saveToFile();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateStudent(int id, String name, int age, String gender, String department, float gpa) {
        s.readFromFile();

        // Check if student exists
        if (!s.containsId(id)) {
            System.out.println("Student with ID " + id + " not found.");
            return false;
        }

        // Delete the old record
        s.deleteRecordId(id);

        // Create updated student record
        StudentUser updatedStudent = new StudentUser(id, name, age, gender, department, gpa);

        // Insert the updated record
        s.insertRecordID(updatedStudent);

        try {
            s.saveToFile();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving updated student: " + e.getMessage());
            return false;
        }
    }
}