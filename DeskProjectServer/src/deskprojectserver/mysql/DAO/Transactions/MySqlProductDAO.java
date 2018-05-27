/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Transactions.Brand;
import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.UnavailableBrandException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.Database.DAO.Transactions.ProductDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.Commons.MySqlBrandDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlProductDAO extends ProductDAO {

    private static final String BAR_CODE = "barCodeProduct";
    private static final String NAME = "nameProduct";
    private static final String PRICE = "priceProduct";
    private static final String QUANTITY = "quantityProduct";
    private static final String BRAND_NAME = "Brand_nameBrand";
    private static final String INSERT_SQL = "INSERT INTO `Product`(`barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand`) "
            + "VALUES (?,?,?,?,?)";

    private static final String GET_ALL_SQL = "SELECT `barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE 1";

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
        ArrayList<Product> products = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
            while (qr.getResultSet().next()) {
                Product product = new Product(
                        qr.getResultSet().getString(BAR_CODE),
                        new Brand(qr.getResultSet().getString(BRAND_NAME)),
                        qr.getResultSet().getInt(PRICE), qr.getResultSet().getString(NAME));
                product.setQuantityInStock(qr.getResultSet().getInt(QUANTITY));
                products.add(product);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return products;
    }
}
