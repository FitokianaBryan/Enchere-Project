package com.example.demo.dao;



import com.example.demo.connex.Connexion;
import com.example.demo.models.HistoriqueOffre;
import com.example.demo.models.ResultatEnchere;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueOffreDao {

    UtilisateurDao ud = new UtilisateurDao();
    EnchereDao ed = new EnchereDao();

    public boolean siUserVendeur(Connexion con, int idenchere, int idutilisateurLogged) throws SQLException {
        int idutilisateurVendeur = ed.getObjetEchere(con,idenchere).getIdUtilisateur();
        if(idutilisateurVendeur==idutilisateurLogged)
        {
            return true;
        }
        return false;
    }

    public void Encherir(Connexion con,int idenchere,int idutilisateur,float montant)
    {
        try{

            String requete="INSERT INTO HistoriqueOffre(idEnchere,idUtilisateur,montant_offre) values ("+idenchere+","+idutilisateur+","+montant+")";
            con = new Connexion(requete);
            System.out.println(requete);
            con.getCommit();
        }
        catch(Exception e)
        {
            try {
                con.getRollBack();
                System.out.println("Transaction échouée : annulation");
            } catch (Exception exc) {}
        }
        finally {}
    }

    public void setCompteUser(int iduser,float montant,Connexion con) throws  Exception
    {
        float soldeUser = ud.getCompteUser(iduser,con)-montant;
        try {
            String requete = "update utilisateur set compte="+soldeUser+" where idutilisateur="+iduser+"";
            con = new Connexion(requete);
            con.getCommit();
        } catch (Exception exc) {
            try {
                con.getRollBack();
                System.out.println("Transaction échouée : annulation");
            } catch (SQLException ex) {}
        }
        finally {}
    }


    public List<HistoriqueOffre> ListeOffre(Connection con, int idenchere) throws Exception {
        List<HistoriqueOffre> liste = new ArrayList<>();
        HistoriqueOffre e = new HistoriqueOffre();
        Object[] result = e.findAll(con,"idenchere="+idenchere+"");
        for(Object o:result)
        {
            liste.add((HistoriqueOffre) o);
        }
        if(liste.size() != 0)
        {
            return liste;
        }
        else {
            return null;
        }
    }

    public List<ResultatEnchere> userGagnant(Connection con, int idenchere) throws Exception {
        List<ResultatEnchere> liste = new ArrayList<>();
        ResultatEnchere e = new ResultatEnchere();
        Object[] result = e.getFromView(con,"ResultatEnchere","where idenchere="+idenchere+"");
        for(Object o:result)
        {
            liste.add((ResultatEnchere) o);
        }
        if(liste.size() != 0)
        {
            return liste;
        }
        else {
            return null;
        }
    }

}
