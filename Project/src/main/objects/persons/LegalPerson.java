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
public class LegalPerson extends Person{
    private String CPF;
    private String RG;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public LegalPerson(String CPF, String RG, String name, Address address, ArrayList<String> telephones) {
        super(name, address, telephones);
        this.CPF = CPF;
        this.RG = RG;
    }
    @Override
    public String getId() {
        return CPF;
    }   
    
}
