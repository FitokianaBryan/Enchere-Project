package com.example.demo.models;

import java.sql.Date;

public class TokenUser {
    public String idtokenuser;
    public int idUtilisateur;
    public String token;
    public Date datecreation;
    public Date dateexpiration;
    public String role;

    public TokenUser() {
    }

    public TokenUser(String idtokenuser, int idUtilisateur, String token, Date datecreation, Date dateexpiration, String role) {
        this.idtokenuser = idtokenuser;
        this.idUtilisateur = idUtilisateur;
        this.token = token;
        this.datecreation = datecreation;
        this.dateexpiration = dateexpiration;
        this.role = role;
    }

    public String getIdtokenuser() {
        return idtokenuser;
    }

    public void setIdtokenuser(String idtokenuser) {
        this.idtokenuser = idtokenuser;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Date dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
