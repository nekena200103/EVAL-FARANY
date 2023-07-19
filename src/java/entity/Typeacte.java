/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import HibernateDao.ObjetDao;
import annotation.Annotationdebase;
import annotation.TableBase;

/**
 *
 * @author Nekena
 */

@TableBase(anarananatybase = "Typeacte")
public class Typeacte extends ObjetDao{
    @Annotationdebase(isprimarykey = true,nomtable="",nomdelacolonne="",reconnaissance =false)
    String idtypeacte;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =true)
    String nom;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
    double  budgetannuel;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
    String code; 

    public double getBudgetannuel() {
        return budgetannuel;
    }

    public void setBudgetannuel(double budgetannuel) {
        this.budgetannuel = budgetannuel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getIdtypeacte() {
        return idtypeacte;
    }

    public void setIdtypeacte(String idtypeacte) {
        this.idtypeacte = idtypeacte;
    }

    

   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    
    
}
