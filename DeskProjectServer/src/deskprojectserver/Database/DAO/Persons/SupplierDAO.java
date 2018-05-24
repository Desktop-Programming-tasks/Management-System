/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.Supplier;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class SupplierDAO {
    public abstract void insertSupplier(Supplier supplier) throws Exception;
    public abstract void updateSupplier(Supplier supplier) throws Exception;
    public abstract void removeSupplier(Supplier supplier) throws Exception;
    public abstract Supplier getSupplier(String id) throws Exception;
    public abstract ArrayList<Supplier> getAllSuppliers() throws Exception;
}
