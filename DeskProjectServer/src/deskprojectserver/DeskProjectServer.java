/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Persons.Person;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;


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
            for(Person p : DAOBuilder.getInstance().getPersonDAO().getAllEmployees()){
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
