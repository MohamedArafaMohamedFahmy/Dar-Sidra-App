package com.arafa.mohamed.darsidraapp.models;

public class SalaryTeacherModel {
    private String daysIncrease, daysAbsence, totalSalary;

    public SalaryTeacherModel() {
    }

    public SalaryTeacherModel(String daysIncrease, String daysAbsence, String totalSalary) {
        this.daysIncrease = daysIncrease;
        this.daysAbsence = daysAbsence;
        this.totalSalary = totalSalary;
    }

    public String getDaysIncrease() {
        return daysIncrease;
    }

    public void setDaysIncrease(String daysIncrease) {
        this.daysIncrease = daysIncrease;
    }

    public String getDaysAbsence() {
        return daysAbsence;
    }

    public void setDaysAbsence(String daysAbsence) {
        this.daysAbsence = daysAbsence;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }
}
