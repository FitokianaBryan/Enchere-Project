package com.example.demo.controllerVIEW;

import com.example.demo.connex.Connexion;
import com.example.demo.dao.StatistiqueDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StatistiqueController {

    Connexion con = new Connexion();
    StatistiqueDao sd = new StatistiqueDao();
    @GetMapping("/Statistique")
    public String Statistique(HttpServletRequest request)
    {
        //chiffre d'affaire par annee , mois
        List<Object[]> graphe = sd.chiffreAffaireAnneeMois(con);

        //nombre total des produits vendus par catégorie
        List<Object[]> NombreTotalProduitVendu = sd.NombreTotalProduitVendu(con);

        //Stat membres
        List<Object[]> StatMembre = sd.StatMembre(con);

        //Stat Enchere
        List<Object[]> StatEnchere = sd.StatEnchere(con);

        List<Object[]> StatGraphe = sd.StatGraphe(con);

        request.setAttribute("graphe",graphe);
        request.setAttribute("NombreTotalProduitVendu",NombreTotalProduitVendu);
        request.setAttribute("StatMembre",StatMembre);
        request.setAttribute("StatEnchere",StatEnchere);
        request.setAttribute("StatGraphe",StatGraphe);
        request.setAttribute("annee",2023);
        return "Statistique";
    }

    @GetMapping("/graphe")
    public String RechercheStatistique(HttpServletRequest request)
    {
        List<Object[]> graphe = sd.chiffreAffaireAnneeMois(con);
        request.setAttribute("graphe",graphe);
        return "Statistique";
    }
}
