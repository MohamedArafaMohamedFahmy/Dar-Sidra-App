package com.arafa.mohamed.darsidraapp.models;

public class UserInformationModel {
    private String userName,emailAddress;

    public UserInformationModel()
    {

    }

    public UserInformationModel(String userName, String emailAddress){
        this.userName=userName;
        this.emailAddress=emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
