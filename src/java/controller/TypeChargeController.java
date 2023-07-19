package controller;


import Service.CRUDGenservice;
import controller.DefaultController;
import entity.Artiste;
import entity.Typecharge;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/typecharge")
public class TypeChargeController extends DefaultController<Typecharge>{
    public TypeChargeController(){
        super(Typecharge.class,"");
    }
    CRUDGenservice gen=new CRUDGenservice();
    

    /*@Override
    @GetMapping("/update/{integer}")
    public String loadUpdate(@PathVariable Integer integer, Model map) {
        return null;
    }*/


}
