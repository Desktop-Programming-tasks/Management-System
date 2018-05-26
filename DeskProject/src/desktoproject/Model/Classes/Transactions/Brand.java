/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.Classes.Transactions;

import java.io.Serializable;

/**
 *
 * @author gabriel
 */
public class Brand implements Serializable{

    private int id;
    private String name;

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" + "id=" + id + ", name=" + name + '}';
    }
}
