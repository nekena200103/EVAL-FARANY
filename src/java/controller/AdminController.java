/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import Service.*;
import Util.Pdf;
import static Util.Pdf.addRows;
import static Util.Pdf.addTableHeader;
import annotation.Recherche;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import entity.Actes;
import entity.Admin;
import entity.Artiste;
import entity.Depenses;
import entity.Typeacte;
import entity.Typecharge;
import entity.Utilisateur;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
  import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;



/**
 *
 * @author USER
 */
@Controller
public class AdminController {
   
    
    
    DAService ent=new DAService();
    EntityService entityserv=new EntityService();
   
   //Acceuil
     @RequestMapping(value="/")
    public String  debut (Model mod) throws Exception{
       // mod.addAttribute("listprojet",ent.find(new Projet(),"projet",Projet.class));
        //mod.addAttribute("listequipe",ent.find(new Equipe(),"equipe",Equipe.class));
       // mod.addAttribute("listetachespaginee",ent.find(new Tachesequipe(),"tachesequipe",Tachesequipe.class));
        //mod.addAttribute("listetachespaginee",ent.getEntities(2, 1," ",Tachesequipe.class));
       return "logadmin";
    }
     @RequestMapping(value="/tableaudebord")
    public String  tableau (Model mod,@RequestParam Map<String,String> parameterMap) throws Exception{
       // mod.addAttribute("listprojet",ent.find(new Projet(),"projet",Projet.class));
        //mod.addAttribute("listequipe",ent.find(new Equipe(),"equipe",Equipe.class));
       // mod.addAttribute("listetachespaginee",ent.find(new Tachesequipe(),"tachesequipe",Tachesequipe.class));
        //mod.addAttribute("listetachespaginee",ent.getEntities(2, 1," ",Tachesequipe.class));
       
       return "redirect:/tableaudebord/"+parameterMap.get("year")+"/"+parameterMap.get("month");
    }
      @RequestMapping(value="/tableaudebord/{annee}/{month}")
    public String  tableaudebord (Model mod,@PathVariable int annee,@PathVariable int month) throws Exception{
        Typeacte actetype=new Typeacte();
        DecimalFormat format = new DecimalFormat("#,###.00");
        ArrayList<Typeacte> typeacte=ent.find(actetype,"typeacte", Typeacte.class);
          Typecharge depensestype=new Typecharge();
        ArrayList<Typecharge> typecharge=ent.find(depensestype,"typecharge", Typecharge.class);
        String[] tabmois={"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
        String recettes="";
        String depenses="";
        double totalrecettesrehetra=0;
        double totalbudgetrehetra=0;
        Actes actes=new Actes();
          for (int i = 0; i < typeacte.size(); i++) {
              actes.setWhere(" where extract(month from jour)="+month+" and extract(year from jour)="+annee+" and idtypeacte='"+typeacte.get(i).getIdtypeacte()+"'");
              double totalrecettes=0;
              double totalbudget=typeacte.get(i).getBudgetannuel()/12;
              totalbudgetrehetra=totalbudgetrehetra+totalbudget;
              double pourcentage=0.0;
              ArrayList<Actes> tabactes=ent.find(actes,"Actes", Actes.class);
              for (int j = 0; j < tabactes.size(); j++) {
                  Actes acteamizao=tabactes.get(j);
                  totalrecettes=totalrecettes+acteamizao.getMontant();     
              }
              totalrecettesrehetra=totalrecettesrehetra+totalrecettes;
              pourcentage=(totalrecettes/totalbudget)*100;
              recettes=recettes+"<tr>\n" +
"                          <th>"+typeacte.get(i).getNom()+"</th>\n" +
"                          <th> "+format.format(totalrecettes)+" Ariary</th>\n" +
"                          <th> "+format.format(totalbudget)+"  Ariary</th>\n" +
"                          <th> "+format.format(Math.round(pourcentage))+"% </th>\n" +
"                        </tr>";
              
        }
         recettes=recettes+"<tr>\n" +
"                          <th> </th>\n" +
"                          <th> "+format.format(totalrecettesrehetra)+" Ariary</th>\n" +
"                          <th> "+format.format(totalbudgetrehetra)+"  Ariary</th>\n" +
"                          <th> "+format.format(Math.round(((totalrecettesrehetra/totalbudgetrehetra)*100)))+"% </th>\n" +
"                        </tr>";
         Depenses depe=new Depenses();
         double totaldepensesrehetra=0;
        double totalbudgetrehetradep=0;
          for (int i = 0; i <typecharge.size(); i++) {
              
              depe.setWhere(" where extract(month from jour)="+month+" and extract(year from jour)="+annee+"and idtypecharge='"+typecharge.get(i).getIdtypecharge()+"'");
              double totaldepenses=0;
              double totalbudget=typecharge.get(i).getBudgetannuel()/12;
              totalbudgetrehetradep=totalbudgetrehetradep+totalbudget;
              double pourcentage=0.0;
              ArrayList<Depenses> tabdepenses=ent.find(depe,"depenses",Depenses.class);
              for (int j = 0; j < tabdepenses.size(); j++) {
                  Depenses depensesamizao=tabdepenses.get(j);
                  totaldepenses=totaldepenses+depensesamizao.getMontant();
                  
              }
              pourcentage=(totaldepenses/totalbudget)*100;
              totaldepensesrehetra=totaldepensesrehetra+totaldepenses;
             depenses=depenses+"<tr>\n" +
"                          <th>"+typecharge.get(i).getNom()+"</th>\n" +
"                          <th> "+format.format(totaldepenses)+" Ariary</th>\n" +
"                          <th> "+format.format(totalbudget)+"  Ariary</th>\n" +
"                          <th> "+Math.round(pourcentage)+"% </th>\n" +
"                        </tr>";
          }
          depenses=depenses+"<tr>\n" +
"                          <th></th>\n" +
"                          <th> "+format.format(totaldepensesrehetra)+" Ariary</th>\n" +
"                          <th> "+format.format(totalbudgetrehetradep)+"  Ariary</th>\n" +
"                          <th> "+format.format(Math.round(((totaldepensesrehetra/totalbudgetrehetradep)*100)))+"% </th>\n" +
"                        </tr>";
          String benefice="<tr>\n" +
"                          <th>Recettes</th>\n" +
"                          <th> "+format.format(totalrecettesrehetra)+" Ariary</th>\n" +
"                          <th> "+format.format(totalbudgetrehetra)+"  Ariary</th>\n" +
"                          <th> "+format.format(Math.round(((totalrecettesrehetra/totalbudgetrehetra)*100)))+"% </th>\n" +
"                        </tr>";
          benefice=benefice+"<tr>\n" +
"                          <th>Depenses</th>\n" +
"                          <th> "+format.format(totaldepensesrehetra)+" Ariary</th>\n" +
"                          <th> "+format.format(totalbudgetrehetradep)+"  Ariary</th>\n" +
"                          <th> "+format.format(Math.round(((totaldepensesrehetra/totalbudgetrehetradep)*100)))+"% </th>\n" +
"                        </tr>";
          benefice=benefice+"<tr>\n" +
"                          <th>Total</th>\n" +
"                          <th> "+format.format((totalrecettesrehetra-totaldepensesrehetra))+" Ariary</th>\n" +
"                          <th> "+format.format((totalbudgetrehetra-totalbudgetrehetradep))+"  Ariary</th>\n" +
"                          <th> "+format.format(Math.round(((totalrecettesrehetra-totaldepensesrehetra)/(totalbudgetrehetra-totalbudgetrehetradep))*100))+"% </th>\n" +
"                        </tr>";
          
       
       mod.addAttribute("recettes",recettes);
       mod.addAttribute("depenses",depenses);
       mod.addAttribute("benefice",benefice);
       mod.addAttribute("year",""+annee);
       mod.addAttribute("month",tabmois[month-1]);
        //mod.addAttribute("listequipe",ent.find(new Equipe(),"equipe",Equipe.class));
       // mod.addAttribute("listetachespaginee",ent.find(new Tachesequipe(),"tachesequipe",Tachesequipe.class));
        //mod.addAttribute("listetachespaginee",ent.getEntities(2, 1," ",Tachesequipe.class));
       return "tableaudebord";
    }
    
    //ConnexionAdministrateur
     @RequestMapping(value="/connexadmin",method = RequestMethod.GET)
    public String  connexadmin (Model mod,@RequestParam Map<String,String> allParams,HttpSession session) throws Exception{
        Admin admin=new Admin();
        admin.setWhere(" where adminname='"+allParams.get("adminname")+"' and password='"+allParams.get("password")+"'");
       ArrayList<Admin> adtab=ent.find(admin,"admin",Admin.class);
         if (adtab.size()>0) {
            session.setAttribute("actuel","admin");
             return "indexo";
         }else{
             return "logadmin";
         }
       // mod.addAttribute("listprojet",ent.find(new Projet(),"projet",Projet.class));
        //mod.addAttribute("listequipe",ent.find(new Equipe(),"equipe",Equipe.class));
       // mod.addAttribute("listetachespaginee",ent.find(new Tachesequipe(),"tachesequipe",Tachesequipe.class));
        //mod.addAttribute("listetachespaginee",ent.getEntities(2, 1," ",Tachesequipe.class));
       
    }
    
    
    
    //Manmboatra pdf andrana
    @RequestMapping("/pdf2")
    public ResponseEntity<ByteArrayResource> midownpdf(HttpServletResponse response) throws DocumentException{
        Document document = new Document(new Rectangle(707,1000));
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
        PdfWriter.getInstance(document,baos);
        
        Image img = Image.getInstance("C://Users//Nekena//Documents//NetBeansProjects//EssaiSpringMVC//cvako2.png");
        img.setAbsolutePosition(0, 0);
        document.open();
        
        
        
        
        PdfPTable table = new PdfPTable(3);
        String[] columnname={"essai","Age","Prenom"};
        Pdf.addTableHeader(table,columnname);
        Pdf.addRows(table,3);
         Pdf.addRows(table,3);
        
        Chapter chuk=new Chapter("Salama tompoko essai ana fanaovana pdf ity",1);
        
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        document.add(chuk);
        document.add(img);
        document.newPage();
        ;
        document.add(table);
        document.close();
         
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] pdfBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XHTML_XML);
        headers.setContentDispositionFormData("attachment", "monfichier.pdf");
        headers.setContentLength(pdfBytes.length);

        // Retourner la réponse HTTP
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        ResponseEntity<ByteArrayResource> essai=new ResponseEntity<ByteArrayResource>(resource,headers,HttpStatus.ACCEPTED);
        return essai;
    }
    /* @RequestMapping(value="/pdf")
    public String  pdf (Model mod) throws Exception{
        
        Pdf.creerpdf();


    mod.addAttribute("listprojet",ent.find(new Projet(),"projet",Projet.class));
    mod.addAttribute("listequipe",ent.find(new Equipe(),"equipe",Equipe.class));
    mod.addAttribute("listetachespaginee",ent.find(new Tachesequipe(),"tachesequipe",Tachesequipe.class));
            //mod.addAttribute("listetachespaginee",ent.getEntities(2, 1," ",Tachesequipe.class));
       return "indexo";
    }

    */
       /*
    @RequestMapping(value="/insertiontaches",method=RequestMethod.POST)
    public String  insertiontaches (Model mod,@RequestParam Map<String,String> allParams){
        
        
        //Objectinsert ray=new Objectinsert(allParams.get("titre"),allParams.get("description"), util.Util.FtoBase64(bytes), util.Util.toGod(allParams.get("un")),util.Util.toGod(allParams.get("deux")), Integer.parseInt(allParams.get("idtype")));
        try{
            
            //Object obj=util.Util.traductionParameterDynamique(allParams);
             System.out.println(ent2.clauseWhere(allParams));
            Tachesequipe ray=new Tachesequipe();
            ray.setDescription(allParams.get("description"));
            ray.setDatedebut(Date.valueOf(allParams.get("datedebut")));
            ray.setDatefin(Date.valueOf(allParams.get("datefin")));
            Projet prf=new Projet();
            prf.setIdprojet(Integer.parseInt(allParams.get("idprojet")));
            ray.setIdprojet(prf);
            Equipe ekip=new Equipe();
            ekip.setIdequipe(Integer.parseInt(allParams.get("idequipe")));
            ray.setIdequipe(ekip);
            ray.setPriorite(Integer.parseInt(allParams.get("priorite")));
            mod.addAttribute("listprojet",prj.find(new Projet(-1,null,null,null)));
            mod.addAttribute("listequipe",ekp.find(new Equipe(-1,null)));
            tacekp.save(ray);
            
            
        }catch(Exception e){
            mod.addAttribute("message",e.getMessage());
            e.printStackTrace();
        }
       return "redirect:/";
    }
    */
        public static Object setAttributes(Object obj, String recherche) {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Recherche) {
                    try {
                        field.setAccessible(true);
                        field.set(obj, recherche);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    Class<?> fieldType = field.getType();
                    if (fieldType.isPrimitive()) {
                        if (fieldType == int.class || fieldType == short.class
                                || fieldType == long.class || fieldType == byte.class
                                || fieldType == float.class || fieldType == double.class) {
                            try {
                                field.setAccessible(true);
                                field.set(obj, -1);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            field.setAccessible(true);
                            field.set(obj, null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    return obj;
}
    /*
    @RequestMapping(value="/listprojet",method=RequestMethod.GET)
    public String  listfilm (Model mod,@RequestParam Map<String,String> allParams){
        int nbPage=3;
        String sessionrecherche="tsisy";
       //int recherche=0;
       int numPage=1;
       if(allParams.get("numPage")!=""&&allParams.get("numPage")!=null){
           numPage=Integer.parseInt(allParams.get("numPage"));
       }
       if((allParams.get("recherche")!=""&&allParams.get("recherche")!=null)){
           try{
               numPage=1;
               sessionrecherche=allParams.get("recherche");
               mod.addAttribute("listeObj",prj.pagination(new Projet(-1,allParams.get("recherche"), datedebut, datefin));
               mod.addAttribute("nombrePage",fil.nombreDePage(fil.pagination(new Film(-1,allParams.get("recherche"),null), numPage, 0,0), nbPage));
               if(fil.pagination(new Film(-1,allParams.get("recherche"),null), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               e.printStackTrace();
           }
     
       }else if((allParams.get("sessionrecherche")!=""&&allParams.get("sessionrecherche")!=null&&allParams.get("sessionrecherche").compareToIgnoreCase("tsisy")!=0)){
           try{
               sessionrecherche=allParams.get("sessionrecherche");
               mod.addAttribute("listeObj",fil.pagination(new Film(-1,allParams.get("sessionrecherche"),null), numPage, nbPage,0));
               mod.addAttribute("nombrePage",fil.nombreDePage(fil.pagination(new Film(-1,allParams.get("sessionrecherche"),null), numPage, 0,0), nbPage));
               if(fil.pagination(new Film(-1,allParams.get("sessionrecherche"),null), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               
           }
    }else {
           try{
               mod.addAttribute("listeObj",fil.pagination(new Film(-1,null,null), numPage, nbPage,1));
               mod.addAttribute("nombrePage",fil.nombreDePage(fil.pagination(new Film(-1,null,null), numPage, 0,1), nbPage));
               if(fil.pagination(new Film(-1,null,null), numPage+1, nbPage,1).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){
               e.printStackTrace();
           }
        }
      
       mod.addAttribute("sessionrecherche",sessionrecherche);
       mod.addAttribute("nbPage",nbPage);
       mod.addAttribute("numPage",numPage);
       
       return "listefilm";
    }
    */
}

