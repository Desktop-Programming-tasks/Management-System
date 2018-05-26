/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;


import Classes.Enums.EmployeeType;
import Classes.Persons.Address;
import Classes.Persons.Employee;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import RMI.ServerMethods;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ecsanchesjr
 */
public class RMIClientTest {

    public static void main(String[] args) throws Exception {
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("888898989");
        telephones.add("888898954");
        Address address = new Address("Rua das rosas", 100, "batata", "Abadiânia", "Goiás");
        Employee emp = new Employee("123", "", EmployeeType.MANAGER, "123112", "batata", address, telephones, "employee");
        Supplier sup = new Supplier(new ArrayList<>(), "Batata Supplier", address, telephones, "supplier");
        JuridicalPerson jurP = new JuridicalPerson("Batata jurídico", address, telephones, "juridicalPerson");
        LegalPerson legP = new LegalPerson("34124342","Batata legal", address, telephones, "legalPerson");
        
        //DAOBuilder.getInstance().getPersonDAO().insertPerson(emp);
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry("Localhost", 1099);
            ServerMethods rmiChannel = (ServerMethods) rmiRegistry.lookup("RMI_BD_Server");

            
            System.out.println("Inserções");
            System.out.println("Empregado");
            rmiChannel.insertPerson(emp);
            System.out.println("Juridico");
            rmiChannel.insertPerson(jurP);
            System.out.println("Legal");
            rmiChannel.insertPerson(legP);
            System.out.println("Supplier");
            rmiChannel.insertPerson(sup);

            System.out.println("Gets");
            System.out.println("Employee");
            Person employee = rmiChannel.queryPerson("employee");
            System.out.println(employee);
            System.out.println("Juridical");
            Person juridical = rmiChannel.queryPerson("juridicalPerson");
            System.out.println(juridical);
            System.out.println("Legal");
            Person legal = rmiChannel.queryPerson("legalPerson");
            System.out.println(legal);
            
            
            //System.out.println("GetAll");
            //ArrayList<Person> persons = rmiChannel.querryAllPersons();
            //System.out.println(persons);
            
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(RMIClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
