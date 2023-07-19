/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import HibernateDao.ObjetDao;
import annotation.Annotationdebase;
import annotation.MisyFile;
import annotation.TableBase;

/**
 *
 * @author Nekena
 */
@TableBase(anarananatybase = "Artiste")
public class Artiste extends ObjetDao{
    @Annotationdebase(isprimarykey = true,nomtable="",nomdelacolonne="",reconnaissance =false)
    String idartiste;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =false)
    int tarifparheure;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =true)
    String nomartiste ;
    @Annotationdebase(isprimarykey = false,nomtable="",nomdelacolonne="",reconnaissance =true)
    @MisyFile(nominput = "photo")
    String photo;
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getIdartiste() {
        return idartiste;
    }

    public void setIdartiste(String idartiste) {
        this.idartiste = idartiste;
    }

    public int getTarifparheure() {
        return tarifparheure;
    }

    public void setTarifparheure(int tarifparheure) {
        this.tarifparheure = tarifparheure;
    }

    public String getNomartiste() {
        return nomartiste;
    }

    public void setNomartiste(String nomartiste) {
        this.nomartiste = nomartiste;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    
}
