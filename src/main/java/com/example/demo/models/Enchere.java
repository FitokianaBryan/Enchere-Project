package com.example.demo.models;

import com.example.demo.ObjectBdd.Mere;

import java.sql.Date;
import java.sql.Timestamp;

public class Enchere extends Mere {
    private int idenchere;
    private int idUtilisateur;
    private  String description;
    private float prixMinimumVente;
    private int durreEnchere;
    private Timestamp dateheureenchere;
    private int status;

    public Enchere()
    {

    }



    public Enchere(int idUtilisateur, String description, float prixMinimumVente, int durreEnchere) {
        this.idUtilisateur = idUtilisateur;
        this.description = description;
        this.prixMinimumVente = prixMinimumVente;
        this.durreEnchere = durreEnchere;
    }

    public Enchere(int idenchere,int idUtilisateur, String description, float prixMinimumVente, int durreEnchere, Timestamp dateheureenchere ,int status) {
        this(idUtilisateur, description, prixMinimumVente, durreEnchere);
        this.status=status;
        this.idenchere=idenchere;
        this.dateheureenchere=dateheureenchere;
    }


    public Enchere(float prixminimumvente, int dureenchere,int idUtilisateur) {
        this.prixMinimumVente = prixminimumvente;
        this.durreEnchere = dureenchere;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrixMinimumVente() {
        return prixMinimumVente;
    }

    public void setPrixMinimumVente(float prixMinimumVente) {
        this.prixMinimumVente = prixMinimumVente;
    }

    public int getDurreEnchere() {
        return durreEnchere;
    }

    public void setDurreEnchere(int durreEnchere) {
        this.durreEnchere = durreEnchere;
    }

    public Timestamp getDateheureenchere() {
        return dateheureenchere;
    }

    public void setDateheureenchere(Timestamp dateheureenchere) {
        this.dateheureenchere = dateheureenchere;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(int idenchere) {
        this.idenchere = idenchere;
    }
}
