/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;
import database.Database;
import java.util.ArrayList;
public class StudentDatabase extends Database<StudentUser> {
    public StudentDatabase(String fileName) {
        super(fileName);
        this.records = new ArrayList<>();
    }

    public void insertRecordID(StudentUser record) {
        if (!containsId(record.getId())) {
            records.add(record);
            System.out.println("Added Successful!");
        } else {
            System.out.println("The id must be unique");
        }
    }
    public void deleteRecordId(int key) {
        for (StudentUser record : records) {
            if (record.getId()==key) {
                records.remove(record);
                System.out.println("Remove Successful!");
                return;
            }
        }
        System.out.println("ID not found");
    }
     public StudentUser getRecordId(int key) {
        for (StudentUser record : records) {
            if (record.getId()==key)
                return record;
        }
        return null;
    }

    public boolean containsId(int key) {
        return getRecordId(key) != null;
    }
    @Override
    protected StudentUser createRecordFrom(String line) {
        if(line==null || line.trim().isEmpty()){
            return null;
        }
        String[] field = line.split(",");
        StudentUser student= new StudentUser(
                Integer.parseInt(field[0]),
                field[1],
                Integer.parseInt(field[2]),
                field[3],
                field[4],
                Float.parseFloat(field[5])
        );
        return student;
    }

}
