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
    private String CNPJ;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public JuridicalPerson(String CNPJ, String name, Address address, ArrayList<String> telephones) {
        super(name, address, telephones);
        this.CNPJ = CNPJ;
    }


    
}
