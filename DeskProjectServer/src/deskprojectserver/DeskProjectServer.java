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
            //ServiceType st = new ServiceType("batata311", 10);
            //ServiceType st2 = new ServiceType("batata2", 120);

            //DAOBuilder.getInstance().getServiceTypeDAO().insertServiceType(st);
            //DAOBuilder.getInstance().getServiceTypeDAO().insertServiceType(st2);
//            for (ServiceType stQ : DAOBuilder.getInstance().getServiceTypeDAO().getAllServiceTypes()) {
//                System.out.println(stQ.getName());
//                System.out.println(stQ.getPrice());
//                System.out.println(stQ.getId());
//            }
            ServiceType st = DAOBuilder.getInstance().getServiceTypeDAO().getServiceType("batata2");
            System.out.println(st.getName());
            System.out.println(st.getPrice());
            System.out.println(st.getId());
            st.setPrice(200321312);
            DAOBuilder.getInstance().getServiceTypeDAO().updateServiceType(st);
        } catch (DatabaseErrorException e) {
            e.printStackTrace();
        } catch (NoResultsException ex) {
            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DuplicatedEntryException ex) {
            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
