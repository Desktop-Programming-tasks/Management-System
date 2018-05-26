/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Transactions.Brand;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;

/**
 *
 * @author gabriel
 */
public class Test2 {
    public static void main(String[] args) {
        try{
            DAOBuilder.getInstance().getbDAO().insertBrand(new Brand("TesteJAVA"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
