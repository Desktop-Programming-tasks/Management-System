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
            ServiceType st = new ServiceType("whatever2", 1550);
            DAOBuilder.getInstance().getServiceTypeDAO().insertServiceType(st);
        } catch (DatabaseErrorException | DuplicatedEntryException e) {
            e.printStackTrace();
        }

    }

}
