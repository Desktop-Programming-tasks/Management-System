/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Transactions;

import java.io.Serializable;

/**
 *
 * @author gabriel
 */
public class Product extends Transaction implements Serializable {

    private int id;
    private String barCode;
    private int quantityInStock;
    private Brand brand;
    private boolean active;
    
    public Product(String barCode) {
        this.barCode = barCode;
    }

    public Product(String barCode, Brand brand, float price, String name) {
        super(price, name);
        this.barCode = barCode;
        this.brand = brand;
    }

    public Product(int id, String barCode, Brand brand, float price, String name) {
        super(price, name);
        this.id = id;
        this.barCode = barCode;
        this.brand = brand;
    }

    public Product(int id, String barCode, int quantityInStock, Brand brand, boolean active, float price, String name) {
        super(price, name);
        this.id = id;
        this.barCode = barCode;
        this.quantityInStock = quantityInStock;
        this.brand = brand;
        this.active = active;
    }
    
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "Product{name=" + getName() + " barCode=" + barCode + ", quantityInStock=" + quantityInStock + ", brand=" + brand + '}';
    }
}
