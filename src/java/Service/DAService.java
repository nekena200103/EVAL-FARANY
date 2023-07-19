/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import HibernateDao.ObjetDao;
import database.Connexion2;

import java.util.ArrayList;


/**
 *
 * @author Nekena
 */

public class DAService {
    
    
    public <T> ArrayList<T> find(Object object,String nomdetable,Class<T> class2) throws Exception{
        Object[] tabobj=ObjetDao.makaresulataanaselect(object,nomdetable);
        ArrayList<T> array=new ArrayList<>();
        for (int i = 0; i < tabobj.length; i++) {
            Object object1 = tabobj[i];
            if (class2.isInstance(tabobj[i])) {
               array.add(class2.cast(tabobj[i]));
            }
        }
        return array;
    }
    public void save(Object obj) throws Exception{
        ObjetDao.save(obj);
    }
    public void delete(Object obj) throws Exception{
        ObjetDao.mamafaobjetanatybase( obj);
    }
    public void update(Object obj) throws Exception{
        ObjetDao.manaoupdate(Connexion2.getConnection(), obj);
    }
    
}
