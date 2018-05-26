/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Service;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class ServiceDAO {
    public abstract void insertService(Service service) throws Exception;
    public abstract void updateService(Service service) throws Exception;
    public abstract void removeService(Service service) throws Exception;   
    public abstract Service getService(String id) throws Exception;
    public abstract ArrayList<Service> getAllServices() throws Exception;
}
