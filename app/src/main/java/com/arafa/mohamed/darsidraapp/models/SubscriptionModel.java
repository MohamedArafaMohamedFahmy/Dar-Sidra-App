package com.arafa.mohamed.darsidraapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class SubscriptionModel implements Serializable {
    private String jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, codeStudent, nameStudent, classStudent;

    public SubscriptionModel() {
    }

    public SubscriptionModel(String jan, String feb, String mar, String apr, String may, String jun, String jul,
                             String aug, String sep, String oct, String nov, String dec, String codeStudent,
                             String nameStudent, String classStudent) {
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
        this.codeStudent = codeStudent;
        this.nameStudent = nameStudent;
        this.classStudent = classStudent;
    }

    public static ArrayList<MonthModel> getMonth(){

        ArrayList <MonthModel> listMonth = new ArrayList<>();
        MonthModel jan = new MonthModel();
        jan.nameMonth = "يناير";
        listMonth.add(jan);

        MonthModel feb = new MonthModel();
        feb.nameMonth = "فبراير";
        listMonth.add(feb);

        MonthModel mar = new MonthModel();
        mar.nameMonth = "مارس";
        listMonth.add(mar);

        MonthModel apr = new MonthModel();
        apr.nameMonth = "ابريل";
        listMonth.add(apr);

        MonthModel may = new MonthModel();
        may.nameMonth = "مايو";
        listMonth.add(may);

        MonthModel jun = new MonthModel();
        jun.nameMonth = "يونيو";
        listMonth.add(jun);

        MonthModel jul = new MonthModel();
        jul.nameMonth = "يوليو";
        listMonth.add(jul);

        MonthModel aug = new MonthModel();
        aug.nameMonth = "اغسطس";
        listMonth.add(aug);

        MonthModel sep = new MonthModel();
        sep.nameMonth = "سبتمبر";
        listMonth.add(sep);

        MonthModel oct = new MonthModel();
        oct.nameMonth = "اكتوبر";
        listMonth.add(oct);

        MonthModel nov = new MonthModel();
        nov.nameMonth = "نوفمبر";
        listMonth.add(nov);

        MonthModel dec = new MonthModel();
        dec.nameMonth = "ديسمبر";
        listMonth.add(dec);


        return listMonth;
    }

    public String getJan() {
        return jan;
    }

    public void setJan(String jan) {
        this.jan = jan;
    }

    public String getFeb() {
        return feb;
    }

    public void setFeb(String feb) {
        this.feb = feb;
    }

    public String getMar() {
        return mar;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getMay() {
        return may;
    }

    public void setMay(String may) {
        this.may = may;
    }

    public String getJun() {
        return jun;
    }

    public void setJun(String jun) {
        this.jun = jun;
    }

    public String getJul() {
        return jul;
    }

    public void setJul(String jul) {
        this.jul = jul;
    }

    public String getAug() {
        return aug;
    }

    public void setAug(String aug) {
        this.aug = aug;
    }

    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public String getOct() {
        return oct;
    }

    public void setOct(String oct) {
        this.oct = oct;
    }

    public String getNov() {
        return nov;
    }

    public void setNov(String nov) {
        this.nov = nov;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
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

    public String getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(String classStudent) {
        this.classStudent = classStudent;
    }
}
