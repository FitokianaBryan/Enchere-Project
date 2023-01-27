package com.example.demo.models;

import com.example.demo.ObjectBdd.Mere;

import java.sql.Date;


public class ProduitCategorie extends Mere {

    public Integer IdProduit;

    public String nomProduit;

    public String description;

    public float prix;

    public String numero_serie;

    public String DateSortie;

    public int Etat;

    public String Provenance;

    public String photo;

    public int idCategorieProduit;

    public String typeCategorie;


    public String getTypeCategorie() {
        return typeCategorie;
    }

    public void setTypeCategorie(String typeCategorie) {
        this.typeCategorie = typeCategorie;
    }

    public Integer getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(Integer idProduit) {
        IdProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }


    public String getDateSortie() {
        return DateSortie;
    }

    public void setDateSortie(String dateSortie) {
        DateSortie = dateSortie;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int etat) {
        Etat = etat;
    }

    public String getProvenance() {
        return Provenance;
    }

    public void setProvenance(String provenance) {
        Provenance = provenance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public ProduitCategorie() {
    }


    public void setIdCategorieProduit(int idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }
}
