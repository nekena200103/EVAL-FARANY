package controller;


import Service.CRUDGenservice;
import controller.DefaultController;
import entity.Artiste;
import entity.Typeacte;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/typeacte")
public class TypeActeController extends DefaultController<Typeacte>{
    public TypeActeController(){
        super(Typeacte.class,"");
    }
    

   


}
