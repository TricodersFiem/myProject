package com.example.dhritidhruve.app;

public class Student {
    private String name, email, password, collegeId, department,year;

    Student(String name, String collegeId, String email, String password, String year, String department){
        this.name = name;
        this.collegeId = collegeId;
        this.email = email;
        this.password = password;
        this.year = year;
        this.department = department;
    }

    public String getName(){ return name;}
    public String getEmail(){return email;}
    public String getCollegeId(){return collegeId;}
    public String getYear(){return year;}
    public String getDepartment(){return department;}
}
