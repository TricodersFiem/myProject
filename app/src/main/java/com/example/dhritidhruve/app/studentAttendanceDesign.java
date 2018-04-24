package com.example.dhritidhruve.app;

import java.text.DecimalFormat;

public class studentAttendanceDesign {
    private String subjectCode,classesattended, totalclases, percent;

    public studentAttendanceDesign(String SubjectCode, String ClassesAttended, String TotalClasses)
    {
        subjectCode=SubjectCode;
        classesattended = ClassesAttended;
        totalclases = TotalClasses;
    }

    public String getSubjectCode(){ return subjectCode;}

    public String getClassesattended(){ return classesattended;}

    public String getTotalclasses(){ return totalclases;}

    public String getPercent(){
        double total,current,p;
        total = Double.parseDouble(totalclases);
        current = Double.parseDouble(classesattended);
        DecimalFormat f = new DecimalFormat("##.00");
        p = (current/total)*100;
        return f.format(p).toString();
    }
}
