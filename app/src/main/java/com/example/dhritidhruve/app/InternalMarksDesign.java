package com.example.dhritidhruve.app;

public class InternalMarksDesign {
    private String name, roll, subjectCode;
    private int test1, test2;

    public InternalMarksDesign(String Name, String Roll, int Test1, int Test2)
    {
        name = Name;
        roll = Roll;
        test1 = Test1;
        test2 = Test2;
    }

    public InternalMarksDesign(String Name, String Roll)
    {
        name = Name;
        roll = Roll;
        test1 = 0;
        test2 = 0;
    }
    public InternalMarksDesign(String Name, String Roll, String SubjectCode){
        name = Name;
        roll = Roll;
        test1 = 0;
        test2 = 0;
        subjectCode = SubjectCode;
    }

    public String getName(){ return name;}

    public String getRoll(){ return roll;}

    public void setTest1(int data){
        test1 = data;
    }

    public void setTest2(int data){
        test2 = data;
    }
    public void setTotal(int data){
        test2 = data;
    }

    public int getTest1(){ return test1;}

    public int getTotal(){ return test1+test2;}

    public int getTest2(){ return test2;}
}
