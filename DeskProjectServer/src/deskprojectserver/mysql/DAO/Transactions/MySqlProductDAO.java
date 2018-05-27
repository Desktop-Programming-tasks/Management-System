/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.UnavailableBrandException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.Database.DAO.Transactions.ProductDAO;
import deskprojectserver.mysql.Commons.MySqlBrandDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlProductDAO extends ProductDAO {

    private static final String INSERT_SQL = "INSERT INTO `Product`(`barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand`) "
            + "VALUES (?,?,?,?,?)";

    @Override
    public void insertProduct(Product product) throws DatabaseErrorException, DuplicatedEntryException, UnavailableBrandException {
        BrandDAO bdao = new MySqlBrandDAO();
        try {
            bdao.checkIfExists(product.getBrand());
        } catch (DatabaseErrorException | NoResultsException e) {
            throw new UnavailableBrandException();
        }
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    product.getBarCode(), product.getName(), product.getPrice(),
                    product.getQuantityInStock(), product.getBrand().getName());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateProduct(Product product) throws DatabaseErrorException, NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeProduct(Product product) throws DatabaseErrorException, NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(String id) throws DatabaseErrorException, NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> getAllProducts() throws DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
