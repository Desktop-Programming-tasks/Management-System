/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.Classes.Transactions;

/**
 *
 * @author gabriel
 */
public class Product extends Transaction {

    private String barCode;
    private int quantityInStock;
    private Brand brand;

    public Product(String barCode) {
        this.barCode = barCode;
    }

    public Product(String barCode, Brand brand, float price, String name) {
        super(price, name);
        this.barCode = barCode;
        this.brand = brand;
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

    @Override
    public String toString() {
        return "Product{" + "barCode=" + barCode + ", quantityInStock=" + quantityInStock + ", brand=" + brand + '}';
    }
}
