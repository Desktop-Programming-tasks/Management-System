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
            ArrayList al = new ArrayList();
            al.add("500-5555");
            al.add("500-5555");
            Address address = new Address("Tomar no cu", 20, "Cu do mundo","Acrelândia", "Acre");
            
            MySqlPersonDAO p = new MySqlPersonDAO();
            LegalPerson lp = new LegalPerson("10", "Gabriel", address, al, "222-3133");
            JuridicalPerson jp = new JuridicalPerson("Gabriel Empresa",
                    address, al, "53-44414");
            Employee emp = new Employee("batata", "1234", 1, 
                    "10 10 ", "Gabriel funcionario", address, al, "000 111 22122");
            //p.insertPerson(emp);
            p.insertPerson(jp);
            p.insertPerson(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
