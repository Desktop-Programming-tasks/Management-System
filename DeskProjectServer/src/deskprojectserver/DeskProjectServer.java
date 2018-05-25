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
import deskprojectserver.Database.DAO.Persons.DAOBuilder;
import deskprojectserver.Enums.EmployeeType;
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
        Address address= new Address("rua do caralho", 20, "block", "Acrelândia", "Acre");
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("111-111");
        telephones.add("222-222");
        try {
            Person emp=new Employee("batata", "123", EmployeeType.COMMOM, "52525", "ze cebola", 
                    address, telephones, "8521");
            MySqlPersonDAO dao = new MySqlPersonDAO();
            //dao.insertPerson(emp);
            Person p = dao.getPerson("5252");  
            System.out.println((LegalPerson) p);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
