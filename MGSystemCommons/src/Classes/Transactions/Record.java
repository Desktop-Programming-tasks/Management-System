/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Transactions;

import Classes.Enums.RecordType;
import Classes.Persons.Employee;
import Classes.Persons.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author gabriel
 */
public class Record implements Serializable {

    private int id;
    private Employee assignedEmployee;
    private Date registerDate;
    private float totalprice;
    private Person customer;
    private ArrayList<Transaction> transactions;
    private int type;

    public Record(int id, Employee assignedEmployee, Date registerDate, float totalprice, Person customer, ArrayList<Transaction> transations, int type) {
        this.id = id;
        this.assignedEmployee = assignedEmployee;
        this.registerDate = registerDate;
        this.totalprice = totalprice;
        this.customer = customer;
        this.transactions = transations;
        this.type = type;
    }

    public Record(Employee assignedEmployee, Person customer, ArrayList<Transaction> transactions, int type) {
        this.assignedEmployee = assignedEmployee;
        for (Transaction t : transactions) {
            System.out.println(t.getPrice());
            System.out.println(t.getQuantity());
            totalprice += (t.getPrice() * t.getQuantity());
        }
        this.customer = customer;
        this.transactions = transactions;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    

    @Override
    public String toString() {
        return "Record{" + "id=" + id + ", assignedEmployee=" + assignedEmployee + ", registerDate=" + registerDate + ", totalprice=" + totalprice + ", customer=" + customer + ", transations=" + transactions + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = hash + (int) (31 * registerDate.getTime());
        hash = hash + (31 * toString().hashCode());
        hash = hash + (401 * assignedEmployee.getDocumentId().hashCode());
        hash = hash + (73 * customer.getDocumentId().hashCode());
        hash = hash + (71 * (int) totalprice);
        hash = Math.abs(hash * (new Random().nextInt(9) + 1));
        return hash;
    }

}
