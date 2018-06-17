/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Enums;

/**
 *
 * @author viniciusmn
 */
public enum TransactionScreenMode {
    ALL,
    SALES,
    PURCHASES;
    
    public String translateEnumToGUI(){
        switch(this){
            case ALL:{
                return "Todas";
            }
            case PURCHASES:{
                return "Compras";
            }
            case SALES:{
                return "Vendas";
            }
        }
        return "";
    }
}
