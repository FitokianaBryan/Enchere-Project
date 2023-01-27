package com.example.demo.controllerAPI;


import com.example.demo.ObjectBdd.ManipDb;
import com.example.demo.connex.Connexion;
import com.example.demo.dao.HistoriqueEnchereDao;
import com.example.demo.dao.HistoriqueOffreDao;
import com.example.demo.dao.TokenUserDao;
import com.example.demo.models.ResultatEnchere;
import com.example.demo.models.TokenUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/api/historique")
public class HistoriqueRestController {
    Connexion con = new Connexion();
    Connection con1;
    {
        try {
            con1 = ManipDb.pgConnect("postgres","railway","qz8iclqVZnx87GipdO80");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("HistoriqueEncherisseur")
    public ResponseEntity<List<Object[]>> HistoriqueEncherisseur(@RequestHeader("token") String token)
    {
        TokenUserDao tud = new TokenUserDao();
        TokenUser tu;
         try {
             if(tud.validTokenUser(token)!=0)
             {
                 tu = tud.getTokenUser(token);
                 return new ResponseEntity<List<Object[]>>(new HistoriqueEnchereDao().HistoriqueEncherisseur(con,tu.getIdUtilisateur()), HttpStatus.OK);
             }
             else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
         }
         catch(Exception e)
         {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }

    @GetMapping("HistoriqueVente")
    public ResponseEntity<List<Object[]>> HistoriqueVente(@RequestHeader("token") String token)
    {
        TokenUserDao tud = new TokenUserDao();
        TokenUser tu;
        try {
            if(tud.validTokenUser(token)!=0)
            {
                tu = tud.getTokenUser(token);
                return new ResponseEntity<List<Object[]>>(new HistoriqueEnchereDao().HistoriqueVente(con,tu.getIdUtilisateur()), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("ResultatEnchere")
    public ResponseEntity<List<ResultatEnchere>> HistoriqueVente(@RequestParam("idEnchere") int idEnchere)
    {
        try {
                return new ResponseEntity<List<ResultatEnchere>>(new HistoriqueOffreDao().userGagnant(con1,idEnchere), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
