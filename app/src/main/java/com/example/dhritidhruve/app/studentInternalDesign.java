package com.example.dhritidhruve.app;

public class studentInternalDesign {
    private String subjectCode,test1, test2, total;

    public studentInternalDesign(String SubjectCode, String Test1, String Test2, String Total)
    {
        subjectCode=SubjectCode;
        test1 = Test1;
        test2 = Test2;
        total = Total;
    }

    public String getSubjectCode(){ return subjectCode;}

    public String getTest1(){ return test1;}

    public String getTotal(){ return total;}

    public String getTest2(){ return test2;}
}
