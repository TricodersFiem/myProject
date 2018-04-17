package com.example.dhritidhruve.app;

public class Staff {
 private String name,collegeid,designation,department,email, password, qualification,photoid;

 Staff(String name, String collegeid, String email, String password, String department,String qualification, String designation){
     this.name=name;
     this.collegeid=collegeid;
     this.department=department;
     this.email=email;
     this.password=password;
     this.designation=designation;
     this.qualification=qualification;
     this.photoid="";
 }
 public String getName(){
     return name;
 }
    public String getCollegeid(){
        return collegeid;
    }
    public String getDepartment(){
        return department;
    }
    public String getPassword(){
        return password;
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
    public String getPhotoid(){
        return photoid;
    }

}
