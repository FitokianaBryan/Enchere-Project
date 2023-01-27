package com.example.demo.controllerAPI;


import com.example.demo.ObjectBdd.ManipDb;
import com.example.demo.connex.Connexion;
import com.example.demo.dao.EnchereDao;
import com.example.demo.dao.HistoriqueOffreDao;
import com.example.demo.dao.TokenUserDao;
import com.example.demo.dao.UtilisateurDao;
import com.example.demo.models.Enchere;
import com.example.demo.models.HistoriqueOffre;
import com.example.demo.models.Response;
import com.example.demo.models.TokenUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/api/HistoriqueOffre")
public class HistoriqueOffreRestController {
    Connexion con = new Connexion();
    HistoriqueOffreDao hod = new HistoriqueOffreDao();
    Connection con1;
    {
        try {
            con1 = ManipDb.pgConnect("postgres","railway","qz8iclqVZnx87GipdO80");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    HistoriqueOffreDao ho = new HistoriqueOffreDao();
    @PostMapping("ReEncherir")
    public Response ReEncherir(@RequestParam("idEnchere") int idEnchere, @RequestHeader("token") String token, @RequestParam("montant") float montant_offre) throws Exception {
        Response response = new Response();
       TokenUserDao tud = new TokenUserDao();
       TokenUser t;
        if(tud.validTokenUser(token)!=0)
       {
           t = tud.getTokenUser(token);
         float montant_user = new UtilisateurDao().getCompteUser(t.getIdUtilisateur(),con);
         Enchere e = new EnchereDao().getObjetEchere(con,idEnchere);
         if(hod.siUserVendeur(con,idEnchere,t.getIdUtilisateur()))
         {
             response.setMessage("vous ne pouvez pas participer sur votre propre enchere");
         }
         else
         {
             if(montant_user<montant_offre)
             {
                 response.setMessage("Solde insuffisante");
             }
             else if(montant_offre<=e.getPrixMinimumVente())
             {
                 response.setMessage("solde inferieur au prix minimum vente");
             }
             else {
                 ho.Encherir(con,idEnchere,t.getIdUtilisateur(),montant_offre);
                 ho.setCompteUser(t.getIdUtilisateur(),montant_offre,con);
                 response.setMessage("votre offre a été bien prise en compte");
             }
         }
        }
        else{
            response.setMessage("veuillez dabord vous authentifier");
        }
        return response;
    }

    @GetMapping("listeOffre")
    public ResponseEntity<List<HistoriqueOffre>> listeOffre(@RequestParam("idEnchere") int idEnchere)
    {
        try {
            return new ResponseEntity<List<HistoriqueOffre>>(new HistoriqueOffreDao().ListeOffre(con1,idEnchere), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
