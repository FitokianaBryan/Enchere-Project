package com.example.demo.models;

import com.example.demo.ObjectBdd.Mere;

import java.sql.Date;

public class PrelevementEnchere extends Mere {
    private int idEnchere;
    private float montant;
    private Date DatePrelevement;

    public PrelevementEnchere(int idEnchere, float montant, Date datePrelevement) {
        this.idEnchere = idEnchere;
        this.montant = montant;
        DatePrelevement = datePrelevement;
    }

    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDatePrelevement() {
        return DatePrelevement;
    }

    public void setDatePrelevement(Date datePrelevement) {
        DatePrelevement = datePrelevement;
    }
}
