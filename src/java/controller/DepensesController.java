package controller;


import Service.CRUDGenservice;
import controller.DefaultController;
import entity.Artiste;
import entity.Depenses;
import entity.Typeacte;
import entity.Typecharge;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/depenses")
public class DepensesController extends DefaultController<Depenses>{
    public DepensesController(){
        super(Depenses.class,"");
    }
    CRUDGenservice gen=new CRUDGenservice();
    @RequestMapping("/insertion")
     public String insertionmultiples(@RequestParam(value="month",required=true)List<String> mycheckboxvalues,@RequestParam Map<String,String> allparams ) throws Exception{
     int jour=Integer.parseInt(allparams.get("jour"));
     int annee=Integer.parseInt(allparams.get("year"));
     double montant=Double.valueOf(allparams.get("montant"));
     Typecharge typecharge=new Typecharge();
     typecharge.setIdtypecharge(allparams.get("idtypecharge"));
     Depenses dep=new Depenses();
     dep.setIdtypecharge(typecharge);
     dep.setMontant(montant);
     
     for (String value : mycheckboxvalues) {
         int mois=Integer.parseInt(value);
         String date=annee+"-"+(mois)+"-"+jour;
         
         dep.setJour(Date.valueOf(Util.Util.dateCorrecte(jour, mois, annee)));
         ent.save(dep);
     
     }
     return "redirect:/"+classModel.getSimpleName().toLowerCase()+"/0";
     }
    @RequestMapping("/multi")
     public String insertionmultiples(Model map ) throws Exception{
         Typecharge typeacte=new Typecharge();
         ArrayList<Typecharge> listtype=ent.find(typeacte,"Typecharge",Typecharge.class);
         map.addAttribute("listtype",listtype);
     return "depensesspec";
     }
    @RequestMapping("/csv")
     public String insertioncsv(Model map ) throws Exception{
     return "csvdep";
     }
     @RequestMapping(value="/upload",method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws ParseException, Exception {
        if (!file.isEmpty()) {
            try {
                // Ouvrir le flux d'entrée pour lire le fichier CSV
                InputStream inputStream = file.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                ArrayList<java.sql.Date> listdate=new ArrayList<java.sql.Date>();
                 ArrayList<String> listcode=new ArrayList<String>();
                 ArrayList<Double> listmontant=new ArrayList<Double>();
                String line;
                while ((line = reader.readLine()) != null) {
                    // Diviser la ligne en éléments séparés par ';'
                    String[] elements = line.split(";");
                    if (elements.length >= 3) {
                        // Premier élément dans un java.sql.Date
                        
                        listdate.add(parseSqlDate(elements[0]));
                        // Deuxième élément dans un tableau de String
                        
                        listcode.add(elements[1]);
                        // Troisième élément dans un tableau de double
                        listmontant.add(Double.valueOf(elements[2]));

                        // Faites ce que vous voulez avec ces éléments...
                    }
                }

                reader.close();
                for (int i = 0; i < listmontant.size(); i++) {
                    double get = listmontant.get(i);
                    Typecharge charge=new Typecharge();
                    charge.setWhere(" where code='"+listcode.get(i)+"'");
                    ArrayList<Typecharge> typechargecode=ent.find(charge,"Typecharge", Typecharge.class);
                    Depenses dep=new Depenses();
                    dep.setIdtypecharge(typechargecode.get(0));
                    dep.setJour(listdate.get(i));
                    dep.setMontant(get);
                    ent.save(dep);
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer les exceptions liées à l'ouverture ou à la lecture du fichier
            }
        } else {
            // Gérer le cas où aucun fichier n'est téléchargé
        }

        // ...

        return "redirect:/"+classModel.getSimpleName().toLowerCase()+"/0"; // Rediriger vers une autre page après le traitement du fichier.
    }

    // Méthode pour convertir la date au format java.sql.Date
    private java.sql.Date parseSqlDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = dateFormat.parse(dateString);
        return new java.sql.Date(date.getTime());
    }

    // Méthode pour convertir les éléments en double[]
    private double[] parseDoubles(String doubleString) {
        String[] doubleStrings = doubleString.split(",");
        double[] doubles = new double[doubleStrings.length];
        for (int i = 0; i < doubleStrings.length; i++) {
            doubles[i] = Double.parseDouble(doubleStrings[i]);
        }
        return doubles;
    }
    /*@Override
    @GetMapping("/update/{integer}")
    public String loadUpdate(@PathVariable Integer integer, Model map) {
        return null;
    }*/


}
