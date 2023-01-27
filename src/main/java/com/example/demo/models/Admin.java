package com.example.demo.models;


public class Admin {

    public int idadmin;
    public String email;
    public String mdp;

    public Admin() {
    }

    public Admin(int idadmin, String email, String mdp) {
        this.idadmin = idadmin;
        this.email = email;
        this.mdp = mdp;
    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
