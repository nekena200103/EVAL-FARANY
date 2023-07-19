package controller;


import Service.CRUDGenservice;
import controller.DefaultController;
import entity.Artiste;
import entity.Patient;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController extends DefaultController<Patient>{
    public PatientController(){
        super(Patient.class,"");
    }
    

    /*@Override
    @GetMapping("/update/{integer}")
    public String loadUpdate(@PathVariable Integer integer, Model map) {
        return null;
    }*/


}
