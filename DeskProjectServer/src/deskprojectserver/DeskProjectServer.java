/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import deskprojectserver.Classes.Persons.Address;
import deskprojectserver.Classes.Persons.Employee;
import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.Classes.Persons.LegalPerson;
import deskprojectserver.Classes.Persons.Supplier;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;
import deskprojectserver.mysql.DAO.Persons.MySqlLegalPersonDAO;
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
        try {
            MySqlPersonDAO dao = new MySqlPersonDAO();
            System.out.println(dao.basicGetPerson("555-222"));          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
