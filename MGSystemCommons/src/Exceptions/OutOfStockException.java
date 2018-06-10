/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import Classes.Transactions.Product;

/**
 *
 * @author gabriel
 */
public class OutOfStockException extends Exception{
    private Product outProduct;
    public OutOfStockException(Product product) {
        super("Out of Stock");
        outProduct=product;
    }

    public Product getOutProduct() {
        return outProduct;
    }
    
}
