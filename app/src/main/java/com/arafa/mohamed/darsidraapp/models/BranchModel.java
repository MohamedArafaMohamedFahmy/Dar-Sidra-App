package com.arafa.mohamed.darsidraapp.models;

import java.io.Serializable;

public class BranchModel implements Serializable {

    private  String nameBranch, idBranch;

    public BranchModel() {
    }

    public BranchModel(String nameBranch, String idBranch) {
        this.nameBranch = nameBranch;
        this.idBranch = idBranch;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public String getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(String idBranch) {
        this.idBranch = idBranch;
    }
}
