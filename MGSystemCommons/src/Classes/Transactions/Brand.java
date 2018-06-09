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
public class Brand implements Serializable {

    private int id;
    private String name;
    private boolean active;

    public Brand(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Brand{" + "name=" + name + '}';
    }
}
