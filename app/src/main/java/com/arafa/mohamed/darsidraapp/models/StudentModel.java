package com.arafa.mohamed.darsidraapp.models;

import java.io.Serializable;

public class StudentModel implements Serializable {

    private String nameStudent, enrollmentStudent, codeStudent, mobileFather,
            mobileMother, classStudent, dateSession,urlStudent;

    public StudentModel() {
    }

    public StudentModel(String nameStudent, String enrollmentStudent, String codeStudent, String mobileFather,
                        String mobileMother, String classStudent, String dateSession, String urlStudent) {
        this.nameStudent = nameStudent;
        this.enrollmentStudent = enrollmentStudent;
        this.codeStudent = codeStudent;
        this.mobileFather = mobileFather;
        this.mobileMother = mobileMother;
        this.classStudent = classStudent;
        this.dateSession = dateSession;
        this.urlStudent = urlStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getEnrollmentStudent() {
        return enrollmentStudent;
    }

    public void setEnrollmentStudent(String enrollmentStudent) {
        this.enrollmentStudent = enrollmentStudent;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public String getMobileFather() {
        return mobileFather;
    }

    public void setMobileFather(String mobileFather) {
        this.mobileFather = mobileFather;
    }

    public String getMobileMother() {
        return mobileMother;
    }

    public void setMobileMother(String mobileMother) {
        this.mobileMother = mobileMother;
    }

    public String getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(String classStudent) {
        this.classStudent = classStudent;
    }

    public String getDateSession() {
        return dateSession;
    }

    public void setDateSession(String dateSession) {
        this.dateSession = dateSession;
    }

    public String getUrlStudent() {
        return urlStudent;
    }

    public void setUrlStudent(String urlStudent) {
        this.urlStudent = urlStudent;
    }
}