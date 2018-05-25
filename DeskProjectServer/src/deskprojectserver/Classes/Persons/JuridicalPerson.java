/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Classes.Persons;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class JuridicalPerson extends Person implements Serializable {

    public JuridicalPerson(String name, Address address, ArrayList<String> telephones, String cnpj) {
        super(name, address, telephones, cnpj);
    }

    public String getCNPJ() {
        return getId();
    }

    public void setCNPJ(String cnpj) {
        setId(cnpj);
    }

    @Override
    public String toString() {
        return super.toString() + "JuridicalPerson{" + '}';
    }

}
