package com.example.demo.models;

import com.example.demo.ObjectBdd.Mere;

public class ResultatEnchere extends Mere {

        private int idresultatenchere;
        private int idenchere;
        private int idutilisateur;
        private float prix_gagnant;
        private String dateheuregagnant;

    public ResultatEnchere() {
    }

    public ResultatEnchere(int idresultatenchere, int idenchere, int idutilisateur, float prix_gagnant, String dateheuregagnant) {
        this.idresultatenchere = idresultatenchere;
        this.idenchere = idenchere;
        this.idutilisateur = idutilisateur;
        this.prix_gagnant = prix_gagnant;
        this.dateheuregagnant = dateheuregagnant;
    }

    public int getIdenchere() {
             return idenchere;
        }

        public void setIdenchere(int idenchere) {
          this.idenchere = idenchere;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public float getPrix_gagnant() {
        return prix_gagnant;
    }

    public void setPrix_gagnant(float prix_gagnant) {
        this.prix_gagnant = prix_gagnant;
    }

    public String getDateheuregagnant() {
        return dateheuregagnant;
    }

    public void setDateheuregagnant(String dateheuregagnant) {
        this.dateheuregagnant = dateheuregagnant;
    }
}
