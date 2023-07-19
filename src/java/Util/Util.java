/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import annotation.*;
import java.util.*;
import java.lang.reflect.*;
import java.sql.Timestamp;
import java.time.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author USER
 */
public class Util {
    public static String FtoBase64(byte[]bytes){
       
       return Base64.getEncoder().encodeToString(bytes);
    }
    public static Date toGod(String dt){
        ZonedDateTime dateTime  = ZonedDateTime.parse(dt+":00.000Z");
        LocalDateTime localDateTime  = dateTime.toLocalDateTime();
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Date date = Date.from(zdt.toInstant());
		return date;
    }
    public static Timestamp toT(String dt){
        ZonedDateTime dateTime  = ZonedDateTime.parse(dt+":00.000Z");
        LocalDateTime localDateTime  = dateTime.toLocalDateTime();
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
        return timestamp;
    }
    public static String translateDate(Date date){
        SimpleDateFormat dateformat=new SimpleDateFormat("dd MMMM yyyy",Locale.FRENCH);
        return dateformat.format(date);
    }
    public static double arrondir(double nombre){
        DecimalFormat decimalformat=new DecimalFormat("0.00");
        String nombreformated=decimalformat.format(nombre);
        return Double.parseDouble(nombreformated.replace(',','.'));
    }
    public static Object traductionParameterDynamique(Map<String,String> attributValue)throws Exception{
        Class ray=Class.forName(attributValue.get("class"));
        Object temp=ray.getDeclaredConstructor().newInstance();
        Field[]fields=ray.getDeclaredFields();
        Method[]methods=ray.getDeclaredMethods();
       for (int i=0;i<fields.length ;i++) 
       {
               for (int j=0;j<methods.length ;j++ ) 
               {
                   if (("set"+fields[i].getName()).compareToIgnoreCase(methods[j].getName())==0)
                   {
                           if(attributValue!=null){
                                       if(attributValue.get(fields[i].getName())!=null){
                                               if (fields[i].getType().getName()=="int")
                                               {
                                                   try{
                                                       int a=Integer.parseInt(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){
                                                       
                                                   }              
                                               }else if (fields[i].getType().getName()=="float")
                                               {
                                                   try{
                                                       float a=Float.parseFloat(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){

                                                   }              
                                               }else if (fields[i].getType().getName()=="double")
                                               {
                                                   try{
                                                       double a=Double.parseDouble(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){

                                                   }              
                                               }else if (fields[i].getType().getName()=="long")
                                               {
                                                   try{
                                                       long a=Long.parseLong(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){

                                                   }              
                                               }else if (fields[i].getType().getName()=="java.lang.String")
                                               {
                                                   try{
                                                       methods[j].invoke(temp,attributValue.get(fields[i].getName()));
                                                   }catch(Exception er){

                                                   }              
                                               }else if(fields[i].getType().getName()=="java.sql.Timestamp"){
                                                try{
                                                    java.sql.Timestamp a=toT(attributValue.get(fields[i].getName()));
                                                    methods[j].invoke(temp,a);
                                                }catch(Exception er){

                                                }  
                                            }
                                            else if(fields[i].getType().getName()=="java.util.Date"){
                                                try{
                                                    Date a=toGod(attributValue.get(fields[i].getName()));
                                                    methods[j].invoke(temp,a);
                                                }catch(Exception er){

                                                }  
                                            }
                                       }
                           }
                           
                   }        
               }    
       }
       return temp;

    }
    
    public static boolean estBissextile(int annee) {
        return (annee % 4 == 0 && annee % 100 != 0) || (annee % 400 == 0);
    }

    public static String dateCorrecte(int jour, int mois, int annee) {
        int[] joursParMois = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Vérifier si l'année est bissextile pour mettre à jour le nombre de jours de février
        if (estBissextile(annee)) {
            joursParMois[2] = 29;
        }

        // Vérifier si le mois est valide (entre 1 et 12)
        if (mois < 1 || mois > 12) {
            throw new IllegalArgumentException("Mois invalide");
        }

        // Vérifier si le jour est valide pour le mois donné
        if (jour < 1 || jour > joursParMois[mois]) {
            throw new IllegalArgumentException("Jour invalide pour ce mois");
        }

        return String.format("%04d-%02d-%02d", annee, mois, jour);
    }

   


//    public static ilike(String ray){
//        return "%"
//    }
}
