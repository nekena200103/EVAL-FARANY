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
@TableBase(anarananatybase = "Patient")
public class Patient extends ObjetDao{
    @Annotationdebase(isprimarykey = true,nomtable="",nomdelacolonne="",reconnaissance =false)
    String idpatient;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =true)
    String nom ;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
    Date datenaissance;
    @Annotationdebase(isprimarykey = false,nomtable="Remboursement",nomdelacolonne="idremboursement",reconnaissance =false)
    Remboursement idremboursement;
    @Annotationdebase(isprimarykey = false,nomtable="Genre",nomdelacolonne="idgenre",reconnaissance =false)
    Genre idgenre;

    public String getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(String idpatient) {
        this.idpatient = idpatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public Remboursement getIdremboursement() {
        return idremboursement;
    }

    public void setIdremboursement(Remboursement idremboursement) {
        this.idremboursement = idremboursement;
    }

    public Genre getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(Genre idgenre) {
        this.idgenre = idgenre;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    

    

   
}
