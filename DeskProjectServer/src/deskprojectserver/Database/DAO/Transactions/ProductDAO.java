/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import deskprojectserver.Classes.Transactions.Product;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class ProductDAO {
    public abstract void insertProduct(Product product) throws Exception;
    public abstract void updateProduct(Product product) throws Exception;
    public abstract void removeProduct(Product product) throws Exception;
    public abstract Product getProduct(String id) throws Exception;
    public abstract ArrayList<Product> getAllProducts() throws Exception;               
}
