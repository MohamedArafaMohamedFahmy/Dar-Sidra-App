package com.arafa.mohamed.darsidraapp.models;

import java.io.Serializable;

public class AdminModel implements Serializable {
   private String idAdmin, nameAdmin, emailAdmin;

    public AdminModel() {
    }

    public AdminModel(String nameAdmin, String emailAdmin, String idAdmin ) {
        this.idAdmin = idAdmin;
        this.nameAdmin = nameAdmin;
        this.emailAdmin = emailAdmin;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }
}
