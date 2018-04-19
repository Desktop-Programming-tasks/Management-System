/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.objects.transations;

/**
 *
 * @author gabriel
 */
public class Movimentation extends Transation{
    private Product product;
    private String operationType;
    private int quantity;

    public Movimentation(Product product, String operationType, int quantity, int id) {
        super(id);
        this.product = product;
        this.operationType = operationType;
        this.quantity = quantity;
    }
    
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
