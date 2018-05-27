/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Persons.Address;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;
import deskprojectserver.Database.DAO.Persons.SupplierDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlSupplierDAO;
import deskprojectserver.mysql.MySqlHandler;
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
        try {
            Address address = new Address("lslsl", 10, "whatever", "Acrelândia", "Acre");
            ArrayList<String> telephones = new ArrayList<>();
            telephones.add("43 3528 2515");
            telephones.add("45 3528 2524");
            LegalPerson lp = new LegalPerson("15", "batatao", address, telephones, "254");
            LegalPerson lpu= new LegalPerson("20", "batatao atualizado", address, telephones, "254");
            //DAOBuilder.getInstance().getPersonDAO().insertPerson(lp);
            DAOBuilder.getInstance().getPersonDAO().updatePerson(lpu);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
