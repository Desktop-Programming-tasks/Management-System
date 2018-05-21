/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.Classes.Transactions;

import desktoproject.Model.Classes.Persons.Person;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gabriel
 */
public class Register {
    private int id;
    private String assignedEmployee;
    private Date registerDate;
    private float totalprice;
    private Person customer;
    private ArrayList<Transaction> transations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public ArrayList<Transaction> getTransations() {
        return transations;
    }

    public void setTransations(ArrayList<Transaction> transations) {
        this.transations = transations;
    }

    @Override
    public String toString() {
        return "Register{" + "id=" + id + ", assignedEmployee=" + assignedEmployee + ", registerDate=" + registerDate + ", totalprice=" + totalprice + ", customer=" + customer + ", transations=" + transations + '}';
    }
    
    
}
