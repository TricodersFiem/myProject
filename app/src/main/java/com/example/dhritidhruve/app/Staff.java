package com.example.dhritidhruve.app;

public class Staff {
 public String name, collegeId,designation,department,email, password, qualification,photoid;

 Staff(String name, String collegeid, String email, String department,String qualification, String designation){
     this.name=name;
     this.collegeId =collegeid;
     this.department=department;
     this.email=email;
     this.designation=designation;
     this.qualification=qualification;
 }
 Staff(){}
 public String getName(){
     return name;
 }
    public String getCollegeId(){
        return collegeId;
    }
    public String getDepartment(){
        return department;
    }
    public String getQualification(){
        return qualification;
    }
    public String getEmail(){
        return email;
    }
    public String getDesignation(){
        return designation;
    }


}
