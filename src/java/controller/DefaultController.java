package controller;


import HibernateDao.ObjetDao;
import Service.CRUDGenservice;
import Service.DAService;
import annotation.Annotationdebase;
import annotation.MisyFile;
import annotation.TableBase;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Controller
public abstract class DefaultController<M extends ObjetDao> {
    protected Class<M> classModel;
    protected String superFolder;
        CRUDGenservice gen=new CRUDGenservice();
        DAService ent=new DAService();
    public DefaultController(Class<M> classModel,String superFolder){
        this.classModel=classModel;
        this.superFolder=superFolder;
    }
    
    
    @RequestMapping(value="/{page}",method =RequestMethod.GET )
    public String paginate(@PathVariable int page, Model map) throws Exception {
        M andrana=classModel.newInstance();
        andrana.setWhere(" limit 5 offset "+page*5);
        TableBase tabbase=andrana.getClass().getAnnotation(TableBase.class);
        List<M> data=ent.find(andrana,tabbase.anarananatybase(),classModel);
        andrana.setWhere(" limit 5 offset "+(page+1)*5);
        List<M> data2=ent.find(andrana,tabbase.anarananatybase(),classModel);
        boolean mbolamisy=false;
        if (data2.size()>0) {
            mbolamisy=true;
        }
        if (data.size()==0) {
            return "redirect:/"+classModel.getSimpleName().toLowerCase()+"/create";
        }
         andrana.setWhere(" limit 5 offset "+page*5);
        map.addAttribute("listetable",gen.generateHTMLTable(ent.find(andrana,tabbase.anarananatybase(),classModel),page,mbolamisy));
        return "read";
    }
    
     @RequestMapping("/update/{id}")
    public String loadUpdate(Model map,@PathVariable String id) throws NoSuchMethodException, Exception, Exception {
        M andrana=instancing();
        Field[] listfield=andrana.getClass().getDeclaredFields();
        String field="";
         for (int i = 0; i < listfield.length; i++) {
            Field get = listfield[i];
            Annotationdebase base=get.getAnnotation(Annotationdebase.class);
             if (base.isprimarykey()==true) {
                 field=get.getName();
             }
             
         }
         Method method=andrana.getClass().getDeclaredMethod("set"+field.substring(0, 1).toUpperCase() + field.substring(1),String.class);
         andrana.setWhere(" where "+field+"='"+id+"'");
         TableBase tabbase=andrana.getClass().getAnnotation(TableBase.class);
         ArrayList<M> essai=ent.find(andrana,tabbase.anarananatybase(),classModel);
         
        try {
            map.addAttribute("create",gen.generateUpdateForm(essai.get(0)));
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return "create";
    }
    @RequestMapping("/create")
    public String loadCreate(Model map) {
        
        try {
            map.addAttribute("create",gen.generateInsertionForm(classModel.newInstance()));
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return "create";
    }
    @RequestMapping("/delete/{id}")
    public String delete(Model map,@PathVariable String id) {
        try{
        M objet=instancing();
        Field[] fields=objet.getClass().getDeclaredFields();
        String nomfiel="";
        for (int i = 0; i < fields.length; i++) {
             System.out.println("tay le");
            Field field = fields[i];
            Annotationdebase annot=field.getAnnotation(Annotationdebase.class);
            if (annot!=null && annot.isprimarykey()==true) {
                nomfiel=field.getName();
            }
            
        }
            System.out.println(nomfiel);
        Method method=objet.getClass().getMethod("set"+nomfiel.substring(0, 1).toUpperCase() + nomfiel.substring(1),String.class);
        method.invoke(objet, id);
        ent.delete(objet);
        }
        catch(PSQLException e){
            map.addAttribute("message","Cette ligne a des references avec d'autres tables.Vous ne pouvez pas la supprimer");
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
       
        return "redirect:/"+classModel.getSimpleName().toLowerCase()+"/0";
    }
    /*
    @RequestMapping(value="/createnow2")
    public String create(@ModelAttribute M model,Model map,@RequestParam(required=false) CommonsMultipartFile file) throws Exception {
        try {
            
            boolean misysary=false;
            int indicefield=0;
            Field[] fieldlist=model.getClass().getDeclaredFields();
            System.out.println(model.getClass());
             for (int i = 0; i < fieldlist.length; i++) {
                Field field = fieldlist[i];
                System.out.println("set"+fieldlist[i].getName());
                 MisyFile filemisy=field.getAnnotation(MisyFile.class);
                 if (filemisy!=null) {
                     misysary=true;
                      
                     indicefield=i;
                     System.out.println("set"+fieldlist[indicefield].getName());
                 }
                
            }
            if(misysary==true){
                byte[] bytes=file.getBytes();
             Method[] methodlist=classModel.getMethods();
             for (int i = 0; i < methodlist.length; i++) {
                 System.out.println("set"+fieldlist[indicefield].toString());
                 if (("set"+fieldlist[indicefield].getName()).toLowerCase().contains(methodlist[i].getName().toLowerCase())) {
                     System.out.println("set"+fieldlist[indicefield].toString());
                     methodlist[i].invoke(model,Util.Util.FtoBase64(bytes));
                 }
                
            }
            }
            ent.save(model);
            return paginate(0,map);
        }catch (Exception e){
            e.printStackTrace();
            return "create";
        }
    }*/
    
    @RequestMapping(value = "/createnow", method = RequestMethod.POST)
    public String traitementRequete(@RequestParam Map<String,String> parameterMap,@RequestParam(required=false) CommonsMultipartFile file,Model map) throws Exception{
  
    
    M model=instancing();
    boolean misysary=false;
            int indicefield=0;
            Field[] fieldlist=model.getClass().getDeclaredFields();
            System.out.println(model.getClass());
             for (int i = 0; i < fieldlist.length; i++) {
                Field field = fieldlist[i];
                System.out.println("set"+fieldlist[i].getName());
                 MisyFile filemisy=field.getAnnotation(MisyFile.class);
                 if (filemisy!=null) {
                     misysary=true;
                      
                     indicefield=i;
                     System.out.println("set"+fieldlist[indicefield].getName());
                 }
                
            }
            if(misysary==true){
                byte[] bytes=file.getBytes();
             Method[] methodlist=classModel.getMethods();
             for (int i = 0; i < methodlist.length; i++) {
                 System.out.println("set"+fieldlist[indicefield].toString());
                 if (("set"+fieldlist[indicefield].getName()).toLowerCase().contains(methodlist[i].getName().toLowerCase())) {
                     System.out.println("set"+fieldlist[indicefield].toString());
                     methodlist[i].invoke(model,Util.Util.FtoBase64(bytes));
                 }
                
            }
            }
    for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
        String paramName =entry.getKey();
        String paramValues = entry.getValue();
        
        
        if (paramName.contains("selectobject")) {
            // Traitement spécifique pour les paramètres contenant "selectobject"
            String fieldName = paramName.substring(paramName.indexOf("selectobject") + "selectobject".length());
            Class<?> fieldType = null;
            try {
                
                Field field = classModel.getDeclaredField(fieldName);
                fieldType = field.getType();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            
            if (fieldType != null) {
                Object fieldValue = createInstance(fieldType);
                Field[] fieldtab=fieldType.getDeclaredFields();
                for (int i = 0; i < fieldtab.length; i++) {
                    Field field = fieldtab[i];
                    Annotationdebase annot=field.getAnnotation(Annotationdebase.class);
                    
                    if (annot.isprimarykey()==true) {
                        Method[] methodlist=fieldValue.getClass().getMethods();
                        for (int j = 0; j < methodlist.length; j++) {
                        
                            if (("set"+field.getName()).toLowerCase().contains(methodlist[j].getName().toLowerCase())) {
                            System.out.println("set"+field.toString());
                            methodlist[j].invoke(fieldValue,paramValues);
                            }
                
                         }
                    }
                    
                }
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method[] methodlist = classModel.getMethods();
                 for (int j = 0; j < methodlist.length; j++) {
                        
                            if ((setterName).toLowerCase().contains(methodlist[j].getName().toLowerCase())) {
                           
                            methodlist[j].invoke(model,fieldValue);
                            }
                
                         }
                

                
            }
        } else {
            // Traitement pour les autres paramètres
            
                // Trouver le setter correspondant dans l'objet
                String fieldName = paramName;
                Class<?> fieldType = null;
                 Field field = classModel.getDeclaredField(fieldName);
                fieldType = field.getType();
                String setterName = "set" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1);
                Method[] methodlist = classModel.getMethods();
                 for (int j = 0; j < methodlist.length; j++) {
                        
                            if ((setterName).toLowerCase().contains(methodlist[j].getName().toLowerCase())) {
                           
                            methodlist[j].invoke(model,convertValue(paramValues, fieldType));
                            }
                
                         }
                
                
          
        }
        
    }
        ent.save(model);
    return paginate(0,map); 
}

private Object createInstance(Class<?> fieldType) {
    try {
        return fieldType.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
private Object convertValue(String value, Class<?> targetType) {
    if (targetType == String.class) {
        return value;
    } else if (targetType == Integer.class || targetType == int.class) {
        return Integer.parseInt(value);
    }else if ( targetType==Date.class ) {
        return Date.valueOf(value);
    } else if (targetType == Long.class || targetType == long.class) {
        return Long.parseLong(value);
    } else if (targetType == Double.class || targetType == double.class) {
        return Double.parseDouble(value);
    } else if (targetType == Boolean.class || targetType == boolean.class) {
        return Boolean.parseBoolean(value);
    } else {
        throw new UnsupportedOperationException("Type de données non pris en charge : " + targetType.getName());
    }
}
        /*
    public abstract String loadUpdate(ID id,Model map);
    public String update(@ModelAttribute M model,@PathVariable(required = false) ID id,Model map){
        try {
            model.update();
            return loadUpdate(id,map);
        }catch (Exception e){
            return superFolder+"template";
        }
    }*/
    protected String getViewName(String folder){
        return folder+"/"+classModel.getSimpleName().toLowerCase();
    }
    
    protected M instancing(){
        try {
            Constructor<M> constructor=classModel.getConstructor();
            return constructor.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
