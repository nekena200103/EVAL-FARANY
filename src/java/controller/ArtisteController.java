package controller;


import Service.CRUDGenservice;
import controller.DefaultController;
import entity.Artiste;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artiste")
public class ArtisteController extends DefaultController<Artiste>{
    public ArtisteController(){
        super(Artiste.class,"");
    }
    CRUDGenservice gen=new CRUDGenservice();
    @Override
    @RequestMapping("/create")
    public String loadCreate(Model map) {
        
        try {
            map.addAttribute("create",gen.generateInsertionForm(classModel.newInstance()));
        } catch (InstantiationException ex) {
            Logger.getLogger(ArtisteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArtisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "create";
    }

    /*@Override
    @GetMapping("/update/{integer}")
    public String loadUpdate(@PathVariable Integer integer, Model map) {
        return null;
    }*/


}
