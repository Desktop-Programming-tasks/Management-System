/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.Classes.Transactions;

import desktoproject.Model.Classes.Persons.Employee;
import desktoproject.Model.Classes.Persons.Person;
import desktoproject.Model.Enums.RecordType;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gabriel
 */
public class Record {

    private int id;
    private Employee assignedEmployee;
    private Date registerDate;
    private float totalprice;
    private Person customer;
    private ArrayList<Transaction> transactions;
    private RecordType type;

    public Record(int id, Employee assignedEmployee, Date registerDate, float totalprice, Person customer, ArrayList<Transaction> transations, RecordType type) {
        this.id = id;
        this.assignedEmployee = assignedEmployee;
        this.registerDate = registerDate;
        this.totalprice = totalprice;
        this.customer = customer;
        this.transactions = transations;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
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
        return transactions;
    }

    public void setTransations(ArrayList<Transaction> transations) {
        this.transactions = transations;
    }

    @Override
    public String toString() {
        return "Record{" + "id=" + id + ", assignedEmployee=" + assignedEmployee + ", registerDate=" + registerDate + ", totalprice=" + totalprice + ", customer=" + customer + ", transations=" + transactions + ", type=" + type + '}';
    }
}
