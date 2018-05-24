/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import deskprojectserver.Classes.Persons.Address;
import deskprojectserver.Classes.Persons.Employee;
import deskprojectserver.Enums.EmployeeType;
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
        //Employee emp = new Employee("test", "", 1, "43434343434", "batatao", address, telephones, "889898898435");
        Employee emp = new Employee("123", "", EmployeeType.MANAGER, "123112", "batata", address, telephones, "732812783744");
        
        //DAOBuilder.getInstance().getPersonDAO().insertPerson(emp);
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry("Localhost", 1099);
            ServerMethods rmiChannel = (ServerMethods) rmiRegistry.lookup("RMI_BD_Server");

            rmiChannel.insertPerson(emp);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(RMIClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
