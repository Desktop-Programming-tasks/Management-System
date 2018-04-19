/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.objects.persons;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Person {
    private String name;
    private Address address;
    private ArrayList<String> telephones;

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

    public Person(String name, Address address, ArrayList<String> telephones) {
        this.name = name;
        this.address = address;
        this.telephones = telephones;
    }
    
}
