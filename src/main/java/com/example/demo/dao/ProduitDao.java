package com.example.demo.dao;


import com.example.demo.connex.Connexion;
import com.example.demo.models.ProduitCategorie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDao {


    public List<ProduitCategorie> getListProduct(Connection con) throws Exception {
        List<ProduitCategorie> liste = new ArrayList<>();
        ProduitCategorie p = new ProduitCategorie();
        Object[] result = p.getFromView(con,"ProduitCategorie","");
        for(Object o:result)
        {
            liste.add((ProduitCategorie) o);
        }
        if(liste.size() != 0)
        {
             return liste;
        }
        else {
            return null;
        }
    }

    public void AjouterProduit(Connexion con, String nomproduit, String description, float prix, String numero_serie, Date datesortie,String etat,String provenance,int categorieproduit)
    {
       String requete ="insert into Produit(nomproduit,description,prix,numero_serie,datesortie,etat,provenance,idcategorieproduit) values ('"+nomproduit+"','"+description+"',"+prix+",'"+numero_serie+"','"+datesortie+"','"+etat+"','"+provenance+"',"+categorieproduit+")";
       con = new Connexion(requete);
    }
    public void AjouterPhotoProduit(Connexion con,int idproduit,String photo)
    {
        String requete = "insert into Photo_produit(idproduit,photo) values ("+idproduit+",'"+photo+"')";
        con = new Connexion(requete);
        System.out.println(requete);
    }

    public int AjouterProduitEnchere(Connexion con,int idenchere,int idproduit) throws SQLException {
        String requete ="insert into Produit_Enchere(idEnchere,idProduit) values ("+idenchere+","+idproduit+") returning idProduit";
        con = new Connexion(requete,"");
        con.getResultset().next();
        int result = con.getResultset().getInt(1);
        return result;
    }

}
