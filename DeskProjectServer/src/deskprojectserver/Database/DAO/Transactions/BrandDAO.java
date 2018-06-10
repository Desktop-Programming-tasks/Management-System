/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class BrandDAO {

    public void insertBrand(Brand brand) throws DatabaseErrorException {
        try {
            insertBrandBasic(brand);
        } catch (DuplicatedEntryException ex) {
            try {
                System.out.println(brand.getName());
                Brand auxBrand = getBrand(brand.getName(), false);
                brand.setId(auxBrand.getId());
                brand.setActive(true);
                updateBrand(brand);
            } catch (NoResultsException ex1) {
                System.out.println("asdadasas");
            }
        }
    }

    public abstract void insertBrandBasic(Brand brand) throws DatabaseErrorException, DuplicatedEntryException;

    protected abstract Brand getBrand(String id, boolean justActive) throws DatabaseErrorException, NoResultsException;

    public abstract void updateBrand(Brand brand) throws DatabaseErrorException, NoResultsException;

    public void removeBrand(Brand brand) throws DatabaseErrorException, NoResultsException {
        brand.setActive(false);
        updateBrand(brand);
    }

    public abstract void checkIfExists(Brand brand) throws DatabaseErrorException, NoResultsException;

    public abstract ArrayList<Brand> getAllBrands() throws DatabaseErrorException;
}
