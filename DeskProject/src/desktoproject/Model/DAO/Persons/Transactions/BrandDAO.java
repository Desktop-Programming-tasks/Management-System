/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons.Transactions;

import desktoproject.Model.Classes.Transactions.Brand;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class BrandDAO {
    public abstract void insertBrand(Brand brand);
    public abstract void updateBrand(Brand brand);
    public abstract void removeBrand(Brand brand);
    public abstract Brand getBrand(String id);
    public abstract ArrayList<Brand>  getAllBrands();    
}
    