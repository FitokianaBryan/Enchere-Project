package com.example.demo.dao;


import com.example.demo.connex.Connexion;
import com.example.demo.models.TokenUser;
import com.example.demo.models.Utilisateur;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Formatter;
public class TokenUserDao {

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public String encrypter(String st) throws Exception {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(st.getBytes("UTF-8"));
        return byteToHex(crypt.digest());
    }

    public String createToken(int id) throws Exception
    {
        String token = null;
        Date timest = Date.valueOf(LocalDate.now());
        String timestamp = String.valueOf(timest);
        String cle = "this_is_secret";
        token = encrypter(timestamp+cle+id);
        return token;
    }

    public String insertTokenUser(Utilisateur u){
        try {
            String token = createToken(u.getId());
            Date creation = Date.valueOf(LocalDate.now());
            Date expiration = Date.valueOf(LocalDate.now().plusDays(3));
            String role = "utilisateur";
            String requete = "insert into tokenuser(idutilisateur,token,datecreation,dateexpiration,role) values ('"+u.getId()+"','"+token+"','"+creation+"','"+expiration+"','"+role+"')";
            Connexion con = new Connexion(requete);
            System.out.println("la requete est" + requete);
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteTokenUser(String token,int idutilisateur){
        Connexion conn = null;
        try {
            String requete = "delete from tokenuser where idutilisateur="+idutilisateur+" and token='"+token+"' ";
            Connexion con = new Connexion(requete);
            System.out.println("la requete est" + requete);

        } catch (Exception e) {
            throw e;
        }
    }

    public int validTokenUser(String token) throws Exception
    {
        String requete = "select count(*) from tokenUser where token='"+token+"' and dateexpiration>current_date";
        Connexion con = new Connexion(requete,"");
        ResultSet res = con.getResultset();
        con.getResultset().next();
        int result = res.getInt(1);
        System.out.println("la requete est" + requete);
        return result;
    }

    public TokenUser getTokenUser(String token)
    {
             TokenUser tu = null;
             try{
               String requete = "select * from tokenuser where token='"+token+"'";
               Connexion con = new Connexion(requete,"");
               con.getResultset().next();
               String idtokenuser = con.getResultset().getString(1);
               int idutilisateur = con.getResultset().getInt(2);
               String t = con.getResultset().getString(3);
               Date datecreation = con.getResultset().getDate(4);
               Date dateexpiration = con.getResultset().getDate(5);
               String role = con.getResultset().getString(6);
               tu = new TokenUser(idtokenuser,idutilisateur,t,datecreation,dateexpiration,role);
               return tu;
             }
             catch(Exception e)
             {
                return null;
             }

    }

}
