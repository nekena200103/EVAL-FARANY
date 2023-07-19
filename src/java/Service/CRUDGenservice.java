/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import HibernateDao.ObjetDao;
import annotation.Annotationdebase;
import annotation.MisyFile;
import annotation.TableBase;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Nekena
 */
public class CRUDGenservice {
    public  String CONTEXT="/EVAL-FARANY/";
    DAService ent=new DAService();
    public  String generateHTMLTable(  ArrayList list,int numero,boolean misyariana) {
        StringBuilder htmlTable = new StringBuilder();
        
        if (list.isEmpty()) {
            return htmlTable.toString(); // Retourne une chaîne vide si la liste est vide
        }
        
        // Récupération du premier objet pour déterminer les attributs
        Object firstObject = list.get(0);
        Class<?> objClass = firstObject.getClass();
        Field[] fields = objClass.getDeclaredFields();
        
        // Construction du thead avec les attributs de l'objet
        htmlTable.append("<h1 style=\"margin-left: 10%; margin-top: 2%;\">Liste  "+objClass.getSimpleName()+"</h1>\n" +
                "<a href='"+CONTEXT+objClass.getSimpleName().toLowerCase()+"/create'><btn class=\"btn btn-primary py-3 px-5\" style='margin-left:10%;'>   Creer "+objClass.getSimpleName()+" </btn></a>");
        if (numero>0) {
            htmlTable.append("<a  href='"+CONTEXT+objClass.getSimpleName().toLowerCase()+"/"+(numero-1)+"'><btn class=\"btn btn-primary py-2 px-3\" style='margin-left:15%;'>   Precedent  </btn></a>");
            
        }
        if (misyariana==true) {
            htmlTable.append("<a href='"+CONTEXT+objClass.getSimpleName().toLowerCase()+"/"+(numero+1)+"'><btn class=\"btn btn-primary py-2 px-3\" style='margin-left:30%;'>   Suivant </btn></a>");
        }
        
               htmlTable.append( "                  <div class=\"card-body\">\n" +
"                  \n" +
"                  <div class=\"table-responsive\"><table class='table'>")
                 .append("<thead>")
                 .append("<tr>");
        
        for (Field field : fields) {
            String attributeName = field.getName();
            
            htmlTable.append("<th>")
                     .append(attributeName)
                     .append("</th>");
        }
        htmlTable.append("<th>Supprimer</th>");
        // Fin de la construction du thead
        htmlTable.append("</tr>")
                 .append("</thead>");

        // Construction du tbody avec les données de chaque objet
        htmlTable.append("<tbody>");
        String id="";
        for (Object obj : list) {
            // Extraction des données de chaque attribut de l'objet
            htmlTable.append("<tr>");
            
            for (Field field : fields) {
                String getterName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Annotationdebase annot=field.getAnnotation(Annotationdebase.class);
                
                try {
                    Method getter = objClass.getMethod(getterName);
                    Object value = getter.invoke(obj); // Appelle le getter pour récupérer la valeur
                    if (annot.isprimarykey()==true) {
                        id=value.toString();
                    }
                    MisyFile sary=field.getAnnotation(MisyFile.class);
                    if (sary!=null) {
                        htmlTable.append("<th>")
                             .append("<img src='data:image/png;base64,"+value.toString()+"' style='width: 10%; height: 10%;'>") // conversion éventuelle en chaîne de caractères
                             .append("</th>");
                    }else if(field.getType()==Date.class){
                    
                    
                    htmlTable.append("<th>")
                             .append(Util.Util.translateDate((Date)value)) // conversion éventuelle en chaîne de caractères
                             .append("</th>");
                    }else if(field.getType()==double.class){
                         DecimalFormat format = new DecimalFormat("#,##0.00");
                    htmlTable.append("<th>")
                             .append(format.format(value)) // conversion éventuelle en chaîne de caractères
                             .append("</th>");
                    }else{
                    htmlTable.append("<th>")
                             .append(String.valueOf(value)) // conversion éventuelle en chaîne de caractères
                             .append("</th>");
                    }
                    
                } catch (Exception e) {
                    // Gestion de l'exception : ignorer l'attribut en question
                    continue;
                }
            }
            htmlTable.append("<th><a href='"+CONTEXT+objClass.getSimpleName().toLowerCase()+"/delete/"+id+"'><btn class=\"btn btn-danger py-3 px-5\" style='margin-left:10%;'>   Supprimer  </btn></a></th>");
            //htmlTable.append("<th><a href='"+CONTEXT+objClass.getSimpleName().toLowerCase()+"/update/"+id+"'><btn class=\"btn btn-warning py-3 px-5\" style='margin-left:10%;'>   Modifier  </btn></a></th>");
            htmlTable.append("</tr>");
        }
        
        // Fin de la construction du tbody et du tableau
        htmlTable.append("</tbody>")
                 .append("</table>");
        
        return htmlTable.toString();
    }
    public String generateInsertionForm(Object object) {
    Class<?> objClass = object.getClass();
    Field[] fields = objClass.getDeclaredFields();

    StringBuilder formBuilder = new StringBuilder();
    formBuilder.append("<h1 style=\"margin-left: 10%; margin-top: 2%;\">Creer ").append(objClass.getSimpleName()).append("</h1>\n" +
"                  <div class=\"card-body\">\n" +
"                  \n" +
"                  <div class=\"table-responsive\"><form  enctype=\"multipart/form-data\" method=\"POST\""
            + "action=\""+CONTEXT+""+objClass.getSimpleName().toLowerCase()+"/createnow\">");

    for (Field field : fields) {
        String fieldName = field.getName();
        String fieldType = field.getType().getSimpleName();
        Annotationdebase annotdebase=field.getAnnotation(Annotationdebase.class);
        if (annotdebase.isprimarykey()==false) {
            
    
        

        if (fieldType.equals("int")) {
            formBuilder.append("<input class=\"form-control\" placeholder=\"").append(fieldName).append("\" type=\"number\" name=\"").append(fieldName).append("\" id=\"").append(fieldName).append("\" required><br>");
        } else if (fieldType.equals("String")) {
            boolean misyfile=false;
        
            MisyFile annotations = field.getDeclaredAnnotation(MisyFile.class);
            if (annotations!=null) {
                misyfile=true;
            }
            if (misyfile==true) {
                formBuilder.append("<input class=\"form-control\" placeholder=\"").append(fieldName).append("\" type=\"file\" name='file' id=\"").append(fieldName).append("\" required><br>");
            }else{
                formBuilder.append("<input  class=\"form-control\" placeholder=\"").append(fieldName).append("\" type=\"text\" name=\"").append(fieldName).append("\" id=\"").append(fieldName).append("\" required><br>");
            }
            
        
            
        } else if(fieldType.equals("double")){
                formBuilder.append("<input  class=\"form-control\" placeholder=\"").append(fieldName).append("\" type=\"number\" name=\"").append(fieldName).append("\" step='0.01'id=\"").append(fieldName).append("\" required><br>");
        }
        else if(fieldType.equals("Date")){
                formBuilder.append("<input  class=\"form-control\" placeholder=\"").append(fieldName).append("\" type=\"date\" name=\"").append(fieldName).append("\" id=\"").append(fieldName).append("\" required><br>");
        }else {
                try {
                    
                    Class<?> class2=field.getType();
                    System.out.println(class2.getSimpleName());
                    TableBase nomdetable=class2.getAnnotation(TableBase.class);
                    ArrayList<?> listofoption=ent.find(field.getType().newInstance(),nomdetable.anarananatybase(), class2);
                    formBuilder.append("<select class=\"form-control\" placeholder=\"").append(fieldName).append("\" name='selectobject").append(fieldName).append("' id=\"").append(fieldName).append("\" required>");
                    for (int i = 0; i < listofoption.size(); i++) {
                        ObjetDao get =(ObjetDao) listofoption.get(i);
                        formBuilder.append("<option value=\"").append(get.makaprimarykey()).append("\">").append(get.toString()).append("</option>");
                    }
                    
                    formBuilder.append("</select><br>");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    formBuilder.append("<input  type='submit' value='Creer  cet "+objClass.getSimpleName()+"' class=\"btn btn-primary py-3 px-5\">");
        System.out.println(formBuilder.toString());
    return formBuilder.toString();
    }
    public String generateUpdateForm(Object object) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InvocationTargetException {
    Class<?> objClass = object.getClass();
    Field[] fields = objClass.getDeclaredFields();

    StringBuilder formBuilder = new StringBuilder();
    formBuilder.append("<h1 style=\"margin-left: 10%; margin-top: 2%;\">Modifier  ").append(objClass.getSimpleName()).append("</h1>\n" +
"                  <div class=\"card-body\">\n" +
"                  \n" +
"                  <div class=\"table-responsive\"><form  enctype=\"multipart/form-data\" method=\"POST\""
            + "action=\""+CONTEXT+""+objClass.getSimpleName().toLowerCase()+"/updatenow\">");

    for (Field field : fields) {
        String fieldName = field.getName();
        String fieldType = field.getType().getSimpleName();
        Annotationdebase annotdebase=field.getAnnotation(Annotationdebase.class);
        if (annotdebase.isprimarykey()==false) {
        Method getteur=object.getClass().getDeclaredMethod("get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
        String value=(String)getteur.invoke(object).toString();
        

        if (fieldType.equals("int")) {
            formBuilder.append("<input class=\"form-control\" default=\"").append(value).append("\" placeholder=\"").append(value+":"+fieldName).append("\" type=\"number\" name=\"").append(fieldName).append("\" id=\"").append(fieldName).append("\" required><br>");
        } else if (fieldType.equals("String")) {
            boolean misyfile=false;
        
            MisyFile annotations = field.getDeclaredAnnotation(MisyFile.class);
            if (annotations!=null) {
                misyfile=true;
            }
            if (misyfile==true) {
                formBuilder.append("<input class=\"form-control\" default=\"").append(value).append("\" placeholder=\"").append(value+":"+fieldName).append("\" type=\"file\" name='file' id=\"").append(fieldName).append("\" required><br>");
            }else{
                formBuilder.append("<input  class=\"form-control\" default=\"").append(value).append("\" placeholder=\"").append(value+":"+fieldName).append("\" type=\"text\" name=\"").append(fieldName).append("\" id=\"").append(fieldName).append("\" required><br>");
            }
            
        
            
        } else if(fieldType.equals("double")){
                formBuilder.append("<input  class=\"form-control\" default=\"").append(value).append("\" placeholder=\"").append(value+":"+fieldName).append("\" type=\"number\" name=\"").append(fieldName).append("\" step='0.01'id=\"").append(fieldName).append("\" required><br>");
        }
        else if(fieldType.equals("Date")){
                formBuilder.append("<input  class=\"form-control\" default=\"").append(value).append("\" placeholder=\"").append(value+":"+fieldName).append("\" type=\"date\" name=\"").append(fieldName).append("\" id=\"").append(fieldName).append("\" required><br>");
        }else {
                try {
                    
                    Class<?> class2=field.getType();
                    System.out.println(class2.getSimpleName());
                    TableBase nomdetable=class2.getAnnotation(TableBase.class);
                    ArrayList<?> listofoption=ent.find(field.getType().newInstance(),nomdetable.anarananatybase(), class2);
                    formBuilder.append("<select class=\"form-control\" default=\"").append(value).append("\" placeholder=\"").append(value+":"+fieldName).append("\" name='selectobject").append(fieldName).append("' id=\"").append(fieldName).append("\" required>");
                    for (int i = 0; i < listofoption.size(); i++) {
                        ObjetDao get =(ObjetDao) listofoption.get(i);
                        formBuilder.append("<option value=\"").append(get.makaprimarykey()).append("\">").append(get.toString()).append("</option>");
                    }
                    
                    formBuilder.append("</select><br>");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    formBuilder.append("<input  type='submit' value='Creer  cet "+objClass.getSimpleName()+"' class=\"btn btn-primary py-3 px-5\">");
        System.out.println(formBuilder.toString());
    return formBuilder.toString();
    }
    /*public String generateForm( ObjetDao obj){
     
    
    }*/
}
