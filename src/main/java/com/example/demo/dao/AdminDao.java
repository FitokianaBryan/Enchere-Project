package com.example.demo.dao;


import com.example.demo.connex.Connexion;
import com.example.demo.models.Admin;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminDao {

    UtilisateurDao ud = new UtilisateurDao();
    public Admin login(String user, String mdp) throws Exception {
        Admin admin;
        try {
            String requete = "select * from Admin where email='" + user + "' and mdp='" + mdp + "' ";
            Connexion c = new Connexion(requete, "");
            ResultSet res = c.getResultset();
            c.getResultset().next();
            int idadmin = c.getResultset().getInt(1);
            String email = c.getResultset().getString(2);
            String motdepasse = c.getResultset().getString(3);
            admin = new Admin(idadmin, email, motdepasse);
        } catch (Exception e) {
            return null;
        }
        return admin;
    }

    public void ValiderRechargementCompte(int idrechargementcompte,Connexion con) {
        try {
            String requete = "update rechargementcompte set estvalider=1 where idrechargementcompte="+idrechargementcompte+"";
            con = new Connexion(requete);
            con.getCommit();
        }
        catch (Exception exc)
        {
            try {
                con.getRollBack();
                System.out.println("Transaction échouée : annulation");
            } catch (Exception e) {

            }
        }
        finally
        {

        }
    }

    public List<Object[]> listeRechargementCompte(Connexion con) throws Exception {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete = "select rc.idRechargementCompte , u.idutilisateur , u.nom , u.prenom , rc.montant , rc.dateheurerechargement from utilisateur u join rechargementcompte rc using(idutilisateur) where rc.estvalider =0";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
                int idRechargementCompte = res.getInt(1);
                int idutilisateur = res.getInt(2);
                String nom = res.getString(3);
                String prenom = res.getString(4);
                float montant = res.getFloat(5);
                Timestamp timestamp = res.getTimestamp(6);
                String date = timestamp.toString();
                liste.add(new Object[] {idRechargementCompte,idutilisateur, nom, prenom, montant, date});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    public void setCompteUser(int idclient,float montant,Connexion con) throws Exception
    {
        float soldeUser = ud.getCompteUser(idclient,con)+montant;
        try {
            String requete = "update utilisateur set compte="+soldeUser+" where idutilisateur="+idclient+"";
            con = new Connexion(requete);
            con.getCommit();
        } catch (Exception exc) {

            try {
                con.getRollBack();
                System.out.println("Transaction échouée : annulation");
            } catch (SQLException ex) {

            }
        }
        finally {}
    }


}
