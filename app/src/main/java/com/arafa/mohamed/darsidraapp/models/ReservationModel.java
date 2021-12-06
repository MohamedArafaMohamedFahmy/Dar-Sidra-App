package com.arafa.mohamed.darsidraapp.models;

public class ReservationModel  {

    private String namePerson, mobileNumber, days, timing, nameClass, idReservation;

    public ReservationModel() {
    }

    public ReservationModel(String namePerson, String mobileNumber, String days, String timing, String nameClass, String idReservation) {
        this.namePerson = namePerson;
        this.mobileNumber = mobileNumber;
        this.days = days;
        this.timing = timing;
        this.nameClass = nameClass;
        this.idReservation = idReservation;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
}
