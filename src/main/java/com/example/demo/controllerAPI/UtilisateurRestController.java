package com.example.demo.controllerAPI;



import com.example.demo.ObjectBdd.ManipDb;
import com.example.demo.connex.Connexion;
import com.example.demo.dao.TokenUserDao;
import com.example.demo.dao.UtilisateurDao;
import com.example.demo.models.Response;
import com.example.demo.models.TokenUser;
import com.example.demo.models.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@RestController
@CrossOrigin
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {

    UtilisateurDao ud = new UtilisateurDao();
    Connection con;
    {
        try {
            con = ManipDb.pgConnect("postgres","railway","qz8iclqVZnx87GipdO80");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    Connexion con1 = new Connexion();

    @PostMapping("/Inscription")
    public Response traitementInscription(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("email") String email, @RequestParam("mdp") String mdp)
    {
        Response response = new Response();
        try{
            ud.Inscription(con1,nom,prenom,email,mdp);
            response.setStatus("200");
            response.setMessage("Iscription reussie");
        }
        catch(Exception e)
        {
            response.setStatus("400");
            response.setMessage("Inscripiton impossible");
        }
        return response;
    }

    @PostMapping("/login")
    public Response login(@RequestParam("email")String email,@RequestParam("mdp")String mdp) throws Exception {
        String token = null;
        Response response = new Response();
        TokenUserDao t = new TokenUserDao();
        Utilisateur u = ud.login(email,mdp);
        if(u!=null)
        {
            token = t.insertTokenUser(u);
            response.setStatus("200");
            response.setMessage("login reussi");
            response.setDatas(token);
        }else{
            response.setStatus("401");
            response.setMessage("Mot de passe ou email incorrect");
            response.setDatas(null);
        }
        return response;
    }

    @PostMapping("/rechargementCompte")
    public Response rechargementCompte(@RequestParam("montant") float montant,@RequestHeader("token") String token)
    {
        Response response = new Response();
        TokenUserDao tud = new TokenUserDao();
        TokenUser tu;
        try {
                    if(tud.validTokenUser(token)!=0)
                    {
                    tu = tud.getTokenUser(token);
            ud.rechargerCompte(tu.getIdUtilisateur(),montant,con1);
            response.setMessage("transaction effectuee");
              }
             else
             {
                response.setMessage("expiration token");
             }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


}

