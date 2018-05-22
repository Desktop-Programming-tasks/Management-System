/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

import desktoproject.Model.Classes.Persons.Supplier;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class SupplierDAO {
    public abstract void insertSupplier(Supplier supplier);
    public abstract void updateSupplier(Supplier supplier);
    public abstract void removeSupplier(Supplier supplier);
    public abstract Supplier getSupplier(String id);
    public abstract ArrayList<Supplier> getAllSuppliers();
}
