/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import desktoproject.Model.Classes.Transactions.ServiceType;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class ServiceTypeDAO {
    public abstract void insertServiceType(ServiceType st) throws Exception;
    public abstract void updateServiceType(ServiceType st) throws Exception;
    public abstract void removeServiceType(ServiceType st) throws Exception;
    public abstract ServiceType getServiceType(String id) throws Exception;
    public abstract ArrayList<ServiceType> getAllServiceTypes() throws Exception;
    
}
