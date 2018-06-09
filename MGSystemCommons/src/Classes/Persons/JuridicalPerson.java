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
public class JuridicalPerson extends Person implements Serializable{

    public JuridicalPerson(String name, Address address, ArrayList<String> telephones, String cnpj) {
        super(name, address, telephones, cnpj);
    }

    public JuridicalPerson(int id, String name, Address address, ArrayList<String> telephones, String documentId) {
        super(id, name, address, telephones, documentId);
    }
    

    public String getCNPJ() {
        return getDocumentId();
    }

    public void setCNPJ(String cnpj) {
        setDocumentId(cnpj);
    }

    @Override
    public String toString() {
        return super.toString() + "JuridicalPerson{" + '}';
    }

}
