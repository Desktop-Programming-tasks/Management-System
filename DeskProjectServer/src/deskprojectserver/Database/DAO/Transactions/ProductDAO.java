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

    public abstract void insertProduct(Product product) throws UnavailableBrandException,DatabaseErrorException, DuplicatedEntryException;

    public abstract void updateProduct(Product product) throws UnavailableBrandException,DatabaseErrorException, NoResultsException,DuplicatedEntryException;

    public abstract void removeProduct(Product product) throws DatabaseErrorException, NoResultsException;

    public abstract Product getProduct(String id) throws DatabaseErrorException, NoResultsException;

    public abstract ArrayList<Product> getAllProducts() throws DatabaseErrorException;
}
