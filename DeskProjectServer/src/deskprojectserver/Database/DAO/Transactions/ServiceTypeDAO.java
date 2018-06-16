/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class ServiceTypeDAO {

    protected abstract void insertBasicServiceType(ServiceType st) throws DatabaseErrorException, DuplicatedEntryException;

    public abstract void updateServiceType(ServiceType st) throws DatabaseErrorException, DuplicatedEntryException;

    public abstract ServiceType getServiceType(String id) throws DatabaseErrorException, NoResultsException;

    public abstract ArrayList<ServiceType> getAllServiceTypes() throws DatabaseErrorException;

    public abstract ArrayList<ServiceType> getLikeServiceTypes(String id) throws DatabaseErrorException;

    public void inactivateServiceType(ServiceType st) throws DatabaseErrorException {
        st.setActive(false);
        try {
            updateServiceType(st);
        } catch (DuplicatedEntryException e) {
            throw new DatabaseErrorException();
        }
    }

    public void insertServiceType(ServiceType st) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            insertBasicServiceType(st);
        } catch (DuplicatedEntryException e) {
            try {
                ServiceType temp = getServiceType(st.getName());
                if (temp.isActive()) {
                    throw e;
                } else {
                    st.setId(temp.getId());
                    st.setActive(true);
                    updateServiceType(st);
                }
            } catch (NoResultsException ex) {
                //
            }
        }
    }
}
