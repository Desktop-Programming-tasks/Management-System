/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.Classes.Persons.LegalPerson;
import deskprojectserver.Database.Database;
import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class DeskProjectServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            ArrayList al = new ArrayList();
            al.add("500-5555");
            al.add("500-5555");
            LegalPerson lp = new LegalPerson("10"   , "Gabriel", null, al, "222-333");
            MySqlPersonDAO  p= new MySqlPersonDAO();
            //p.insertPerson(lp);
            JuridicalPerson jp= new  JuridicalPerson("Gabriel Empresa",
                    null, al, "53-4444");
            p.insertPerson(jp);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
