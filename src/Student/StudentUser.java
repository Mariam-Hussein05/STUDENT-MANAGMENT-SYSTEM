/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

import database.Representation;
public final class StudentUser implements Representation {
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

        this.gender=gender;

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
        return id+","+name+","+age+","+gender+","+department+","+gpa;
    }
    @Override
    public String getSearchKey() {
        return String.valueOf(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public float getGpa() {
        return gpa;
    }
    
}

