/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import deskprojectserver.Database.DAOBuilder;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            for(ServiceType st : DAOBuilder.getInstance().getServiceTypeDAO().getAllServiceTypes()){
                System.out.println(st.getName());
                System.out.println(st.getPrice());
            }
        } catch (DatabaseErrorException e) {
            e.printStackTrace();
        }

    }

}
