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
import deskprojectserver.Utils.ActivationStatus;
import deskprojectserver.Utils.FormatUtils;
import deskprojectserver.Utils.QueryExecuter;
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

    private static final String ID = "idProduct";
    private static final String BAR_CODE = "barCodeProduct";
    private static final String NAME = "nameProduct";
    private static final String PRICE = "priceProduct";
    private static final String QUANTITY = "quantityProduct";
    private static final String BRAND_NAME = "Brand_nameBrand";
    private static final String IS_ACTIVE="isActiveProduct";
    private static final String INSERT_SQL = "INSERT INTO `Product`"
            + "(`barCodeProduct`, `nameProduct`, `priceProduct`, `quantityProduct`, "
            + "`Brand_nameBrand`, `isActiveProduct`)"
            + " VALUES (?,?,?,?,?,?)";

    private static final String GET_ALL_SQL = "SELECT `idProduct`,`barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE isActiveProduct";

    private static final String GET_ONE_SQL = "SELECT `idProduct`,`barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE ((barCodeProduct=? OR idProduct=?) AND isActiveProduct)";

    private static final String GET_ONE_SQL_INACTIVE = "SELECT `idProduct`,`barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE (barCodeProduct=? OR idProduct=?)";

    private static final String GET_LIKE_SQL = "SELECT `idProduct`,`barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE nameProduct LIKE ? AND isActiveProduct";

    private static final String UPDATE_SQL = ""
            + "UPDATE `Product`"
            + " SET `barCodeProduct`=?,`nameProduct`=?,"
            + "`priceProduct`=?,`quantityProduct`=?,`"
            + "Brand_nameBrand`=?,`isActiveProduct`=? WHERE idProduct=?";

    @Override
    public void updateProduct(Product product) throws DatabaseErrorException, NoResultsException, DuplicatedEntryException, UnavailableBrandException {
        try {
            getProduct(Integer.toString(product.getId()),false);
        } catch (NoResultsException e) {
            throw e;
        }
        try {
            BrandDAO brand = new MySqlBrandDAO();
            brand.checkIfExists(product.getBrand());
        } catch (NoResultsException e) {
            throw new UnavailableBrandException();
        }
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL, product.getBarCode(),
                    product.getName(), product.getPrice(), product.getQuantityInStock(),
                    product.getBrand().getName(), product.isActive() ? ActivationStatus.ACTIVE_STATE
                    : ActivationStatus.INACTIVE_STATE, product.getId());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void insertProductBasic(Product product) throws DatabaseErrorException, DuplicatedEntryException, UnavailableBrandException {
        BrandDAO bdao = new MySqlBrandDAO();
        try {
            bdao.checkIfExists(product.getBrand());
        } catch (DatabaseErrorException | NoResultsException e) {
            throw new UnavailableBrandException();
        }
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    product.getBarCode(), product.getName(), product.getPrice(),
                    product.getQuantityInStock(), product.getBrand().getName(),
                    ActivationStatus.ACTIVE_STATE);
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Product getProduct(String id, boolean justActive) throws DatabaseErrorException, NoResultsException {
        Product product = null;
        QueryResult qr;
        try {

            if (justActive) {
                qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id,
                        Integer.parseInt(id));
            } else {
                qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL_INACTIVE, id,
                        Integer.parseInt(id));
            }
            while (qr.getResultSet().next()) {
                product = new Product(qr.getResultSet().getInt(ID),
                        qr.getResultSet().getString(BAR_CODE),
                        new Brand(qr.getResultSet().getString(BRAND_NAME)),
                        qr.getResultSet().getFloat(PRICE),
                        qr.getResultSet().getString(NAME));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
        if (product == null) {
            throw new NoResultsException();
        }
        return product;
    }

    @Override
    public ArrayList<Product> getAllProducts() throws DatabaseErrorException {
        return getProductsGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }

    @Override
    public ArrayList<Product> getLikeProducts(String id) throws DatabaseErrorException {
        return getProductsGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_LIKE_SQL,
                            FormatUtils.setLikeParam(id));
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }

    private ArrayList<Product> getProductsGeneric(QueryExecuter exec) throws DatabaseErrorException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            QueryResult qr = exec.execute();
            while (qr.getResultSet().next()) {
                Product product = new Product(qr.getResultSet().getInt(ID),
                        qr.getResultSet().getString(BAR_CODE),
                        new Brand(qr.getResultSet().getString(BRAND_NAME)),
                        qr.getResultSet().getFloat(PRICE), qr.getResultSet().getString(NAME));
                product.setQuantityInStock(qr.getResultSet().getInt(QUANTITY));
                product.setActive(qr.getResultSet().getBoolean(IS_ACTIVE));
                products.add(product);
            }
            qr.closeAll();
        } catch (SQLException e) {
            throw new DatabaseErrorException();
        }
        return products;
    }
}
