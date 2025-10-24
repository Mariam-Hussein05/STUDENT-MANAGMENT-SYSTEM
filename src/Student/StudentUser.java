/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

import database.Representation;
public class StudentUser implements Representation {
    private final int id;
    private String name;
    private int age;
    private String gender;
    private String department;
    private float gpa;

    public StudentUser(int id, String name, int age, String gender, String department, float gpa) {
        this.id = id;
        setName(name);
        setAge(age);
        setGender(gender);
        setDepartment(department);
        setGpa(gpa);
    }
    //---------------------------------Name Validation---------------------------------------------------//
    public void setName(String name) {
       /* if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
                throw new IllegalArgumentException("Name can only contain letters and spaces");
            }
        }*/
        this.name = name;
    }

    //--------------------------------Age Validation-----------------------------------------------------//
    public void setAge(int age) {
        if(age<15 || age>100){
            throw new IllegalArgumentException("Error:Invalid Age number");
        }
        this.age=age;
    }

    //--------------------------------Gender Validation--------------------------------------------------//
    public void setGender(String gender) {
        /*if(gender==null || gender.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Gender cannot be empty");
        }
        gender = gender.trim().toLowerCase();
        if(!gender.equals("female")&& !gender.equals("male")){
            throw new IllegalArgumentException("Error: Gender must be female or male");
        }
        String result = gender.substring(0, 1).toUpperCase() + gender.substring(1);
        this.gender=result;*/
        this.gender=gender

    }

    //-----------------------------Department Validation-------------------------------------------------//
    public void setDepartment(String department) {
        if(department==null || department.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Department cannot be empty"); 
        }
        this.department =department;
    }

    public void setGpa(float gpa) {
        if(gpa<0 || gpa>4){
            throw new IllegalArgumentException("Error: GPA must be between 0.0 and 4.0");
        }
        this.gpa=gpa;
    }

       @Override
    public String lineRepresentation() {
        return id+","+name+","+age+","+department+","+gender+","+gpa;
    }
    @Override
    public String getSearchKey() {
        return String.valueOf(name);
    }

    public int getId() {
        return id;
    }
}

