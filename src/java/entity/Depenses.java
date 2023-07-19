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
@TableBase(anarananatybase = "Depenses")
public class Depenses extends ObjetDao{
    @Annotationdebase(isprimarykey = true,nomtable="",nomdelacolonne="",reconnaissance =false)
    String iddepense;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =true)
    String description;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
    double montant;
    @Annotationdebase(isprimarykey = false,nomtable="Typecharge",nomdelacolonne="idtypecharge",reconnaissance =false)
    Typecharge idtypecharge;  
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
    Date jour;

    public String getIddepense() {
        return iddepense;
    }

    public void setIddepense(String iddepense) {
        this.iddepense = iddepense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    

    public Typecharge getIdtypecharge() {
        return idtypecharge;
    }

    public void setIdtypecharge(Typecharge idtypecharge) {
        this.idtypecharge = idtypecharge;
    }

    

    

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    
}
