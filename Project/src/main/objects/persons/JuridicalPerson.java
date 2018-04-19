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
public class JuridicalPerson extends Person{

    public JuridicalPerson(String name, Address address, ArrayList<String> telephones, String Id) {
        super(name, address, telephones, Id);
    }
    
}
