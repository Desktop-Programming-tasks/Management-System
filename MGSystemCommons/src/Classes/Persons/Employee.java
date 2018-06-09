/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Persons;

import Classes.Enums.EmployeeType;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Employee extends LegalPerson implements Serializable{
    private String login;
    private String password;
    private EmployeeType type;

    
    public Employee(String login, String password, EmployeeType type, String RG, String name, Address address, ArrayList<String> telephones, String cpf) {
        super(RG, name, address, telephones, cpf);
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public Employee(String login, String password, EmployeeType type, String RG, int id, String name, Address address, ArrayList<String> telephones, String documentId) {
        super(RG, id, name, address, telephones, documentId);
        this.login = login;
        this.password = password;
        this.type = type;
    }
    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeType getEmployeeType() {
        return type;
    }

    public void setTEmployeeType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString()+"Employee{" + "login=" + login + ", password=" + password + ", type=" + type + '}';
    }
}
