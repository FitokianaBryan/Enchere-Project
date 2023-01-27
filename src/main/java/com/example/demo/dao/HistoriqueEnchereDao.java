package com.example.demo.dao;



import com.example.demo.connex.Connexion;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueEnchereDao {

    public List<Object[]> HistoriqueEncherisseur(Connexion con, int idutilisateur)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete ="SELECT  e.description, e.prixMinimumVente, e.durreEnchere, e.DateHeureEnchere, ho.montant_offre, ho.DateHeureOffre, p.nomProduit, p.description, cp.typeCategorie , e.idutilisateur as iduser FROM Utilisateur u JOIN HistoriqueOffre ho using(idUtilisateur) JOIN Enchere e using(idEnchere) JOIN Produit_Enchere pe  using(idEnchere) JOIN Produit p using(idProduit) JOIN CategorieProduit cp using(idCategorieProduit) WHERE u.idUtilisateur = "+idutilisateur+" ORDER BY ho.DateHeureMise DESC";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                String description = con.getResultset().getString(1);
                float prixMinimumVente = con.getResultset().getFloat(2);
                int durreeEnchere = con.getResultset().getInt(3);
                Timestamp DateHeureEnchere = con.getResultset().getTimestamp(4);
                float montant_offre = con.getResultset().getFloat(5);
                Timestamp DateHeureMise = con.getResultset().getTimestamp(6);
                String nomProduit = con.getResultset().getString(7);
                String descriptionProduit = con.getResultset().getString(8);
                String typeCategorie = con.getResultset().getString(9);
                int iduser = con.getResultset().getInt(10);
                liste.add(new Object[] {description,prixMinimumVente,durreeEnchere,DateHeureEnchere,montant_offre,DateHeureMise,nomProduit,descriptionProduit,typeCategorie,iduser});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> HistoriqueVente(Connexion con, int idutilisateur)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete ="SELECT e.description, e.prixMinimumVente, e.durreEnchere, e.DateHeureEnchere, p.nomProduit, p.description, cp.typeCategorie, re.prix_gagnant,u2.idutilisateur,u2.nom,u2.prenom FROM Utilisateur u JOIN Enchere e using(idUtilisateur) JOIN Produit_Enchere pe using(idEnchere) JOIN Produit p using(idProduit) JOIN CategorieProduit cp using(idCategorieProduit) LEFT JOIN ResultatEnchere re using(idEnchere) LEFT JOIN Utilisateur u2 ON u2.idUtilisateur = re.idUtilisateur WHERE u.idUtilisateur = "+idutilisateur+" ORDER BY e.DateHeureEnchere DESC";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                String description = con.getResultset().getString(1);
                float prixMinimumVente = con.getResultset().getFloat(2);
                int durreeEnchere = con.getResultset().getInt(3);
                Timestamp DateHeureEnchere = con.getResultset().getTimestamp(4);
                String nomProduit = con.getResultset().getString(5);
                String descriptionProduit = con.getResultset().getString(6);
                String typeCategorie = con.getResultset().getString(7);
                float prix_gagnant = con.getResultset().getFloat(8);
                int iduser = con.getResultset().getInt(9);
                String nom = con.getResultset().getString(10);
                String prenom = con.getResultset().getString(11);
                liste.add(new Object[] {description,prixMinimumVente,durreeEnchere,DateHeureEnchere,nomProduit,descriptionProduit,typeCategorie,prix_gagnant,iduser,nom,prenom});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }
}
