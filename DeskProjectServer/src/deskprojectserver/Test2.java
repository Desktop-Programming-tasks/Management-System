/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Enums.EmployeeType;
import Classes.Persons.Address;
import Classes.Persons.Employee;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.LegalPerson;
import Classes.Persons.Supplier;
import Classes.Transactions.Brand;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Test2 {

    public static void main(String[] args) {
        try {
            Address address = new Address("lslsl", 10, "whatever", "Acrelândia", "Acre");
            ArrayList<String> telephones = new ArrayList<>();
            telephones.add("dasds");
            telephones.add("dsadas");
            
            Employee emp = new Employee("Teste", "1234", EmployeeType.MANAGER, "15",
                    "Gabriel", address, telephones, "251");
            ArrayList<Brand> brands = new ArrayList<>();
            brands.add(new Brand("AMD"));
            brands.add(new Brand("NVIDIA"));
            brands.add(new Brand("Intel"));
            Supplier sup = new Supplier(brands, "Supplier", address, telephones, "21");
            
            JuridicalPerson jp = new JuridicalPerson("Teste Again", address, telephones, "25213");
            
            LegalPerson lp = new LegalPerson("15", "batatao", address, telephones, "254");
            DAOBuilder.getInstance().getPersonDAO().insertPerson(sup);
            //DAOBuilder.getInstance().getPersonDAO().removePerson(sup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
