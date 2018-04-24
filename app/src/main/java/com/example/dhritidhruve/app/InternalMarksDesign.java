package com.example.dhritidhruve.app;

public class InternalMarksDesign {
    private String name, roll, subjectCode,test1,test2;


    public InternalMarksDesign(String Name, String Roll,String SubjectCode, String Test1, String Test2)
    {
        name = Name;
        roll = Roll;
        test1 = Test1;
        test2 = Test2;
        subjectCode = SubjectCode;
    }

    public InternalMarksDesign(String Name, String Roll)
    {
        name = Name;
        roll = Roll;
        test1 = "";
        test2 = "";
    }
    public InternalMarksDesign(String Name, String Roll, String SubjectCode){
        name = Name;
        roll = Roll;
        test1 = "";
        test2 = "";
        subjectCode = SubjectCode;
    }


    public String getName(){ return name;}

    public String getRoll(){ return roll;}

    public void setTest1(String data){
        test1 = data;
    }

    public void setTest2(String data){
        test2 = data;
    }
    public void setTotal(String data){
        test2 = data;
    }
    public String getSubjectCode(){return subjectCode;}
    public String getTest1(){ return test1;}

    public String getTotal(){ return String.valueOf(Double.parseDouble(test1)+Double.parseDouble(test2));}

    public String getTest2(){ return test2;}
}
