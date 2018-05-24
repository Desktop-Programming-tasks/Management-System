/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Classes.Transactions;

/**
 *
 * @author gabriel
 */
public abstract class Transaction {
    private float price;
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Transaction() {
    }

    public Transaction(float price, String name) {
        this.price = price;
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    protected void setPrice(float price) {
        this.price = price;
    }    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Transaction{" + "price=" + price + ", name=" + name + '}';
    }
}
