package com.example.dhritidhruve.app;

import java.text.DecimalFormat;

public class studentAttendanceDesign {
    private String docId,subjectCode,classesattended, totalclases, percent,name,roll;
    public studentAttendanceDesign(String docId,String name, String roll,String SubjectCode, String ClassesAttended, String TotalClasses){
        this.name = name;
        this.roll = roll;
        classesattended = ClassesAttended;
        totalclases = TotalClasses;
        subjectCode=SubjectCode;
        this.docId = docId;
    }

    public studentAttendanceDesign(String SubjectCode, String ClassesAttended, String TotalClasses)
    {
        subjectCode=SubjectCode;
        classesattended = ClassesAttended;
        totalclases = TotalClasses;
    }
    public String getDocId(){return docId;}
    public String getName(){return name;}

    public String getRoll(){return roll;}

    public String getSubjectCode(){ return subjectCode;}

    public String getClassesattended(){ return classesattended;}

    public void setClassesattended(int value){
        int current = Integer.parseInt(classesattended);
        if(current>0) {
            current = current + value;
            classesattended = String.valueOf(current);
        }

    }

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
