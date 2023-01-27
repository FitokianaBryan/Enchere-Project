package com.example.demo.models;

import com.example.demo.ObjectBdd.Mere;

public class HistoriqueOffre extends Mere {
    private int idHistoriqueOffre;
    private int idEnchere;
    private int idUtilisateur;
    private float montant_offre;
    private String DateHeureOffre;


    public HistoriqueOffre() {
    }

    public HistoriqueOffre(int idEnchere, int idUtilisateur, float montant_offre, String dateHeureOffre) {
        this.idEnchere = idEnchere;
        this.idUtilisateur = idUtilisateur;
        this.montant_offre = montant_offre;
        DateHeureOffre = dateHeureOffre;
    }


    public int getIdHistoriqueOffre() {
        return idHistoriqueOffre;
    }

    public void setIdHistoriqueOffre(int idHistoriqueOffre) {
        this.idHistoriqueOffre = idHistoriqueOffre;
    }

    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public float getMontant_offre() {
        return montant_offre;
    }

    public void setMontant_offre(float montant_offre) {
        this.montant_offre = montant_offre;
    }

    public String getDateHeureOffre() {
        return DateHeureOffre;
    }

    public void setDateHeureOffre(String dateHeureOffre) {
        DateHeureOffre = dateHeureOffre;
    }
}
