package com.example.demo.dao;


import com.example.demo.connex.Connexion;
import com.example.demo.models.Enchere;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnchereDao {
    Enchere ench;

    public List<Enchere> getListEnchere(Connection con) throws Exception {
        List<Enchere> liste = new ArrayList<>();
        Enchere e = new Enchere();
        Object[] result = e.findAll(con,"");
        for(Object o:result)
        {
            liste.add((Enchere) o);
        }
        if(liste.size() != 0)
        {
            return liste;
        }
        else {
            return null;
        }
    }

    public List<Enchere> getListEnchereTerminer(Connection con) throws Exception {
        List<Enchere> liste = new ArrayList<>();
        Enchere e = new Enchere();
        Object[] result = e.findAll(con,"status=1 order by dateheureenchere desc");
        for(Object o:result)
        {
            liste.add((Enchere) o);
        }
        if(liste.size() != 0)
        {
            return liste;
        }
        else {
            return null;
        }
    }


    public List<Enchere> getListEnchereRecherche(PreparedStatement stm) throws Exception {
        List<Enchere> liste = new ArrayList<>();
        ResultSet res=stm.executeQuery();
        while(res.next()){
            Enchere e=new Enchere(res.getInt("idenchere"),res.getInt("idutilisateur"),
                    res.getString("description"),res.getFloat("prixminimumvente"),
                    res.getInt("durreenchere"),
                    res.getTimestamp("dateheureenchere"),res.getInt("status"));
            liste.add(e);
        }
        return liste;
    }

    public List<Object[]> getListeEnchereUser(Connexion con, int iduser)
    {
        List<Object[]> liste = new ArrayList<>();
        try {
            String requete = "select*from enchereUser where idutilisateur="+iduser+"";
            con = new Connexion(requete, "hh");
            ResultSet res = con.getResultset();
            while (con.getResultset().next()) {
               int idenchere = con.getResultset().getInt(1);
               int idutilisateur = con.getResultset().getInt(2);
               String description = con.getResultset().getString(3);
               float prixMinimumvente = con.getResultset().getFloat(4);
               int durreenchere = con.getResultset().getInt(5);
               Timestamp DateheureEnchere = con.getResultset().getTimestamp(6);
               int status = con.getResultset().getInt(7);
               String nomproduit = con.getResultset().getString(8);
               String descProduit = con.getResultset().getString(9);
               String typecategorie = con.getResultset().getString(10);
                liste.add(new Object[] {idenchere,idutilisateur,description,prixMinimumvente,durreenchere,DateheureEnchere,status,nomproduit,descProduit,typecategorie});
            }
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Enchere> getFicheEnchere(Connection con,int idenchere) throws Exception {
        List<Enchere> liste = new ArrayList<>();
        Enchere e = new Enchere();
        Object[] result = e.findAll(con,"idenchere="+idenchere+"");
        for(Object o:result)
        {
            liste.add((Enchere) o);
        }
        if(liste.size() != 0)
        {
            return liste;
        }
        else {
            return null;
        }
    }

    public void AjouterEnchere(Connexion con, int idutilisateur, String description, float prixminimumvente, int durreenchere) throws Exception
    {
        String requete="INSERT INTO Enchere (idUtilisateur, description, prixMinimumVente,durreEnchere) values ("+idutilisateur+",'"+description+"',"+prixminimumvente+","+durreenchere+")";
        con = new Connexion(requete);
        System.out.println(requete);
    }

    public float getPourcentage() throws SQLException {
        String requete = "select pourcentage from PourcentagePrelevee" ;
        Connexion con = new Connexion(requete,"hh");
        con.getResultset().next();
        float pourcentage = con.getResultset().getFloat(1);
        return pourcentage;
    }

    public float getPrixDeVente(int idenchere) throws SQLException {
        String requete = "select prixMinimumVente from enchere where idenchere="+idenchere+"";
        Connexion con = new Connexion(requete,"hh");
        con.getResultset().next();
        float prixVente = con.getResultset().getFloat(1);
        return prixVente;
    }

    public float MontantPrelevee(int idenchere) throws Exception
    {
        float pourcentage = this.getPourcentage();
        float montantPrelevee = (this.getPrixDeVente(idenchere) * pourcentage)/100;
        return montantPrelevee;
    }

    public int AjouterEncher(Connexion con,int idutilisateur,String description,float prixminimumvente,int durreenchere) throws Exception
    {
        String requete="INSERT INTO Enchere (idUtilisateur, description, prixMinimumVente,durreEnchere) values ("+idutilisateur+",'"+description+"',"+prixminimumvente+","+durreenchere+") returning idenchere";
        con = new Connexion(requete,"");
        con.getResultset().next();
        int result = con.getResultset().getInt(1);
        return result;
    }

    public Enchere getObjetEchere(Connexion con,int idenchere) throws SQLException {
        String requete="select prixminimumvente , dureenchere , idutilisateur from Enchere where idenchere="+idenchere+"";
        con = new Connexion(requete,"");
        con.getResultset().next();
        float prixminimumvente = con.getResultset().getFloat(1);
        int dureenchere = con.getResultset().getInt(2);
        int idutilisateur = con.getResultset().getInt(3);
        Enchere e = new Enchere(prixminimumvente,dureenchere,idutilisateur);
        return e;
    }

    public void setStatutEnchere(Connexion con,int idenchere) throws SQLException {
        String requete ="update enchere set status=1 where idenchere="+idenchere+"";
        con = new Connexion(requete);
    }

    public void EnchereTerminer(Connexion con,int idenchere) throws SQLException {
        String requete="SELECT (dateheureenchere + durreenchere * INTERVAL '1 minute') AS dateheurefin FROM enchere where idenchere="+idenchere+" ";
        con = new Connexion(requete,"");
        con.getResultset().next();
        Timestamp dateheurefin = con.getResultset().getTimestamp(1);
        Date currentDate = new Date();
        System.out.println(currentDate);
        if(dateheurefin.before(currentDate))
        {
           setStatutEnchere(con,idenchere);
           System.out.println("tafiditra V"+idenchere);
        }
    }


    public PreparedStatement generateStatement(Connexion conn, String startDate,String endDate,
                                               String category,String auctionStatus, String keywords) {
        PreparedStatement stmt =null;
        try {

            String query = "SELECT e.*, p.nomproduit FROM enchere e join produit_enchere pe using (idenchere) " +
                    "join produit p using (idproduit) WHERE 1=1";
            StringBuilder sb = new StringBuilder(query);


            int parameterIndex = 1;

            // check if the user entered a start date
            if (startDate != null) {
                sb.append(" AND dateheureenchere >= '"+java.sql.Date.valueOf(startDate)+"'");

            }

            // check if the user entered an end date
            if (endDate != null) {
                sb.append(" AND dateheureenchere <= '"+java.sql.Date.valueOf(startDate)+"'");
            }

            // check if the user entered a category
            if (category != null) {
                sb.append(" AND e.description  like '%"+category+"%'");
            }

            // check if the user entered an auction status
            if (auctionStatus != null) {

                sb.append(" AND status = '"+auctionStatus+"'");
            }
            // check if the user entered a name or description
            if (keywords != null) {
                sb.append(" AND (nomproduit LIKE '%"+keywords+"%' OR e.description LIKE '%"+keywords+"%')");
            }
            query=sb.toString();
            stmt = conn.prepareStatement(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }
}
