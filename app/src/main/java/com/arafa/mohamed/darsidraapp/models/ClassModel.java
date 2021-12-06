package com.arafa.mohamed.darsidraapp.models;

import java.io.Serializable;

public class ClassModel implements Serializable {
    private String days, timing, nameClass,idClass,status;

    public ClassModel() {
    }

    public ClassModel(String days, String timing, String nameClass, String idClass, String status) {
        this.days = days;
        this.timing = timing;
        this.nameClass = nameClass;
        this.idClass = idClass;
        this.status = status;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
