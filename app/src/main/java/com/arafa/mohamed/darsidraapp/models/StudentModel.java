package com.arafa.mohamed.darsidraapp.models;

import java.io.Serializable;

public class StudentModel implements Serializable {

    private String codeStudent,nameStudent,urlStudent;

    public StudentModel() {
    }

    public StudentModel(String codeStudent, String nameStudent, String urlStudent) {
        this.codeStudent = codeStudent;
        this.nameStudent = nameStudent;
        this.urlStudent = urlStudent;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getUrlStudent() {
        return urlStudent;
    }

    public void setUrlStudent(String urlStudent) {
        this.urlStudent = urlStudent;
    }
}
