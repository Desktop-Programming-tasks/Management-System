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
public abstract class Transaction {
    private float price;
    private int id;

    public Transaction() {
    }
    
    public Transaction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    protected void setPrice(float price) {
        this.price = price;
    }    

    @Override
    public String toString() {
        return "Transation{" + "price=" + price + ", id=" + id + '}';
    }
    
    
}
