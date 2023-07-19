/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekena
 */
@Component
public class Connexion2 {
    public static Connection con = null;
	  
	   
	    public static Connection getConnection()
	    {	 
	        
	       
	        
                               try {
	            Class.forName("org.postgresql.Driver");
	            con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinique","postgres","ashisbae");
                    con.setAutoCommit(true);
	            return con;
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    
	        return con;
	    }
}
