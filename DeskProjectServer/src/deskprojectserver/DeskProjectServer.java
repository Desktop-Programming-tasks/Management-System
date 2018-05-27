/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Enums.EmployeeType;
import Classes.Persons.Address;
import Classes.Persons.Employee;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Classes.Transactions.Brand;
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
            ArrayList<Brand> brands = new ArrayList<>();
            //brands.add(new Brand("AMD"));
            brands.add(new Brand("NVIDIA"));
            Address address = new Address("lslsl", 10, "whatever", "Acrelândia", "Acre");
            ArrayList<String> telephones = new ArrayList<>();
            telephones.add("43 3528 2515");
            telephones.add("45 3528 2524");
            Employee emp = new Employee("login update", "12345", EmployeeType.MANAGER, "95",
                    "Gabriel de Abreu", address, telephones, "func cpf");
            Supplier sup = new Supplier(brands, "Fornecedor fuck", address, telephones, "2424");
            //DAOBuilder.getInstance().getPersonDAO().insertPerson(sup);
            DAOBuilder.getInstance().getPersonDAO().updatePerson(sup);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
