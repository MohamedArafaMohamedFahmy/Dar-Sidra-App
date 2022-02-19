package com.arafa.mohamed.darsidraapp.models;

import java.io.Serializable;

public class TeachersModel implements Serializable {
    private String codeTeacher, nameTeacher, phoneNumber, dateEnrollment, emailAdmin ;

    public TeachersModel() {
    }

    public TeachersModel(String codeTeacher, String nameTeacher, String phoneNumber, String dateEnrollment) {
        this.codeTeacher = codeTeacher;
        this.nameTeacher = nameTeacher;
        this.phoneNumber = phoneNumber;
        this.dateEnrollment = dateEnrollment;
    }

    public TeachersModel(String nameTeacher, String codeTeacher, String emailAdmin) {
        this.codeTeacher = codeTeacher;
        this.nameTeacher = nameTeacher;
        this.emailAdmin = emailAdmin;

    }

    public String getCodeTeacher() {
        return codeTeacher;
    }

    public void setCodeTeacher(String codeTeacher) {
        this.codeTeacher = codeTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateEnrollment() {
        return dateEnrollment;
    }

    public void setDateEnrollment(String dateEnrollment) {
        this.dateEnrollment = dateEnrollment;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }
}
