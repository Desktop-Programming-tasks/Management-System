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
public class ServiceType implements Serializable{
    
    private int id;
    private String name;
    private float price;
    public ServiceType(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    
    public ServiceType(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ServiceType{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }
}
