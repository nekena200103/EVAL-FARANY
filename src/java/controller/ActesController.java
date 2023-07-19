package controller;


import Service.CRUDGenservice;
import controller.DefaultController;
import entity.Actes;
import entity.Artiste;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
import entity.Admin;
import entity.Artiste;
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
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
@Controller
@RequestMapping("/actes")
public class ActesController extends DefaultController<Actes>{
    public ActesController(){
        super(Actes.class,"");
    }
    CRUDGenservice gen=new CRUDGenservice();
   
    
    @RequestMapping("/pdf/{page}")
    public ResponseEntity<ByteArrayResource> midownpdf(HttpServletResponse response,ArrayList<Actes> actearray) throws DocumentException{
        Document document = new Document(new Rectangle(707,1000));
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
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
            Pdf.addTableHeader(table,columnname);
              // Ajout d'une seule ligne avec des valeurs aléatoires
            for (int i = 0; i < actearray.size(); i++) {
                Actes get = actearray.get(i);
                table.addCell(get.getDescription());
            table.addCell(get.getIdpatient().getNom());
            table.addCell(""+get.getMontant()); // Montant aléatoire entre 0 et 100
            table.addCell(Util.Util.translateDate((Date)get.getJour()));
            table.addCell(get.getIdtypeacte().getNom());
            }
            
            // Remplissage des cellules avec des valeurs aleatoires
            
            Paragraph paragraph = new Paragraph();
            paragraph.setSpacingBefore(150);
            table.setWidthPercentage(80);
            
            document.add(paragraph);
            // Ajout du tableau au document
            document.add(table);

            // Fermeture du document
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
    /*@Override
    @GetMapping("/update/{integer}")
    public String loadUpdate(@PathVariable Integer integer, Model map) {
        return null;
    }*/


}
