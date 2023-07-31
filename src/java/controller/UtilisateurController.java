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
import entity.Patient;
import entity.Typeacte;
import entity.Utilisateur;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
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
public class UtilisateurController {
   
    
    
    DAService ent=new DAService();
    EntityService entityserv=new EntityService();
   
   //Acceuil
    
    @RequestMapping(value="/user")
    public String  debutuilisateur (Model mod) throws Exception{
       
       return "logutilisateur";
    }
    @RequestMapping(value="/facturage")
    public String  facturage (Model mod) throws Exception{
       Patient pat=new Patient();
       ArrayList<Patient> patarray=ent.find(pat,pat.getClass().getSimpleName(),Patient.class);
       mod.addAttribute("listepatient",patarray);
       return "facturage";
    }
    @RequestMapping(value="/facturage2/{id}")
    public String  facturage2 (Model mod,@PathVariable String id) throws Exception{
       Actes pat=new Actes();
       pat.setWhere(" where idpatient='"+id+"'");
       ArrayList<Actes> patarray=ent.find(pat,pat.getClass().getSimpleName(),Actes.class);
       mod.addAttribute("listeactes",patarray);
       return "factactes";
    }
    @RequestMapping(value="/pdfactes")
    public ResponseEntity<ByteArrayResource>  mamoronapdf (Model mod,@RequestParam(value="listeacte",required=false)List<String> mycheckboxvalues,HttpServletResponse response)throws Exception{
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        if (mycheckboxvalues!=null && !mycheckboxvalues.isEmpty()) {
            
            try {
                ArrayList<Actes> actesarray=new ArrayList<Actes>();
            for (String value : mycheckboxvalues) {
                Actes pat=new Actes();
                pat.setWhere(" where idactes='"+value+"'");
                ArrayList<Actes> patarray=ent.find(pat,pat.getClass().getSimpleName(),Actes.class);
                actesarray.add(patarray.get(0));
            }
        Document document = new Document(new Rectangle(707,1000));
        
        
        PdfWriter.getInstance(document,baos);
        
        Image img = Image.getInstance("C://Users//Nekena//Documents//NetBeansProjects//EssaiSpringMVC//fondfacture.png");
        img.setAbsolutePosition(0, 0);
        PdfWriter.getInstance(document,baos);

            // Ouverture du document
            document.open();
            document.add(img);
            // Création du tableau
            PdfPTable table = new PdfPTable(5); // 5 colonnes
            table.setWidthPercentage(60); // Largeur du tableau en pourcentage de la page
            table.setHorizontalAlignment(Element.ALIGN_CENTER); // Alignement au centre

            // Ajout des entêtes de colonnes
            
            String[] columnname={"Description","Patient","Montant","Jour","Type dacte"};
                for (int i = 0; i < columnname.length; i++) {
                    String string = columnname[i];
                    table.addCell(columnname[i]);
                    
                }
              // Ajout d'une seule ligne avec des valeurs aléatoires
              String nomdupatient="";
            double totalapayer=0.0;
            double totalremboursement=0;
            DecimalFormat format = new DecimalFormat("#,###.00");
            Patient patient=new Patient();
            String datedefacturation="";
            for (int i = 0; i < actesarray.size(); i++) {
                Actes get = actesarray.get(i);
                table.addCell(get.getDescription());
                patient=get.getIdpatient();
                datedefacturation=get.getJour().toString();
                if (get.getIdpatient().getIdremboursement().getIdremboursement().contains("1")) {
                    totalremboursement=totalremboursement+(get.getMontant()*0.2);
                }
            table.addCell(get.getIdpatient().getNom());
            table.addCell(format.format(get.getMontant())+" Ariary"); // Montant aléatoire entre 0 et 100
            table.addCell(Util.Util.translateDate((Date)get.getJour()));
            nomdupatient=get.getIdpatient().getNom();
            table.addCell(get.getIdtypeacte().getNom());
            totalapayer=totalapayer+get.getMontant();
            }
            
            // Remplissage des cellules avec des valeurs aleatoires
            
            Paragraph paragraph = new Paragraph();
            paragraph.setSpacingBefore(150);
            Font largeFront=new Font(Font.FontFamily.TIMES_ROMAN,22);
            Paragraph titre = new Paragraph("Patient: "+nomdupatient,largeFront);
            titre.setAlignment(Paragraph.ALIGN_CENTER);
            titre.setSpacingAfter(20);
            Paragraph total = new Paragraph("TOTAL A PAYER: "+(totalapayer+(int)totalremboursement)+ "  Ar",largeFront);
            total.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph majoration = new Paragraph("MAJORATION: "+format.format(totalremboursement)+ "  Ar",largeFront);
            majoration.setAlignment(Paragraph.ALIGN_CENTER);
            majoration.setSpacingAfter(20);
            total.setAlignment(Paragraph.ALIGN_CENTER);
            total.setSpacingAfter(20);
            paragraph.setSpacingBefore(150);
            table.setWidthPercentage(90);
            
            document.add(paragraph);
            document.add(titre);
            // Ajout du tableau au document
            document.add(table);
            document.add(total);
            document.add(majoration);
                if (totalremboursement>0.0) {
                    Actes actevao2=new Actes();
                    Typeacte actes=new Typeacte();
                    actes.setIdtypeacte("t19");
                    actevao2.setIdtypeacte(actes);
                    actevao2.setMontant((int)totalremboursement);
                    actevao2.setJour(Date.valueOf(datedefacturation));
                    actevao2.setDescription("remboursement");
                    actevao2.setIdpatient(patient);
                    ent.save(actevao2);
                }
            // Fermeture du document
            document.close();
            byte[] pdfBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XHTML_XML);
        headers.setContentDispositionFormData("attachment", "monfichier.pdf");
        headers.setContentLength(pdfBytes.length);

        // Retourner la réponse HTTP
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        ResponseEntity<ByteArrayResource> essai=new ResponseEntity<ByteArrayResource>(resource,headers,HttpStatus.ACCEPTED);
        return essai;
         
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
        
       
    
     @RequestMapping(value="/indexuser")
    public String  indexuser (Model mod) throws Exception{
       
       return "indexuser";
    }
    
    
    @RequestMapping(value="/connexutilisateur",method = RequestMethod.GET)
    public String  connexutilisateur (Model mod,@RequestParam Map<String,String> allParams,HttpSession session) throws Exception{
        Utilisateur admin=new Utilisateur();
        admin.setWhere(" where username='"+allParams.get("username")+"' and password='"+allParams.get("password")+"'");
       ArrayList<Utilisateur> adtab=ent.find(admin,"utilisateur",Utilisateur.class);
         if (adtab.size()>0) {
            session.setAttribute("actuel","client");
             return "indexuser";
         }else{
             return "logutilisateur";
         }
       // mod.addAttribute("listprojet",ent.find(new Projet(),"projet",Projet.class));
        //mod.addAttribute("listequipe",ent.find(new Equipe(),"equipe",Equipe.class));
       // mod.addAttribute("listetachespaginee",ent.find(new Tachesequipe(),"tachesequipe",Tachesequipe.class));
        //mod.addAttribute("listetachespaginee",ent.getEntities(2, 1," ",Tachesequipe.class));
       
    }
    
    
    
    
}

