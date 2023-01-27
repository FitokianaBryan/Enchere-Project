package com.example.demo.connex;


import com.example.demo.dao.TokenUserDao;
import com.example.demo.models.TokenUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{

      /*  ProduitDao pd = new ProduitDao();
        Connection con = objectBdd.ManipDb.pgConnect("postgres","cloudfinal","hardi");
        List<ProduitCategorie> list = pd.getListProduct(con);
        for (ProduitCategorie p : list){
            System.out.println(p.getTypeCategorie());
        }*/
    /*    TokenUserDao tud = new TokenUserDao();
        UtilisateurDao ud = new UtilisateurDao();
        Utilisateur u = ud.login("hardi@gmail.com","hardi");
       // String  token = tud.insertTokenUser(u);
       // System.out.println(token);
        String token = tud.createToken(u.getId());
        System.out.println(token);*

     */
        TokenUserDao tud = new TokenUserDao();
        TokenUser tu;
        tu = tud.getTokenUser("6857e63413be4f45c309c3f4dab68aebff76b7d8");
        System.out.println(tu.getIdUtilisateur());
    }
}
