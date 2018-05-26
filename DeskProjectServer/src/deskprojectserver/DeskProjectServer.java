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
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.Classes.Persons.Supplier;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.NoResultsException;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;
import deskprojectserver.Enums.EmployeeType;
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

        Address address = new Address("lslsl", 10, "whatever", "Acrelândia", "Acre");
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("dasds");
        telephones.add("dsadas");
//            LegalPerson lp = new LegalPerson("10","batata",address,telephones,"2");
//            Employee emp = new Employee("teste", "123", EmployeeType.MANAGER, "15", 
//                    "batata func", address, telephones, "1");
        //DAOBuilder.getInstance().getPersonDAO().insertPerson(lp);
        //Juridical
        JuridicalPerson jp = new JuridicalPerson("teste", address, telephones, "3");
        try {
            DAOBuilder.getInstance().getPersonDAO().insertPerson(jp);
            Person p = DAOBuilder.getInstance().getPersonDAO().getPerson("3");
            System.out.println(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
