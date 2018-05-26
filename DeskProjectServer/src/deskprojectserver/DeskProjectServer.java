/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Persons.Address;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.Person;
import Classes.Transactions.Brand;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class DeskProjectServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Brand brand = DAOBuilder.getInstance().getbDAO().getBrand("8");
            System.out.println(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
