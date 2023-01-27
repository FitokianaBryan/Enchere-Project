package com.example.demo.models;

import com.example.demo.ObjectBdd.Mere;

import java.sql.Date;


public class Utilisateur extends Mere {

    private Integer Id;
    private String nom;

    private String prenom;

    private String email;

    public String mdp;

    public String DateInscription;

    public float compte;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public float getCompte() {
        return compte;
    }

    public String getDateInscription() {
        return DateInscription;
    }

    public void setDateInscription(String dateInscription) {
        DateInscription = dateInscription;
    }

    public Utilisateur() {
    }

    public void setCompte(float compte) {
        this.compte = compte;
    }

    public Utilisateur(int id,String nom, String prenom, String email, String mdp) {
        this.Id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }
}
