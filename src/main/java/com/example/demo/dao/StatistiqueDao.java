package com.example.demo.dao;

import com.example.demo.connex.Connexion;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StatistiqueDao {

    //chiffre d'affaire  mois par année
    public List<Object[]> chiffreAffaireAnneeMois(Connexion con)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete ="select idenchere , montant_total from chiffreAffaire";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                int idenchere = res.getInt(1);
                float montant = res.getFloat(2);
               liste.add(new Object[] {idenchere,montant});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }
    // nombre total des produits vendus par catégorie
    public List<Object[]> NombreTotalProduitVendu(Connexion con)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete ="select*from categorieProduitVendu";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                int idcategorie = con.getResultset().getInt(1);
                String typecategorie = con.getResultset().getString(2);
                int nombreTotal = con.getResultset().getInt(3);
                liste.add(new Object[] {idcategorie,typecategorie,nombreTotal});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    //Stat membres
    public List<Object[]> StatMembre(Connexion con)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete ="select count(idUtilisateur) as nombre , extract(year from DateInscription) as annee , extract(month from DateInscription) as mois from Utilisateur group by extract(year from DateInscription) , extract(month from DateInscription)";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                int nombre = con.getResultset().getInt(1);
                int annee = con.getResultset().getInt(2);
                int mois = con.getResultset().getInt(3);
                liste.add(new Object[] {nombre,annee,mois});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    //Stat enchere
    public List<Object[]> StatEnchere(Connexion con)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete ="select count(idEnchere) as nombre , extract(year from DateHeureEnchere) as annee , extract(month from DateHeureEnchere) as mois from enchere group by extract(year from DateHeureEnchere) , extract(month from DateHeureEnchere)";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                int nombre = con.getResultset().getInt(1);
                int annee = con.getResultset().getInt(2);
                int mois = con.getResultset().getInt(3);
                liste.add(new Object[] {nombre,annee,mois});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    //StatClient
    public List<Object[]> StatClient(Connexion con)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete ="select*from StatClient";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                String nom = con.getResultset().getString(1);
                String prenom = con.getResultset().getString(2);
                int idutilisateur = con.getResultset().getInt(3);
                int nombreProduitVendu = con.getResultset().getInt(4);
                liste.add(new Object[] {nom,prenom,idutilisateur,nombreProduitVendu});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

}
