package com.example.demo.dao;


import com.example.demo.connex.Connexion;
import com.example.demo.models.Admin;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Formatter;
public class TokenAdminDao {

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

    public String insertTokenAdmin(Admin a){
        try {
            String token = createToken(a.getIdadmin());
            Date creation = Date.valueOf(LocalDate.now());
            Date expiration = Date.valueOf(LocalDate.now().plusDays(3));
            String role = "Admin";
            String requete = "insert into tokenAdmin(idadmin,token,datecreation,dateexpiration,role) values ('"+a.getIdadmin()+"','"+token+"','"+creation+"','"+expiration+"','"+role+"')";
            Connexion con = new Connexion(requete);
            System.out.println("la requete est" + requete);
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteTokenAdmin(String token,int idAdmin){
        Connexion conn = null;
        try {
            String requete = "delete from tokenAdmin where idadmin="+idAdmin+" and token='"+token+"' ";
            Connexion con = new Connexion(requete);
            System.out.println("la requete est" + requete);

        } catch (Exception e) {
            throw e;
        }
    }

    public int validTokenAdmin(String token) throws Exception
    {
        String requete = "select count(*) from tokenAdmin where token='"+token+"' and dateexpiration>current_date";
        Connexion con = new Connexion(requete,"");
        ResultSet res = con.getResultset();
        con.getResultset().next();
        int result = res.getInt(1);
        System.out.println("la requete est" + requete);
        return result;
    }

    public int isAdminToken(String token,String role) throws Exception
    {
        String requete = "select count(*) from tokenAdmin where token='"+token+"' and role='"+role+"' ";
        Connexion con = new Connexion(requete,"");
        ResultSet res = con.getResultset();
        con.getResultset().next();
        int result = res.getInt(1);
        System.out.println("la requete est" + requete);
        return result;
    }

   /* public TokenAdmin getTokenAdmin(String token) throws Exception{
        TokenAdmin token_result = null;
        String requete = "select*from tokenAdmin where token='"+token+"' ";
        Connexion con = new Connexion(requete);
        ResultSet res = con.getResultset();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        while(con.getResultset().next())
        {
            String idtokenadmin = res.getString(0);
            String idadmin = res.getString(1);
            String tokenf = res.getString(2);
            String datecreation = df.format(res.getDate(3));
            String dateexpiration = df.format(res.getDate(4));
            String role = df.format(res.getDate(5));
            token_result = new TokenAdmin(idtokenadmin,idadmin,tokenf,datecreation,dateexpiration,role);
        }
        return token_result;
    }*/

}
