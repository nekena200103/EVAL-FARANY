/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import HibernateDao.ObjetDao;
import annotation.Annotationdebase;
import annotation.TableBase;
import java.sql.Date;

/**
 *
 * @author Nekena
 */
@TableBase(anarananatybase = "Actes")
public class Actes extends ObjetDao{
    @Annotationdebase(isprimarykey = true,nomtable="",nomdelacolonne="",reconnaissance =false)
     String idactes;
    @Annotationdebase(isprimarykey =false,nomtable="",nomdelacolonne="",reconnaissance =true)
     String description;
    @Annotationdebase(isprimarykey = false,nomtable="Patient",nomdelacolonne="idpatient",reconnaissance =false)
     Patient idpatient;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
     int montant;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
     Date jour;
    @Annotationdebase(isprimarykey = false,nomtable="Typeacte",nomdelacolonne="idtypeacte",reconnaissance =false)
     Typeacte idtypeacte ;  

    public String getIdactes() {
        return idactes;
    }

    public void setIdactes(String idactes) {
        this.idactes = idactes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(Patient idpatient) {
        this.idpatient = idpatient;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public Typeacte getIdtypeacte() {
        return idtypeacte;
    }

    public void setIdtypeacte(Typeacte idtypeacte) {
        this.idtypeacte = idtypeacte;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    
    
}
