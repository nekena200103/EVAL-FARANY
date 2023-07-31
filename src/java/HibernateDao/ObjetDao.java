package HibernateDao;



import database.Connexion2;
import annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


import java.lang.Object.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

public class ObjetDao  {
	Connection c;
	
	public String where="";
        
	public void setWhere(String where) {
		this.where = where;
	}
	public String getWhere() {
		return where;
	}
    public static String toUpperFirst(String a) {
        char[] toChar = a.toCharArray();
        char[] f = new char[1];
        f[0] = toChar[0];
        String first = new String(f);
        String other;
        char[] oth = new char[toChar.length - 1];
        for (int i = 0; i < oth.length; i++) {
            oth[i] = toChar[i + 1];
        }
        other = new String(oth);
        return first.toUpperCase() + other;
    }        
    @Override
    public String toString() {
        String averina="";
       Field[] fields=this.getClass().getDeclaredFields();
       Method[] methods=this.getClass().getDeclaredMethods();
       
        for (int i = 0; i < fields.length; i++) {
            Annotationdebase base=fields[i].getDeclaredAnnotation(Annotationdebase.class);
            if (base.reconnaissance()==true) {
                for (int j = 0; j < methods.length; j++) {
                    if (methods[j].getName().toLowerCase().contains("get"+fields[i].getName().toLowerCase())) {
                        try {
                            averina=(String)methods[j].invoke(this);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                }
            }
            
        }
        if (averina==null) {
            TableBase tablebase=this.getClass().getDeclaredAnnotation(TableBase.class);
            if (tablebase!=null) {
                return tablebase.anarananatybase();
            }
            
            
        }else if(averina.length()<1){
            TableBase tablebase=this.getClass().getDeclaredAnnotation(TableBase.class);
            if (tablebase!=null) {
                return tablebase.anarananatybase();
            }
        }
        return averina;
    }
    
    public String makaprimarykey() {
        String averina="";
       Field[] fields=this.getClass().getDeclaredFields();
       Method[] methods=this.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            Annotationdebase base=fields[i].getDeclaredAnnotation(Annotationdebase.class);
            if (base.isprimarykey()==true) {
                for (int j = 0; j < methods.length; j++) {
                    if (methods[j].getName().toLowerCase().contains("get"+fields[i].getName().toLowerCase())) {
                        try {
                            averina=(String)methods[j].invoke(this);
                        } catch (Exception ex) {
                            
                        }
                    }
                    
                }
            }
            
        }
        
        return averina;
    }
        
	
        public static int nombreelement(Connection co,ObjetDao obj) throws Exception{
		Statement st=co.createStatement();
                TableBase tababase=obj.getClass().getDeclaredAnnotation(TableBase.class);
		ResultSet rs=st.executeQuery("SELECT * FROM "+tababase.anarananatybase()+" ");
                int taille=0;
		while (rs.next()) {
                    taille++;
                
                }
		return taille;
	}
	
	public static String[] mamoakaanaranatable(Connection co,String nomdetable) throws Exception{
		Statement st=co.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM "+nomdetable);
		ResultSetMetaData rsmd=rs.getMetaData();
		int nbColonnes=rsmd.getColumnCount();
		String[] typename =new String[nbColonnes]; 
		for (int i = 1; i <= nbColonnes; i++) {
			typename[i-1]=rsmd.getColumnName(i);
		}
		return typename;
		
	}
	
	public static  Object makaobjetanazymitokana(Connection co,Object object) throws Exception {
		Field[] nombreargumments=object.getClass().getDeclaredFields();
		
		String nomclass=object.getClass().getName();
		String nomclassfinal=nomclass.substring(nomclass.indexOf(".")+1);
		String select="SELECT * FROM "+nomclassfinal+" ";
		Method[]  methodclass=object.getClass().getDeclaredMethods();
		if (object instanceof ObjetDao) {
			ObjetDao obj= (ObjetDao) object;
			String sql="SELECT *FROM "+nomclassfinal.toUpperCase()+obj.getWhere();
		}
		
		
		
		
		String[] ilainahoannygetresultset=((ObjetDao) object).mamokainserttable(co,nomclassfinal.toUpperCase());
		String where=" where ";
		for (int i = 0; i < nombreargumments.length; i++) {
			for (int j = 0; j < methodclass.length; j++) {
				if (methodclass[j].toString().toLowerCase().contains("get"+nombreargumments[i].getName().toLowerCase())) {
					if (i!=nombreargumments.length-1) {
						where=where.concat(nombreargumments[i].getName().toLowerCase()+"="+methodclass[j].invoke(object)+" and ");
					}else {
						where=where.concat(nombreargumments[i].getName().toLowerCase()+"="+methodclass[j].invoke(object));
					}
					
					
				}
			}
		}
		select=select.concat(where);
		
		String nomobj=object.getClass().toString().substring(object.getClass().toString().indexOf("class")+6);
		Object resultat=Class.forName(nomobj);
		
		
		return resultat;
		

	}public static  void mamafaobjetanatybase(Object object) throws Exception {
            Connection co=Connexion2.getConnection();
		Field[] nombreargumments=object.getClass().getDeclaredFields();
		
		
                        TableBase tblbase=object.getClass().getDeclaredAnnotation(TableBase.class);
		String nomclassfinal=tblbase.anarananatybase();
		String select="DELETE FROM "+nomclassfinal+" ";
		Method[]  methodclass=object.getClass().getDeclaredMethods();
		if (object instanceof ObjetDao) {
			ObjetDao obj= (ObjetDao) object;
			String sql="SELECT *FROM "+nomclassfinal.toUpperCase()+obj.getWhere();
		}
		
		
		
		
		String[] ilainahoannygetresultset=((ObjetDao) object).mamokainserttable(co,nomclassfinal.toUpperCase());
		String where=" where ";
		for (int i = 0; i < nombreargumments.length; i++) {
			for (int j = 0; j < methodclass.length; j++) {
                            Annotationdebase base=nombreargumments[i].getDeclaredAnnotation(Annotationdebase.class);
				if(base.isprimarykey()==true){
                                    if (methodclass[j].toString().toLowerCase().contains("get"+nombreargumments[i].getName().toLowerCase())) {
                                            if(methodclass[j].invoke(object) instanceof Integer) {
                                            where=where.concat(nombreargumments[i].getName().toLowerCase()+"="+methodclass[j].invoke(object));
				
                                        }else{
                                                String request=nombreargumments[i].getName().toLowerCase()+"='"+methodclass[j].invoke(object)+"'";
                                                where=where.concat(request);                         
                                            }
                                                
						
					
					
                                    }
                                }
			}
		}
		select=select.concat(where);
		System.out.println(select);
               
		Statement stat=co.createStatement();
		stat.execute(select);
		
		return;
		

	}
	
	public static void manaoupdate(Connection co,Object objet) throws Exception {
		
		Field[] nombreargumments=objet.getClass().getDeclaredFields();
                String changement=" ";
		String nomclass=objet.getClass().getName();
		Method[]  methodclass=objet.getClass().getDeclaredMethods();
		String nomclassfinal=nomclass.substring(nomclass.indexOf(".")+1);
		String prempartie;
		String recherche2="id"+nomclassfinal+"= ";
		
		int nombreelementsbase=0;
                for (int k = 0; k < nombreargumments.length; k++) {
                    Field field = nombreargumments[k];
                    Annotationdebase base2 =field.getDeclaredAnnotation(Annotationdebase.class);
                    if (base2!=null) {
                        nombreelementsbase++;
                    }

                }
		
		for (int i = 0; i < nombreargumments.length; i++) {
                Annotationdebase base=nombreargumments[i].getDeclaredAnnotation(Annotationdebase.class);
                String virgule=",";
                                        if (nombreelementsbase<=1) {
                                            virgule="";
                                        }
                    for (int j = 0; j < methodclass.length; j++) {
                        Method methodclas = methodclass[j];
                        if (methodclas.getName().toString().toLowerCase().contains("get"+nombreargumments[i].getName().toString().toLowerCase())) {
                            if (base!=null && base.isprimarykey()==true) {
                                nombreelementsbase--;
                                recherche2=recherche2.concat("'"+(String) methodclas.invoke(objet)+"'");
                            }
                            if(base!=null && base.isprimarykey()==false){
                               
                                if (methodclas.invoke(objet) instanceof ObjetDao) {
                                     System.out.println("Ato");
                                    ObjetDao objbdd=(ObjetDao)methodclas.invoke(objet);
                                    Field[] fields=objbdd.getClass().getDeclaredFields();
                                    Method[] method=objbdd.getClass().getMethods();
                                    
                                    for (int k = 0; k < fields.length; k++) {
                                        Field field = fields[k];
                                        Annotationdebase base2 =field.getDeclaredAnnotation(Annotationdebase.class);
                                        
                                        for (int l = 0; l < method.length; l++) {
                                            Method method1 = method[l];
                                            if (method1.getName().toString().toLowerCase().contains("get"+field.getName().toString().toLowerCase())) {
                                                 if (base2!= null && base2.isprimarykey()==true) {
                                                     nombreelementsbase--;
                                                     System.out.print(nombreelementsbase);
                                                    changement=changement.concat(""+nombreargumments[i].getName()+"='"+(String) method1.invoke(objbdd)+"'"+virgule);
                                                }
                                            }
                                           
                                            
                                        }
                                        
                                        
                                    }
                                }else{
                                    System.out.print("tay"+nombreargumments[i].getName());
                                    
                                        nombreelementsbase--;
                                        changement=changement.concat(nombreargumments[i].getName()+"='"+methodclas.invoke(objet).toString()+"'"+virgule);
                                }
                                
                            }
                        }
                        
                    }
                
                }
		
		/*for (int i = 0; i < methodclass.length; i++) {
                        Annotationdebase base=po
			if  (methodclass[i].toString().toLowerCase().contains(recherche.toLowerCase())) {
				
				scdmpartie=scdmpartie.concat("'"+(String)methodclass[i].invoke(objet)+"'");
				
			}-*/
		
                String scdmpartie=" where "+recherche2;
                prempartie="UPDATE "+nomclassfinal.toUpperCase()+" SET "+changement;
		prempartie=prempartie.concat(scdmpartie);
		System.out.println(prempartie);
		Statement st=co.createStatement();
		
		st.execute(prempartie);
		return;

	}
	
	 public static String[] mamokainserttable(Connection co,String nomdetable) throws Exception {
		Statement st=co.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM "+nomdetable);
		ResultSetMetaData rsmd=rs.getMetaData();
		int nbColonnes=rsmd.getColumnCount();
	
		String[] typename =new String[nbColonnes]; 
		for (int i = 1; i <= nbColonnes; i++) {
			
			if (rsmd.getColumnTypeName(i).contentEquals("VARCHAR2")) {
				typename[i-1]="String";
			}else if (rsmd.getColumnTypeName(i).contentEquals("NUMBER")) {
				typename[i-1]="Int";
			}else if (rsmd.getColumnTypeName(i).toLowerCase().contains("date")) {
				typename[i-1]="DATE";
			}else {
				typename[i-1]=rsmd.getColumnTypeName(i);
			}
		}
	
		
		
		
		
		return typename;
		
	}
        
	 public  static Object[]  makaresulataanaselect(Object objet,String nomdetable) throws Exception{
                        Connection co=Connexion2.getConnection();
			Field[] nombreargumments=objet.getClass().getDeclaredFields();
			
			Method[]  methodclass=objet.getClass().getDeclaredMethods();
			String sql="SELECT * FROM "+nomdetable.toUpperCase();
			if (objet instanceof ObjetDao) {
				ObjetDao obj = (ObjetDao) objet;
				 sql="SELECT * FROM "+nomdetable.toUpperCase()+obj.getWhere();
				
			}
			
			Statement st1=co.createStatement();
			ResultSet rs1=null;
			if (objet instanceof ObjetDao) {
				ObjetDao obj = (ObjetDao) objet;
                                 System.out.println("SELECT * FROM "+nomdetable.toUpperCase()+obj.getWhere());
				 rs1=st1.executeQuery("SELECT * FROM "+nomdetable.toUpperCase()+obj.getWhere());
				 
			}
			
			int taille=0;
			while (rs1.next()) {
				taille++;
			}
                        
			         
			Statement st=co.createStatement();
			          System.out.println(sql);
			ResultSet rs=st.executeQuery(sql);
			Method[]  methodrs=rs.getClass().getMethods();
			
			
		
			
			Object[] tabobj= new Object[taille];
			String[] ilainahoannygetresultset=ObjetDao.mamokainserttable(co,nomdetable.toUpperCase());
			
			
			
			int i=0;
			int compteurvoampiditra=0;
			
			while (rs.next()) {
				String nomobj=objet.getClass().toString().substring(objet.getClass().toString().indexOf("class")+6);
				
				tabobj[i]=(Class.forName(nomobj).newInstance());
				
				
				for (int e = 0; e < nombreargumments.length;e++) {
					Annotationdebase primarykey1=nombreargumments[e].getDeclaredAnnotation(Annotationdebase.class);
					if (primarykey1.nomdelacolonne().length()>2) {
						String nomfield=nombreargumments[e].getType().toString().substring(nombreargumments[e].getClass().toString().indexOf("class")+6);
						
						Object obj=(Class.forName(nomfield).newInstance());
						String setname="set"+primarykey1.nomdelacolonne().toUpperCase().charAt(0)+primarykey1.nomdelacolonne().substring(1);
						
						Method[] setteur=obj.getClass().getMethods();
						for (int j = 0; j < setteur.length; j++) {
							if (setteur[j].toString().contains(setname)) {
                                                            System.out.println("setteur:"+setteur[j].toString()+":"+setname);
								setteur[j].invoke(obj,rs.getString(e+1) );
								
							}
						}
						for (int j = 0; j < methodclass.length; j++) {
                                                    String[] method=methodclass[j].toString().split("\\.");
                                                    
                                                    String methodfinal="";
                                                    if (method.length>4) {
                                                        methodfinal=method[method.length-3];
                                                    }else if(method.length<=2){
                                                        methodfinal=method[method.length-1];
                                                    }
                                                    
                                                    String nbd="set"+primarykey1.nomdelacolonne().toString().toLowerCase();
                                                    
							if (methodclass[j].toString().toLowerCase().contains("set"+nombreargumments[e].getName().toLowerCase()) && methodfinal.length()-nbd.length()<2) {
								
									ObjetDao new_name = (ObjetDao) obj;
									new_name.setWhere(" where "+primarykey1.nomdelacolonne()+"='"+rs.getString(e+1)+"'");
								
								Object[] essai=ObjetDao.makaresulataanaselect(new_name, primarykey1.nomtable());
								if (essai[0].getClass() == obj.getClass()) {
									
									//Class.forName(nomfield) new_name2 = (Class.forName(nomfield)) essai[0];
									
									methodclass[j].invoke(tabobj[i],essai[0]);
								}
								
								
								compteurvoampiditra++;
							}
						}
						
					}else if (nombreargumments[e].getType()==int.class) {
					
						for (int j = 0; j < methodclass.length; j++) {
							
							if (methodclass[j].toString().toLowerCase().contains("set"+nombreargumments[e].getName().toLowerCase())) {
								
								methodclass[j].invoke(tabobj[i],rs.getInt(e+1));
								
								compteurvoampiditra++;
							}
						}
					}
					if (nombreargumments[e].getType()==String.class) {
						
						for (int j = 0; j < methodclass.length; j++) {
							
							if (methodclass[j].toString().toLowerCase().contains("set"+nombreargumments[e].getName().toLowerCase()+"")) {
								
								methodclass[j].invoke(tabobj[i],rs.getString(e+1));
								compteurvoampiditra++;
							}
						}
					}
					if (nombreargumments[e].getType()==Date.class) {
						
						for (int j = 0; j < methodclass.length; j++) {
							if (methodclass[j].toString().toLowerCase().contains("set"+nombreargumments[e].getName().toLowerCase()+"")) {
								
								methodclass[j].invoke(tabobj[i],rs.getDate(e+1));
								compteurvoampiditra++;
							}
						}
					}
                                        if (nombreargumments[e].getType()==Timestamp.class) {
						
						for (int j = 0; j < methodclass.length; j++) {
							if (methodclass[j].toString().toLowerCase().contains("set"+nombreargumments[e].getName().toLowerCase()+"")) {
								
								methodclass[j].invoke(tabobj[i],rs.getTimestamp(e+1));
								compteurvoampiditra++;
							}
						}
					}
					if (nombreargumments[e].getType()==double.class) {
						System.out.println(nombreargumments[e].getName());
						for (int j = 0; j < methodclass.length; j++) {
							if (methodclass[j].toString().toLowerCase().contains("set"+nombreargumments[e].getName().toLowerCase()+"")) {
								DecimalFormat format = new DecimalFormat("#,###");
                                                               
                                                               
								methodclass[j].invoke(tabobj[i],Util.Util.arrondir(rs.getDouble(e+1)));
								compteurvoampiditra++;
							}
						}
					}
					
					
				
					}
				i++;
				}
                       
			
                        
			return tabobj;
			}
public static  void insererdansuneTable(Connection co,Object objet,String nomdebase) throws Exception {
                                    
		Field[] nombreargumments=objet.getClass().getDeclaredFields();
		Annotationdebase primarykey1=nombreargumments[0].getDeclaredAnnotation(Annotationdebase.class);
		String nomprimarykey="";
		int indiceprimarykey=0;
		for (int i = 0; i < nombreargumments.length; i++) {
			primarykey1=nombreargumments[i].getDeclaredAnnotation(Annotationdebase.class);
			if (primarykey1!=null) {
				
				if (primarykey1.isprimarykey() ==true) {
					
					nomprimarykey=nombreargumments[i].getName();
					indiceprimarykey=i;
					break;
				}
			}
			
		}
		Method[]  methodclass=objet.getClass().getDeclaredMethods();
		String sql="INSERT INTO "+objet.getClass().getSimpleName()+"(";
		String sql2="VALUES (";
		
		for (int i = 0; i < nombreargumments.length;i++) {
                   
			if (i==nombreargumments.length-1 && i!=indiceprimarykey) {
                            
                                                      Annotationdebase base= nombreargumments[i].getDeclaredAnnotation(Annotationdebase.class);
				sql=sql.concat(nombreargumments[i].getName()+") ");
				for (int j = 0; j < methodclass.length; j++) {
			if (base.nomdelacolonne().toString().length()>1&& methodclass[j].toString().toLowerCase().contains("get"+nombreargumments[i].getName())) {
                                                                                           System.out.println(base.nomdelacolonne());
                                                                                        String nomfield=nombreargumments[i].getType().toString().substring(nombreargumments[i].getClass().toString().indexOf("class")+6);
                                                                                                   
                                                                                                    Object obj=methodclass[j].invoke(objet);
                                                                                                    Method[] listfonction=nombreargumments[i].getType().getMethods();
                                                                                                    for (int k = 0; k < listfonction.length; k++) {
                                                                                                            
                                                                                                        if (listfonction[k].getName().toLowerCase().contains("get"+base.nomdelacolonne().toLowerCase())) {
                                                                                                           
                                                                                                            String idapetaka=(String)listfonction[k].invoke(obj);
                                                                                                            sql2=sql2.concat("'"+idapetaka+"')");
                                                                                                            break;
                                                                                                        }
                                                                                                        
                                                                                                }
                                                                            
                                                                                           }else{
                                        
					if (methodclass[j].toString().toLowerCase().contains("get"+nombreargumments[i].getName().toLowerCase())) {
						
					
						if (methodclass[j].invoke(objet) instanceof Integer==false && methodclass[j].invoke(objet) instanceof Date==false ) {
							
							if (i==indiceprimarykey) {
								sql2=sql2.concat("'"+objet.getClass().getSimpleName()+"'||"+methodclass[j].invoke(objet)+")");
							}
							else {
								sql2=sql2.concat("'"+methodclass[j].invoke(objet)+"')");
							}
						}else if (nombreargumments[i].getType()==Date.class) {
							String lol=methodclass[j].invoke(objet).toString();
							
							String[] datesep=lol.split("-");
							String finalstring="";
							for (int z = datesep.length-1; z >=0 ; z--) {
								
								if (z==0) {
									finalstring=finalstring.concat(datesep[z]);
								}else {
									finalstring=finalstring.concat(datesep[z]+"-");
								}
							}
							
							sql2=sql2.concat("'"+finalstring+"')");
						}
                                                else if (nombreargumments[i].getType()==Timestamp.class) {
							String lol=methodclass[j].invoke(objet).toString();
							System.out.println("ito oh"+lol);
							String[] datesep=lol.split("-");
							String finalstring="";
							for (int z = datesep.length-1; z >=0 ; z--) {
								
								if (z==0) {
									finalstring=finalstring.concat(datesep[z]);
								}else {
									finalstring=finalstring.concat(datesep[z]+"-");
								}
							}
							
							sql2=sql2.concat("'"+finalstring+"')");
						}else {
							sql2=sql2.concat("'"+methodclass[j].invoke(objet)+"')");
						}
						
					
						
						
					}
                                         }
				}
				
			}else 
                        if(i!=indiceprimarykey && i!=nombreargumments.length-1 ){
                                                          
                                                     Annotationdebase base= nombreargumments[i].getDeclaredAnnotation(Annotationdebase.class);
                                                                        
				sql=sql.concat(nombreargumments[i].getName()+",");
				
				for (int j = 0; j < methodclass.length; j++) {
                                    String[] method=methodclass[j].toString().split("\\.");
                                    String methodfinal=method[method.length-1];
                                    String nbd="get"+base.nomdelacolonne().toString().toLowerCase();
                                    System.out.println(base.nomdelacolonne().toString().length()+"------------"+"get"+base.nomdelacolonne().toString().toLowerCase()+ "----"+(methodfinal.length()-nbd.length()));
                                                                                  if (base.nomdelacolonne().toString().length()>5&& methodclass[j].toString().toLowerCase().contains("get"+base.nomdelacolonne().toString().toLowerCase())&& methodfinal.length()-nbd.length()<4 ) {
                                                                                                                       String nomfield=nombreargumments[i].getType().toString().substring(nombreargumments[i].getClass().toString().indexOf("class")+6);
                                                                                                   
                                                                                                    Object obj=methodclass[j].invoke(objet);
                                                                                                    System.out.println("Ato le olana tompoko oh"+nombreargumments[i].getType().toString());
                                                                                                    Method[] listfonction=nombreargumments[i].getType().getMethods();
                                                                                                    for (int k = 0; k < listfonction.length; k++) {
                                                                                                            
                                                                                                        if (listfonction[k].getName().toLowerCase().contains("get"+base.nomdelacolonne().toLowerCase())) {
                                                                                                           
                                                                                                            String idapetaka=(String)listfonction[k].invoke(obj);
                                                                                                            System.out.println(listfonction[k].invoke(obj));
                                                                                                            sql2=sql2.concat("'"+idapetaka+"',");
                                                                                                            break;
                                                                                                        }
                                                                                                        
                                                                                                }
                                                                            
                                                                                           }else{
					if (methodclass[j].toString().toLowerCase().contains("get"+nombreargumments[i].getName().toLowerCase() )&& methodfinal.length()-nombreargumments[i].getName().length()<7) {
                                            
                                           
						if (methodclass[j].invoke(objet) instanceof Double==false &&methodclass[j].invoke(objet) instanceof Integer==false && nombreargumments[i].getType()!=Date.class) {
							
							
							if (i==indiceprimarykey) {
                                                             
								sql2=sql2.concat("'"+objet.getClass().getSimpleName()+"'||"+methodclass[j].invoke(objet)+",");
							}else {
                                                             
								sql2=sql2.concat("'"+methodclass[j].invoke(objet)+"',");
							}
						}else if (nombreargumments[i].getType()==Date.class) {
							
							String lol=methodclass[j].invoke(objet).toString();
							String[] datesep=lol.split("-");
							String finalstring="";
							for (int z = datesep.length-1; z >=0 ; z--) {
								
								if (z==0) {
									finalstring=finalstring.concat(datesep[z]);
								}else {
									finalstring=finalstring.concat(datesep[z]+"-");
								}
							}
							
							sql2=sql2.concat("'"+finalstring+"',");
						} else if(nombreargumments[i].getType()==double.class) {
							
							sql2=sql2.concat(""+methodclass[j].invoke(objet)+",");
						}else {
							
							sql2=sql2.concat("'"+methodclass[j].invoke(objet)+"',");
						}
						
						
						
					}
                                                                                                            }
					
				
					
				}
				
			}else if(i==indiceprimarykey){
				if (i!=nombreargumments.length-1) {
					sql=sql.concat(nombreargumments[i].getName()+",");
					for (int j = 0; j < methodclass.length; j++) {
						
						if (methodclass[j].toString().toLowerCase().contains("get"+nombreargumments[i].getName().toLowerCase())) {
							char[] nomdetable2=objet.getClass().getSimpleName().toLowerCase().toCharArray();
							if (nomdebase.toLowerCase().contains("oracle")) {
							
								sql2=sql2.concat("'"+nomdetable2[0]+"'"+"||"+nomprimarykey+".nextval,");
							}else if (nomdebase.toLowerCase().contains("postgres")) {
								sql2=sql2.concat("'"+nomdetable2[0]+"'"+"||"+"nextval('"+nomprimarykey+"'),");
							}
							
							
						}
						
						
					}
				}else{
					sql=sql.concat(nombreargumments[i].getName()+")");
					for (int j = 0; j < methodclass.length; j++) {
						char[] nomdetable2=objet.getClass().getSimpleName().toLowerCase().toCharArray();
						if (methodclass[j].toString().toLowerCase().contains("get"+nombreargumments[i].getName().toLowerCase())) {
							if (nomdebase.toLowerCase().contains("oracle")) {
								sql2=sql2.concat("'"+nomdetable2[0]+"'"+"||"+nomprimarykey+".nextval)");
							}else if (nomdebase.toLowerCase().contains("postgres")) {
								sql2=sql2.concat("'"+nomdetable2[0]+"'"+"||"+"nextval('"+nomprimarykey+"'))");
							}
							
							
							
						}
						
						
					
				}
				
				
			}
				
			}
			
		}
		String reqfinal=sql+sql2;
		
		
		
		  System.out.println(reqfinal);
		Statement st=co.createStatement();
		
		st.execute(reqfinal);
                
	
		
		
	}
public static  String miprintyselect(String requete,Connection co,String nomdelatable) throws Exception{
	
	Statement state=co.createStatement();
	ResultSet rs=state.executeQuery(requete);
	
	ResultSetMetaData rsmd=rs.getMetaData();
	String[] type=mamokainserttable(co,nomdelatable);
	String all="<table>\r\n"
			+ "	<tr>\r\n"
			+ "		";
	
	int nbColonnes=rsmd.getColumnCount();
	
	for (int i = 1; i <= nbColonnes; i++) {
		String nomColonne=rsmd.getColumnName(i);
		String typeColonne=rsmd.getColumnTypeName(i);
		all=all.concat("<th>"+nomColonne+"</th>");
		
	}
	
	all=all.concat("</tr>\n");
	
	ObjetDao form=new ObjetDao();
	Method[] methods=form.getClass().getDeclaredMethods();
		int y=0;
		while (rs.next()) {
			all=all.concat("<tr>\r\n");
			 	for (int i = 0; i < nbColonnes; i++) {
			 		String nomColonne=rsmd.getColumnName(i+1);
			 		all=all.concat("<th>"+rs.getString(nomColonne)+"</th>\r\n");
			 	}
			y++;
			all=all.concat("</tr>\r\n");
		}
		
		all=all.concat("</table>");
		
			 	return all;
			
		
}

   
    public static void save(Object ray) throws Exception {
        ObjetDao.insererdansuneTable(Connexion2.getConnection(),ray,"postgres");
    }

  
    public void update(Object ray) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public void delete(Object ray) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ArrayList<Object> find(Object ray)throws Exception{
            
        Field[] fields = ray.getClass().getDeclaredFields();
        Method[] method = new Method[fields.length];
        Object[] value = new Object[fields.length];
        NomTable nomTable=(NomTable)ray.getClass().getAnnotation(NomTable.class);
        Attribut att=null;
        int countForeignkey=0;
        for(int i=0;i<fields.length;i++) {
            att=fields[i].getAnnotation(Attribut.class);
             if(att.isforeignkey()==true){
                countForeignkey++;
             }
            method[i] = ray.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
            value[i] = method[i].invoke(ray);
        }
        int[]fkey=null;
        if(countForeignkey>0){
            fkey=new int[countForeignkey];
            int count=0;
            for(int i=0;i<fields.length;i++) {
                    att=fields[i].getAnnotation(Attribut.class);
                    if(att.isforeignkey()==true){
                            fkey[count]=i;
                            count++;
                    }
            }
        }
        for(int i=0;i<fields.length;i++) {
            method[i] = ray.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
            value[i] = method[i].invoke(ray);
        }
        String req = "SELECT * FROM "+nomTable.nom();
        boolean setWhere = false;
        for(int i=0;i<value.length;i++) {
            if(i==0) {
                Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=0) {
                        
                        req += " WHERE "+atte.columnName()+"="+value[i];
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=0) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        req += " WHERE "+atte.columnName()+" like TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=0) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else {
                    if(value[i]!=null) {
                        if(value[i] instanceof String){
                                if (value[i].toString().contains("%")==false) {
                                    req += " WHERE "+atte.columnName()+"='"+value[i]+"' ";
                                }else{
                                    req += " WHERE "+atte.columnName()+" like '"+value[i]+"' ";
                                }
                        }else{
                            if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     String val=null;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(String)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     req += " WHERE "+atter.columnName()+"='"+val+"' ";
                                                }
                                            }
                                        }
                        }
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=0) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
            }
            if(i<value.length-1 && i>0) {
               Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=0) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=0) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<0) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"="+value[i];
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=0) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=0) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<0) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+" like TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=0) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=0) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<0) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        if(value[i] instanceof String){
                            if(value[i].toString().contains("%")==false){
                                req += atte.columnName()+"='"+value[i]+"' ";    
                            }else{
                                req += atte.columnName()+" like '"+value[i]+"' ";
                            } 
                        }else{
                             if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     String val=null;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(String)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     req +=atter.columnName()+"='"+val+"' ";
                                                }
                                            }
                                        }
                        }
                        
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=0) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
            }
            if(i==value.length-1) {
                Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=0) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=0) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<0) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"="+value[i];
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=0) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<0) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"  like TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                    }
                }
                else {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=0) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<0) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        if(value[i] instanceof String){
                                if (value[i].toString().contains("%")==false) {
                                req += atte.columnName()+"='"+value[i]+"'";
                            }else{
                                req += atte.columnName()+" like '"+value[i]+"'";
                            }
                        }else{
                            if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     String val=null;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(String)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     req +=atter.columnName()+"='"+val+"' ";
                                                }
                                            }
                                        }
                        }
                    }
                }
            }
        }
        try{
            Connection c=Connexion2.getConnection();
        }catch(Exception e){
            throw e;
        }
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        int nb = 0;
        while(res.next()) {
            nb++;
        }
        Object[] retour = new Object[nb];
        ResultSet res2 = stmt.executeQuery(req);
        int indice = 0;
        int id = 1;
        while(res2.next()) {
            retour[indice] = ray.getClass().getDeclaredConstructor().newInstance();
            for(int i=0;i<method.length;i++) {
                Class[] classe = new Class[1];
                classe[0] = fields[i].getType();
                Attribut roa=fields[i].getAnnotation(Attribut.class);
                if(classe[0].getSimpleName().equals("int")){
                    ray.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getInt(id));
                }else if(classe[0].getSimpleName().equals("double")){
                     ray.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getDouble(id));
                }else if(classe[0].getSimpleName().equals("String")){
                    ray.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getString(id));
                }else if(classe[0].getSimpleName().equals("Date")){
                     ray.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getDate(id));
                }else{
                    if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(fields[i].getType().getSimpleName())==0){
                                                    Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                    Object temp=fields[fkey[j]].getType().getDeclaredConstructor().newInstance();
                                                    String exemple="mechant";
                                                    Object obj=new Object();
                                                    Field[]foreign=temp.getClass().getDeclaredFields();
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                               temp.getClass().getMethod("set"+this.toUpperFirst(foreign[k].getName()),exemple.getClass()).invoke(temp,res2.getString(id));
                                                                        }
                                                                    }
                                                    Object finalise= new ObjetDao().find(temp).get(0);
                                                    ray.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),obj.getClass()).invoke(retour[indice],finalise);
                                                }
                                            }
                                        }
                }
                id++;
            }
            id=1;
            indice++;
        }
        ArrayList<Object>obj=new ArrayList<Object>();
        if(retour.length>0){
            for (int i = 0; i <retour.length; i++) {
                obj.add(retour[i]);
            }
        }
        if(stmt!=null) stmt.close();
        if(res!=null) res.close();
        if(res2!=null) res2.close();
        if(c!=null) c.close();
        return obj;
    }

   
    public ArrayList<Object> pagination(String ray, int page, int nbPage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
