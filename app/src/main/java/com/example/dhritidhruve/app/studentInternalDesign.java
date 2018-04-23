package com.example.dhritidhruve.app;

public class studentInternalDesign {
    private String subjectCode;
    private int test1, test2, total;

    public studentInternalDesign(String SubjectCode, int Test1, int Test2, int Total)
    {
        subjectCode=SubjectCode;
        test1 = Test1;
        test2 = Test2;
        total = Total;
    }

    public String getSubjectCode(){ return subjectCode;}

    public int getTest1(){ return test1;}

    public int getTotal(){ return total;}

    public int getTest2(){ return test2;}
}
