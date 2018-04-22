package com.example.dhritidhruve.app;

class SubjectDesign {
    String transferDepartment, transferYear, subjectName, subjectCode;
    SubjectDesign(String Department, String transferYear, String subjectName, String subjectCode){
        this.transferDepartment = Department;
        this.transferYear = transferYear;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }
    public String getDepartment(){return transferDepartment;}

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getYear() {
        return transferYear;
    }
}
