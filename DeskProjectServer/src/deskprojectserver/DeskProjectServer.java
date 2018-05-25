/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import deskprojectserver.Classes.Persons.Person;
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
            //Juridical
            Person p = DAOBuilder.getInstance().getPersonDAO().getPerson("123");
            System.out.println(p);
            //Legal
            p= DAOBuilder.getInstance().getPersonDAO().getPerson("5252");
            System.out.println(p);
            //Employee
            p= DAOBuilder.getInstance().getPersonDAO().getPerson("8521");
            System.out.println(p);
            //Supplier
            p=DAOBuilder.getInstance().getPersonDAO().getPerson("121");
            System.out.println(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
