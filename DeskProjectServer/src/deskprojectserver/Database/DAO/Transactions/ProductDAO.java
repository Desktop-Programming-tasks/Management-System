/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.UnavailableBrandException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class ProductDAO {

    public void insertProduct(Product product) throws UnavailableBrandException, DatabaseErrorException, DuplicatedEntryException {
        try {
            insertProductBasic(product);
        } catch (DuplicatedEntryException e) {
            try {
                Product aux = getProduct(product.getBarCode(),false);
                if (aux.isActive()) {
                    throw e;
                } else {
                    product.setId(aux.getId());
                    product.setActive(true);
                    updateProduct(product);
                }
            } catch (NoResultsException ex) {
                //
            }
        }
    }

    protected abstract void insertProductBasic(Product product) throws UnavailableBrandException, DatabaseErrorException, DuplicatedEntryException;

    public abstract void updateProduct(Product product) throws UnavailableBrandException, DatabaseErrorException, NoResultsException, DuplicatedEntryException;

    public abstract Product getProduct(String id, boolean justActive) throws DatabaseErrorException, NoResultsException;

    public abstract ArrayList<Product> getAllProducts() throws DatabaseErrorException;

    public abstract ArrayList<Product> getLikeProducts(String id) throws DatabaseErrorException;

    public void inactivateProduct(Product product) throws UnavailableBrandException, DatabaseErrorException, NoResultsException, DuplicatedEntryException {
        product.setActive(false);
        updateProduct(product);

    }
}
