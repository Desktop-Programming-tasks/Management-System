/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import deskprojectserver.Classes.Persons.Address;
import deskprojectserver.Classes.Persons.Employee;
<<<<<<< HEAD
import deskprojectserver.Classes.Persons.LegalPerson;
=======
>>>>>>> b47cd83134db4b592927c7968f77ff84f2eaad5c
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.Classes.Persons.Supplier;
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

        try {
<<<<<<< HEAD
            Address address = new Address("lslsl", 10, "whatever", "Acrelândia", "Acre");
            ArrayList<String> telephones = new ArrayList<>();
            telephones.add("dasds");
            telephones.add("dsadas");
//            LegalPerson lp = new LegalPerson("10","batata",address,telephones,"2");
//            Employee emp = new Employee("teste", "123", EmployeeType.MANAGER, "15", 
//                    "batata func", address, telephones, "1");
           Supplier lp = new Supplier(null, "batata", address, telephones, "4");
            DAOBuilder.getInstance().getPersonDAO().insertPerson(lp);
            //Juridical
            Person p = DAOBuilder.getInstance().getPersonDAO().getPerson("4");
            System.out.println(p);
            //Legal
            //p= DAOBuilder.getInstance().getPersonDAO().getPerson("2");
            //System.out.println(p);
            //Employee
            //p= DAOBuilder.getInstance().getPersonDAO().getPerson("3");
            //System.out.println(p);
            //Supplier
            //p=DAOBuilder.getInstance().getPersonDAO().getPerson("4");
            //System.out.println(p);
=======
//            //Juridical
//            Person p = DAOBuilder.getInstance().getPersonDAO().getPerson("123");
//            System.out.println(p);
//            //Legal
//            p= DAOBuilder.getInstance().getPersonDAO().getPerson("5252");
//            System.out.println(p);
//            //Employee
//            p= DAOBuilder.getInstance().getPersonDAO().getPerson("8521");
//            System.out.println(p);
//            //Supplier
//            p=DAOBuilder.getInstance().getPersonDAO().getPerson("121");
//            System.out.println(p);

        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("888898989");
        telephones.add("888898954");
        Address address = new Address("Rua das rosas", 100, "batata", "Abadiânia", "Goiás");
        //Employee emp = new Employee("test", "", 1, "43434343434", "batatao", address, telephones, "889898898435");
        Employee emp = new Employee("123", "", EmployeeType.MANAGER, "123112", "batata", address, telephones, "732812783744");
        Supplier sup = new Supplier(new ArrayList<>(), "Batata Supplier", address, telephones, "89587345345");
        
        //DAOBuilder.getInstance().getPersonDAO().insertPerson(sup);
>>>>>>> b47cd83134db4b592927c7968f77ff84f2eaad5c
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
