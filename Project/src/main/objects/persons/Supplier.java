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
public class Supplier extends LegalPerson{
    private ArrayList<Brand> avaliableBrands;

    public ArrayList<Brand> getAvaliableBrands() {
        return avaliableBrands;
    }

    public void setAvaliableBrands(ArrayList<Brand> avaliableBrands) {
        this.avaliableBrands = avaliableBrands;
    }

    public Supplier(ArrayList<Brand> avaliableBrands, String CPF, String RG, String name, Address address, ArrayList<String> telephones) {
        super(CPF, RG, name, address, telephones);
        this.avaliableBrands = avaliableBrands;
    }
    
    
}
