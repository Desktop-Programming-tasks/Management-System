/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Transactions;

import desktoproject.Model.Classes.Transactions.Product;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class ProductDAO {
    public abstract void insertProduct(Product product);
    public abstract void updateProduct(Product product);
    public abstract void removeProduct(Product product);
    public abstract Product getProduct(String id);
    public abstract ArrayList<Product> getAllProducts();               
}
