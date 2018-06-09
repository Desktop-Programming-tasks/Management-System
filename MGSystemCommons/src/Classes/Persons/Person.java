/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Persons;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class Person implements Serializable {
    
    private int id;
    private String name;
    private Address address;
    private ArrayList<String> telephones;
    private String documentId;

    public Person(int id, String name, Address address, ArrayList<String> telephones, String documentId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephones = telephones;
        this.documentId = documentId;
    }
    
    
    public Person(String name, Address address, ArrayList<String> telephones, String Id) {
        this.name = name;
        this.address = address;
        this.telephones = telephones;
        this.documentId = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(ArrayList<String> telephones) {
        this.telephones = telephones;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String Id) {
        this.documentId = Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", address=" + address + ", telephones=" + telephones + ", Id=" + documentId + '}';
    }

}
