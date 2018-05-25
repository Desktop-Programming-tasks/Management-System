/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import deskprojectserver.Classes.Transactions.Brand;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class BrandDAO {
    public abstract void insertBrand(Brand brand) throws Exception;
    public abstract void updateBrand(Brand brand) throws Exception;
    public abstract void removeBrand(Brand brand) throws Exception;
    public abstract Brand getBrand(String id) throws Exception;
    public abstract ArrayList<Brand>  getAllBrands() throws Exception;    
}
    