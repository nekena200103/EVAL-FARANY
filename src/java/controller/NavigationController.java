/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nekena
 */
@Controller
public class NavigationController {
    @RequestMapping(value="/acceuiladmin")
    public String  acceuiladmin (Model mod) throws Exception{
       // mod.addAttribute("listprojet",ent.find(new Projet(),"projet",Projet.class));
        //mod.addAttribute("listequipe",ent.find(new Equipe(),"equipe",Equipe.class));
       // mod.addAttribute("listetachespaginee",ent.find(new Tachesequipe(),"tachesequipe",Tachesequipe.class));
        //mod.addAttribute("listetachespaginee",ent.getEntities(2, 1," ",Tachesequipe.class));
       return "indexo";
    }
}
