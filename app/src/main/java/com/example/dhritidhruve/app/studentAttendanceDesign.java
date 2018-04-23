package com.example.dhritidhruve.app;

public class studentAttendanceDesign {
    private String subjectCode,classesattended, totalclases, percent;

    public studentAttendanceDesign(String SubjectCode, String ClassesAttended, String TotalClasses, String Percent)
    {
        subjectCode=SubjectCode;
        classesattended = ClassesAttended;
        totalclases = TotalClasses;
        percent = Percent;
    }

    public String getSubjectCode(){ return subjectCode;}

    public String getClassesattended(){ return classesattended;}

    public String getTotalclasses(){ return totalclases;}

    public String getPercent(){ return percent;}
}
